package com.bard.spring.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Order {

    private Long id;
    private LocalDateTime placedAt;
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

    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco taco) {
        if (taco != null) {
            this.tacos.add(taco);
        }

    }

}
