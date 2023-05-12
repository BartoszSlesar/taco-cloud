package com.bard.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private CreditCard creditCard;

}
