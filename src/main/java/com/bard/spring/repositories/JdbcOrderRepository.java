package com.bard.spring.repositories;


import com.bard.spring.domain.CreditCard;
import com.bard.spring.domain.Order;
import com.bard.spring.domain.Taco;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInsert;
    private SimpleJdbcInsert orderTacoInsert;

    private SimpleJdbcInsert creditCardInsert;
    private ObjectMapper objectMapper;

    public JdbcOrderRepository(SimpleJdbcInsert orderInsert,
                               SimpleJdbcInsert orderTacoInsert,
                               SimpleJdbcInsert creditCardInsert,
                               ObjectMapper objectMapper) {
        this.orderInsert = orderInsert;
        this.orderTacoInsert = orderTacoInsert;
        this.creditCardInsert = creditCardInsert;
        this.objectMapper = objectMapper;
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(LocalDateTime.now());
        long creditCardId = saveCreditCardDetails(order.getCreditCard());
        order.getCreditCard().setId(creditCardId);
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        order.getTacos().forEach(taco -> saveTacoToOrder(taco, orderId));
        return order;
    }

    private long saveCreditCardDetails(CreditCard creditCard) {
        Map<String, Object> values = new HashMap<>();
        values.put("card_holder", creditCard.getCardHolder());
        values.put("cc_number", creditCard.getCcNumber());
        values.put("cc_expiration", creditCard.getCcExpiration());
        values.put("cc_cvv", creditCard.getCcCVV());
        return creditCardInsert.executeAndReturnKey(values).longValue();
    }

    private long saveOrderDetails(Order order) {
        Map<String, Object> values = new HashMap<>();
        values.put("delivery_name", order.getName());
        values.put("delivery_street", order.getStreet());
        values.put("delivery_city", order.getCity());
        values.put("delivery_state", order.getState());
        values.put("delivery_zip", order.getState());
        values.put("credit_card", order.getCreditCard().getId());
        values.put("placed_at", order.getPlacedAt());
        return orderInsert.executeAndReturnKey(values).longValue();
    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("taco_order_id", orderId);
        values.put("taco_id", taco.getId());
        orderTacoInsert.execute(values);
    }


}
