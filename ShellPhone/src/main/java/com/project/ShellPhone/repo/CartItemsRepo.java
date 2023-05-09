package com.project.ShellPhone.repo;

import com.project.ShellPhone.models.CartItems;
import com.project.ShellPhone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemsRepo extends JpaRepository<CartItems, Long> {
    List<CartItems> findAll();
}
