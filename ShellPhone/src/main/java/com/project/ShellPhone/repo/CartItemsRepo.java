package com.project.ShellPhone.repo;

import com.project.ShellPhone.models.Cart.CartItem;
import com.project.ShellPhone.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartItemsRepo extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);

    void deleteByUser(User user);
    void deleteById(Long id);
}