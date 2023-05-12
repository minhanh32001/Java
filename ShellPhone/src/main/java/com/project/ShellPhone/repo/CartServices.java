package com.project.ShellPhone.repo;

import com.project.ShellPhone.models.Cart.CartItem;
import com.project.ShellPhone.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServices {
    @Autowired
    private CartItemsRepo cartRepo;
    public List<CartItem> cartItemList(User user){
        return cartRepo.findByUser(user);
    }
}
