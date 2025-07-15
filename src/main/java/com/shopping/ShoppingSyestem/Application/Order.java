package com.shopping.ShoppingSyestem.Application;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import java.util.List;

public class Order {
    private int order_id;
    private int user_id;
    private int id;
    private int order_date;
    private int quantity;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Order> orders;
    public Order(int order_id, int user_id, int id, int order_date, int quantity) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.id = id;
        this.order_date = order_date;
        this.quantity = quantity;
    }
    public Order() {
    }
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrder_date() {
        return order_date;
    }
    public void setOrder_date(int order_date) {
        this.order_date = order_date;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
