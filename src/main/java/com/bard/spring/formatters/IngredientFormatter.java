package com.bard.spring.formatters;

import com.bard.spring.domain.Ingredient;
import com.bard.spring.domain.Type;
import com.bard.spring.repositories.IngredientRepository;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class IngredientFormatter implements Formatter<Ingredient> {

    private final IngredientRepository ingredientRepository;


    public IngredientFormatter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient parse(String text, Locale locale) throws ParseException {

        return ingredientRepository.findById(text);
    }

    @Override
    public String print(Ingredient object, Locale locale) {
        return object.getName();
    }
}
