package com.project.ShellPhone.controllers;


import com.project.ShellPhone.models.Cart.CartItem;

import com.project.ShellPhone.models.user.User;

import com.project.ShellPhone.repo.CartServices;
import com.project.ShellPhone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/cart")
public class CartController {
    @Autowired
    private CartServices cartServices;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/{id}")
    public List<CartItem> showCart(@PathVariable("id") Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        User user = optionalUser.get();
        List<CartItem> cartItems = cartServices.cartItemList(user);
        return cartItems;
    }

}