package me.borawski.staff.data;

import java.sql.*;

public class MySQL {

    private Connection connection;
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;

    /*
     * Methods
     */

    public MySQL(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;

        openConnection();
    }

    private void openConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Getters
     */

    public Connection getConnection() {
        if (connection == null) {
            openConnection();
            return connection;
        }

        try {
            if (connection.isClosed()) {
                openConnection();
                return connection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * Utils
     */

    public void update(String query) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(query);
        new Thread(() -> {
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public ResultSet query(String query) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(query);
        return statement.executeQuery();
    }

}
