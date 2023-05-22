package com.bard.spring.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Entity
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    @Enumerated(value = EnumType.STRING)
    private final Type type;

    @ManyToMany(mappedBy = "ingredients")
    private List<Taco> tacos = new ArrayList<>();

}
