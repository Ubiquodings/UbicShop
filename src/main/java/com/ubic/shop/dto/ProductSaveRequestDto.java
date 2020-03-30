package com.ubic.shop.dto;

import com.ubic.shop.domain.Product;
import com.ubic.shop.domain.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {
    private String name;
    private int price;
    private int stockQuantity;
    private List<String> categoryList;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }
}
