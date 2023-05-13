package com.project.ShellPhone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long Id;
    private String name;
    private String username;
    private String password;
    private String url;

//    private List<Order> orders;

    public User() {
    }

    public User(String name, String username, String password, String url) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.url = url;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}