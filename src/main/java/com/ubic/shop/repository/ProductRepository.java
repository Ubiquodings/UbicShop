package com.ubic.shop.repository;

import com.ubic.shop.domain.Product;
import com.ubic.shop.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public ProductResponseDto save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }/* else { // 변경 감지로 사용하라고 했다
            em.merge(product);
        }*/
        return new ProductResponseDto(product);
    }

    public Product findOne(Long id) {
        return em.find(Product.class, id);
    }
    
    public List<Product> findAll() {
        return em.createQuery("select i from Product i",Product.class).getResultList();
    }
}
