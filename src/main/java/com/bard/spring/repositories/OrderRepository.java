package com.bard.spring.repositories;

import com.bard.spring.domain.Order;

public interface OrderRepository {

    Order save(Order order);
}
