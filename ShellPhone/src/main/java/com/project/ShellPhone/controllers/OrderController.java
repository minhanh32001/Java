package com.project.ShellPhone.controllers;


import com.project.ShellPhone.models.DTO.OrderDTO;
import com.project.ShellPhone.models.DTO.OrderItemDTO;
import com.project.ShellPhone.models.DTO.OrdersIteamsDTO;
import com.project.ShellPhone.models.order.DonHang;
import com.project.ShellPhone.models.order.OrderItem;
import com.project.ShellPhone.models.user.User;

import com.project.ShellPhone.repo.OrderItemsRepo;
import com.project.ShellPhone.repo.OrderRepo;
import com.project.ShellPhone.repo.ProductRepo;
import com.project.ShellPhone.repo.UserRepo;
import com.project.ShellPhone.service.DTOService;
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
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/order")
public class OrderController {
    @Autowired
    private OrderItemsRepo orderItemsRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private DTOService dtoService;


    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return currentUser;
    }

    @GetMapping("/allOrder")
    public List<OrderDTO> getAllOrder() {
        List<DonHang> allOrders = orderRepo.findAll();
        return dtoService.getOrders(allOrders);
    }

    @GetMapping("/myorder")
    public List<OrderDTO> showOrderByUser() {
        List<DonHang> allUserOrders = orderRepo.findByUser(getCurrentUser());
        return dtoService.getOrders(allUserOrders);
    }

    @GetMapping("/myorder/{orderId}")
    public OrderItemDTO showAOrderById(@PathVariable("orderId") Long orderId) {
        DonHang donHang = orderRepo.findById(orderId).get();
        List<OrderItem> orderItems = orderItemsRepo.findByDonHang(donHang);
        OrderItemDTO orderItemDTO = new OrderItemDTO(dtoService.getOrdersItems(orderItems));
        orderItemDTO.setOrderItemId(donHang.getId());
        return orderItemDTO;
    }


    private DonHang themDonHang() {
        DonHang donHang = new DonHang();
        donHang.setUser(getCurrentUser());
        donHang.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return donHang;
    }

    @PostMapping("/themSanPham/{id}")
    public Long themSanPham(@PathVariable("id") Long id, @PathParam("quantity") int quantity) {
        OrderItem orderItem = new OrderItem(); // Tạo đối tượng order_item mới
        DonHang donHang = themDonHang(); // Tạo 1 đơn hàng có sẵn người dùng và thời gian, thằng này chưa được lưu nên chưa có id
        orderItem.setDonHang(donHang); // set đơn hàng cho order_item, thằng này không thể set vì thằng trên ch
        orderItem.setProduct(productRepo.findById(id).get()); // set product cho order_item
        orderItem.setQuantity(quantity); // set quantity cho order item
        orderRepo.save(donHang); // lưu lại cái thằng đơn hàng
        orderItemsRepo.save(orderItem);
        return donHang.getId();
    }

}