package com.project.ShellPhone.repo;

import com.project.ShellPhone.models.Cart.CartItem;
import com.project.ShellPhone.models.DonHang;

import com.project.ShellPhone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepo extends JpaRepository<DonHang, Long> {
    public List<DonHang> findByUser(User user);

}