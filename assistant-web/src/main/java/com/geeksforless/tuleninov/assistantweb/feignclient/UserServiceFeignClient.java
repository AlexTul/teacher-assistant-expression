package com.geeksforless.tuleninov.assistantweb.feignclient;

import com.geeksforless.tuleninov.assistantweb.data.user.SaveUserUIRequest;
import com.geeksforless.tuleninov.assistantweb.data.user.UserUIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.geeksforless.tuleninov.assistantweb.RoutesWeb.*;

/**
 * Feign Client for the User.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@FeignClient(name = "UserController.class", url = "${services.assistant.api}")
public interface UserServiceFeignClient {

    /**
     * Create the user in the database.
     *
     * @param request request with user parameters
     * @return the user from database in response format
     */
    @PostMapping(value = URL_REGISTER, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserUIResponse register(@RequestBody SaveUserUIRequest request);

    /**
     * Get all users from database in response format with pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @return all users from database in response format
     */
    @GetMapping(value = URL_ADMIN + URL_USER, produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserUIResponse> getAllByRolesEquals(Pageable pageable);

    /**
     * Get the user by email from the database in response format.
     *
     * @param email email of user
     * @return the user from database in response format
     */
    @GetMapping(value = URL_ADMIN + URL_USER + "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserUIResponse getCurrentUser(@PathVariable String email);

    /**
     * Get the user by id from the database in response format.
     *
     * @param id id of user
     * @return the user from database in response format
     */
    @GetMapping(value = URL_USER + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserUIResponse getUserById(@PathVariable int id);

    /**
     * Exists user by email in the database.
     *
     * @param email email from user
     * @return true - if user exists in database and false - is user does not exist in database
     */
    @GetMapping(value = URL_REGISTER + "/{email}")
    boolean existsByEmail(@PathVariable String email);

    /**
     * Update the user in the database.
     *
     * @param id      id of goods
     * @param request request with user parameters
     */
    @PutMapping(value = URL_REGISTER + URL_USER + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void update(@PathVariable int id, @RequestBody SaveUserUIRequest request);

    /**
     * Change user`s password by email int the database.
     *
     * @param email       user`s login
     * @param newPassword new user`s password
     */
    @PutMapping(value = URL_ADMIN + URL_USER + "/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void changePasswordByEmail(@PathVariable String email, @RequestBody String newPassword);

    /**
     * Delete the user in the database.
     *
     * @param email email of user
     */
    @DeleteMapping(value = URL_ADMIN + URL_USER + "/{email}")
    void delete(@PathVariable String email);
}
