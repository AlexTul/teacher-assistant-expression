package com.geeksforless.tuleninov.assistantweb.service.calculator;

import com.geeksforless.tuleninov.assistantweb.service.calculator.token.Token;

import java.util.List;

public class Calculator implements Calculable {

    private final Lexer lexer = new Lexer();
    private final PostfixConverter converter = new PostfixConverter();
    private final StackMachine stackMachine = new StackMachine();

    @Override
    public double calculate(String expression, double root) {
        var prepared = prepareExpression(expression, root);

        List<Token> tokens = lexer.getTokens(prepared);
        var postfixExpression = converter.convertToPostfix(tokens);

        return stackMachine.evaluate(postfixExpression);
    }

    private String prepareExpression(String expression, double root) {
        String prepared = expression.replaceAll("[xX]", root + "");
        int index = prepared.indexOf('=');
        return (prepared.substring(0, index) + "-(" + prepared.substring(index + 1) + ")").strip();
    }
}
