package com.geeksforless.tuleninov.assistantapi.service.user;

import com.geeksforless.tuleninov.assistantapi.data.user.UserResponse;
import com.geeksforless.tuleninov.assistantlib.data.user.SaveUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.Optional;

import static com.geeksforless.tuleninov.assistantlib.Routes.URL_ADMIN;
import static com.geeksforless.tuleninov.assistantlib.Routes.URL_USER;

/**
 * Interface CRUD for User.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public interface UserCRUD {

    /**
     * Create the user in the database.
     *
     * @param request request with user parameters
     * @return the user from database in response format
     */
    UserResponse create(SaveUserRequest request);

    /**
     * Find all users from database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @return all users from database in response format
     */
    Page<UserResponse> findAll(Pageable pageable);

    /**
     * Find the user by email from the database in response format.
     *
     * @param email email of user
     * @return the user from database in response format
     */
    Optional<UserResponse> findByEmail(String email);

    /**
     * Find the user by id from the database in response format.
     *
     * @param id id of user
     * @return the user from database in response format
     */
    Optional<UserResponse> findById(int id);

    /**
     * Exists user by email in the database in boolean format.
     *
     * @param email email of user
     * @return true - if user exists in database and false - is user does not exist in database
     */
    boolean existsByEmail(String email);

    /**
     * Update the user in the database.
     *
     * @param id      id of goods
     * @param request request with user parameters
     */
    void update(int id, SaveUserRequest request);

    /**
     * Change user`s password by email int the database.
     *
     * @param email       user`s login
     * @param newPassword new user`s password
     */
    void changePasswordByEmail(String email, String newPassword);

    /**
     * Delete the user in the database.
     *
     * @param email email of user
     */
    void deleteByEmail(String email);
}
