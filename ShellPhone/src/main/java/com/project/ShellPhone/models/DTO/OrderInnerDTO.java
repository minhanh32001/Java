package com.project.ShellPhone.models.DTO;

import java.util.List;

public class OrderInnerDTO {
    private Long orderId;
    private List<OrdersIteamsDTO> iteamsDTOList;
    private double orderTotal;

    public OrderInnerDTO(List<OrdersIteamsDTO> iteamsDTOList) {
        this.iteamsDTOList = iteamsDTOList;
        for(OrdersIteamsDTO ordersIteamsDTO: iteamsDTOList){
            this.orderTotal += ordersIteamsDTO.getOrderItemtotal();
        }
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
