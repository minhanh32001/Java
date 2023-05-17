package com.project.ShellPhone.controllers;


import com.project.ShellPhone.models.order.DonHang;
import com.project.ShellPhone.models.order.OrderItem;
import com.project.ShellPhone.models.user.User;

import com.project.ShellPhone.repo.OrderItemsRepo;
import com.project.ShellPhone.repo.OrderRepo;
import com.project.ShellPhone.repo.ProductRepo;
import com.project.ShellPhone.repo.UserRepo;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/order")
public class OrderController {
    @Autowired
    private OrderItemsRepo orderItemsRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;


    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return currentUser;
    }

    @GetMapping("/allOrder")
    public List<DonHang> showAllOrder() {
        List<DonHang> allOrder = orderRepo.findAll();
        return allOrder;
    }

    @GetMapping("/myorder")
    public List<DonHang> showOrderByUser() {
        List<DonHang> donHang = orderRepo.findByUser(getCurrentUser());
        return donHang;
    }

    @GetMapping("/myorder/{maDonHang}")
    public List<OrderItem> showAOrderById(@PathVariable("maDonHang") Long maDonHang) {
        DonHang donHang = orderRepo.findById(maDonHang).get();
        List<OrderItem> orderItems = orderItemsRepo.findByDonHang(donHang);
        return orderItems;
    }

    @PostMapping("/taoDonHang")
    public DonHang themDonHang() {
        DonHang donHang = new DonHang();
        donHang.setUser(getCurrentUser());
        donHang.setTimestamp(new Timestamp(System.currentTimeMillis()));
        orderRepo.save(donHang);
        return donHang;
    }

    @PostMapping("/themSanPham/{id}")
    public void themSanPham(@PathVariable("id") Long id, @PathParam("quantity") int quantity) {
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setDonHang(themDonHang());
        orderItem1.setProduct(productRepo.findById(id).get());
        orderItem1.setQuantity(quantity);
        orderItemsRepo.save(orderItem1);
        System.out.println("da them san pham voi so luong la" + quantity);
    }

}