package com.geeksforless.tuleninov.assistantweb.controller.expression;

import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;
import com.geeksforless.tuleninov.assistantweb.service.crud.expression.ExpressionService;
import com.geeksforless.tuleninov.assistantweb.service.validation.ExpressionValidationService;
import com.geeksforless.tuleninov.assistantweb.service.validation.Validable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_ACTION;
import static com.geeksforless.tuleninov.assistantlib.Routes.URL_EXPRESSION;
import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_MESSAGE;

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

    public ExpressionController(ExpressionService expressionService) {
        this.expressionService = expressionService;
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
        if (processValid(request.expression(), model)) {
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

        return "redirect:" + URL_ACTION;
    }

    /**
     * Validate expression.
     *
     * @param expression expression from user
     * @param model      holder for model attributes
     * @return true - if expression is validated
     */
    private boolean processValid(String expression, Model model) {
        Validable valid = new ExpressionValidationService();
        if (!valid.isValidBrackets(expression)) {
            model.addAttribute(SCOPE_MESSAGE, "Expression: '" + expression + "' is not correct. Check brackets.");
            return false;
        }
        if (!valid.isValidExpressionSings(expression)) {
            model.addAttribute(SCOPE_MESSAGE, "Expression: '" + expression + "' is not correct. Check math signs.");
            return false;
        }

        return true;
    }
}
