package com.project.ShellPhone.models.DTO;

import java.util.List;

public class OrderItemDTO {
    private Long orderItemId;
    private List<OrdersIteamsDTO> iteamsDTOList;
    private double orderTotal;

    public OrderItemDTO(List<OrdersIteamsDTO> iteamsDTOList) {
        this.iteamsDTOList = iteamsDTOList;
        for(OrdersIteamsDTO ordersIteamsDTO: iteamsDTOList){
            this.orderTotal += ordersIteamsDTO.getOrderItemtotal();
        }
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public List<OrdersIteamsDTO> getIteamsDTOList() {
        return iteamsDTOList;
    }

    public void setIteamsDTOList(List<OrdersIteamsDTO> iteamsDTOList) {
        this.iteamsDTOList = iteamsDTOList;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
}
