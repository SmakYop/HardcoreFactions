package com.smakyop.factions.database;

import com.smakyop.factions.HardcoreFactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

    private Connection connection = null;
    private String url, user, pass;

    private void setAuth(String url, String user, String pass){
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public void connect() {
        this.setAuth(HardcoreFactions.getInstance().getGeneralConfig().getDatabaseUrl(), HardcoreFactions.getInstance().getGeneralConfig().getDatabaseUser(),
                HardcoreFactions.getInstance().getGeneralConfig().getDatabasePassword());
        try {
            if (connection != null)
                disconnect();
            if (url != null && user != null && pass != null) {
                connection = DriverManager.getConnection(url, user, pass);
                HardcoreFactions.getInstance().log("Database connected");
            } else HardcoreFactions.getInstance().log("ERROR: Can't connect to database");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    private void disconnect() throws SQLException {
        if(!connection.isClosed()){
            connection.close();
        }
    }

    public PreparedStatement createStatement(String query) {
        try {
            if (connection.isClosed())
                connect();
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
