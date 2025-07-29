package com.shopping.ShoppingSyestem.Service;

import com.shopping.ShoppingSyestem.Model.Employee;
import com.shopping.ShoppingSyestem.Model.Order;
import com.shopping.ShoppingSyestem.Model.Product;
import com.shopping.ShoppingSyestem.Repository.EmployeeRepository;
import com.shopping.ShoppingSyestem.Repository.OrderRepository;
import com.shopping.ShoppingSyestem.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<OrderResponse> placeOrder(List<OrderRequest> requests) {
        List<OrderResponse> responses = new ArrayList<>();
        for (OrderRequest request : requests) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Employee employee = employeeRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            Order order = new Order();
            order.setEmployee(employee);
            order.setOrderDate(LocalDate.now());
            List<OrderResponse.ProductInfo> productInfos = new ArrayList<>();
            for (OrderRequest.ProductRequest pr : request.getProducts()) {
                Product product = productRepository.findById(pr.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrder(order);
                orderProduct.setProduct(product);
                orderProduct.setPrice(product.getPrice());
                orderProduct.setQuantity(pr.getQuantity());
                order.getOrderProducts().add(orderProduct);
                OrderResponse.ProductInfo pi = new OrderResponse.ProductInfo();
                pi.setProductId(product.getId());
                pi.setName(product.getName());
                pi.setPrice(product.getPrice());
                pi.setQuantity(pr.getQuantity());
                productInfos.add(pi);
            }
            orderRepository.save(order);
            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getOrder_id());
            response.setEmployeeName(employee.getName());
            response.setOrderDate(order.getOrderDate());
            response.setProducts(productInfos);
            responses.add(response);
        }
        return responses;
    }

    public List<Order> getMyOrders(String username) {
        Employee employee = employeeRepository.findByUsername(username).orElse(null);
        if (employee == null) return Collections.emptyList();
        return orderRepository.findByEmployee(employee);
    }
}