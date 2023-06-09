package com.geeksforless.tuleninov.assistantapi.model.expression;

import com.geeksforless.tuleninov.assistantapi.model.user.User;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class for Expression entity.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Entity
@Table(name = "expressions")
public class Expression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String expression;

    private double root;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Expression() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getRoot() {
        return root;
    }

    public void setRoot(double root) {
        this.root = root;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}
