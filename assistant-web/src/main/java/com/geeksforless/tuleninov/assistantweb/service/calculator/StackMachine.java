package com.geeksforless.tuleninov.assistantweb.service.calculator;

import com.geeksforless.tuleninov.assistantweb.service.calculator.token.BinaryOperationToken;
import com.geeksforless.tuleninov.assistantweb.service.calculator.token.NumberToken;
import com.geeksforless.tuleninov.assistantweb.service.calculator.token.Token;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class StackMachine {

    public BigDecimal evaluate(List<Token> postfixExpression) {
        Deque<BigDecimal> valueStack = new LinkedList<>();
        for (Token token : postfixExpression) {
            if (token instanceof NumberToken number) {
                valueStack.push(number.value());
            } else if (token instanceof BinaryOperationToken operation) {
                var right = valueStack.pop();
                var left = valueStack.pop();
                var result = switch (operation.operationType()) {
                    case PLUS -> left.add(right);
                    case MINUS -> left.subtract(right);
                    case MULTIPLY -> left.multiply(right);
                    case DIVIDE -> {
                        if (right.compareTo(BigDecimal.valueOf(0)) == 0) {
                            throw new RuntimeException("Divide by zero!");
                        }
                        yield left.divide(right);
                    }
                };
                valueStack.push(result);
            }
        }
        return valueStack.pop().setScale(2, RoundingMode.HALF_UP);
    }
}
