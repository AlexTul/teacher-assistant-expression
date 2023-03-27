package com.geeksforless.tuleninov.assistantweb.service.crud.expression;

import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;
import com.geeksforless.tuleninov.assistantweb.data.expression.ExpressionUIResponse;
import com.geeksforless.tuleninov.assistantweb.feignclient.ExpressionServiceFeignClient;
import com.geeksforless.tuleninov.assistantweb.service.crud.user.UserServiceMessagesMaker;
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
     * @return expression from database in response format
     */
    public ExpressionUIResponse create(SaveExpressionRequest request) {
        return expressionServiceFeignClient.create(request);
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
     * Delete the expression in the database.
     *
     * @param id id of expression
     */
    public void delete(long id) {
        expressionServiceFeignClient.delete(id);
    }
}
