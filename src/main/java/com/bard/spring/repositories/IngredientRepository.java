package com.bard.spring.repositories;

import com.bard.spring.domain.Ingredient;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, String> {


}
