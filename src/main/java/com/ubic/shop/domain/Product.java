package com.ubic.shop.domain;
import com.ubic.shop.exception.NotEnoughStockException;
import lombok.*;

import javax.persistence.*;
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
    private int stockQuantity;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private ProductCategory productCategory;

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
    public Product(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

}
