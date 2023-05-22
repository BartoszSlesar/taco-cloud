package com.bard.spring.repositories;

import com.bard.spring.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {


}
