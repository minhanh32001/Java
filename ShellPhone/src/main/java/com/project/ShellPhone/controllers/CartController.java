package com.project.ShellPhone.controllers;


import com.project.ShellPhone.models.Cart.Cart;
import com.project.ShellPhone.models.Cart.CartItem;

import com.project.ShellPhone.models.DTO.CartItemsDTO;
import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.order.DonHang;
import com.project.ShellPhone.models.order.OrderItem;
import com.project.ShellPhone.models.user.User;

import com.project.ShellPhone.repo.*;
import com.project.ShellPhone.service.DTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private DTOService dtoService;

    @Autowired
    private OrderRepo orderRepo;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return currentUser;
    }


    @GetMapping("/mycart")
    public List<CartItemsDTO> getCart(){
        return dtoService.getCartItems(cartServices.cartItemList(getCurrentUser()));
    }

    @PostMapping("/addToCart/{id}")
    private String addToCart(@PathVariable("id") Long id, @RequestParam("quantity") int quantity){
        List<CartItem> cartItemList = cartItemsRepo.findByUser(getCurrentUser());
        Product product = productRepo.findById(id).get();
        CartItem cartItemMoi = null;
        for (CartItem cartItem: cartItemList){
            if (cartItem.getProduct().getId().equals(id)){
                cartItemMoi = cartItem;
                cartItemMoi.setQuantity(cartItemMoi.getQuantity()+quantity);
            }
        }
        if(cartItemMoi==null){
            cartItemMoi = new CartItem(getCurrentUser(), product, quantity);
        }
        cartItemsRepo.save(cartItemMoi);
        return ("Thêm sản phẩm vào giỏ hành thành công");
    }
    @DeleteMapping("/mycart/delete")
    public ResponseEntity<String> deleteCartItemByUser() {
        cartServices.deleteCartItemByUser(getCurrentUser());
        return ResponseEntity.ok("Sản phẩm đã được xóa khỏi giỏ hàng.");
    }
    @PostMapping("/mycart/makeorder")
    public Long makeOrder(){
        List<CartItem> cart = cartServices.cartItemList(getCurrentUser());
        List<OrderItem> orderItems = new ArrayList<>();
        final DonHang donHang = new DonHang();
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