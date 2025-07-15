package com.shopping.ShoppingSyestem.controller;
import com.shopping.ShoppingSyestem.Application.Product;
import com.shopping.ShoppingSyestem.Application.Shop;
import com.shopping.ShoppingSyestem.JpaRespository.ShopRespository;
import com.shopping.ShoppingSyestem.service.Shopservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class ShopController {
    @Autowired
    Shopservice service;
    @Autowired
    private ShopRespository shopRepository;
    @GetMapping("/shop")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Shop> getshops(){
        return service.getshops();
    }
    @GetMapping("/shop/{shopid}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Shop getshopid(@PathVariable Integer shopid){
        return service.getshopsid(shopid);
    }
    @DeleteMapping("/shop/{shopid}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Shop deleteShopById(@PathVariable Integer shopid){
        return service.getshopsid(shopid);
    }
    @PutMapping("/shop/{shopid}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Shop updateShopById(@PathVariable Integer shopid){
        return service.getshopsid(shopid);
    }
    @GetMapping("/shop/{shopid}/product")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Product> getProductsByShopId(@PathVariable Integer shopid) {
        return service.getProductsByShopId(shopid);
    }
    @PostMapping("/shop")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void addshop(@RequestBody Shop sho){
        service.addshop(sho);
    }
}
