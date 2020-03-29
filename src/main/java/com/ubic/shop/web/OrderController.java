package com.ubic.shop.web;

import com.ubic.shop.config.LoginUser;
import com.ubic.shop.dto.SessionUser;
import com.ubic.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public String list(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("shopLists", // List<Order>
                    orderService.findAllOrders(user.getId()));
        }
        return "order-list";
    }

    @GetMapping("/orders/{id}")
    public String detail(@PathVariable int id, Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "order-detail";
    }

}
