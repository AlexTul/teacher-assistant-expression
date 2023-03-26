package com.geeksforless.tuleninov.assistantweb.feignclient;

import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;
import com.geeksforless.tuleninov.assistantweb.data.expression.ExpressionUIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_EXPRESSION;

/**
 * Feign Client for the Expression.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@FeignClient(name = "ExpressionController.class", url = "${services.assistant.api}")
public interface ExpressionServiceFeignClient {

    /**
     * Create the expression in the database.
     *
     * @param request request with expression parameters
     * @return true if created successfully
     */
    @PostMapping(value = URL_EXPRESSION, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ExpressionUIResponse create(@RequestBody SaveExpressionRequest request);


}
