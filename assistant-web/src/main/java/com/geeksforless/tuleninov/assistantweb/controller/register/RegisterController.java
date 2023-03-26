package com.geeksforless.tuleninov.assistantweb.controller.register;

import com.geeksforless.tuleninov.assistantlib.data.user.SaveUserRequest;
import com.geeksforless.tuleninov.assistantweb.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.geeksforless.tuleninov.assistantlib.Routes.*;
import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_MESSAGE;

/**
 * Controller for the register page.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
@RequestMapping(value = URL_REGISTER)
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get register page.
     *
     * @return index page
     */
    @GetMapping()
    public String getRegisterPage() {
        return "register/register";
    }

    /**
     * Add user to database.
     *
     * @param request request with category parameters
     * @return goods page
     */
    @PostMapping()
    public String registerPost(@Valid SaveUserRequest request,
                               HttpServletRequest httpRequest) {
        boolean register = userService.register(request);

        if (!register) {
            httpRequest.getSession().setAttribute(SCOPE_MESSAGE, "Email: " + request.email() + " is already taken");
            return "redirect:/message";
        }

        httpRequest.getSession().setAttribute(SCOPE_MESSAGE, "Successful registration. Go to email page");
        return "redirect:/message";
    }

    /**
     * Update user`s credentials in the database.
     *
     * @param request request with category parameters
     * @return goods page
     */
    @PutMapping(value = "/{id}")
    public String updatePut(@PathVariable(value = "id") int id,
                            @Valid SaveUserRequest request) {
        userService.update(id, request);

        return "redirect:" + URL_INDEX;
    }
}