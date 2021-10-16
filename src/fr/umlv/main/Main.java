package fr.umlv.main;

import org.apache.derby.jdbc.EmbeddedDataSource;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, NamingException {
        try {
            var dbUrl = "db;create=true";
            var ds = new EmbeddedDataSource();
            ds.setDatabaseName(dbUrl);
            var conn = ds.getConnection();
            String sql = "Create Table calendar (id int not null generated always as identity," + "hours int, text varchar(128))";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            System.out.println(conn.getClientInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
