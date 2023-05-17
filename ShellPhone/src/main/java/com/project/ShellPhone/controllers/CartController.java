package com.project.ShellPhone.controllers;


import com.project.ShellPhone.models.Cart.CartItem;

import com.project.ShellPhone.models.user.User;

import com.project.ShellPhone.repo.CartItemsRepo;
import com.project.ShellPhone.repo.CartServices;
import com.project.ShellPhone.repo.ProductRepo;
import com.project.ShellPhone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartItemsRepo cartItemsRepo;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return currentUser;
    }
    @GetMapping("/mycart")
    public List<CartItem> getCart(){
        List<CartItem> cartItems = cartServices.cartItemList(getCurrentUser());
        return cartItems;
    }

    @PostMapping("/addToCart/{id}")
    private String addToCart(@PathVariable("id") Long id, @RequestParam("quantity") int quantity){
        CartItem cartItem = new CartItem();
        cartItem.setProduct(productRepo.findById(id).get());
        cartItem.setQuantity(quantity);
        cartItem.setUser(getCurrentUser());
        cartItemsRepo.save(cartItem);
        return cartItem.getId().toString();
    }

    @PostMapping("/makeorder")
    @
}