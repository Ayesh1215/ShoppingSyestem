package com.shopping.ShoppingSyestem.Repository;

import com.shopping.ShoppingSyestem.Model.Employee;
import com.shopping.ShoppingSyestem.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByEmployee(Employee employee);
}