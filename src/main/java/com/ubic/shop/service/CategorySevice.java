package com.ubic.shop.service;

import com.ubic.shop.domain.Category;
import com.ubic.shop.dto.CategoryResponseDto;
import com.ubic.shop.dto.CategorySaveRequestDto;
import com.ubic.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategorySevice {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category saveCategory(CategorySaveRequestDto requestDto) {
        return categoryRepository.save(requestDto.toEntity());
    }

}
