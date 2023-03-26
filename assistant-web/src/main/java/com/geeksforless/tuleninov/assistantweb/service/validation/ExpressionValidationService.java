package com.geeksforless.tuleninov.assistantweb.service.validation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionValidationService implements Validable {

    /**
     * Validate brackets.
     *
     * @param input expression
     * @return true - if input is valid
     */
    @Override
    public boolean isValidBrackets(String input) {
        Map<Character, Character> brackets = new HashMap<>();
        brackets.put(')', '(');
        brackets.put('}', '{');
        brackets.put(']', '[');

        Deque<Character> stack = new LinkedList<>();
        for (char c : input.toCharArray()) {
            if (brackets.containsValue(c)) {
                // одна из открывающих скобок
                stack.push(c);
            } else if (brackets.containsKey(c)) {
                // одна из закрывающих скобок
                if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * Validate sings.
     *
     * @param input expression
     * @return true - if input is valid
     */
    @Override
    public boolean isValidExpressionSings(String input) {
        List<String> regexes = Arrays.asList("\\+\\*", "\\-\\*", "\\+\\/", "\\-\\/");

        return regexes.stream()
                .map(Pattern::compile)
                .map(p -> p.matcher(input))
                .noneMatch(Matcher::find);
    }
}
