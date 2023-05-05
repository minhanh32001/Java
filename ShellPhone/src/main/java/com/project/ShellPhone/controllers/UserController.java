package com.project.ShellPhone.controllers;


import com.project.ShellPhone.models.RespondObject;
import com.project.ShellPhone.models.User;
import com.project.ShellPhone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("")
    List<User> getAllProducts() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getUser(@PathVariable Long id) {
        Optional<User> userFound = userRepo.findById(id);
        return userFound.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("ok", "found product", userFound)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("false", "can not found", "")
                );
    }

}



