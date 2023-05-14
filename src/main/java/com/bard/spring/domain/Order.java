package com.bard.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @NotBlank(message = "Name and second name are required")
    private String name;
    @NotBlank(message = "Street is required to process your order")
    private String street;
    @NotBlank(message = "City is required to process your order")
    private String city;
    @NotBlank(message = "State is required to process your order")
    private String state;
    @NotBlank(message = "Zip code is required to process your order")
    private String zip;

    @Valid
    @NotNull(message = "Please input your payment details")
    private CreditCard creditCard;

}
