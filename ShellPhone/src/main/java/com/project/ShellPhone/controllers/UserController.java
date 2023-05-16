package com.project.ShellPhone.controllers;

import com.project.ShellPhone.models.RespondObject;
import com.project.ShellPhone.models.user.Role;
import com.project.ShellPhone.models.user.User;
import com.project.ShellPhone.models.user.auth.UserDTO;
import com.project.ShellPhone.models.user.auth.UserService;
import com.project.ShellPhone.repo.RoleRepo;
import com.project.ShellPhone.repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired private UserService service;
    @Autowired private RoleRepo roleRepo;
    @Autowired
    private UserRepo userRepo;
    @GetMapping("")
    ResponseEntity<RespondObject> getAllUsers(){
        List<User> allUsers = userRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Get all user successfully", allUsers));

    }


    @PutMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        Role role = roleRepo.findById(1L).get();
        user.setRoles(Set.of(role));
        User createdUser = service.save(user);
        URI uri = URI.create("/register/" + createdUser.getId());

        UserDTO userDto = new UserDTO(createdUser.getId(), createdUser.getUsername());

        return ResponseEntity.created(uri).body(userDto);
    }
    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getUserById(@PathVariable("id") Long id) {
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



