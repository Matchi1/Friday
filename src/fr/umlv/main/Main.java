package fr.umlv.main;

import org.apache.derby.jdbc.EmbeddedDataSource;

import javax.naming.NamingException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, NamingException {
        var dbUrl = "db";
        var ds = new EmbeddedDataSource();
        ds.setDatabaseName(dbUrl);
        var conn = ds.getConnection();
        System.out.println(conn.getClientInfo());
    }
}
