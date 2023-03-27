package com.geeksforless.tuleninov.assistantlib.data.root;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Record for the Root request.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public record SaveRootRequest(

        @NotBlank(message = "The root is mandatory")
        @Size(min = 1, message = "The root should be 1 or more characters")
        double value

) {
}
