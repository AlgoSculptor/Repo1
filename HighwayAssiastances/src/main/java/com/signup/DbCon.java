package com.signup;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class DbCon {

    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/highway?useSSL=false&allowPublicKeyRetrieval=true");
        dataSource.setUsername("root");
        dataSource.setPassword("abcdef");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

//    public static void closeConnection(Connection connection) {
////        if (connection != null) {
////            try {
////                //connection.close();
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
//    }
}
