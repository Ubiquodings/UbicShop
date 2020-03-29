package com.ubic.shop.dto;

import com.ubic.shop.domain.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    public ProductResponseDto(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.stockQuantity = entity.getStockQuantity();
    }
}
