package com.geeksforless.tuleninov.assistantweb.controller.register;

import com.geeksforless.tuleninov.assistantlib.data.user.SaveUserRequest;
import com.geeksforless.tuleninov.assistantweb.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Controller for the register page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get register page with parameters.
     *
     * @return index page
     */
    @GetMapping("/register")
    public String getRegisterPage() {
        return "register/register";
    }

    /**
     * Add user to database.
     *
     * @param request request with category parameters
     * @return goods page
     */
    @PostMapping("/register")
    public String registeredPost(@Valid SaveUserRequest request,
                                 HttpServletRequest httpRequest) {
        boolean register = userService.register(request);

        if (!register) {
            httpRequest.getSession().setAttribute("message", "Email: " + request.email() + " is already taken");
            return "redirect:/message";
        }

        httpRequest.getSession().setAttribute("message", "Successful registration. Go to email page");
        return "redirect:/message";
    }
}
