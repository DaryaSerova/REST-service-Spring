package ru.aston;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.aston.config.*;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ServiceConfig.class, DataSourceConfig.class,
                        DataBasePropertyConfig.class, HibernateConfig.class, JacksonConfiguration.class);

    }
}