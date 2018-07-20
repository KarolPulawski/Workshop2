package com.coderslab;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private int id;
    private String name;
    private String username;
    private String password;
    private String email;

    public User(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Active Record
    public void saveToDB() {
        //insert/update
        if(this.id==0) {
            //insert
            try {
                String sql = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
                String generatedColumns[] = { "ID" };
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, this.username);
                preparedStatement.setString(2, this.email);
                preparedStatement.setString(3, this.password);
                preparedStatement.setInt(4, this.id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //update
        }
    }

    public void delete() {
        //DELETE na bazie i zamienia id na 0
    }

    public static User loadById(int id) { // przez id mapujemy usera z bazy na obiekt
        return null;
    }

    public static ArrayList<User> loadAll() {
        return null;
    }


}
