package com.geeksforless.tuleninov.assistantweb.controller.message;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_MESSAGE;
import static com.geeksforless.tuleninov.assistantweb.RoutesWeb.URL_MESSAGE;

/**
 * Controller for the message page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping(value = URL_MESSAGE)
public class MessageController {

    /**
     * Get register page with parameters.
     *
     * @return              index page
     */
    @GetMapping
    public String getRegisterPage(HttpServletRequest httpRequest, Model model) {
        model.addAttribute(SCOPE_MESSAGE, httpRequest.getSession().getAttribute(SCOPE_MESSAGE));
        httpRequest.getSession().removeAttribute(SCOPE_MESSAGE);

        return "message/message";
    }
}
