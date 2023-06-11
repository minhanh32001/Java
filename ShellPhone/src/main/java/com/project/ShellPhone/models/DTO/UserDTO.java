package com.project.ShellPhone.models.DTO;

public class UserDTO {
    private Long id;
    private String username;
    private boolean isAdmin;
    private String url;

    public UserDTO() {
    }

    public UserDTO( Long id, String username, boolean isAdmin, String url) {
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}