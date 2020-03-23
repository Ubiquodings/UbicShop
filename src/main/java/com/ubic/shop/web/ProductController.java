package com.ubic.shop.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ProductController {

    @GetMapping("/products")
    public String list(){
        return "product-list";
    }

    @GetMapping("/products/{id}")
    public String detail(@PathVariable int id){
        return "product-detail";
    }


}
