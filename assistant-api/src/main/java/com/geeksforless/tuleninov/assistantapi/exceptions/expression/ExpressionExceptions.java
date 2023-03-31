package com.geeksforless.tuleninov.assistantapi.exceptions.expression;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Class for exceptions process for Expression with specific HTTP response status codes.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public class ExpressionExceptions {

    /**
     * Exceptions process for User with specific HTTP response status codes NOT_FOUND.
     *
     * @param expression expression from user
     * @return specific HTTP response status codes
     */
    public static ResponseStatusException expressionNotFound(String expression) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Expression '" + expression + "' was not found");
    }

    /**
     * Exceptions process for Expression with specific HTTP response status codes BAD_REQUEST.
     *
     * @param expression expression from user
     * @return specific HTTP response status codes
     */
    public static ResponseStatusException duplicateExpression(String expression) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expression '" + expression + "' already taken");
    }
}
