package com.ubic.shop.domain;

import lombok.*;
//import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.Id;
@NoArgsConstructor
@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @Builder
    public Category(String name) {
        this.name = name;
    }

}
