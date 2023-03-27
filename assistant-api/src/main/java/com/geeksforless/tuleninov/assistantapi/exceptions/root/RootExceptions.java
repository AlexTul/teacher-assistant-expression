package com.geeksforless.tuleninov.assistantapi.exceptions.root;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Class for exceptions process for Root with specific HTTP response status codes.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public class RootExceptions {

    /**
     * Exceptions process for Root with specific HTTP response status codes BAD_REQUEST.
     *
     * @param value value from user
     * @return specific HTTP response status codes
     */
    public static ResponseStatusException duplicateRoot(double value) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Root '" + value + "' already taken.");
    }
}
