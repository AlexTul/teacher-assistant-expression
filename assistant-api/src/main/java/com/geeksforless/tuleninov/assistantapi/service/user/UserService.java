package com.geeksforless.tuleninov.assistantapi.service.user;

import com.geeksforless.tuleninov.assistantapi.data.user.SaveUserRequest;
import com.geeksforless.tuleninov.assistantapi.data.user.UserResponse;
import com.geeksforless.tuleninov.assistantapi.exceptions.user.UserExceptions;
import com.geeksforless.tuleninov.assistantapi.model.role.Role;
import com.geeksforless.tuleninov.assistantapi.model.user.User;
import com.geeksforless.tuleninov.assistantapi.repository.RoleRepository;
import com.geeksforless.tuleninov.assistantapi.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.geeksforless.tuleninov.assistantapi.exceptions.role.RoleExceptions.roleNotFound;
import static com.geeksforless.tuleninov.assistantapi.exceptions.user.UserExceptions.userNotFound;

/**
 * Service class for User.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
@Service
public class UserService implements UserCRUD {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    /**
     * Create the user in the database.
     *
     * @param request request with user parameters
     * @return the user from database in response format
     */
    @Override
    @Transactional
    public UserResponse create(SaveUserRequest request) {
        validateUniqueFields(request);
        return UserResponse.fromUser(save(request));
    }

    /**
     * Find all users from the database in response format with ROLE_USER and pagination information.
     *
     * @param pageable abstract interface for pagination information
     * @return all users from database in response format
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> findAllByRolesEquals(Pageable pageable) {
        Role role = roleRepository.findById(1)
                .orElseThrow(() -> roleNotFound(1));

        return userRepository.findAllByRolesEquals(pageable, role)
                .map(UserResponse::fromUser);
    }

    /**
     * Find the user by email from the database in response format.
     *
     * @param email email of user
     * @return the user from database in response format
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponse> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserResponse::fromUser);
    }

    /**
     * Find the user by id from the database in response format.
     *
     * @param id id of user
     * @return the user from database in response format
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponse> findById(int id) {
        return userRepository.findById(id)
                .map(UserResponse::fromUser);
    }

    /**
     * Exists user by email in the database in boolean format.
     *
     * @param email email of user
     * @return true - if user exists in database and false - is user does not exist in database
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Update the user in the database.
     *
     * @param id      id of goods
     * @param request request with user parameters
     */
    @Override
    @Transactional
    public void update(int id, SaveUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> userNotFound(id));

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
    }

    /**
     * Change user`s password by email int the database.
     *
     * @param email       user`s login
     * @param newPassword new user`s password
     */
    @Override
    @Transactional
    public void changePasswordByEmail(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> userNotFound(email));

        user.setPassword(passwordEncoder.encode(newPassword));
    }

    /**
     * Delete the user in the database.
     *
     * @param email email of user
     */
    @Override
    @Transactional
    public void deleteByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw userNotFound(email);
        }
        userRepository.deleteByEmail(email);
    }

    /**
     * Validate the user`s name field in the database.
     *
     * @param request request with user parameters
     */
    private void validateUniqueFields(SaveUserRequest request) {
        String email = request.email();
        if (userRepository.existsByEmail(email)) {
            throw UserExceptions.duplicateEmail(email);
        }
    }

    /**
     * Save the user in the database.
     *
     * @param request request with user parameters
     * @return the user entity
     */
    private User save(SaveUserRequest request) {
        var user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(1)
                .orElseThrow(() -> roleNotFound(1)));
        user.setRoles(roles);
        userRepository.save(user);

        return user;
    }
}
