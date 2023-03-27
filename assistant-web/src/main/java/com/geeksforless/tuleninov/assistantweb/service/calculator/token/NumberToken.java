package com.geeksforless.tuleninov.assistantweb.service.calculator.token;

public record NumberToken(Double value) implements Token {

    @Override
    public TokenType type() {
        return TokenType.NUMBER;
    }
}
