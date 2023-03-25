package com.geeksforless.tuleninov.assistantweb.controller.message;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for the message page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    /**
     * Get register page with parameters.
     *
     * @return              index page
     */
    @GetMapping
    public String getRegisterPage(HttpServletRequest httpRequest, Model model) {
        model.addAttribute("message", httpRequest.getSession().getAttribute("message"));

        return "message/message";
    }
}
