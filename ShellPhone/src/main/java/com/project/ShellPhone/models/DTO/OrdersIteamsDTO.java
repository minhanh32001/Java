package com.project.ShellPhone.models.DTO;

public class OrdersIteamsDTO {
    private Long id;
    private ProductDTO product;
    private int quantity;
    private double orderItemtotal;
    public OrdersIteamsDTO() {
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

    public double getOrderItemtotal() {
        this.setOrderItemtotal();
        return orderItemtotal;
    }

    public void setOrderItemtotal() {
        this.orderItemtotal = this.product.getProductLastPrice() * this.quantity ;
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

