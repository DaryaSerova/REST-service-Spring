package ru.aston.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("ru.aston")
@EnableJpaRepositories(basePackages = "ru.aston.repository")
public class ServiceConfig {


}
