package com.bard.spring.controllers;


import com.bard.spring.domain.CreditCard;
import com.bard.spring.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

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
        Order order = new Order();
        order.setCreditCard(new CreditCard());
        model.addAttribute("order", order);
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Validated @ModelAttribute Order order, Errors errors) {
        if(errors.hasErrors()){
            return "orderForm";
        }
        log.info("Order was placed " + order);
        return "redirect:/";
    }
}
