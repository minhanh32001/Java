package com.project.ShellPhone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private Type type;
    private double price;
    private int number;
    private String describe;
    private String url;

    public Product(){}

    public Product(String name, Type type, double price, int number, String describe, String url) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.number = number;
        this.describe = describe;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
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
}
