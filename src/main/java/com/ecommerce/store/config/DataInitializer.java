package com.ecommerce.store.config;

import com.ecommerce.store.entity.User;
import com.ecommerce.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.count() == 0) {

            userRepository.save(User.builder()
                    .name("Admin")
                    .email("admin@email.com")
                    .password(passwordEncoder.encode("123456"))
                    .role("ROLE_ADMIN")
                    .build());

            userRepository.save(User.builder()
                    .name("User")
                    .email("user@email.com")
                    .password(passwordEncoder.encode("123456"))
                    .role("ROLE_USER")
                    .build());

            System.out.println("Usuários iniciais criados!");
        }
    }
}