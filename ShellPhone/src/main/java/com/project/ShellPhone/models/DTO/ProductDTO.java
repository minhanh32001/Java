package com.project.ShellPhone.models.DTO;

public class ProductDTO {
    private Long productId;
    private String productName;
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
