package com.shopping.ShoppingSyestem.Service;

import com.shopping.ShoppingSyestem.Model.Product;
import com.shopping.ShoppingSyestem.Model.Shop;
import com.shopping.ShoppingSyestem.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Shopservice {
    @Autowired
    ShopRepository shopRepository;

    public Shop createShopWithProducts(Shop shop, List<Product> products) {
        shop.setProducts(products);
        for (Product product : products) {
            product.setShop(shop);
        }
        return shopRepository.save(shop);
    }

    public List<Shop> getshops() {
        return shopRepository.findAll();
    }

    public Shop getshopsid(Integer id) {
        return shopRepository.findById(id).orElse(null);
    }

    public Shop addShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public Shop getShopByOwner(String ownerName) {
        return shopRepository.findByOwner(ownerName);
    }

    public List<Product> getProductsByShopId(Integer shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found with ID: " + shopId));
        return shop.getProducts();
    }

    public void addShops(List<Shop> shops) {
        shopRepository.saveAll(shops);
    }

    public void deleteShop(Integer shopid) {
        shopRepository.deleteById(shopid);

    }


    public Shop updateShop(Integer shopid, Shop updatedShop) {
        Shop existing = shopRepository.findById(shopid)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        existing.setName(updatedShop.getName());
        existing.setAddress(updatedShop.getAddress());
        return shopRepository.save(existing);
    }
}


