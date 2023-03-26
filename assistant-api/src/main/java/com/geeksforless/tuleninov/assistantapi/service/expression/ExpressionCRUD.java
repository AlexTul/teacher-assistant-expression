package com.geeksforless.tuleninov.assistantapi.service.expression;

import com.geeksforless.tuleninov.assistantapi.data.expression.ExpressionResponse;
import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;

/**
 * Interface CRUD for Expression.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public interface ExpressionCRUD {

    /**
     * Create the expression in the database.
     *
     * @param request request with expression parameters
     * @return the expression from database in response format
     */
    ExpressionResponse create(SaveExpressionRequest request);

}
