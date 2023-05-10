package com.project.ShellPhone.controllers;

import com.project.ShellPhone.models.CartItems;
import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.RespondObject;
import com.project.ShellPhone.models.User;
import com.project.ShellPhone.repo.CartItemsRepo;
import com.project.ShellPhone.repo.UserRepo;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/cart")
public class CartController {
    @Autowired
    private CartItemsRepo cartItemsRepo;

    @GetMapping("")
    ResponseEntity<RespondObject> getAllCartItems() {
        List<CartItems> allItems = cartItemsRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Gets successfully", allItems));

    }

    @GetMapping("/{user_id}")
    ResponseEntity<RespondObject> getAllItems(@PathVariable("user_id") Long user_id) {
        List<CartItems> allItems = cartItemsRepo.findByUserId(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Gets successfully", allItems));

    }

}