package com.ecommerce.store.service;

import com.ecommerce.store.entity.User;
import com.ecommerce.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("🔍 Tentando autenticar usuário: {}", email);

        User user = repository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("❌ Usuário não encontrado: {}", email);
                    return new UsernameNotFoundException("❌ User not found");
                });

        String role = user.getRole();
        // Assegurar que o role tem prefixo ROLE_
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        log.info("✅ Usuário encontrado: {}, role: {}", email, role);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(role)));
    }
}
