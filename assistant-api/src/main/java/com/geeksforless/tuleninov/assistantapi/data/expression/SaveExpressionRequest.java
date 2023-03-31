package com.geeksforless.tuleninov.assistantapi.data.expression;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Record for the Expression request.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public record SaveExpressionRequest(
        @NotBlank(message = "The expression is mandatory")
        @Size(min = 3, message = "The expression should be 3 or more characters")
        String expression,

        @NotNull(message = "The root is mandatory")
        double root
) {
}
