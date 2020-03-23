package com.ubic.shop.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class OrderController {

    @GetMapping("/orders")
    public String list(){
        return "order-list";
    }

    @GetMapping("/orders/{id}")
    public String detail(@PathVariable int id){
        return "order-detail";
    }

}
