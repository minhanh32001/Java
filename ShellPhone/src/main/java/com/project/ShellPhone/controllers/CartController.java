package com.project.ShellPhone.controllers;


import com.project.ShellPhone.models.Cart.CartItem;

import com.project.ShellPhone.models.order.DonHang;
import com.project.ShellPhone.models.order.OrderItem;
import com.project.ShellPhone.models.user.User;

import com.project.ShellPhone.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/cart")
public class CartController {
    @Autowired
    private CartServices cartServices;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartItemsRepo cartItemsRepo;

    @Autowired
    private OrderItemsRepo orderItemsRepo;

    @Autowired
    private OrderRepo orderRepo;

//    private User getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = (User) authentication.getPrincipal();
//        return currentUser;
//    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepo.findByUsername(username);
        User currentUser = userOptional.orElse(null); // hoặc sử dụng orElseThrow() để ném ngoại lệ
        return currentUser;
    }


    @GetMapping("/mycart")
    public List<CartItem> getCart(){
        List<CartItem> cartItems = cartServices.cartItemList(getCurrentUser());
        return cartItems;
    }

    @PostMapping("/addToCart/{id}")
    private String addToCart(@PathVariable("id") Long id, @RequestParam("quantity") int quantity){
        CartItem cartItem = new CartItem();
        cartItem.setProduct(productRepo.findById(id).get());
        cartItem.setQuantity(quantity);
        cartItem.setUser(getCurrentUser());
        cartItemsRepo.save(cartItem);
        return cartItem.getId().toString();
    }
    @DeleteMapping("/mycart/delete")
    public ResponseEntity<String> deleteCartItemByUser() {
        cartServices.deleteCartItemByUser(getCurrentUser());
        return ResponseEntity.ok("Sản phẩm đã được xóa khỏi giỏ hàng.");
    }
    @PostMapping("/mycart/makeorder")
    public Long makeOrder(){
        List<CartItem> cart = getCart();
        List<OrderItem> orderItems = new ArrayList<>();
        DonHang donHang = new DonHang();
        donHang.setUser(getCurrentUser());
        donHang.setTimestamp(new Timestamp(System.currentTimeMillis()));
        orderRepo.save(donHang);
        for (CartItem cartItem : cart ){
            orderItems.add(cartItem.toOrder());
        };
        for (OrderItem orderItem : orderItems){
            orderItem.setDonHang(donHang);
            orderItemsRepo.save(orderItem);
        }
        deleteCartItemByUser();
        return donHang.getId();
    }
}