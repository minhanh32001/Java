package com.project.ShellPhone.controllers;

import com.project.ShellPhone.models.user.Role;
import com.project.ShellPhone.models.user.User;
import com.project.ShellPhone.models.user.auth.UserDTO;
import com.project.ShellPhone.models.user.auth.UserService;
import com.project.ShellPhone.repo.RoleRepo;
import com.project.ShellPhone.repo.UserRepo;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.BeanUtils;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired private UserService service;
    @Autowired private RoleRepo roleRepo;
    @Autowired
    private UserRepo userRepo;
    @GetMapping("")
    List getAllUsers(){
        List<User> allUsers = userRepo.findAll();
        return allUsers;

    }
    @CrossOrigin
    @PostMapping("/register")
    public HttpStatus createUser(@RequestBody @Valid User user) {
        Optional userPresent = userRepo.findByUsername(user.getUsername());
        if (userPresent.isPresent())
            return HttpStatus.CONFLICT;
        else {
            Role roleuser = roleRepo.findById(3L).get();
            user.setRoles(Set.of(roleuser));
            service.save(user);
            return HttpStatus.CREATED;
        }
    }

    
    @CrossOrigin
    @PutMapping("/update/{id}/role")
    @RolesAllowed("ROLE_ADMIN")
    public HttpStatus createUser(@PathVariable("id") Long id, @RequestParam("role") Long roleId ) {
        User user = userRepo.findById(id).get();
        try {
            Role role = roleRepo.findById(roleId).get();
            Set userRoles = user.getRoles();
            userRoles.add(role);
            user.setRoles(userRoles);
            user.setAdmin();
            userRepo.save(user);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;

        }
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDto) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = optionalUser.get();

        // Sao chép các giá trị từ userDto vào existingUser, loại bỏ trường username
        BeanUtils.copyProperties(userDto, existingUser, "username");

        User updatedUser = service.save(existingUser);
        UserDTO updatedUserDto = new UserDTO(updatedUser.getUsername(), updatedUser.getUrl());

        return ResponseEntity.ok(updatedUserDto);
    }


    @GetMapping("/myprofile")
    UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        UserDTO userDto = new UserDTO(currentUser.getUsername(), currentUser.getUrl());
        return userDto;
    }

}



