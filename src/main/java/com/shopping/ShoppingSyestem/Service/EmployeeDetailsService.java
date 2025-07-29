package com.shopping.ShoppingSyestem.Service;

import com.shopping.ShoppingSyestem.Model.Employee;
import com.shopping.ShoppingSyestem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        Set<GrantedAuthority> authorities = Set.of(
                new SimpleGrantedAuthority(employee.getRoles().getName())
        );
        return new org.springframework.security.core.userdetails.User(
                username,
                employee.getPassword(),
                authorities
        );

    }
}