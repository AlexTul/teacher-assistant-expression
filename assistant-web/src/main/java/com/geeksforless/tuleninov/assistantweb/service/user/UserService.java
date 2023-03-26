package com.geeksforless.tuleninov.assistantweb.service.user;

import com.geeksforless.tuleninov.assistantlib.data.user.SaveUserRequest;
import com.geeksforless.tuleninov.assistantweb.data.user.OverrideUserPasswordRequest;
import com.geeksforless.tuleninov.assistantweb.data.user.UserUIResponse;
import com.geeksforless.tuleninov.assistantweb.feignclient.UserServiceFeignClient;
import com.geeksforless.tuleninov.assistantweb.model.role.RoleUI;
import com.geeksforless.tuleninov.assistantweb.model.user.UserUI;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for User.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Service
public class UserService {

    @Value("${services.assistant.messages}")
    private String messages;

    private final UserServiceFeignClient userServiceFeignClient;
    private final MailSender mailSender;

    public UserService(UserServiceFeignClient userServiceFeignClient, MailSender mailSender) {
        this.userServiceFeignClient = userServiceFeignClient;
        this.mailSender = mailSender;
    }

    /**
     * Create the user in the database.
     *
     * @param request request with user parameters
     * @return true if successful registration
     */
    public boolean register(SaveUserRequest request) {
        if (userServiceFeignClient.existsByEmail(request.email())) {
            return false;
        }

        userServiceFeignClient.register(request);

        if (!Strings.isBlank(request.email())) {
            new Thread(() ->
                    mailSender.send(
                            request.email(),
                            "Successful registration",
                            UserServiceMessagesMaker.makeWelcomeMessage(request))
            ).start();
        }
        return true;
    }

    /**
     * Find all users from database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @return all users from database in response format
     */
    public Page<UserUIResponse> findAllByRolesEquals(Pageable pageable) {
        return userServiceFeignClient.getAllByRolesEquals(pageable);
    }

    /**
     * Find the user by email from the database in response format.
     *
     * @param email email of user
     * @return the user from database in response format
     */
    public UserUI findByEmail(String email) {
        return UserUI.fromUserResponse(
                userServiceFeignClient.getCurrentUser(email));
    }

    /**
     * Exists user by email in the database in boolean format.
     *
     * @param email email of user
     * @return true - if user exists in database and false - is user does not exist in database
     */
    public boolean existsByEmail(String email) {
        return userServiceFeignClient.existsByEmail(email);
    }

    /**
     * Update the user in the database.
     *
     * @param id      id of goods
     * @param request request with user parameters
     */
    public void update(int id, SaveUserRequest request) {
        var userEmail = userServiceFeignClient.getUserById(id).email();

        userServiceFeignClient.update(id, request);

        new Thread(() ->
                mailSender.send(
                        userEmail,
                        "Changing credentials",
                        UserServiceMessagesMaker.makeUpdateMessage(request))
        ).start();
    }

    /**
     * Change user`s password by email int the database.
     *
     * @param email   user`s login
     * @param request request with user`s password credentials
     * @return true - if changing is successful
     */
    public boolean changePasswordByEmail(String email, OverrideUserPasswordRequest request) {
        if(!request.newPassword().equals(request.confirmPassword())) {
//            log.info("New password and confirmation password do not match");
            return false;
        }

        userServiceFeignClient.changePasswordByEmail(email, request.newPassword());

        new Thread(() ->
                mailSender.send(
                        email,
                        "Changing credentials",
                        UserServiceMessagesMaker.makeUpdatePasswordMessage(email, request.newPassword()))
        ).start();

        return true;
    }

    /**
     * Delete the user in the database.
     *
     * @param email email of user
     */
    public void delete(String email) {
        var userUI = findByEmail(email);

        for (var variable : userUI.getRoles()) {
            if (!variable.getName().equals("ROLE_ADMIN")) {
                userServiceFeignClient.delete(email);

                new Thread(() ->
                        mailSender.send(
                                userUI.getEmail(),
                                "Deleting profile",
                                UserServiceMessagesMaker.makeDeleteMessage(userUI.getEmail()))
                ).start();
            }
        }
    }
}
