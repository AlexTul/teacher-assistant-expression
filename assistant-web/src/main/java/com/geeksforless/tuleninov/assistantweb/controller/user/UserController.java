package com.geeksforless.tuleninov.assistantweb.controller.user;

import com.geeksforless.tuleninov.assistantweb.config.pagination.PaginationConfig;
import com.geeksforless.tuleninov.assistantweb.data.user.OverrideUserPasswordRequest;
import com.geeksforless.tuleninov.assistantweb.service.crud.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_ADMIN;
import static com.geeksforless.tuleninov.assistantlib.Routes.URL_USER;
import static com.geeksforless.tuleninov.assistantweb.Constants.*;

/**
 * Controller for the user.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping(value = URL_ADMIN + URL_USER)
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get user page.
     *
     * @param request the servlet container, which provide request information for HTTP servlets
     * @param model   holder for model attributes
     * @return user page
     */
    @GetMapping()
    public String getUsersPage(HttpServletRequest request,
                               Model model) {
        var config = PaginationConfig.config(request);
        var users = userService.findAllByRolesEquals(PageRequest.of(config.page(), config.size()));

        model.addAttribute(SCOPE_USERS, users);

        return "user/user";
    }

    /**
     * Get user update page.
     *
     * @param email email of user
     * @param model holder for model attributes
     * @return user update page
     */
    @GetMapping(value = "/{email}")
    public String getPasswordUpdatePage(@PathVariable(value = "email") String email,
                                        Model model, HttpServletRequest httpRequest) {
        var userUI = userService.findByEmail(email);

        model.addAttribute(SCOPE_USER, userUI);
        model.addAttribute(SCOPE_MESSAGE, httpRequest.getSession().getAttribute(SCOPE_MESSAGE));
        httpRequest.getSession().removeAttribute(SCOPE_MESSAGE);

        return "user/user-update";
    }

    /**
     * Update user in database.
     *
     * @param email   email of user
     * @param request request with category parameters
     * @return user page
     */
    @PutMapping(value = "/{email}")
    public String updatePasswordUser(@PathVariable(value = "email") String email,
                                     @Valid OverrideUserPasswordRequest request,
                                     HttpServletRequest httpRequest) {
        boolean changed = userService.changePasswordByEmail(email, request);

        if (!changed) {
            log.info("New password and confirmation password do not match");
            httpRequest.getSession().setAttribute(SCOPE_MESSAGE, "New password and confirmation password do not match");
            return "redirect:" + URL_ADMIN + URL_USER + "/{email}";
        }

        log.info("Password for'" + email + "' was changed.");

        return "redirect:" + URL_ADMIN + URL_USER;
    }

    /**
     * Delete user from database.
     *
     * @param email email of user
     * @return user page
     */
    @DeleteMapping(value = "/{email}")
    public String deleteUser(@PathVariable(value = "email") String email) {
        userService.delete(email);
        log.info("User '" + email + "' was deleted.");

        return "redirect:" + URL_ADMIN + URL_USER;
    }
}
