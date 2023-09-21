package ru.aston.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "{ru.aston.repository}")
public class RepositoryConfig {

}
