package com.shopping.ShoppingSyestem.Controller;
import com.shopping.ShoppingSyestem.Model.Product;
import com.shopping.ShoppingSyestem.Service.ProductService;
import com.shopping.ShoppingSyestem.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.shopping.ShoppingSyestem.Model.ProductDTO;


@RestController
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private ShopRepository shopRepository;

    @RequestMapping("/product")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('SELLER')")
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/product/{prodId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('SELLER')")
    public Product getProductsById(@PathVariable Integer prodId) {
        return service.getProductsId(prodId);
    }

    @PutMapping("/product/{prodId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('SELLER')")
    public Product updateProductById(@PathVariable Integer prodId, @RequestBody Product updatedProduct) {
        return service.updateProduct(prodId, updatedProduct);}

    @DeleteMapping("/product/{prodId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('SELLER')")
    public Product deleteProductsById(@PathVariable Integer prodId) {
        service.deleteProductById(prodId);
        return null;}

    @GetMapping("/product/name/{name}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('SELLER')")
    public Product getName(@PathVariable String name) {
        return service.getName(name);
    }

    @PostMapping("/product/{shopId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('SELLER')")
    public List<ProductDTO> createProducts(@PathVariable Integer shopId, @RequestBody List<ProductDTO> productDTOs) {
        return service.createProducts(productDTOs, shopId); // ✅ pass DTOs and return DTOs
    }
}

