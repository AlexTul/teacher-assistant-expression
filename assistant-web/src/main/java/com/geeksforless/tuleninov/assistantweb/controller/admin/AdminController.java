package com.geeksforless.tuleninov.assistantweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the admin page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * Get admin`s page.
     *
     * @return admin`s page
     */
    @GetMapping
    public String adminPage() {
        System.out.println();

        return "admin/admin";
    }
}
