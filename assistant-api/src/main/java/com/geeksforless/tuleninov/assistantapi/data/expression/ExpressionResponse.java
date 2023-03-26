package com.geeksforless.tuleninov.assistantapi.data.expression;

import com.geeksforless.tuleninov.assistantapi.model.expression.Expression;

/**
 * Record for the expression response.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public record ExpressionResponse(

        long id,
        String expression

) {

    /**
     * Create the new record from User.
     *
     * @param expression expression
     * @return new record from ExpressionResponse
     */
    public static ExpressionResponse fromExpression(Expression expression) {
        return new ExpressionResponse(
                expression.getId(),
                expression.getExpression()
        );
    }
}
