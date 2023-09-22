package ru.aston.config;


import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import ru.aston.CommonTestcontainer;

import javax.sql.DataSource;


@ActiveProfiles({"test"})
@Configuration
public class TestDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl(CommonTestcontainer.getPostgreSQLContainer().getJdbcUrl());
        ds.setDatabaseName(CommonTestcontainer.getPostgreSQLContainer().getDatabaseName());
        ds.setUser(CommonTestcontainer.getPostgreSQLContainer().getUsername());
        ds.setPassword(CommonTestcontainer.getPostgreSQLContainer().getPassword());
        return ds;
    }
}
