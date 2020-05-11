package com.ubic.shop.service;

import com.ubic.shop.domain.Category;
import com.ubic.shop.dto.CategoryResponseDto;
import com.ubic.shop.dto.CategorySaveRequestDto;
import com.ubic.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategorySevice {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category saveCategory(CategorySaveRequestDto requestDto) {
        Category category = requestDto.toEntity();
        validateDuplicateCategory(category); //중복 카테고리 검증
        return categoryRepository.save(category);
    }

    private void validateDuplicateCategory(Category category) {
        List<Category> findCategoryList = categoryRepository.findByName(category.getName());
        if (!findCategoryList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 카테고리입니다.");
        }
    }

}
