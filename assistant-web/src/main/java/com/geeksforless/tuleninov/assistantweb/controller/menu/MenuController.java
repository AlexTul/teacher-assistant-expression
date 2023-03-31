package com.geeksforless.tuleninov.assistantweb.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.geeksforless.tuleninov.assistantweb.RoutesWeb.URL_MENU;

/**
 * Controller for the admin page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping(value = URL_MENU)
public class MenuController {

    /**
     * Get the user`s menu page.
     *
     * @return user`s menu page
     */
    @GetMapping
    public String actionPage() {

        return "menu/menu";
    }
}
