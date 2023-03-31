package com.geeksforless.tuleninov.assistantapi.service.expression;

import com.geeksforless.tuleninov.assistantapi.data.expression.ExpressionResponse;
import com.geeksforless.tuleninov.assistantapi.data.expression.SaveExpressionRequest;
import com.geeksforless.tuleninov.assistantapi.exceptions.expression.ExpressionExceptions;
import com.geeksforless.tuleninov.assistantapi.model.expression.Expression;
import com.geeksforless.tuleninov.assistantapi.model.user.User;
import com.geeksforless.tuleninov.assistantapi.repository.ExpressionRepository;
import com.geeksforless.tuleninov.assistantapi.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.geeksforless.tuleninov.assistantapi.exceptions.user.UserExceptions.userNotFound;

/**
 * Service class for Expression.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Service
public class ExpressionService implements ExpressionCRUD {

    private final ExpressionRepository expressionRepository;
    private final UserRepository userRepository;

    public ExpressionService(ExpressionRepository expressionRepository, UserRepository userRepository) {
        this.expressionRepository = expressionRepository;
        this.userRepository = userRepository;
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
     * Find all expressions by user from the database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @param email    email from user
     * @return all expressions from the database in response format
     */
    @Override
    public Page<ExpressionResponse> findAllByUser(Pageable pageable, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> userNotFound(email));

        return expressionRepository.findAllByUser(pageable, user)
                .map(ExpressionResponse::fromExpression);
    }

    /**
     * Find all expressions by root from the database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @param root     root of expression
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
     * @param email email from user
     * @return true - if expression exists in the database and false - is expression does not exist in the database
     */
    @Override
    public boolean existsByExpressionAndUser(String expression, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> userNotFound(email));

        return expressionRepository.existsExpressionByExpressionAndUser(expression, user);
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
        if (existsByExpressionAndUser(request.expression(), request.email())) {
            throw ExpressionExceptions.duplicateExpression(request.expression());
        }
    }

    /**
     * Save the expression in the database.
     *
     * @param request request with expression parameters
     * @return expression entity
     */
    private Expression save(SaveExpressionRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> userNotFound(request.email()));

        var expression = new Expression();
        expression.setExpression(request.expression());
        expression.setRoot(request.root());
        expression.setUser(user);
        expressionRepository.save(expression);

        return expression;
    }
}
