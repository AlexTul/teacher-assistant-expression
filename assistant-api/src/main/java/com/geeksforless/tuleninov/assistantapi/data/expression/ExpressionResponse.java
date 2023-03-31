package com.geeksforless.tuleninov.assistantapi.data.expression;

import com.geeksforless.tuleninov.assistantapi.model.expression.Expression;

import java.math.BigDecimal;

/**
 * Record for the Expression response.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public record ExpressionResponse(

        long id,

        String expression,

        double root

) {

    /**
     * Create the new record from Expression.
     *
     * @param expression expression
     * @return new record from ExpressionResponse
     */
    public static ExpressionResponse fromExpression(Expression expression) {
        return new ExpressionResponse(
                expression.getId(),
                expression.getExpression(),
                expression.getRoot()
        );
    }
}
