package com.project.ShellPhone.repo;

import com.project.ShellPhone.models.order.DonHang;

import com.project.ShellPhone.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepo extends JpaRepository<DonHang, Long> {
    public List<DonHang> findByUser(User user);

}