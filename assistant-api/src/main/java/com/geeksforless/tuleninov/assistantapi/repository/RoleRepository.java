package com.geeksforless.tuleninov.assistantapi.repository;

import com.geeksforless.tuleninov.assistantapi.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for working with the repository of Role.
 *
 * @author Oleksandr Tuleninov
 * @version 01
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
