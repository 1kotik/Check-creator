package org.example.utility;

import org.example.dto.DBInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static DBInfo dbInfo;
    //private static final String INIT_SCRIPT = ";INIT=RUNSCRIPT classpath:init.sql";

    public static void setDbInfo(DBInfo dbInfo) {
        DBConnector.dbInfo = dbInfo;
    }

    public static Connection getConnnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbInfo.getUrl(), dbInfo.getUsername(), dbInfo.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
