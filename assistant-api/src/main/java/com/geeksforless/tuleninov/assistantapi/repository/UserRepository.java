package com.geeksforless.tuleninov.assistantapi.repository;

import com.geeksforless.tuleninov.assistantapi.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Interface for working with the repository of User.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);
}
