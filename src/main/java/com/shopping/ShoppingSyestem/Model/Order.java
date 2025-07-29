package com.shopping.ShoppingSyestem.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shopping.ShoppingSyestem.Service.OrderProduct;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long orderid;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "order-orderproduct")
    private List<OrderProduct> orderProducts = new ArrayList<>();
    private LocalDate orderDate;

    public Order(Long orderid, int order_date, int quantity) {
        this.orderid = orderid;
        this.quantity = quantity;
    }

    public Order() {
    }

    public Long getOrder_id() {
        return orderid;
    }

    public void setOrder_id(Long order_id) {
        this.orderid = order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}