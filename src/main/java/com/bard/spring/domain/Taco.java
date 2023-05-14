package com.bard.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taco {
    @NotNull
    @Size(min=5, message = "Name must contain at least five characters")
    private String name;

    @NotEmpty(message = "You need to select at least one ingredient")
    private List<String> ingredients;
}
