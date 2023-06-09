package com.geeksforless.tuleninov.assistantapi.repository;

import com.geeksforless.tuleninov.assistantapi.model.expression.Expression;
import com.geeksforless.tuleninov.assistantapi.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface for working with the repository of Expression.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public interface ExpressionRepository extends JpaRepository<Expression, Long> {

    Page<Expression> findAllByUser(Pageable pageable, User user);

    Page<Expression> findAllByRoot(Pageable pageable, double root);

    boolean existsExpressionByExpressionAndUser(String expression, User user);

}
