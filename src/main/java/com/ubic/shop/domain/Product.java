package com.ubic.shop.domain;
import com.ubic.shop.exception.NotEnoughStockException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity=50;

    private String description;
    private String imgUrl;

//    @OneToMany
//    @JoinColumn(name = "order_id")
//    private List<ProductCategory> productCategories = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    //==연관관계 메서드==//
    public void addProductCategory(ProductCategory productCategory) {
//        productCategories.add(productCategory);
//        productCategory.setProduct(this);
    }

    //==비즈니스 로직==//
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    // Entity 함부로 만들지 말라고 했다 ?
    @Builder
    public Product(String name, int price, int stockQuantity, String description, /*Long categoryId,*/ String imgUrl,
                   Category category) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
//        this.category.setId(categoryId); // 초기화 전 값 할당했다고 오류났다
        this.imgUrl = imgUrl;
    }

    //==생성 메서드==//
//    public static Product createProduct(Category category){
//        Product product = new Product();
//    }

}
