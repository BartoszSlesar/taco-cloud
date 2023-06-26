package com.bard.spring.controllers;

import com.bard.spring.domain.Ingredient;
import com.bard.spring.domain.Order;
import com.bard.spring.domain.Taco;
import com.bard.spring.domain.Type;
import com.bard.spring.repositories.crud.IngredientRepository;
import com.bard.spring.repositories.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    private final EntityLinks entityLinks;

    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository, EntityLinks entityLinks) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.entityLinks = entityLinks;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> tacoById = tacoRepository.findById(id);
        return tacoById
                .map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }

    @ModelAttribute("ingredients")
    public List<Ingredient> loadIngredients(Map<String, Object> model) {
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "wheat", Type.WRAP),
//                new Ingredient("COTO", "corn", Type.WRAP),
//                new Ingredient("GRBF", "ground beef", Type.PROTEIN),
//                new Ingredient("CARN", "pieces of meat", Type.PROTEIN),
//                new Ingredient("TMTO", "sliced tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "cheddar", Type.CHEESE),
//                new Ingredient("JACK", "monterey jack", Type.CHEESE),
//                new Ingredient("SLSA", "spicy tomato sauce", Type.SAUCE),
//                new Ingredient("SRCR", "cream", Type.SAUCE)
//        );
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
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
    public String processDesign(@Valid @ModelAttribute("design")
                                Taco design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
        log.info("Processing taco: " + design);
        return "redirect:/orders/current";
    }


}
