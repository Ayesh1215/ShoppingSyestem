package com.shopping.ShoppingSyestem.JpaRespository;
import com.shopping.ShoppingSyestem.Application.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface ShopRespository extends JpaRepository<Shop, Integer> {

    Optional<Shop> findById(Integer id);
    Shop findByOwner(String owner);
}
