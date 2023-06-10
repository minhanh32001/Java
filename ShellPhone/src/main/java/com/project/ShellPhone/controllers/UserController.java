package com.project.ShellPhone.controllers;

import com.project.ShellPhone.models.user.Role;
import com.project.ShellPhone.models.user.User;
import com.project.ShellPhone.models.DTO.UserDTO;
import com.project.ShellPhone.models.user.auth.UserService;
import com.project.ShellPhone.repo.RoleRepo;
import com.project.ShellPhone.repo.UserRepo;
import com.project.ShellPhone.service.DTOService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired private UserService service;
    @Autowired private DTOService mappingService;
    @Autowired private RoleRepo roleRepo;
    @Autowired
    private UserRepo userRepo;

    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR"})
    @GetMapping("/allUser")
    List getAllUsers(){
        List<User> allUsers = userRepo.findAll();
        return mappingService.getAllUsersDTO(allUsers);
    }
    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userRepo.deleteById(id);
            return ResponseEntity.ok("Da xoa user id " + id);
    }

    @CrossOrigin
    @PostMapping("/register")
    public HttpStatus createUser(@RequestBody @Valid User user) {
        if (userRepo.existsByUsername(user.getUsername()))
            return HttpStatus.CONFLICT;
        else {
            Role roleuser = roleRepo.findById(3L).get();
            user.setRoles(Set.of(roleuser));
            service.save(user);
            return HttpStatus.CREATED;
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
        UserDTO updatedUserDto = new UserDTO(getCurrentUser().getId(), updatedUser.getUsername(), updatedUser.getName(), updatedUser.isAdmin(), updatedUser.getUrl());

        return ResponseEntity.ok(updatedUserDto);
    }


    @GetMapping("/myprofile")
    UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return mappingService.convertUserIntoDTO(currentUser);
    }
}



