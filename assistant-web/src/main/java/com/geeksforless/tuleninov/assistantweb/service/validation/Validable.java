package com.geeksforless.tuleninov.assistantweb.service.validation;

/**
 * Interface for the expression validation.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public interface Validable {

    boolean isValidBrackets(String input);

    /**
     * Validate sings.
     *
     * @param input expression
     * @return true - if input is valid
     */
    boolean isValidExpressionSings(String input);

}
