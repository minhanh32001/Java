package com.project.ShellPhone.models.user.auth;


import com.project.ShellPhone.models.user.User;
import com.project.ShellPhone.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {
    @Autowired private UserRepo userRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    public User save(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        return userRepo.save(user);
    }
}