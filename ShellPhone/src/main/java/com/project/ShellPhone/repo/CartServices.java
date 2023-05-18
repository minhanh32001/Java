package com.project.ShellPhone.repo;

import com.project.ShellPhone.models.Cart.CartItem;
import com.project.ShellPhone.models.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartServices {
    @Autowired
    private CartItemsRepo cartItemsRepo;

    public List<CartItem> cartItemList(User user) {
        return cartItemsRepo.findByUser(user);
    }


    public void deleteCartItemByUser(User user) {
        cartItemsRepo.deleteByUser(user);
    }
}
