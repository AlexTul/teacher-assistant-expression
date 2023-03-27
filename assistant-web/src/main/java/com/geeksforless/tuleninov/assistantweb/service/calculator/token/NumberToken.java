package com.geeksforless.tuleninov.assistantweb.service.calculator.token;

import java.math.BigDecimal;

public record NumberToken(BigDecimal value) implements Token {

    @Override
    public TokenType type() {
        return TokenType.NUMBER;
    }
}
