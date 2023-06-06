package com.project.ShellPhone.service;

import com.project.ShellPhone.models.DTO.OrderDTO;
import com.project.ShellPhone.models.order.DonHang;
import com.project.ShellPhone.models.user.User;
import com.project.ShellPhone.models.DTO.UserDTO;
import com.project.ShellPhone.repo.OrderRepo;
import com.project.ShellPhone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MappingService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private OrderRepo orderRepo;
    public List<UserDTO> getAllUsers() {
        return ((List<User>) userRepository
                .findAll())
                .stream()
                .map(this::convertUserIntoDTO)
                .collect(Collectors.toList());
    }
    public UserDTO getUser(User user){
        return convertUserIntoDTO(user);
    }


    private UserDTO convertUserIntoDTO(User userData) {
        UserDTO dto = new UserDTO();

        dto.setId(userData.getId());
        dto.setUsername(userData.getUsername());
        dto.setName(userData.getName());
        dto.setUrl(userData.getUrl());
        dto.setAdmin(userData.isAdmin());
        return dto;
    }

    public List<OrderDTO> getAllOrder() {
        return ((List<DonHang>) orderRepo
                .findAll())
                .stream()
                .map(this::convertOrderIntoDTO)
                .collect(Collectors.toList());
    }
    public List<OrderDTO> getOrdersByUser(User user) {
        return ((List<DonHang>) orderRepo
                .findByUser(user))
                .stream()
                .map(this::convertOrderIntoDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrder(DonHang donHang){
        return convertOrderIntoDTO(donHang);
    }
    private OrderDTO convertOrderIntoDTO(DonHang donHangData) {
        OrderDTO dto = new OrderDTO();
        dto.setId(donHangData.getId());
        dto.setUserId(donHangData.getUser().getId());
        dto.setUserUserName(donHangData.getUser().getName());
        dto.setTimestamp(donHangData.getTimestamp());
        dto.setExecute(donHangData.isExecute());
        dto.setIssued(donHangData.isIssued());
        if(donHangData.getEmployee() != null){
            dto.setEmployeeId(donHangData.getEmployee().getId());
            dto.setEmployeeName(donHangData.getEmployee().getName());
        }
        return dto;
    }
}