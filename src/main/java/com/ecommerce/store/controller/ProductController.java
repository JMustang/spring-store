package com.ecommerce.store.controller;


import com.ecommerce.store.entity.Product;
import com.ecommerce.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", service.findAll());
        return "products/list";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/form";
    }

    @PostMapping
    public String save(@ModelAttribute Product product) {
        service.save(product);
        return "redirect:/products";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("product", service.findById(id));
        return "products/form";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/products";
    }
}


