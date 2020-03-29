package com.ubic.shop.web;

import com.ubic.shop.config.LoginUser;
import com.ubic.shop.dto.ProductResponseDto;
import com.ubic.shop.dto.ProductSaveRequestDto;
import com.ubic.shop.dto.SessionUser;
import com.ubic.shop.service.OrderService;
import com.ubic.shop.service.ProductService;
import com.ubic.shop.service.ShopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RestAPIController {

    private final ProductService productService;
    private final ShopListService shopListService;
    private final OrderService orderService;

    @PostMapping("/products/new")
    public ProductResponseDto save(@RequestBody ProductSaveRequestDto requestDto){
        return productService.saveProduct(requestDto.toEntity());
    }

    @PostMapping("/carts/new/{id}") // TODO  restful 하진 않다
    public String cart(@PathVariable(name = "id") Long productId, Model model, @LoginUser SessionUser user){

        // TODO 장바구니 카프카 로직 추가 !!

        if(user != null){
            model.addAttribute("userName", user.getName());
            // 장바구니 저장
            shopListService.shopList(user.getId(), productId, 1);
        }
        return "";
    }

    @PostMapping("/orders/new/{id}") // TODO  restful 하진 않다
    public String order(@PathVariable(name = "id") Long productId, Model model, @LoginUser SessionUser user){

        // TODO 주문 카프카 로직 추가 !!

        if(user != null){
            model.addAttribute("userName", user.getName());
            // 장바구니 저장
            orderService.order(user.getId(), productId, 1);
        }
        return "";
    }


}
