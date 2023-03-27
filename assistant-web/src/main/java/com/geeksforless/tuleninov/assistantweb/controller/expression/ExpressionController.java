package com.geeksforless.tuleninov.assistantweb.controller.expression;

import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;
import com.geeksforless.tuleninov.assistantweb.controller.Authenticator;
import com.geeksforless.tuleninov.assistantweb.model.role.RoleType;
import com.geeksforless.tuleninov.assistantweb.service.calculator.Calculable;
import com.geeksforless.tuleninov.assistantweb.service.calculator.Calculator;
import com.geeksforless.tuleninov.assistantweb.service.crud.expression.ExpressionService;
import com.geeksforless.tuleninov.assistantweb.service.crud.user.UserService;
import com.geeksforless.tuleninov.assistantweb.service.validation.ExpressionValidationService;
import com.geeksforless.tuleninov.assistantweb.service.validation.Validable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.geeksforless.tuleninov.assistantlib.Routes.*;
import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_MESSAGE;
import static java.lang.Math.ceil;

/**
 * Controller for the expression page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping(value = URL_EXPRESSION)
public class ExpressionController {

    private final ExpressionService expressionService;
    private final UserService userService;

    public ExpressionController(ExpressionService expressionService, UserService userService) {
        this.expressionService = expressionService;
        this.userService = userService;
    }

    /**
     * Get the expression page.
     *
     * @return expression page
     */
    @GetMapping
    public String getExpressionPage(HttpServletRequest httpRequest, Model model) {
        model.addAttribute(SCOPE_MESSAGE, httpRequest.getSession().getAttribute(SCOPE_MESSAGE));
        httpRequest.getSession().removeAttribute(SCOPE_MESSAGE);

        return "expression/expression";
    }

    /**
     * Add expression to database.
     *
     * @param request request with expression parameters
     * @return expression page
     */
    @PostMapping()
    public String createExpressionPost(@Valid SaveExpressionRequest request,
                                       Model model, HttpServletRequest httpRequest) {
        if (processValid(request, model)) {
            boolean created = expressionService.create(request);

            if (!created) {
                httpRequest.getSession().setAttribute(SCOPE_MESSAGE, "Expression '" + request.expression() + "' is already exists.");
                return "redirect:/expression";
            }

            httpRequest.getSession().setAttribute(SCOPE_MESSAGE, "Expression created successfully.");
            return "redirect:/expression";
        } else {
            return "expression/expression";
        }
    }

    /**
     * Delete expression from the database.
     *
     * @param id id of expression
     * @return user page
     */
    @DeleteMapping(value = "/{id}")
    public String deleteExpression(@PathVariable(value = "id") long id) {
        expressionService.delete(id);

        var user = new Authenticator(userService).getUserUI();
        if (user == null) return null;

        if (user.getRoles().get(0).getName().equals(RoleType.ROLE_ADMIN.toString())) {
            return "redirect:" + URL_REPORT;
        } else {
            return "redirect:" + URL_ACTION;
        }
    }

    /**
     * Validate expression.
     *
     * @param request request with expression parameters
     * @param model      holder for model attributes
     * @return true - if expression is validated
     */
    private boolean processValid(SaveExpressionRequest request, Model model) {
        Validable valid = new ExpressionValidationService();
        Calculable calculable = new Calculator();
        var expression = request.expression();
        var root = request.root();
        var calculate = calculable.calculate(expression, root);

        if (!valid.isValidBrackets(request.expression())) {
            model.addAttribute(SCOPE_MESSAGE, "Expression: '" + expression + "' is not correct. Check brackets.");
            return false;
        }
        if (!valid.isValidExpressionSings(request.expression())) {
            model.addAttribute(SCOPE_MESSAGE, "Expression: '" + expression + "' is not correct. Check math signs.");
            return false;
        }

        if (!(calculate.compareTo(BigDecimal.valueOf(0)) == 0.00)) {
            model.addAttribute(SCOPE_MESSAGE, "Root: '" + root + "' is not correct.");
            return false;
        }

        return true;
    }
}
