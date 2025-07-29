package com.shopping.ShoppingSyestem.Controller;

import com.shopping.ShoppingSyestem.Model.Employee;
import com.shopping.ShoppingSyestem.Model.LoginRequest;
import com.shopping.ShoppingSyestem.Model.Roles;
import com.shopping.ShoppingSyestem.Model.RegisterRequest;
import com.shopping.ShoppingSyestem.Repository.EmployeeRepository;
import com.shopping.ShoppingSyestem.Repository.RolesRepository;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RolesRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody RegisterRequest request) {
        if (employeeRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setUsername(request.getUsername());
        employee.setEmail(request.getEmail());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));

        Roles selectedRole = roleRepository.findById(request.getRoles().getId())
                .orElse(null);

        if (selectedRole == null) {
            return ResponseEntity.badRequest().body("Invalid role ID: " + request.getRoles().getId());
        }


        employee.setRoles(selectedRole);

        employeeRepository.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body("Employee registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Login successful");

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

}
