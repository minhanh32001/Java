package com.project.ShellPhone.controllers;


import com.project.ShellPhone.models.Address;
import com.project.ShellPhone.models.DTO.OrderDTO;
import com.project.ShellPhone.models.DTO.OrderInnerDTO;
import com.project.ShellPhone.models.DTO.OrderDTO;
import com.project.ShellPhone.models.DTO.OrderItemDTO;
import com.project.ShellPhone.models.DTO.OrdersIteamsDTO;
import com.project.ShellPhone.models.order.DonHang;
import com.project.ShellPhone.models.order.OrderItem;
import com.project.ShellPhone.models.user.User;

import com.project.ShellPhone.repo.OrderItemsRepo;
import com.project.ShellPhone.repo.OrderRepo;
import com.project.ShellPhone.repo.ProductRepo;
import com.project.ShellPhone.service.DTOService;
import com.project.ShellPhone.repo.UserRepo;
import com.project.ShellPhone.service.DTOService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.List;
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
    public OrderInnerDTO showAOrderById(@PathVariable("orderId") Long orderId) {
        DonHang donHang = orderRepo.findById(orderId).get();
        List<OrderItem> orderItems = orderItemsRepo.findByDonHang(donHang);
        OrderInnerDTO orderInnerDTO = new OrderInnerDTO(dtoService.getOrdersItems(orderItems));
        orderInnerDTO.setOrderId(donHang.getId());
        return orderInnerDTO;
    }


    private DonHang themDonHang() {
        DonHang donHang = new DonHang();
        donHang.setUser(getCurrentUser());
        donHang.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return donHang;
    }

    @PostMapping("/muangay")
    public String themSanPham(@RequestParam("productId") Long id, @RequestParam(name ="quantity", defaultValue = "1") int quantity, @RequestBody Address address) {
        OrderItem orderItem = new OrderItem();
        DonHang donHang = themDonHang();
        donHang.setAddress(address.getAddress());
        donHang.setPhoneNumber(address.getPhoneNumber());
        donHang.setTenNguoiNhan(address.getReceiveName());
        orderItem.setDonHang(donHang);
        orderItem.setProduct(productRepo.findById(id).get());
        orderItem.setQuantity(quantity);
        orderRepo.save(donHang);
        orderItemsRepo.save(orderItem);
        return ("Đã tạo đơn hàng mới, mã đơn hàng: " + donHang.getId());
    }

}