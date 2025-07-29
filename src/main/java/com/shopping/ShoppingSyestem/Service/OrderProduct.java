package com.shopping.ShoppingSyestem.Service;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopping.ShoppingSyestem.Model.Order;
import com.shopping.ShoppingSyestem.Model.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @JsonBackReference(value = "order-orderproduct")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference(value = "product-orderproduct")
    private Product product;
    private int quantity;
    private double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}