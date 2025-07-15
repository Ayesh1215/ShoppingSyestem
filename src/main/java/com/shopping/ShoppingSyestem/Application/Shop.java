package com.shopping.ShoppingSyestem.Application;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "Shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String owner;
    private String address;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();
    public List<Product> getProducts() {
        return products;
    }
    public Shop() {
    }
    public Shop(Integer id, String name, String owner, String address) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.address = address;
    }
    public Integer getId() {

        return id;
    }
    public String getOwner() {

        return owner;
    }
    public String getName() {

        return name;
    }
    public String getAddress() {

        return address;
    }
    public void setAddress(String address) {

        this.address = address;
    }
    public void setId(Integer id) {

        this.id = id;
    }
    public void setName(String name) {

        this.name = name;
    }
    public void setOwner(String owner) {

        this.owner = owner;
    }
    @Override
    public String toString() {
        return "Shop{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void setProducts(List<Product> products) {
        this.products = products;

    }
    public  List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

}
