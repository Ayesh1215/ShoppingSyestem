package com.shopping.ShoppingSyestem.Service;

import com.shopping.ShoppingSyestem.Model.Product;
import com.shopping.ShoppingSyestem.Model.ProductDTO;
import com.shopping.ShoppingSyestem.Model.Shop;
import com.shopping.ShoppingSyestem.Repository.ProductRepository;
import com.shopping.ShoppingSyestem.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;
    @Autowired
    private ShopRepository shopRepository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductsId(Integer prodId) {
        return repository.findById(prodId).orElse(null);
    }

    public Product getName(String name) {
        return repository.findByName(name);
    }

    public Product updateProduct(Integer prodId, Product updatedProduct) {
        return updatedProduct;
    }

    public void deleteProductById(Integer prodId) {
    }

    public List<ProductDTO> createProducts(List<ProductDTO> productDTOs, Integer shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        List<Product> products = new ArrayList<>();
        for (ProductDTO dto : productDTOs) {
            Product product = new Product();
            product.setName(dto.getName());
            product.setPrice((int) dto.getPrice());
            product.setQuantity(dto.getQuantity());
            product.setShop(shop);
            products.add(product);
        }
        List<Product> savedProducts = repository.saveAll(products);
        return savedProducts.stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setQuantity(product.getQuantity());
            return dto;
        }).toList();
    }
}
