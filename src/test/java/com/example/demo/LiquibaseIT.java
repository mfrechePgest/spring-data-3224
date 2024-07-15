package com.example.demo;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LiquibaseIT {


    /**
     * Liquibase syntax for easy csv loading
     */
    public static void execute(Connection c, String changeLogFile) throws LiquibaseException, IOException {
        Liquibase liquibase;
        try {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
            liquibase = new Liquibase(changeLogFile, new DirectoryResourceAccessor(new ClassPathResource("liquibase/").getFile()), database);
            liquibase.update();
        } finally {
            if (c != null) {
                try {
                    c.rollback();
                    c.close();
                } catch (SQLException e) {
                    //nothing to do
                }
            }
        }
    }

}
