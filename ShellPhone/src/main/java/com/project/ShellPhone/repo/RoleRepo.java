package com.project.ShellPhone.repo;


import com.project.ShellPhone.models.user.Role;
import com.project.ShellPhone.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findById(Long id);
}