package com.project.ShellPhone.models.DTO;

import com.project.ShellPhone.models.Type;

public class ProductDTO {
    private Long productId;
    private String productName;

    private Type type;
    private double price;
    private int discount;
    private double productLastPrice;
    private String productDescribe;
    private String productUrl;

    public ProductDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getProductLastPrice() {
        return productLastPrice;
    }

    public void setProductLastPrice(double productLastPrice) {
        this.productLastPrice = productLastPrice;
    }

    public String getProductDescribe() {
        return productDescribe;
    }

    public void setProductDescribe(String productDescribe) {
        this.productDescribe = productDescribe;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
}
