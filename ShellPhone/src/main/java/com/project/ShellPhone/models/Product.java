package com.project.ShellPhone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = true, length = 64)
    private String photos;
    private String name;
    private Type type;
    private double price;
    private int discount;
    private double lastPrice;
    private int number;
    private List<Comment> comments;

    @Column(name = "descr")
    private String describe;
    private String url;

    public Product(){}

    public Product(String name, Type type, double price,int discount, int number, String describe, String url) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.lastPrice = this.price-this.price*this.discount/100;
        this.number = number;
        this.describe = describe;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", discount=" + discount +
                ", lastPrice=" + lastPrice +
                ", number=" + number +
                ", describe='" + describe + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public String getUrl() {
        return url;
    }

    public String getDescribe() {
        return describe;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setLastPrice() {
        double roundedPrice = Math.round((this.price - (this.price * this.discount / 100)) / 10000) * 10000;
        this.lastPrice = roundedPrice;
    }

    public double getLastPrice() {
        return lastPrice;
    }

}
