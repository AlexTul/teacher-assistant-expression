package com.geeksforless.tuleninov.assistantweb.controller.action;

import com.geeksforless.tuleninov.assistantweb.config.pagination.PaginationConfig;
import com.geeksforless.tuleninov.assistantweb.data.expression.ExpressionUIResponse;
import com.geeksforless.tuleninov.assistantweb.service.crud.expression.ExpressionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.geeksforless.tuleninov.assistantweb.RoutesWeb.URL_ACTION;
import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_EXPRESSIONS;
import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_ROOT;

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

        var allExpressions = processFiltering(request);

        model.addAttribute(SCOPE_EXPRESSIONS, allExpressions);

        return "action/action";
    }

    /**
     * Get all expressions depending on user filter.
     *
     * @param request HttpServletRequest request
     * @return all expressions by user filter from database in response format with pagination information.
     */
    private Page<ExpressionUIResponse> processFiltering(HttpServletRequest request) {
        String root = request.getParameter(SCOPE_ROOT);

        if (root != null) {
            request.getSession().setAttribute(SCOPE_ROOT, root);
        } else {
            request.getSession().removeAttribute(SCOPE_ROOT);
        }

        var config = PaginationConfig.config(request);

        Page<ExpressionUIResponse> allExpressions;
        if (!(request.getSession().getAttribute(SCOPE_ROOT) == null)) {
            assert root != null;
            allExpressions = expressionService.findAllByRoot(
                    PageRequest.of(config.page(), config.size()),
                    Double.parseDouble(root));
        } else {
            allExpressions = expressionService.findAll(PageRequest.of(config.page(), config.size()));
        }
        return allExpressions;
    }
}
