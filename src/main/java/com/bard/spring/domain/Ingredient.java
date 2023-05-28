package com.bard.spring.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(exclude = "tacos")
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
