package com.project.ShellPhone.models.Cart;
import com.project.ShellPhone.models.DTO.CartItemsDTO;
import com.project.ShellPhone.service.DTOService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    @Autowired
    private DTOService dtoService;
    private List<CartItemsDTO> cartItemsDTOList;
    private double total;

    public Cart(List<CartItem> cartItemList) {
        this.cartItemsDTOList = dtoService.getCartItems(cartItemList);
        for (CartItem cartItem: cartItemList)
            this.total += cartItem.getTotal();
        }

    public List<CartItemsDTO> getCartItemList() {
        return cartItemsDTOList;
    }

    public double getTotal() {
        return total;
    }
}
