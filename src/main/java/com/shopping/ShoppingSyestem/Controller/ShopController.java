package com.shopping.ShoppingSyestem.Controller;

import com.shopping.ShoppingSyestem.Model.Product;
import com.shopping.ShoppingSyestem.Model.Shop;
import com.shopping.ShoppingSyestem.Repository.ShopRepository;
import com.shopping.ShoppingSyestem.Service.Shopservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopController {
    @Autowired
    Shopservice service;
    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/shop")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('SELLER')")
    public List<Shop> getshops() {
        return service.getshops();
    }

    @GetMapping("/shop/{shopid}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Shop getshopid(@PathVariable Integer shopid) {
        return service.getshopsid(shopid);
    }

    @DeleteMapping("/shop/{shopid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteShopById(@PathVariable Integer shopid) {
        service.deleteShop(shopid);
        return "Shop deleted successfully";
    }

    @PutMapping("/shop/{shopid}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Shop updateShopById(@PathVariable Integer shopid, @RequestBody Shop updatedShop) {
        return service.updateShop(shopid, updatedShop);
    }

    @GetMapping("/shop/{shopid}/product")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Product> getProductsByShopId(@PathVariable Integer shopid) {
        return service.getProductsByShopId(shopid);
    }

    @PostMapping("/shop")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SELLER')")
    public Shop addShop(@RequestBody Shop shop) {
        return service.addShop(shop);
    }
}
