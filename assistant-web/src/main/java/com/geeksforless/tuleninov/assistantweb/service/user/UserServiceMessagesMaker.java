package com.geeksforless.tuleninov.assistantweb.service.user;

import com.geeksforless.tuleninov.assistantlib.data.user.SaveUserRequest;

/**
 * Service class for making message.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public class UserServiceMessagesMaker {

    /**
     * Create message for user about successful registration.
     *
     * @param request request with user parameters
     * @return message about successful registration
     */
    public static String makeWelcomeMessage(SaveUserRequest request) {
        return String.format(
                """
                        Dear %s,<br>welcome to Teacher assistant for expressions.<br>
                        Thank you for your choosing!<br><br>Best regards!<br>Teacher assistant for expressions team<br>
                        """,
                request.email());
    }

    /**
     * Create message for user about updating your credential.
     *
     * @param request request with user parameters
     * @return message about updating user`s credential
     */
    public static String makeUpdateMessage(SaveUserRequest request) {
        return String.format(
                """
                        Dear user, your data was change in Market Place.
                        Your first name is: %s, last name is: %s, password is: %s.
                        Best Regards,
                        Market Place
                        """,
                request.firstName(),
                request.lastName(),
                request.password());
    }

    /**
     * Create message for user about updating your credential.
     *
     * @return message about deleting user from database
     */
    public static String makeDeleteMessage() {
        return """
                Dear user, your profile was delete in Market Place.
                Best Regards,
                Market Place""";
    }
}
