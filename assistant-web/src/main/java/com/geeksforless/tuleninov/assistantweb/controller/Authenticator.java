package com.geeksforless.tuleninov.assistantweb.controller;

import com.geeksforless.tuleninov.assistantweb.model.user.CustomUserDetail;
import com.geeksforless.tuleninov.assistantweb.model.user.UserUI;
import com.geeksforless.tuleninov.assistantweb.service.crud.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * Class for User authentication.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public class Authenticator {

    private final UserService userService;

    public Authenticator(UserService userService) {
        this.userService = userService;
    }

    /**
     * Find the authenticated user.
     *
     * @return the authenticated user
     */
    public UserUI getUserUI() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        CustomUserDetail customUserDetail = (principal instanceof CustomUserDetail) ? (CustomUserDetail) principal : null;
        String email = Objects.nonNull(customUserDetail) ? customUserDetail.getUsername() : null;

        return userService.findByEmail(email);
    }
}
