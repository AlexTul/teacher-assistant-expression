package com.geeksforless.tuleninov.assistantapi.data.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Record for the user request.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public record SaveUserRequest(

        @NotBlank(message = "The first name of user is mandatory")
        @Size(min = 2, max = 30, message = "The first name should be between 2 and 30 characters")
        @Pattern(regexp = "^[A-Za-zА-ЩЬЮЯҐІЇЄа-щьюяґіїє'\\- ]{1,20}", message = "The first name does not match the pattern.")
        String firstName,

        @Size(min = 2, max = 30, message = "The last name should be between 2 and 30 characters")
        @Pattern(regexp = "^[A-Za-zА-ЩЬЮЯҐІЇЄа-щьюяґіїє'\\- ]{1,20}", message = "The last name does not match the pattern.")
        String lastName,

        @NotBlank(message = "The email of user is mandatory")
        @Email(message = "Email is not correct")
        String email,

        @NotBlank(message = "The password of user is mandatory")
        @Size(min = 8, max = 32, message = "The password should be between 8 and 32 characters")
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$", message = "The password does not match the pattern.")
        String password
) {
}
