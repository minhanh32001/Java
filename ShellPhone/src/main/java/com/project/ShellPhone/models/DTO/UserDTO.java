package com.project.ShellPhone.models.DTO;

public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private boolean isAdmin;
    private String url;

    public UserDTO() {
    }

    public UserDTO( Long id, String username, String name, boolean isAdmin, String url) {
        this.id = id;
        this.username = username;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}