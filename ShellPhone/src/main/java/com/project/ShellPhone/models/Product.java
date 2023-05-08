package com.project.ShellPhone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
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
}
