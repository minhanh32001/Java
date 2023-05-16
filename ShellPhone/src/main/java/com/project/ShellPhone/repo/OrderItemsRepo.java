package com.project.ShellPhone.repo;

import com.project.ShellPhone.models.order.DonHang;
import com.project.ShellPhone.models.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findByDonHang(DonHang donHang);

}