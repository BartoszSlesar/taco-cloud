package com.bard.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditCard extends BaseEntity {

    @NotBlank(message = "Please type name of the card holder")
    @Size(max = 25, message = "Your name is too long")
    private String cardHolder;
//    @CreditCardNumber(message = "Credit Card Number is incorrect, please check and try again")
    private String ccNumber;
    private LocalDate ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Incorrect CCV number")
    private String ccCVV;
}
