package com.geeksforless.tuleninov.assistantweb.controller.profile;

import com.geeksforless.tuleninov.assistantweb.controller.Authenticator;
import com.geeksforless.tuleninov.assistantweb.model.role.RoleType;
import com.geeksforless.tuleninov.assistantweb.service.crud.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_PROFILE;
import static com.geeksforless.tuleninov.assistantweb.Constants.*;

/**
 * Controller for the profile page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping(value = URL_PROFILE)
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get profile page with parameters.
     *
     * @return              index page
     */
    @GetMapping
    public String getProfilePage(Model model) {
        var user = new Authenticator(userService).getUserUI();
        if (user == null) return null;

        boolean isAdmin = false;
        boolean isUser = false;
        if (user.getRoles().get(0).getName().equals(RoleType.ROLE_ADMIN.toString())) {
            isAdmin = true;
        } else {
            isUser = true;
        }

        model.addAttribute(SCOPE_IS_ADMIN, isAdmin);
        model.addAttribute(SCOPE_IS_USER, isUser);
        model.addAttribute(SCOPE_USER, user);

        return "register/register-update";
    }
}
