package com.shopping.ShoppingSyestem.JpaRespository;
import com.shopping.ShoppingSyestem.Application.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRespository extends JpaRepository<Product, Integer> {

    Product findByName(String name);
    Optional<Product> findById(Integer id);
}
