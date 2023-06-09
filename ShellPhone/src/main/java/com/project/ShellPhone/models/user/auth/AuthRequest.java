package com.project.ShellPhone.models.user.auth;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public class AuthRequest {
    @NotNull @Length(min = 5, max = 50)
    private String username;

    @NotNull @Length(min = 1, max = 600)
    private String password;

    public AuthRequest() {

    }

    public AuthRequest(String username,String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
