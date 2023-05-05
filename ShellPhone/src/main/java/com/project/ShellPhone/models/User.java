package com.project.ShellPhone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String username;
    private String password;
    private String name;
    private String url;
    //private Cart cart;
    public User(){};


    public User(String username, String password, String name, String url) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
    /*public Cart getCart() {
        return cart;
    }*/
}
