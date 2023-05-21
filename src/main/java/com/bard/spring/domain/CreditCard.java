package com.bard.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    private Long id;
    @NotBlank(message = "Please type name of the card holder")
    @Size(max = 25, message = "Your name is too long")
    private String cardHolder;
//    @CreditCardNumber(message = "Credit Card Number is incorrect, please check and try again")
    private String ccNumber;

//    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([1-9][0-9])$", message = "Value should be in MM/YY format")
    private LocalDate ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Incorrect CCV number")
    private String ccCVV;
}
