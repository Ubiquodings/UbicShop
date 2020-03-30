package com.ubic.shop.repository;

import com.ubic.shop.domain.Category;
import com.ubic.shop.domain.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public Category save(Category category) {
        em.persist(category);
        return category;
    }

    public Category findByName(String name) {
        return em.createQuery("select m from Category m where m.name = :name", Category.class)
                .setParameter("name", name)
                .getResultList().get(0);
    }
}
