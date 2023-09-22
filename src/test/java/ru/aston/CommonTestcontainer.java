package ru.aston;

import org.junit.jupiter.api.AfterAll;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class CommonTestcontainer {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:13");
        postgreSQLContainer.start();
    }


    @AfterAll
    static void destroy() {
        postgreSQLContainer.stop();
    }


    public static PostgreSQLContainer<?> getPostgreSQLContainer() {
        return postgreSQLContainer;
    }

    public static void setPostgreSQLContainer(PostgreSQLContainer<?> postgreSQLContainer) {
        CommonTestcontainer.postgreSQLContainer = postgreSQLContainer;
    }
}
