package com.project.ShellPhone.controllers;

import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.RespondObject;
import com.project.ShellPhone.models.User;
import com.project.ShellPhone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("")
    ResponseEntity<RespondObject> getAllUsers(){
        List<User> allUsers = userRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Get all user successfully", allUsers));

    }
    @GetMapping("/getUser/{id}")
    ResponseEntity<RespondObject> getUserById(@PathVariable Long id) {
        Optional<User> userFound = userRepo.findById(id);
        return userFound.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("ok", "found user", userFound)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("false", "can not found", "")
                );
    }


}



