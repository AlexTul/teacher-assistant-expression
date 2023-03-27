package com.geeksforless.tuleninov.assistantweb.controller.report;

import com.geeksforless.tuleninov.assistantweb.config.pagination.PaginationConfig;
import com.geeksforless.tuleninov.assistantweb.service.crud.expression.ExpressionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_REPORT;
import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_EXPRESSIONS;

@Controller
@RequestMapping(value = URL_REPORT)
public class ReportController {

    private final ExpressionService expressionService;

    public ReportController(ExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    @GetMapping
    public String getReportPage(HttpServletRequest request, Model model) {
        var config = PaginationConfig.config(request);
        var allExpressions = expressionService.findAll(PageRequest.of(config.page(), config.size()));

        model.addAttribute(SCOPE_EXPRESSIONS, allExpressions);


        return "report/report";
    }
}
