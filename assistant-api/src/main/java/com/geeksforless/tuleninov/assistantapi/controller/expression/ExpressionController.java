package com.geeksforless.tuleninov.assistantapi.controller.expression;

import com.geeksforless.tuleninov.assistantapi.data.expression.ExpressionResponse;
import com.geeksforless.tuleninov.assistantapi.service.expression.ExpressionCRUD;
import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_EXPRESSION;

/**
 * Rest controller for the Expression.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Controller
public class ExpressionController {

    private final ExpressionCRUD expressionCRUD;

    public ExpressionController(ExpressionCRUD expressionCRUD) {
        this.expressionCRUD = expressionCRUD;
    }

    /**
     * Create the expression in the database.
     *
     * @param request       request with expression parameters
     * @param ucb           builder for UriComponents
     * @return              the expression from database in response format
     */
    @PostMapping(value = URL_EXPRESSION, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpressionResponse> create(@Valid @RequestBody SaveExpressionRequest request, UriComponentsBuilder ucb) {
        ExpressionResponse response = expressionCRUD.create(request);
        return ResponseEntity
                .created(ucb.path(URL_EXPRESSION + "/{id}").build(response.id()))
                .body(response);
    }



}
