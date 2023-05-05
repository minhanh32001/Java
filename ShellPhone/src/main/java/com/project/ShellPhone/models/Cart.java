package com.project.ShellPhone.models;

import jakarta.persistence.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cart;

    public Cart() {
        this.cart = new ArrayList<Product>();
    }
    protected void showCart(){
        for (Product product: this.cart){
            product.toString();
        }

    }
    protected void addProduct(Product product){
        this.cart.add(product);
    }
    protected void remove(Product product) {
        this.cart.remove(product);
    }
    public double getPrice(){
        double sumPrice = 0;
        for (Product product: this.cart){
            sumPrice += product.getPrice();
        }
        return sumPrice;
    }
}
