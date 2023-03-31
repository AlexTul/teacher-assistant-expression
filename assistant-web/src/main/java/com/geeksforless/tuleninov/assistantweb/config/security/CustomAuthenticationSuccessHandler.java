package com.geeksforless.tuleninov.assistantweb.config.security;

import com.geeksforless.tuleninov.assistantweb.model.role.RoleType;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static com.geeksforless.tuleninov.assistantweb.RoutesWeb.*;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains(RoleType.ROLE_ADMIN.toString())) {
            response.sendRedirect(URL_ADMIN);
        } else if (roles.contains(RoleType.ROLE_USER.toString())) {
            response.sendRedirect(URL_MENU);
        }
        else {
            response.sendRedirect(URL_LOGIN);
        }
    }
}
