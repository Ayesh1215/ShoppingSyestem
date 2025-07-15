package com.shopping.ShoppingSyestem.JpaRespository;
import com.shopping.ShoppingSyestem.Application.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRespository extends JpaRepository<Employee,Long> {
    Employee findByUsername(String username);
}
