package com.project.ShellPhone.repo;

import com.project.ShellPhone.models.DonHang;
import com.project.ShellPhone.models.OrderItem;
import com.project.ShellPhone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findByDonHang(DonHang donHang);

}