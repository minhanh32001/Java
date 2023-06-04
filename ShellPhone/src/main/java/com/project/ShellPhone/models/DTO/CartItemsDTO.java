package com.project.ShellPhone.models.DTO;

public class CartItemsDTO {
    private ProductDTO product;
    private int quantity;

    public CartItemsDTO() {
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
}
