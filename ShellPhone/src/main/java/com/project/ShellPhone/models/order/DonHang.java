package com.project.ShellPhone.models.order;

import com.project.ShellPhone.models.user.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "donhang")
public class DonHang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "donhang_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "time")
    private Timestamp timestamp;
    @Column(name = "status")
    private boolean isIssued = false;
    @Column(name = "execute")
    private boolean isExecute = false;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = true)
    private User employee;
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", timestamp=" + timestamp +
                '}';
    }

    public DonHang() {
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    public boolean isExecute() {
        return isExecute;
    }

    public void setExecute(boolean execute) {
        isExecute = execute;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
