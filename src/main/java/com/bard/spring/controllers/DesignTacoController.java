package com.bard.spring.controllers;

import com.bard.spring.domain.Ingredient;
import com.bard.spring.domain.Taco;
import com.bard.spring.domain.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @ModelAttribute("ingredients")
    public List<Ingredient> loadIngredients(Map<String, Object> model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "wheat", Type.WRAP),
                new Ingredient("COTO", "corn", Type.WRAP),
                new Ingredient("GRBF", "ground beef", Type.PROTEIN),
                new Ingredient("CARN", "pieces of meat", Type.PROTEIN),
                new Ingredient("TMTO", "sliced tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "lettuce", Type.VEGGIES),
                new Ingredient("CHED", "cheddar", Type.CHEESE),
                new Ingredient("JACK", "monterey jack", Type.CHEESE),
                new Ingredient("SLSA", "spicy tomato sauce", Type.SAUCE),
                new Ingredient("SRCR", "cream", Type.SAUCE)
        );
        Arrays.stream(Type.values())
                .forEach(type -> model.put(type.name().toLowerCase(), filterByType(ingredients, type)));
        return ingredients;
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping
    public String showDesignForm(Model model) {

        model.addAttribute("design", new Taco());
        return "design";

    }



    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing taco: " + design);
        return "redirect:/orders/current";
    }


}
