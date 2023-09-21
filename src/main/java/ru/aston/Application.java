package ru.aston;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.aston.config.DataBasePropertyConfig;
import ru.aston.config.DataSourceConfig;
import ru.aston.config.RepositoryConfig;
import ru.aston.config.ServiceConfig;
import ru.aston.repository.OrderRepository;
import ru.aston.repository.PermissionRepository;
import ru.aston.repository.UserRepository;

public class Application {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ServiceConfig.class, DataSourceConfig.class,
            DataBasePropertyConfig.class, RepositoryConfig.class);

    context.getBean(UserRepository.class);
    context.getBean(OrderRepository.class);
    context.getBean(PermissionRepository.class);

  }
}
