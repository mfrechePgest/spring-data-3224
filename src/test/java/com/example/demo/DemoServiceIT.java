package com.example.demo;

import com.example.demo.domain.DemoObject;
import com.example.demo.repository.DemoRepository;
import com.example.demo.service.DemoService;
import liquibase.exception.LiquibaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ImportTestcontainers(TestContainers.class)
public class DemoServiceIT {

    @Autowired
    private DemoService demoService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DemoRepository demoRepository;

    @BeforeEach
    public void init() throws SQLException, LiquibaseException, IOException {
        LiquibaseIT.execute(dataSource.getConnection(), "insert-changelog.xml");
    }

    @Test
    public void testDataWillCrash() {
        // Correctly inserted Liquibase
        assertThat(demoRepository.count()).isPositive();

        // WHEN
        Page<DemoObject> result = demoService.searchObjects(
                PageRequest.of(0, 5)
                        .withSort(Sort.by(Sort.Direction.DESC, "creationDate"))
        );

        assertThat(result.stream().toList())
                .isNotNull()
                .isNotEmpty()
                .hasSize(5);
    }

    @Test
    public void wontCrashWithLessElementThanPageSize() {
        // Correctly inserted Liquibase
        assertThat(demoRepository.count()).isPositive();

        // WHEN
        Page<DemoObject> result = demoService.searchObjects(
                PageRequest.of(0, 6)
                        .withSort(Sort.by(Sort.Direction.DESC, "creationDate"))
        );

        assertThat(result.stream().toList())
                .isNotNull()
                .isNotEmpty()
                .hasSize(5);
    }

}
