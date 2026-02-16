package com.ecommerce.store.service;

import com.ecommerce.store.entity.Product;
import com.ecommerce.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("❌ Product not found."));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
