package com.surya.login_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoginAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginAppApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            repository.save(new User("surya", "password123"));
            System.out.println("Default user 'surya' created for testing.");
        };
    }
}