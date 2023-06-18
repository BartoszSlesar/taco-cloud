package com.bard.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "taco_order")
public class Order extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime placedAt;
    @NotBlank(message = "Name and second name are required")
    private String deliveryTo;
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
    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "taco_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "taco_id"))
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addDesign(Taco taco) {
        if (taco != null) {
            this.tacos.add(taco);
        }

    }

    @PrePersist
    public void placedAt() {
        this.placedAt = LocalDateTime.now();
    }

}
