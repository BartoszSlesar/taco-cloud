package com.bard.spring.repositories;

import com.bard.spring.domain.Ingredient;
import com.bard.spring.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
