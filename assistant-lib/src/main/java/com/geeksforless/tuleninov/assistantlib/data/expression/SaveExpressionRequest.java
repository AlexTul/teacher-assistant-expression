package com.geeksforless.tuleninov.assistantlib.data.expression;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record SaveExpressionRequest(
        @NotBlank(message = "The expression is mandatory")
        @Size(min = 3, message = "The expression should be 3 or more characters")
        String expression
) {
}
