package com.bard.spring.domain;


import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private final String userName;
    private final String password;
    private final String fullName;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                userName,
                passwordEncoder.encode(password),
                fullName,
                street,
                city,
                state,
                zip,
                phoneNumber);
    }
}
