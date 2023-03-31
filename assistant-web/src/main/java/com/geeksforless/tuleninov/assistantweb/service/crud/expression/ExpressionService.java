package com.geeksforless.tuleninov.assistantweb.service.crud.expression;

import com.geeksforless.tuleninov.assistantweb.data.expression.ExpressionUIResponse;
import com.geeksforless.tuleninov.assistantweb.data.expression.SaveExpressionUIRequest;
import com.geeksforless.tuleninov.assistantweb.feignclient.ExpressionServiceFeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for Expression.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Service
public class ExpressionService {

    private final ExpressionServiceFeignClient expressionServiceFeignClient;

    public ExpressionService(ExpressionServiceFeignClient expressionServiceFeignClient) {
        this.expressionServiceFeignClient = expressionServiceFeignClient;
    }

    /**
     * Create the expression in the database.
     *
     * @param request request with expression parameters
     * @return true - if expression created in the database
     */
    public boolean create(SaveExpressionUIRequest request) {
        if (expressionServiceFeignClient.existsByExpression(request.expression(), request.email())) {
            return false;
        }

        expressionServiceFeignClient.create(request);
        return true;
    }

    /**
     * Find all expressions from database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @return all expressions from database in response format
     */
    public Page<ExpressionUIResponse> findAll(Pageable pageable) {
        return expressionServiceFeignClient.getAll(pageable);
    }

    /**
     * Find all expressions by user from database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @param email    email from user
     * @return all expressions from database in response format
     */
    public Page<ExpressionUIResponse> findAllByUser(Pageable pageable, String email) {
        return expressionServiceFeignClient.getAllByUser(pageable, email);
    }

    /**
     * Find all expressions by root from database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @param root     root of expression
     * @return all expressions from database in response format
     */
    public Page<ExpressionUIResponse> findAllByRoot(Pageable pageable, double root) {
        return expressionServiceFeignClient.getAllByRoot(pageable, root);
    }

    /**
     * Delete the expression in the database.
     *
     * @param id id of expression
     */
    public void delete(long id) {
        expressionServiceFeignClient.delete(id);
    }
}
