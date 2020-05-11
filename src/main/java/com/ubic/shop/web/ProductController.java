package com.ubic.shop.web;

import com.ubic.shop.config.LoginUser;
import com.ubic.shop.dto.SessionUser;
import com.ubic.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String list(Model model, @LoginUser SessionUser user){ // 화면 :: 윤진
        model.addAttribute("products", productService.findAllProducts());
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "product-list";
    }

    @GetMapping("/products/{id}")
    public String detail(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        model.addAttribute("product", productService.findById(id));
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "product-detail";
    }

}
