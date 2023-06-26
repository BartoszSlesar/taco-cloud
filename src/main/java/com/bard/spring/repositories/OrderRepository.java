package com.bard.spring.repositories;

import com.bard.spring.domain.Ingredient;
import com.bard.spring.domain.Order;
import com.bard.spring.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByZip(String deliveryZip);

    List<Order> readOrdersByZipAndPlacedAtBetween(String deliveryZip, LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByDeliveryToAndCityAllIgnoreCase(String deliveryTo, String deliveryCity);

    List<Order> findByCityOrderByDeliveryTo(String city);

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.city='Katowice'")
    List<Order> readOrdersDeliveredInKatowice();

}
