package fr.umlv.main;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

@SpringBootApplication
public class SpringBootTest {
    public static void main(String[] args) throws SQLException, IOException {
        SpringApplication.run(SpringBootTest.class, args);
    }
}
