package com.geeksforless.tuleninov.assistantweb.controller.user;

import com.geeksforless.tuleninov.assistantweb.config.pagination.PaginationConfig;
import com.geeksforless.tuleninov.assistantweb.data.user.OverrideUserPasswordRequest;
import com.geeksforless.tuleninov.assistantweb.service.user.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_ADMIN;
import static com.geeksforless.tuleninov.assistantlib.Routes.URL_USER;

/**
 * Controller for the user.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping(value = URL_ADMIN + URL_USER)
public class UserController {

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
        var usersUI = userService.findAllByRolesEquals(PageRequest.of(config.page(), config.size()));

        model.addAttribute("users", usersUI);

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
                                        Model model) {
        var userUI = userService.findByEmail(email);

        model.addAttribute("user", userUI);

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
                                     @Valid OverrideUserPasswordRequest request) {
        boolean changed = userService.changePasswordByEmail(email, request);

        if (!changed) {
            // todo
        }

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

        return "redirect:" + URL_ADMIN + URL_USER;
    }
}
