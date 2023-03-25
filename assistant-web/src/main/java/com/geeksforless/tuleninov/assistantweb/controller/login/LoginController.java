package com.geeksforless.tuleninov.assistantweb.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for the email page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
public class LoginController {

    /**
     * Get email page.
     *
     * @return email page
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        request.getSession().removeAttribute("message");

        return "index";
    }
}
