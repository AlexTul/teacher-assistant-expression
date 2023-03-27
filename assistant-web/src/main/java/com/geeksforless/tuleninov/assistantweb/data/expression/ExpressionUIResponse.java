package com.geeksforless.tuleninov.assistantweb.data.expression;

/**
 * Record for the Expression response.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public record ExpressionUIResponse (

        long id,
        String expression,
        double root

) {
}
