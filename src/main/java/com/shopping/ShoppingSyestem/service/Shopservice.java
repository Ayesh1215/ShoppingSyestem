package com.shopping.ShoppingSyestem.service;
import com.shopping.ShoppingSyestem.Application.Product;
import com.shopping.ShoppingSyestem.Application.Shop;
import com.shopping.ShoppingSyestem.JpaRespository.ShopRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class Shopservice {
    @Autowired
    ShopRespository repository;
    public Shop createShopWithProducts(Shop shop, List<Product> products) {
        shop.setProducts(products);
        for (Product product : products) {
            product.setShop(shop);
        }
        return repository.save(shop);
    }
    public List<Shop> getshops() {
        return repository.findAll();
    }
    public Shop getshopsid(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public void addshop(Shop shop){
        repository.save(shop);
    }
    public Shop getShopByOwner(String ownerName) {
        return repository.findByOwner(ownerName);
    }
    public List<Product> getProductsByShopId(Integer shopId) {
        Shop shop = repository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found with ID: " + shopId));
        return shop.getProducts();
    }
}


