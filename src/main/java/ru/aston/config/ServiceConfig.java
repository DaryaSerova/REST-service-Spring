package ru.aston.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aston.service.order.OrderService;
import ru.aston.service.order.OrderServiceImpl;
import ru.aston.service.permission.PermissionService;
import ru.aston.service.permission.PermissionServiceImpl;
import ru.aston.service.user.UserService;
import ru.aston.service.user.UserServiceImpl;

@Configuration
public class ServiceConfig {

  @Bean
  public UserService userService() {
    return new UserServiceImpl();
  }

  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl();
  }

  @Bean
  public PermissionService permissionService() {
    return new PermissionServiceImpl();
  }

}
