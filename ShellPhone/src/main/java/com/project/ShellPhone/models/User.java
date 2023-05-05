package com.project.ShellPhone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private String username;
    private String password;
    private String url;
    //private Cart cart;

    public User (){}
    public User(String name, String username, String password, String url) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.url = url;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
    /*public Cart getCart() {
        return cart;
    }*/
}
