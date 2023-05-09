package com.project.ShellPhone.models;

import jakarta.persistence.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_items_id")
    private Long cartId;

    @JoinColumn(name = "cartId")
    @ManyToOne
    private Cart cart;

    @JoinColumn(name = "productId")
    @ManyToOne
    private Product product;
    private int itemNumber;

    public CartItems() {
    }

    public CartItems(Cart cart, Product product, int itemNumber) {
        this.cart = cart;
        this.product = product;
        this.itemNumber = itemNumber;
    }

    public Long getId() {
        return cartId;
    }

    public Cart getUser() {
        return cart;
    }

    public void setUser(Cart user) {
        this.cart = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getItemNumber() {
        return itemNumber;
    }
}
