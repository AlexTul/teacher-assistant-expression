package com.geeksforless.tuleninov.assistantapi.controller.user;

import com.geeksforless.tuleninov.assistantapi.data.user.UserResponse;
import com.geeksforless.tuleninov.assistantapi.service.user.UserCRUD;
import com.geeksforless.tuleninov.assistantapi.data.user.SaveUserRequest;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.geeksforless.tuleninov.assistantapi.exceptions.user.UserExceptions.userNotFound;
import static com.geeksforless.tuleninov.assistantapi.RoutesAPI.*;

/**
 * Rest controller for the User.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@RestController
public class UserController {

    private final UserCRUD userCRUD;

    public UserController(UserCRUD userCRUD) {
        this.userCRUD = userCRUD;
    }

    /**
     * Create the user in the database.
     *
     * @param request request with user parameters
     * @param ucb     builder for UriComponents
     * @return the user from database in response format
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = URL_REGISTER, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> register(@Valid @RequestBody SaveUserRequest request, UriComponentsBuilder ucb) {
        UserResponse response = userCRUD.create(request);
        return ResponseEntity
                .created(ucb.path(URL_REGISTER + "/{id}").build(response.id()))
                .body(response);
    }

    /**
     * Get all users from database in response format with ROLE_USER and pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @return all users from database in response format
     */
    @GetMapping(value = URL_ADMIN + URL_USER, produces = MediaType.APPLICATION_JSON_VALUE)
    @PageableAsQueryParam
    public Page<UserResponse> getAllByRolesEquals(@Parameter(hidden = true) Pageable pageable) {
        return userCRUD.findAllByRolesEquals(pageable);
    }

    /**
     * Get the user by email from the database in response format.
     *
     * @param email email of user
     * @return the user from database in response format
     */
    @GetMapping(value = URL_ADMIN + URL_USER + "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getCurrentUser(@PathVariable String email) {
        return userCRUD.findByEmail(email)
                .orElseThrow(() -> userNotFound(email));
    }

    /**
     * Get the user by id from the database in response format.
     *
     * @param id id of user
     * @return the user from database in response format
     */
    @GetMapping(value = URL_USER + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUserById(@PathVariable int id) {
        return userCRUD.findById(id)
                .orElseThrow(() -> userNotFound(id));
    }

    /**
     * Exists user by email in the database in boolean format.
     *
     * @param email email of user
     * @return true - if user exists in database and false - is user does not exist in database
     */
    @GetMapping(value = URL_REGISTER + "/{email}")
    public boolean existsByEmail(@PathVariable String email) {
        return userCRUD.existsByEmail(email);
    }

    /**
     * Update the user in the database.
     *
     * @param id      id of goods
     * @param request request with user parameters
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = URL_REGISTER + URL_USER + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable int id, @Valid @RequestBody SaveUserRequest request) {
        userCRUD.update(id, request);
    }

    /**
     * Change user`s password by email int the database.
     *
     * @param email       user`s login
     * @param newPassword new user`s password
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = URL_ADMIN + URL_USER + "/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void changePasswordByEmail(@PathVariable String email, @Valid @RequestBody String newPassword) {
        userCRUD.changePasswordByEmail(email, newPassword);
    }

    /**
     * Delete the user in the database.
     *
     * @param email email of user
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = URL_ADMIN + URL_USER + "/{email}")
    public void delete(@PathVariable String email) {
        userCRUD.deleteByEmail(email);
    }
}
