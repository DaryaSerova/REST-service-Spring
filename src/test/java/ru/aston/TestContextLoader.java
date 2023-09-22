package ru.aston;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.aston.config.*;
import ru.aston.repository.OrderRepository;
import ru.aston.repository.PermissionRepository;
import ru.aston.repository.UserRepository;
import ru.aston.service.order.OrderService;
import ru.aston.service.permission.PermissionService;
import ru.aston.service.user.UserService;

@ExtendWith({SpringExtension.class})
@WebAppConfiguration
@ContextConfiguration(classes = {ServiceConfig.class, TestDataSourceConfig.class,
        DataBasePropertyConfig.class, HibernateConfig.class,
        CommonTestcontainer.class, MvcConfig.class, JacksonConfiguration.class})
public class TestContextLoader {
    @Autowired
    protected PermissionRepository permissionRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected PermissionService permissionService;

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @AfterEach
    public void clear() {
        userRepository.deleteAll();
        orderRepository.deleteAll();
        permissionRepository.deleteAll();
    }
}
