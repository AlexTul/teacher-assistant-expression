package com.geeksforless.tuleninov.assistantweb.data.expression;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Record for the Expression request.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public record SaveExpressionUIRequest(

        @NotBlank(message = "The expression is mandatory")
        @Size(min = 3, message = "The expression should be 3 or more characters")
        String expression,

        @NotNull(message = "The root is mandatory")
        double root,

        @NotBlank(message = "The email of user is mandatory")
        @Email(message = "Email is not correct")
        String email
) {
}
