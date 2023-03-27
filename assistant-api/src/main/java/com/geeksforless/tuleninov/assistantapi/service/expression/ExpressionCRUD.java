package com.geeksforless.tuleninov.assistantapi.service.expression;

import com.geeksforless.tuleninov.assistantapi.data.expression.ExpressionResponse;
import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * Find all expressions from the database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @return all expressions from the database in response format
     */
    Page<ExpressionResponse> findAll(Pageable pageable);

    /**
     * Delete the expression in the database.
     *
     * @param id id of expression
     */
    void deleteByID(long id);
}
