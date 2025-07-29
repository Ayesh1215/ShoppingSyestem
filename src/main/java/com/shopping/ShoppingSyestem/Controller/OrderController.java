package com.shopping.ShoppingSyestem.Controller;

import com.shopping.ShoppingSyestem.Model.Order;
import com.shopping.ShoppingSyestem.Service.OrderProduct;
import com.shopping.ShoppingSyestem.Service.OrderRequest;
import com.shopping.ShoppingSyestem.Service.OrderResponse;
import com.shopping.ShoppingSyestem.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('SELLER')")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('SELLER')")
    public ResponseEntity<List<OrderResponse>> getMyOrder() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<Order> myOrders = orderService.getMyOrders(username);
        if (myOrders == null || myOrders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<OrderResponse> responseList = new ArrayList<>();

        for (Order order : myOrders) {
            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getOrder_id());
            response.setOrderDate(order.getOrderDate());
            response.setEmployeeName(order.getEmployee().getName());
            List<OrderResponse.ProductInfo> productInfos = new ArrayList<>();
            for (OrderProduct op : order.getOrderProducts()) {
                OrderResponse.ProductInfo pi = new OrderResponse.ProductInfo();
                pi.setProductId(op.getProduct().getId());
                pi.setName(op.getProduct().getName());
                pi.setPrice(op.getPrice());
                pi.setQuantity(op.getQuantity());
                productInfos.add(pi);
            }
            response.setProducts(productInfos);
            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('SELLER')")
    public ResponseEntity<List<OrderResponse>> placeOrder(@RequestBody List<OrderRequest> requests) {
        List<OrderResponse> responses = orderService.placeOrder(requests);
        return ResponseEntity.ok(responses);
    }
}