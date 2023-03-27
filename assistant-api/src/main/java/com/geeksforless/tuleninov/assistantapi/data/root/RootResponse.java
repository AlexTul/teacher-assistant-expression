package com.geeksforless.tuleninov.assistantapi.data.root;

import com.geeksforless.tuleninov.assistantapi.model.root.Root;

/**
 * Record for the Root response.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public record RootResponse(

        long id,
        double value


) {

    /**
     * Create the new record from Root.
     *
     * @param root expression
     * @return new record from ExpressionResponse
     */
    public static RootResponse fromRoot(Root root) {
        return new RootResponse(
                root.getId(),
                root.getValue()
        );
    }
}
