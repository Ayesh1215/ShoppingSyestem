package com.shopping.ShoppingSyestem.Repository;

import com.shopping.ShoppingSyestem.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);

}