package com.geeksforless.tuleninov.assistantapi.controller.expression;

import com.geeksforless.tuleninov.assistantapi.data.expression.ExpressionResponse;
import com.geeksforless.tuleninov.assistantapi.service.expression.ExpressionCRUD;
import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.geeksforless.tuleninov.assistantlib.Routes.*;

/**
 * Rest controller for the Expression.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@RestController
public class ExpressionController {

    private final ExpressionCRUD expressionCRUD;

    public ExpressionController(ExpressionCRUD expressionCRUD) {
        this.expressionCRUD = expressionCRUD;
    }


    /**
     * Create the expression in the database.
     *
     * @param request request with expression parameters
     * @param ucb     builder for UriComponents
     * @return the expression from database in response format
     */
    @PostMapping(value = URL_EXPRESSION, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpressionResponse> create(@Valid @RequestBody SaveExpressionRequest request, UriComponentsBuilder ucb) {
        ExpressionResponse response = expressionCRUD.create(request);
        return ResponseEntity
                .created(ucb.path(URL_EXPRESSION + "/{id}").build(response.id()))
                .body(response);
    }

    /**
     * Get all expressions from database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @return all expressions from database in response format
     */
    @GetMapping(value = URL_EXPRESSION, produces = MediaType.APPLICATION_JSON_VALUE)
    @PageableAsQueryParam
    Page<ExpressionResponse> getAll(@Parameter(hidden = true) Pageable pageable) {
        Page<ExpressionResponse> all = expressionCRUD.findAll(pageable);
        return expressionCRUD.findAll(pageable);
    }

    /**
     * Delete the expression in the database.
     *
     * @param id id of expression
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = URL_EXPRESSION + "/{id}")
    public void delete(@PathVariable long id) {
        expressionCRUD.deleteByID(id);
    }
}
