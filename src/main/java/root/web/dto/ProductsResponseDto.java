package root.web.dto;

import lombok.Getter;
import root.domain.Products;

@Getter
public class ProductsResponseDto {

    private Long id;

    private String productName;
    private Long productPrice;
    private String detailPageUrl;

    public ProductsResponseDto(Products entity){
        this.id=entity.getId();
        this.productName=entity.getProductName();
        this.productPrice=entity.getProductPrice();
        this.detailPageUrl=entity.getDetailPageUrl();
    }
}
