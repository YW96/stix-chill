package DatabaseConnections;

import java.sql.*;

public class DBConnector {
    private static Connection con = null;
    private final String CONNECTION_URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Stix;integratedSecurity=true;";

    protected DBConnector() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException e) {
            System.out.println("ERROR: Problem with SQL or server!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Problem with JDBC driver!");
            e.printStackTrace();
        }
    }

    protected ResultSet runSQL(String sql) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeQuery(sql);
    }

    protected void crudSQL(String sql) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute(sql);
    }


    protected static Connection getCon() {
        return con;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            //con.close();
        } finally {
            super.finalize();
        }
    }
}
