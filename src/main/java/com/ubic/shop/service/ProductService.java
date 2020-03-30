package com.ubic.shop.service;

import com.ubic.shop.domain.Product;
import com.ubic.shop.domain.ProductCategory;
import com.ubic.shop.dto.ProductResponseDto;
import com.ubic.shop.dto.ProductSaveRequestDto;
import com.ubic.shop.repository.ProductCategoryRepository;
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
    private final ProductCategoryService productCategoryService;
//    private final ProductCategoryRepository productCategoryRepository;

    @Transactional
    public ProductResponseDto saveProduct(ProductSaveRequestDto productDto) {
        Product product = productDto.toEntity();
        // 여기까지 dto 끌고 들어와서, category list 처리 ?
        productCategoryService.saveCategoryList(productDto.getCategoryList(), product);
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long productId) {
        return productRepository.findOne(productId);
    }
}
