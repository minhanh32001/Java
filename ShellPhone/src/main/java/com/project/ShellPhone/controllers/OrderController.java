package com.project.ShellPhone.controllers;


import com.project.ShellPhone.models.Address;
import com.project.ShellPhone.models.DTO.OrderDTO;
import com.project.ShellPhone.models.DTO.OrderInnerDTO;
import com.project.ShellPhone.models.order.DonHang;
import com.project.ShellPhone.models.order.OrderItem;
import com.project.ShellPhone.models.user.User;

import com.project.ShellPhone.repo.OrderItemsRepo;
import com.project.ShellPhone.repo.OrderRepo;
import com.project.ShellPhone.repo.ProductRepo;
import com.project.ShellPhone.service.DTOService;
import com.project.ShellPhone.service.ProductServices;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private ProductServices productServices;


    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return currentUser;
    }


    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR"})
    @GetMapping("/allOrder")
    public List<OrderDTO> getAllOrder() {
        List<DonHang> allOrders = orderRepo.findAll();
        return dtoService.getOrders(allOrders);
    }
    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR"})
    @PutMapping("execute/{orderId}")
    public ResponseEntity<String> executeOrder(@PathVariable("orderId") Long id){
        DonHang donHang = orderRepo.findById(id).get();
        if(donHang != null | donHang.isExecute() == false){
            donHang.setExecute(true);
            donHang.setEmployee(getCurrentUser());
            orderRepo.save(donHang);
            return ResponseEntity.ok("Nhan vien " + getCurrentUser().getUsername() + "da xu ly don hang "+ donHang.getId());}
        else return ResponseEntity.ok("Don hang khong ton tai hoac da duoc xu ly");
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
    @PutMapping("myorder/cancel/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable("id") Long id) {
        List<DonHang> donHangList = orderRepo.findByUser(getCurrentUser());
        for (DonHang donHang : donHangList) {
            if (donHang.getId().equals(id) && !donHang.isCancel()) {
                donHang.setCancel(true);
                List<OrderItem> orderItems = orderItemsRepo.findByDonHang(donHang);
                for(OrderItem orderItem : orderItems){
                    productServices.cancelOrder(orderItem.getProduct().getId(), orderItem.getQuantity());
                }
                orderRepo.save(donHang);
                return ResponseEntity.ok("Da huy don hang id " + id);
            }
        }
        return ResponseEntity.ok("Khong tim thay don hang hoac da bi huy");
    }
    @RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR"})
    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> adminCancelOrder(@PathVariable("id") Long id) {
        try {
            DonHang donHang = orderRepo.findById(id).get();
            donHang.setCancel(true);
            donHang.setEmployee(getCurrentUser());
            List<OrderItem> orderItems = orderItemsRepo.findByDonHang(donHang);
            for(OrderItem orderItem : orderItems){
                productServices.cancelOrder(orderItem.getProduct().getId(), orderItem.getQuantity());
            }
            orderRepo.save(donHang);
            return ResponseEntity.ok("Da huy don hang voi id " + id);
        } catch (Exception exception){
            return ResponseEntity.ok(exception.getMessage());
        }
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
        productServices.makeOrder(id, quantity); // Hàm trừ số lượng sản phẩm trong kho, được định nghĩa trong ProductServices
        orderRepo.save(donHang);
        orderItemsRepo.save(orderItem);
        return ("Da tao don hang moi, ma don hang: " + donHang.getId());
    }

}