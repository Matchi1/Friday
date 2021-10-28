package fr.umlv.main;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

@SpringBootApplication
public class SpringBootTest {
    public static String getQueries() throws IOException {
        var queries = new StringBuilder();
        var path = Path.of("create.sql");
        Files.lines(path).forEach(queries::append);
        return queries.toString();
    }

    public static void main(String[] args) throws SQLException, IOException {
        var dbUrl = "db/calendar";
        var ds = new EmbeddedDataSource();
        SpringApplication.run(SpringBootTest.class, args);
        ds.setDatabaseName(dbUrl);
        ds.setCreateDatabase("create");
        var conn = ds.getConnection();
        var stmt = conn.createStatement();
        var query1 = "CREATE SCHEMA hello";
        stmt.execute(query1);
        var query = "CREATE TABLE event( "
                + "Id varchar(255) for bit data not null, "
                + "Date VARCHAR(255), "
                + "Heure VARCHAR(255), "
                + "info VARCHAR(255), "
                + "PRIMARY KEY (Id))";
        stmt.execute(query);

    }
}
