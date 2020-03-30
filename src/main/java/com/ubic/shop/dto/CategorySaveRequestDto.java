package com.ubic.shop.dto;

import com.ubic.shop.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategorySaveRequestDto {
    private String name;

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .build();
    }

}
