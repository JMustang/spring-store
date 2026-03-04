package com.ecommerce.store.controller;

import com.ecommerce.store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCartForCurrentUser());
        return "cart/view";
    }

    @PostMapping("/add/{productId}")
    public String addProduct(@PathVariable Long productId) {
        cartService.addProduct(productId);
        return "redirect:/products";
    }

    @GetMapping("/remove/{itemId}")
    public String remove(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
        return "redirect:/cart";
    }
}