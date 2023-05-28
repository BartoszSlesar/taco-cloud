package com.bard.spring.domain;

import lombok.*;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Taco extends BaseEntity {

    private LocalDateTime createdAt;
    @NotNull
    @Size(min = 5, message = "Name must contain at least five characters")
    private String name;

    @NotEmpty(message = "You need to select at least one ingredient")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "taco_ingredient",
            joinColumns = @JoinColumn(name = "taco_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToMany(mappedBy = "tacos")
    private List<Order> orders = new ArrayList<>();

    @PrePersist
    public void createdAt() {
        this.createdAt = LocalDateTime.now();
    }
}
