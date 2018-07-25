package com.coderslab;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private int user_group_id;

    public User(String username, String password, String email, int user_group_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.user_group_id = user_group_id;
    }

    public User() {

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

    public void setUser_group_id(int user_group_id) {
        this.user_group_id = user_group_id;
    }

    //Active Record
    public void saveToDB() {
        //insert/update
        if(this.id == 0) {
            //insert
            try {
                String sql = "INSERT INTO users(username, email, password, user_group_id) VALUES (?, ?, ?, ?)";
                String generatedColumns[] = { "ID" };
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, this.username);
                preparedStatement.setString(2, this.email);
                preparedStatement.setString(3, this.password);
                preparedStatement.setInt(4, this.user_group_id);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()) {
                    this.id = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //update
            try {
                String sql = "UPDATE users SET username=?, email=?, password=?, user_group_id = ? where id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, this.username);
                preparedStatement.setString(2, this.email);
                preparedStatement.setString(3, this.password);
                preparedStatement.setInt(4, this.user_group_id);
                preparedStatement.setInt(5, this.id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) { e.printStackTrace();}
        }
    }

    public void delete() {
        //DELETE na bazie i zamienia id na 0
        if (this.id != 0) {
            try {
                String sql = "DELETE FROM users WHERE id=?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, this.id);
                preparedStatement.executeUpdate();
                this.id = 0;
            } catch (SQLException e) { e.printStackTrace();}
        }
    }

    public static User loadById(int id) { // przez id mapujemy usera z bazy na obiekt
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                User user = new User();
                user.id = rs.getInt("id");
                user.username = rs.getString("username");
                user.email = rs.getString("email");
                user.password = rs.getString("password");
                user.user_group_id = rs.getInt("user_group_id");
                return user;
            }
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }

    public static ArrayList<User> loadAll() {
        try {
            ArrayList<User> users = new ArrayList<>();
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                User user = new User();
                user.id = rs.getInt("id");
                user.username = rs.getString("username");
                user.email = rs.getString("email");
                user.password = rs.getString("password");
                user.user_group_id = rs.getInt("user_group_id");
                users.add(user);
            }
            return users;
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }
}
