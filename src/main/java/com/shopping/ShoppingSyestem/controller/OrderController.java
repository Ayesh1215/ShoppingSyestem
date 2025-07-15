package com.shopping.ShoppingSyestem.controller;
import com.shopping.ShoppingSyestem.Application.Order;
import com.shopping.ShoppingSyestem.Application.Product;
import com.shopping.ShoppingSyestem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Order")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/Order/my")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Order> getMyOrder() {
        Order myOrder = orderService.getMyOrder();
        return new ResponseEntity<>(myOrder, HttpStatus.OK);
    }
    @PostMapping("/Order")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> addProduct(@RequestBody Product prod) {
        orderService.addProduct(prod);
        return new ResponseEntity<>("Product add to order: ", HttpStatus.CREATED);
    }

}
