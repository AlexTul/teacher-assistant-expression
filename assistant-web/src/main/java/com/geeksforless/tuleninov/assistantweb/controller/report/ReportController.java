package com.geeksforless.tuleninov.assistantweb.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_REPORT;

@Controller
@RequestMapping(value = URL_REPORT)
public class ReportController {

    @GetMapping
    public String getReportPage(Model model) {

        return "report";
    }
}
