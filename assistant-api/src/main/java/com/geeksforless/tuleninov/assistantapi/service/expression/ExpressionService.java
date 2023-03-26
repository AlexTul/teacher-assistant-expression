package com.geeksforless.tuleninov.assistantapi.service.expression;

import com.geeksforless.tuleninov.assistantapi.data.expression.ExpressionResponse;
import com.geeksforless.tuleninov.assistantapi.data.user.UserResponse;
import com.geeksforless.tuleninov.assistantapi.exceptions.expression.ExpressionExceptions;
import com.geeksforless.tuleninov.assistantapi.exceptions.user.UserExceptions;
import com.geeksforless.tuleninov.assistantapi.model.expression.Expression;
import com.geeksforless.tuleninov.assistantapi.model.role.Role;
import com.geeksforless.tuleninov.assistantapi.model.user.User;
import com.geeksforless.tuleninov.assistantapi.repository.ExpressionRepository;
import com.geeksforless.tuleninov.assistantlib.data.expression.SaveExpressionRequest;
import com.geeksforless.tuleninov.assistantlib.data.user.SaveUserRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.geeksforless.tuleninov.assistantapi.exceptions.role.RoleExceptions.roleNotFound;

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

    /**
     * Validate the expression`s name field in the database.
     *
     * @param request request with user parameters
     */
    private void validateUniqueFields(SaveExpressionRequest request) {
        String expression = request.expression();
//        if (expressionRepository.existsByExpression(expression)) {
//            throw ExpressionExceptions.duplicateExpression(expression);
//        }
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
        expressionRepository.save(expression);

        return expression;
    }
}
