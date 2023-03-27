package com.geeksforless.tuleninov.assistantweb.controller.action;

import com.geeksforless.tuleninov.assistantweb.config.pagination.PaginationConfig;
import com.geeksforless.tuleninov.assistantweb.service.crud.expression.ExpressionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_ACTION;
import static com.geeksforless.tuleninov.assistantlib.Routes.URL_EXPRESSION;
import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_EXPRESSIONS;

/**
 * Controller for the admin page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping(value = URL_ACTION)
public class ActionController {

    private final ExpressionService expressionService;

    public ActionController(ExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    /**
     * Get the user`s action page.
     *
     * @return user`s action page
     */
    @GetMapping
    public String getActionPage(HttpServletRequest request, Model model) {
        var config = PaginationConfig.config(request);
        var allExpressions = expressionService.findAll(PageRequest.of(config.page(), config.size()));

        model.addAttribute(SCOPE_EXPRESSIONS, allExpressions);

        return "action/action";
    }


}
