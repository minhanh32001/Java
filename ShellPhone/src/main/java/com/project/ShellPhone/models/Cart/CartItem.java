package com.project.ShellPhone.models.Cart;

import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.order.OrderItem;
import com.project.ShellPhone.models.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @JoinColumn(name = "productId")
    @ManyToOne
    private Product product;
    private int quantity;

    @Transient
    private double total;

    public CartItem(){}
    public CartItem(User user, Product product, int quantity) {
        this.user = user;
        this.product =product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    public OrderItem toOrder(){
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(this.product);
        orderItem.setQuantity(this.quantity);
        return orderItem;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        this.total =  this.product.getLastPrice() * this.quantity;
        return total;
    }
}