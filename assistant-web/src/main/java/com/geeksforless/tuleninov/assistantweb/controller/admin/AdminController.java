package com.geeksforless.tuleninov.assistantweb.controller.admin;

import com.geeksforless.tuleninov.assistantweb.model.user.CustomUserDetail;
import com.geeksforless.tuleninov.assistantweb.model.user.UserUI;
import com.geeksforless.tuleninov.assistantweb.service.user.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_ADMIN;

/**
 * Controller for the admin page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping(value = URL_ADMIN)
public class AdminController {

    /**
     * Get admin`s page.
     *
     * @return admin`s page
     */
    @GetMapping
    public String adminPage() {

        return "admin/admin";
    }
}
