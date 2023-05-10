package com.project.ShellPhone.models;

import jakarta.persistence.*;
import org.springframework.data.repository.NoRepositoryBean;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart_items")
@NoRepositoryBean
public class CartItems implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_items_id")
    private Long cartId;

    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;

    @JoinColumn(name = "productId")
    @ManyToOne
    private Product product;
    private int itemNumber;

    public CartItems() {
    }

    public CartItems(User user, Product product, int itemNumber) {
        this.user = user;
        this.product = product;
        this.itemNumber = itemNumber;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "cartId=" + cartId +
                ", user=" + user +
                ", product=" + product +
                ", itemNumber=" + itemNumber +
                '}';
    }

    public Long getId() {
        return cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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