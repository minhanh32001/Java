package com.project.ShellPhone.models.DTO;

public class CartItemsDTO {

    private Long id;
    private ProductDTO product;
    private int quantity;
    private double total;

    public CartItemsDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getProductLastPrice() * quantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
