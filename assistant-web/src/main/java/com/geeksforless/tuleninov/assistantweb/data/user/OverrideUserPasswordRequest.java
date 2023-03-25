package com.geeksforless.tuleninov.assistantweb.data.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Record for overriding user`s password.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public record OverrideUserPasswordRequest(

        @NotBlank(message = "Password must not be blank")
        @Size(min = 10, message = "Password's length must be at least 10")
        String newPassword,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 10, message = "Password's length must be at least 10")
        String confirmPassword
) {
}
