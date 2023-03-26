package com.geeksforless.tuleninov.assistantapi.model.expression;

import javax.persistence.*;

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
}
