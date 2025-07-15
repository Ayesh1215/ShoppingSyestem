package com.shopping.ShoppingSyestem.service;
import com.shopping.ShoppingSyestem.Application.Order;
import com.shopping.ShoppingSyestem.Application.Product;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public void addProduct(Product product) {
        System.out.println("Product added: " + product.getName());
    }
    public Order getMyOrder() {
        Order order = new Order();
        return order;
    }
}
