package com.bard.spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class SimpleJdbcConfig {


    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public SimpleJdbcInsert orderInsert() {
        return new SimpleJdbcInsert(dataSource()).withTableName("Taco_Order").usingGeneratedKeyColumns("id");
    }

    @Bean
    public SimpleJdbcInsert orderTacoInsert() {
        return new SimpleJdbcInsert(dataSource()).withTableName("Taco_Orders_Tacos");
    }

    @Bean
    public SimpleJdbcInsert creditCardInsert() {
        return new SimpleJdbcInsert(dataSource()).withTableName("Credit_Card").usingGeneratedKeyColumns("id");
    }
}
