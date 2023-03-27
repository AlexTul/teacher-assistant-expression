package com.geeksforless.tuleninov.assistantweb.feignclient;

import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;
import com.geeksforless.tuleninov.assistantweb.data.expression.ExpressionUIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Get all expressions from database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @return all expressions from database in response format
     */
    @GetMapping(value = URL_EXPRESSION, produces = MediaType.APPLICATION_JSON_VALUE)
    Page<ExpressionUIResponse> getAll(Pageable pageable);

    /**
     * Checking for the existence of an expression in the database.
     *
     * @param expression expression from user
     * @return true - if expression exists in the database and false - is expression does not exist in the database
     */
    @GetMapping(value = URL_EXPRESSION + "/c" + "/{expression}")
    boolean existsByExpression(@PathVariable String expression);

    /**
     * Delete the expression in the database.
     *
     * @param id id of expression
     */
    @DeleteMapping(value = URL_EXPRESSION + "/{id}")
    void delete(@PathVariable long id);
}
