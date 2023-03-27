package com.geeksforless.tuleninov.assistantapi.service.expression;

import com.geeksforless.tuleninov.assistantapi.data.expression.ExpressionResponse;
import com.geeksforless.tuleninov.assistantapi.exceptions.expression.ExpressionExceptions;
import com.geeksforless.tuleninov.assistantapi.model.expression.Expression;
import com.geeksforless.tuleninov.assistantapi.repository.ExpressionRepository;
import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;
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
public class ExpressionService implements ExpressionCRUD {

    private final ExpressionRepository expressionRepository;

    public ExpressionService(ExpressionRepository expressionRepository) {
        this.expressionRepository = expressionRepository;
    }

    /**
     * Create the expression in the database.
     *
     * @param request request with expression parameters
     * @return the expression from database in response format
     */
    @Override
    public ExpressionResponse create(SaveExpressionRequest request) {
        validateUniqueFields(request);
        return ExpressionResponse.fromExpression(save(request));
    }

    @Override
    public Page<ExpressionResponse> findAll(Pageable pageable) {
        return expressionRepository.findAll(pageable)
                .map(ExpressionResponse::fromExpression);
    }

    /**
     * Find all expressions by root from the database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @param root root of expression
     * @return all expressions from the database in response format
     */
    @Override
    public Page<ExpressionResponse> findAllByRoot(Pageable pageable, double root) {
        return expressionRepository.findAllByRoot(pageable, root)
                .map(ExpressionResponse::fromExpression);
    }

    /**
     * Checking for the existence of an expression in the database.
     *
     * @param expression expression from user
     * @return true - if expression exists in the database and false - is expression does not exist in the database
     */
    @Override
    public boolean existsByExpression(String expression) {
        return expressionRepository.existsByExpression(expression);
    }

    /**
     * Delete the expression in the database.
     *
     * @param id id of expression
     */
    @Override
    public void deleteByID(long id) {
        expressionRepository.deleteById(id);
    }

    /**
     * Validate the expression`s name field in the database.
     *
     * @param request request with user parameters
     */
    private void validateUniqueFields(SaveExpressionRequest request) {
        String expression = request.expression();
        if (existsByExpression(expression)) {
            throw ExpressionExceptions.duplicateExpression(expression);
        }
    }

    /**
     * Save the expression in the database.
     *
     * @param request request with expression parameters
     * @return expression entity
     */
    private Expression save(SaveExpressionRequest request) {
        var expression = new Expression();
        expression.setExpression(request.expression());
        expression.setRoot(request.root());
        expressionRepository.save(expression);

        return expression;
    }
}
