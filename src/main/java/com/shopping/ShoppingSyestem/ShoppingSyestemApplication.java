package com.shopping.ShoppingSyestem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableWebSecurity

public class ShoppingSyestemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingSyestemApplication.class, args);
	}

}
