package com.geeksforless.tuleninov.assistantweb.data.user;

/**
 * Record for the user response.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public record UserUIResponse(

        int id,

        String firstName,

        String lastName,

        String email,

        String password,

        int roleId,

        String roleName
) {
}
