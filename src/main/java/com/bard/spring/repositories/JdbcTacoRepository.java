package com.bard.spring.repositories;

import com.bard.spring.domain.Ingredient;
import com.bard.spring.domain.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


@Repository
public class JdbcTacoRepository implements TacoRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        taco.getIngredients().forEach(i -> saveIngredientToTaco(i, tacoId));
        return taco;
    }

    private long saveTacoInfo(Taco taco) {

        taco.setCreatedAt(LocalDateTime.now());

        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory("INSERT INTO Taco (name, created_at) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP);
        pscFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
                Arrays.asList(taco.getName(),
                        Timestamp.valueOf(taco.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbcTemplate.update(
                "INSERT INTO Taco_Ingredients (taco_id, ingredient_id) values (?,?)", tacoId, ingredient.getId()
        );
    }
}
