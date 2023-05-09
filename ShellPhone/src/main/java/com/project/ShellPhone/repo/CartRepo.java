package com.project.ShellPhone.repo;

import com.project.ShellPhone.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findAll();
}
