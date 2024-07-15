package com.example.demo;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public interface TestContainers {

    @ServiceConnection
    @Container
    PostgreSQLContainer<?> postgres = createPostgreSQLContainer();

    static PostgreSQLContainer<?> createPostgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres")
                .withReuse(true)
                .withDatabaseName("demo_db")
                .withUsername("demo")
                .withPassword("demo");
    }

}
