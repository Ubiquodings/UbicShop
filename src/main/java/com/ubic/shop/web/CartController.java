package com.ubic.shop.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class CartController {

    @GetMapping("/carts")
    public String list(){
        return "cart-list";
    }

    @GetMapping("/carts/{id}")
    public String detail(@PathVariable int id){
        return "cart-detail";
    }

}
