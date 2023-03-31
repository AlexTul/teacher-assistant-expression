package com.geeksforless.tuleninov.assistantapi.service.expression;

import com.geeksforless.tuleninov.assistantapi.data.expression.ExpressionResponse;
import com.geeksforless.tuleninov.assistantapi.data.expression.SaveExpressionRequest;
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
     * Find all expressions by user from the database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @param email    email from user
     * @return all expressions from the database in response format
     */
    Page<ExpressionResponse> findAllByUser(Pageable pageable, String email);

    /**
     * Find all expressions by root from the database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @param root     root of expression
     * @return all expressions from the database in response format
     */
    Page<ExpressionResponse> findAllByRoot(Pageable pageable, double root);

    /**
     * Checking for the existence of an expression in the database.
     *
     * @param expression expression from user
     * @return true - if expression exists in the database and false - is expression does not exist in the database
     */
    boolean existsByExpression(String expression);

    /**
     * Delete the expression in the database.
     *
     * @param id id of expression
     */
    void deleteByID(long id);
}
