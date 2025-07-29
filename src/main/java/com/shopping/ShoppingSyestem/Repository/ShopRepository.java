package com.shopping.ShoppingSyestem.Repository;

import com.shopping.ShoppingSyestem.Model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {

    Optional<Shop> findById(Integer id);

    Shop findByOwner(String owner);
}
