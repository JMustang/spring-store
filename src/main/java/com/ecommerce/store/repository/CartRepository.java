package com.ecommerce.store.repository;

import com.ecommerce.store.entity.Cart;
import com.ecommerce.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
