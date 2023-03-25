package com.geeksforless.tuleninov.assistantweb.controller.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_ACTION;

/**
 * Controller for the admin page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping(value = URL_ACTION)
public class ActionController {

    /**
     * Get the user`s action page.
     *
     * @return user`s action page
     */
    @GetMapping
    public String actionPage() {

        return "action/action";
    }
}
