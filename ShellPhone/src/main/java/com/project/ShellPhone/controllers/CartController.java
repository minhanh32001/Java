package com.project.ShellPhone.controllers;


import com.project.ShellPhone.models.Address;
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
    public Cart getCart(){
        List<CartItemsDTO> cartItems = dtoService.getCartItems(cartServices.cartItemList(getCurrentUser()));
        Cart cart = new Cart(cartItems);
        return cart;
    }

    @PostMapping("/addToCart/{id}")
    private CartItem addToCart(@PathVariable("id") Long id, @RequestParam(name = "quantity", defaultValue = "1") int quantity){
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
        return (cartItemsRepo.save(cartItemMoi));
    }
    @DeleteMapping("/mycart/delete")
    public ResponseEntity<String> deleteCartItemByUser() {
        cartServices.deleteCartItemByUser(getCurrentUser());
        return ResponseEntity.ok("Toàn bộ sản phẩm đã được xóa khỏi giỏ hàng.");
    }
    @DeleteMapping("mycart/delete/{id}")
    public ResponseEntity<String> deleteAnItem(@PathVariable("id") Long id){
        cartServices.deleteCartItemById(id);
        return ResponseEntity.ok("Đã xóa sản phẩm này khỏi giỏ hàng");
    }

    @PutMapping("/updateItems")
    public ResponseEntity<String> updateItemsInCart(@RequestBody List<CartItem> items) {
        for (CartItem item : items) {
            if(item.getQuantity()<1){
                this.deleteAnItem(item.getId());
            }
            else{
                cartServices.updateQuantity(item.getId(), item.getQuantity());
        }
        }
        return ResponseEntity.ok("Đã cập nhật giỏ hàng.");
    }
    @PostMapping("/mycart/makeorder")
    public Long makeOrder(@RequestBody Address address){
        List<CartItem> cart = cartServices.cartItemList(getCurrentUser());
        List<OrderItem> orderItems = new ArrayList<>();
        DonHang donHang = new DonHang();
        donHang.setAddress(address.getAddress());
        donHang.setPhoneNumber(address.getPhoneNumber());
        donHang.setTenNguoiNhan(address.getReceiveName());
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