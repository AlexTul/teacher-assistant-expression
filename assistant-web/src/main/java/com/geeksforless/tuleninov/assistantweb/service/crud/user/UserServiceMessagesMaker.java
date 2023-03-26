package com.geeksforless.tuleninov.assistantweb.service.crud.user;

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
                        Thank you for your choosing!<br><br>
                        Best regards!<br>Teacher assistant for expressions team<br>
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
                        Dear user, your data was change in Teacher assistant for expressions.
                        Your first name is: %s, last name is: %s, password is: %s.\n\n
                        Best regards!\nTeacher assistant for expressions team
                        """,
                request.firstName(),
                request.lastName(),
                request.password());
    }

    /**
     * Create message for user about updating your credential.
     *
     * @param login login of user
     * @return message about updating user`s credential
     */
    public static String makeUpdatePasswordMessage(String login, String newPassword) {
        return String.format(
                """
                        Dear %s, your password was change in Teacher assistant for expressions.
                        Your password is: %s.\n\n
                        Best regards!\nTeacher assistant for expressions team
                        """,
                login,
                newPassword);
    }

    /**
     * Create message for user about updating your credential.
     *
     * @return message about deleting user from database
     */
    public static String makeDeleteMessage(String email) {
        return String.format(
                """
                        Dear %s, your profile was delete in Teacher assistant for expressions.\n\n
                        Best regards!\nTeacher assistant for expressions team
                        """,
                email);
    }
}
