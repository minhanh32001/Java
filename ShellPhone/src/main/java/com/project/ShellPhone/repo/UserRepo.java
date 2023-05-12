package com.project.ShellPhone.repo;
import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByName(String name);
    Optional<User> findById(Long id);

}
