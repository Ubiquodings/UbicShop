package com.ubic.shop.service;

import com.ubic.shop.domain.Category;
import com.ubic.shop.dto.CategoryResponseDto;
import com.ubic.shop.dto.CategorySaveRequestDto;
import com.ubic.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
//        List<Category> findCategoryList
        Optional<Category> optionalCategory = categoryRepository.findById(category.getKurlyId());
        if(optionalCategory.isPresent()){ // 존재한다
            throw new IllegalStateException("이미 존재하는 카테고리입니다.");
        }
//        if (result != null) {
//            throw new IllegalStateException("이미 존재하는 카테고리입니다.");
//        }
    }

    public Category getCategoryById(Long categoryId){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()){ // 존재한다
            return optionalCategory.get();
        }
        return null;
    }

}
