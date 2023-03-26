package com.geeksforless.tuleninov.assistantweb.service.crud.expression;

import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;
import com.geeksforless.tuleninov.assistantweb.data.expression.ExpressionUIResponse;
import com.geeksforless.tuleninov.assistantweb.feignclient.ExpressionServiceFeignClient;
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

}
