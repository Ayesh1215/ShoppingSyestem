package com.shopping.ShoppingSyestem.service;
import java.time.LocalDateTime;

public class OrderResponse {
    private Long orderId;
    private String status;
    private LocalDateTime createdAt;
    public OrderResponse(Long orderId, String status, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.status = status;
        this.createdAt = createdAt;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
