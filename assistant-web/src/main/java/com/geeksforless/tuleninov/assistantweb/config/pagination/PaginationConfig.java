package com.geeksforless.tuleninov.assistantweb.config.pagination;

import com.geeksforless.tuleninov.assistantweb.data.pagination.ConfigDTO;

import javax.servlet.http.HttpServletRequest;

import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_PAGE;
import static com.geeksforless.tuleninov.assistantweb.Constants.SCOPE_SIZE;

/**
 * Pagination configuration.
 * @version 01
 *
 * @author Oleksandr Tuleninov
 */
public class PaginationConfig {

    /**
     * Customize the pagination.
     *
     * @param request   the servlet container, which provide request information for HTTP servlets
     * @return          DTO with pagination settings
     */
    public static ConfigDTO config(HttpServletRequest request) {
        int page = 0;
        int size = 5;

        if (request.getParameter(SCOPE_PAGE) != null && !request.getParameter(SCOPE_PAGE).isEmpty()) {
            page = Integer.parseInt(request.getParameter(SCOPE_PAGE)) - 1;
        }
        if (request.getParameter(SCOPE_SIZE) != null && !request.getParameter(SCOPE_SIZE).isEmpty()) {
            size = Integer.parseInt(request.getParameter(SCOPE_SIZE));
        }

        return new ConfigDTO(page, size);
    }
}
