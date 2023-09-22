package ru.aston.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("!test")
public class DataSourceConfig {

    @Autowired
    private DataBasePropertyConfig dataBasePropertyConfig;

    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName(dataBasePropertyConfig.getServerName());
        ds.setDatabaseName(dataBasePropertyConfig.getDatabaseName());
        ds.setUser(dataBasePropertyConfig.getUser());
        ds.setPassword(dataBasePropertyConfig.getPassword());
        return ds;
    }


}
