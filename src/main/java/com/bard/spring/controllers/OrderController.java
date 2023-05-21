package com.bard.spring.controllers;


import com.bard.spring.domain.CreditCard;
import com.bard.spring.domain.Order;
import com.bard.spring.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @InitBinder("order")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                YearMonth month = YearMonth.from(LocalDate.parse("01/" + text, formatter));
                setValue(month.atEndOfMonth());
            }
        });
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
//        Order order = new Order();
//        order.setCreditCard(new CreditCard());
//        model.addAttribute("order", order);
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Validated @ModelAttribute Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        orderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Order was placed " + order);
        return "redirect:/";
    }
}
