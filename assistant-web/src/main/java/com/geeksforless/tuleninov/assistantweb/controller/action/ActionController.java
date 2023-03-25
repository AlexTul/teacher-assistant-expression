/*
 * Copyright (c) 2022
 * For NIX Solutions
 */
package com.geeksforless.tuleninov.assistantweb.controller.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for the admin page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping("/action")
public class ActionController {

    /**
     * Get the user`s action page.
     *
     * @return user`s action page
     */
    @GetMapping
    public String actionPage() {
        System.out.println();

        return "action/action";
    }
}
