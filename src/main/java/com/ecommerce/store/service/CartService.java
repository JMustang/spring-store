package com.ecommerce.store.service;

import com.ecommerce.store.entity.*;
import com.ecommerce.store.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Cart getCartForCurrentUser() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(user)
                            .build();
                    return cartRepository.save(newCart);
                });
    }

    public void addProduct(Long productId) {

        Cart cart = getCartForCurrentUser();
        Product product = productRepository.findById(productId).orElseThrow();

        // Verifica se já existe no carrinho
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + 1);
                cartRepository.save(cart);
                return;
            }
        }

        // Se não existir, cria novo
        CartItem newItem = CartItem.builder()
                .product(product)
                .quantity(1)
                .cart(cart)
                .build();

        cart.getItems().add(newItem);

        cartRepository.save(cart);
    }

    public void removeItem(Long itemId) {
        Cart cart = getCartForCurrentUser();
        cart.getItems().removeIf(item -> item.getId().equals(itemId));
        cartRepository.save(cart);
    }
}