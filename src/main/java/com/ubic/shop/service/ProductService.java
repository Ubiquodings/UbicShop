package com.ubic.shop.service;

import com.ubic.shop.domain.Category;
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
        validateDuplicateProduct(product); //중복 상품 검증
        // 여기까지 dto 끌고 들어와서, category list 처리 ?
        ProductResponseDto productResponseDto = productRepository.save(product);
        productCategoryService.saveCategoryList(productDto.getCategoryList(), product);
        return productResponseDto;
    }

    private void validateDuplicateProduct(Product product) {
        List<Product> findProductList = productRepository.findByName(product.getName());
        if (!findProductList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 상품입니다.");
        }
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long productId) {
        return productRepository.findOne(productId);
    }
}
