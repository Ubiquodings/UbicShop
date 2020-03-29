package com.ubic.shop.service;

import com.ubic.shop.domain.Product;
import com.ubic.shop.dto.ProductResponseDto;
import com.ubic.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long productId) {
        return productRepository.findOne(productId);
    }
}
