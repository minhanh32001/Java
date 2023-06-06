package com.project.ShellPhone.service;

import com.project.ShellPhone.models.Cart.CartItem;
import com.project.ShellPhone.models.DTO.*;
import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.order.DonHang;
import com.project.ShellPhone.models.order.OrderItem;
import com.project.ShellPhone.models.user.User;
import com.project.ShellPhone.repo.OrderRepo;
import com.project.ShellPhone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DTOService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private OrderRepo orderRepo;

    // User
    public List<UserDTO> getAllUsersDTO(List<User> users) {
        List<UserDTO> allUsers = new ArrayList<>();
        for (User user : users){
            allUsers.add(this.convertUserIntoDTO(user));
        }
        return allUsers;
    }

    public UserDTO convertUserIntoDTO(User userData) {
        UserDTO dto = new UserDTO();

        dto.setId(userData.getId());
        dto.setUsername(userData.getUsername());
        dto.setName(userData.getName());
        dto.setUrl(userData.getUrl());
        dto.setAdmin(userData.isAdmin());
        return dto;
    }

    //    Order

    public List<OrderDTO> getOrders(List<DonHang> donHangList) {
        List<OrderDTO> allOrders = new ArrayList<>();
        for (DonHang donHang : donHangList){
            allOrders.add(this.convertOrderIntoDTO(donHang));
        }
        return allOrders;
    }

    public OrderDTO convertOrderIntoDTO(DonHang donHangData) {
        OrderDTO dto = new OrderDTO();
        dto.setId(donHangData.getId());
        dto.setUserId(donHangData.getUser().getId());
        dto.setUserUserName(donHangData.getUser().getUsername());
        dto.setTimestamp(donHangData.getTimestamp());
        dto.setExecute(donHangData.isExecute());
        dto.setIssued(donHangData.isIssued());
        if(donHangData.getEmployee() != null){
            dto.setEmployeeId(donHangData.getEmployee().getId());
            dto.setEmployeeName(donHangData.getEmployee().getName());
        }
        return dto;
    }
    // Cart_Items

    public List<CartItemsDTO> getCartItems(List<CartItem> cartItems){
        List<CartItemsDTO> cartItemsDTOList = new ArrayList<>();
        for (CartItem cartItem: cartItems){
            cartItemsDTOList.add(this.convertCartItemIntoDTO(cartItem));
        }
        return cartItemsDTOList;
    }
    public CartItemsDTO convertCartItemIntoDTO(CartItem cartItem) {
        CartItemsDTO dto = new CartItemsDTO();
        dto.setProduct(this.convertProductIntoDTO(cartItem.getProduct()));
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }


    // Orders_items
    public List<OrdersIteamsDTO> getOrdersItems(List<OrderItem> orderItemList) {
        List<OrdersIteamsDTO> allOrdersItems = new ArrayList<>();
        for (OrderItem orderItem :  orderItemList){
            allOrdersItems.add(this.convertOrdersItemsIntoDTO(orderItem));
        }
        return allOrdersItems;
    }

    public OrdersIteamsDTO convertOrdersItemsIntoDTO(OrderItem orderItemData) {
        OrdersIteamsDTO dto = new OrdersIteamsDTO();

        dto.setId(orderItemData.getId());
        dto.setProduct(this.convertProductIntoDTO(orderItemData.getProduct()));
        dto.setQuantity(orderItemData.getQuantity());
        return dto;
    }

//    Product
    public ProductDTO convertProductIntoDTO(Product product){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId(product.getId());
        productDTO.setProductName(product.getName());
        productDTO.setProductLastPrice(product.getLastPrice());
        productDTO.setProductUrl(product.getUrl());
        productDTO.setProductDescribe(product.getDescribe());

        return productDTO;
    }
}