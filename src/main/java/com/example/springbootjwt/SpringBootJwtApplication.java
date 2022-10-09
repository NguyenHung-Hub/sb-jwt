package com.example.springbootjwt;

import com.example.springbootjwt.models.Role;
import com.example.springbootjwt.models.User;
import com.example.springbootjwt.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringBootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "Nguyen Hung", "hung", "123456", new ArrayList<>()));
            userService.saveUser(new User(null, "Nguyen Hung 2", "hung2", "123456", new ArrayList<>()));
            userService.saveUser(new User(null, "Nguyen Hung 3", "hung3", "123456", new ArrayList<>()));


            userService.addRoleToUser("hung", "ROLE_USER");
            userService.addRoleToUser("hung2", "ROLE_USER");
            userService.addRoleToUser("hung2", "ROLE_MANAGER");
            userService.addRoleToUser("hung3", "ROLE_ADMIN");
        };
    }
}
