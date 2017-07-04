package br.cefetmg.jquest.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresqlConnection implements ConnectionFactory {

    private final static String dbDriver = "org.postgresql.Driver";
    private final static String dbURL = "jdbc:postgresql://localhost:5432/sislocdb";
    private final static String user = "postgres";
    private final static String pass = "postgres";

    public PostgresqlConnection() {
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL, user, pass);
    }
    
    public static void main(String[] args) {
        try {
            ConnectionFactory cf = new PostgresqlConnection();            
            cf.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostgresqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
