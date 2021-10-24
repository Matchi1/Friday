package fr.umlv.main;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class SpringBootTest {
    public static void main(String[] args) throws SQLException {
        var dbUrl = "db/calendar";
        var ds = new EmbeddedDataSource();
        ds.setDatabaseName(dbUrl);
        ds.setCreateDatabase("create");
        SpringApplication.run(SpringBootTest.class, args);
    }
}
