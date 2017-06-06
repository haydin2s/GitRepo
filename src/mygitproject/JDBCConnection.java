package mygitproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author haydin
 */

// Singleton 
public class JDBCConnection {

    // Exception Handling hier in der Klasse realisieren damit Aufrufer sich nicht damit rumschlagen muss!
    private static JDBCConnection connection;
    private String login = "lpuch2s";
    private String pw = "lpuch2s";
    private String url = "jdbc:postgresql://dumbo.inf.fh-bonn-rhein-sieg.de/lpuch2s";
    private Connection con;

    private JDBCConnection() throws DatabaseException {
        initConnection();
    }
    
    public static JDBCConnection getInstance() throws DatabaseException {
        if (connection == null) {
            connection = new JDBCConnection();
        }
        return connection;
    }

    public void initConnection() throws DatabaseException {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            openConnection();
        } catch (SQLException ex) {
            System.err.println("SQL FEHLER!");
            throw new DatabaseException("Fehler bei Zugriff auf die Datenbank! Sichere Verbindung vorhanden?");
        }
    }

    public void openConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "lpuch2s");
        props.setProperty("password", "lpuch2s");

        con = DriverManager.getConnection(url, props);
    }

    public Statement getStatement() {
        Statement stmt = null;
        try {
            if (con.isClosed()) {
                openConnection();
            }
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
        return stmt;
    }

    public PreparedStatement getPreparedStatement(String pstmt) {
        PreparedStatement stmt = null;
        try {
            if (con.isClosed()) {
                openConnection();
            }
            stmt = con.prepareStatement(pstmt, ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
        return stmt;
    }
    
    public void closeConnect() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
