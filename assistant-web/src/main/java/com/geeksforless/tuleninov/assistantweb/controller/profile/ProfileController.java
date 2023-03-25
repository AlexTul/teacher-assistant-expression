package com.geeksforless.tuleninov.assistantweb.controller.profile;

import com.geeksforless.tuleninov.assistantweb.model.user.CustomUserDetail;
import com.geeksforless.tuleninov.assistantweb.model.user.UserUI;
import com.geeksforless.tuleninov.assistantweb.service.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_PROFILE;
import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_USER;

@Controller
@RequestMapping(value = URL_PROFILE)
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getProfilePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        CustomUserDetail customUserDetail = (principal instanceof CustomUserDetail) ? (CustomUserDetail) principal : null;
        String email = Objects.nonNull(customUserDetail) ? customUserDetail.getUsername() : null;

        UserUI user = userService.findByEmail(email);

        model.addAttribute(SCOPE_USER, user);

        return "register/register-update";
    }
}
