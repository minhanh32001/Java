package com.project.ShellPhone.models.Cart;
import com.project.ShellPhone.models.DTO.CartItemsDTO;
import com.project.ShellPhone.service.DTOService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItemsDTO> cartItemsDTOList;
    private double totalCart;

    public Cart(List<CartItemsDTO> cartItemsDTOList) {
        this.cartItemsDTOList = cartItemsDTOList;
        for (CartItemsDTO cartItemsDTO: this.cartItemsDTOList)
            this.totalCart += cartItemsDTO.getTotal();
        }

    public List<CartItemsDTO> getCartItemList() {
        return cartItemsDTOList;
    }

    public double getTotal() {
        return totalCart;
    }
}
