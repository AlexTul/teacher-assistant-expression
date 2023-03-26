package com.geeksforless.tuleninov.assistantapi.repository;

import com.geeksforless.tuleninov.assistantapi.model.expression.Expression;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for working with the repository of Expression.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public interface ExpressionRepository extends JpaRepository<Expression, Long> {
}
