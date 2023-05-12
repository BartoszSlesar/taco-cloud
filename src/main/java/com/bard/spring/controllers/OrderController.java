package com.bard.spring.controllers;


import com.bard.spring.domain.CreditCard;
import com.bard.spring.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(Model model) {
        Order order = new Order();
        order.setCreditCard(new CreditCard());
        model.addAttribute("order", order);
        return "orderForm";
    }
}
