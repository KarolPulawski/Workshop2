package com.coderslab;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Exercise {

    private int id;
    private String title;
    private String description;

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Exercise() {}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Active Record

    public void saveToDB() {
        // insert/update
        if(this.id == 0) {
            // insert
            try {
                String sql = "INSERT INTO exercise(title, description) VALUES (?,?)";
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, this.title);
                preparedStatement.setString(2, this.description);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()) {
                    this.id = rs.getInt(1);
                }
            } catch (SQLException e) { e.printStackTrace(); }
        } else {
            // update
            try {
                String sql = "UPDATE exercise SET title = ?, description = ? WHERE id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, this.title);
                preparedStatement.setString(2, this.description);
                preparedStatement.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public static Exercise loadById(int id) { // id maps Exercise from data base onto object
        try {
            String sql = "SELECT * FROM exercise WHERE id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                Exercise exercise = new Exercise();
                exercise.id = rs.getInt("id");
                exercise.title = rs.getString("title");
                exercise.description = rs.getString("description");
                return exercise;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static ArrayList<Exercise> loadAll() {
        try {
            ArrayList<Exercise> exercises = new ArrayList<>();
            String sql = "SELECT * FROM exercise";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Exercise exercise = new Exercise();
                exercise.id = rs.getInt("id");
                exercise.title = rs.getString("title");
                exercise.description = rs.getString("description");
                exercises.add(exercise);
            }
            return exercises;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
