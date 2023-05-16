package com.project.ShellPhone.controllers;


import com.project.ShellPhone.models.order.DonHang;
import com.project.ShellPhone.models.order.OrderItem;
import com.project.ShellPhone.models.user.User;

import com.project.ShellPhone.repo.OrderItemsRepo;
import com.project.ShellPhone.repo.OrderRepo;
import com.project.ShellPhone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("")
    public List<DonHang> showAllOrder(){
        List<DonHang> allOrder = orderRepo.findAll();
        return allOrder;
    }
    @GetMapping("/{id}")
    public List<DonHang> showOrderByUser(@PathVariable("id") Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        User user = optionalUser.get();
        List<DonHang> donHang = orderRepo.findByUser(user);
        return donHang;
    }
    @GetMapping("/{id}/{maDonHang}")
    public List<OrderItem> showOrderByUser(@PathVariable("id") Long id, @PathVariable("maDonHang") Long maDonHang){
        Optional<User> optionalUser = userRepo.findById(id);
        User user = optionalUser.get();
        List<DonHang> donHang = orderRepo.findByUser(user);
        Optional<DonHang> optionalDonHang = orderRepo.findById(maDonHang);
        DonHang donHang1 = optionalDonHang.get();
        List<OrderItem> orderItems = orderItemsRepo.findByDonHang(donHang1);
        return orderItems;
    }
}