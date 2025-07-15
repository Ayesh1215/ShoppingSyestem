package com.shopping.ShoppingSyestem.controller;
import com.shopping.ShoppingSyestem.Application.Product;
import com.shopping.ShoppingSyestem.service.ProductService;
import com.shopping.ShoppingSyestem.JpaRespository.ShopRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private ShopRespository shopRepository;
    @RequestMapping("/product")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Product> getProducts() {
        return service.getProducts();
    }
    @GetMapping("/product/{prodId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Product getProductsById(@PathVariable Integer prodId) {
        return service.getProductsId(prodId);
    }
    @PutMapping("/product/{prodId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Product updateProductsById(@PathVariable Integer prodId) {
        return service.getProductsId(prodId);
    }
    @DeleteMapping("/product/{prodId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Product deleteProductsById(@PathVariable Integer prodId) {
        return service.getProductsId(prodId);
    }
    @GetMapping("/product/name/{name}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Product getName(@PathVariable String name) {
        return service.getName(name);
    }
    @PostMapping("/product")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void addProduct(@RequestBody Product prod) {
        service.addProduct(prod);
    }
}

