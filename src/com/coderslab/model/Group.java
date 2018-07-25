package com.coderslab.model;

import com.coderslab.sql.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {

    private int id;
    private String name;

    public Group(String name) {
        this.name = name;
    }

    public Group() {}

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Active Record

    public void saveToDB() {
        // insert/update
        if(this.id == 0) {
            // insert
            try {
                String sql = "INSERT INTO user_group(name) VALUES (?)";
                String generatedColumns[] = { "ID" };
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, this.name);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()) {
                    this.id = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // update
            try {
                String sql = "UPDATE user_group SET name = ? WHERE id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, this.name);
                preparedStatement.setInt(2, this.id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public void delete() {
        // DELETE on data base and change id onto 0
        if (this.id != 0) {
            try {
                String sql = "DELETE FROM user_group WHERE id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, this.id);
                preparedStatement.executeUpdate();
                this.id = 0;
            } catch(SQLException e) {e.printStackTrace();}
        }
    }

    public static Group loadById(int id) { // id maps user from database on object
        try {
            String sql = "SELECT * FROM user_group WHERE id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                Group group = new Group();
                group.id = rs.getInt("id");
                group.name = rs.getString("name");
                return group;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static ArrayList<Group> loadAll() {
        try {
            ArrayList<Group> groups = new ArrayList<>();
            String sql = "SELECT * FROM user_group";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.id = rs.getInt("id");
                group.name = rs.getString("name");
                groups.add(group);
            }
            return groups;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

}
