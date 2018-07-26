package com.coderslab.admin;

import com.coderslab.model.Solution;
import com.coderslab.sql.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SolutionUpdateAdministration {

    private static Scanner scanner = new Scanner(System.in);

    private static String getStringFromUser(String s) {
        System.out.println(s);
        return scanner.nextLine();
    }

    private static int getIntFromUser(String s) {
        System.out.println(s);
        Integer number = null;
        while(number == null || number < 1) {
            try {
                number = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid number and greater than 0.");
            }
        }
        return number;
    }

    private static void displayNonSolvedExercise(int idFromConsole) {
        try {
            String sql =
                    "SELECT exercise.id, title, created FROM " +
                    "exercise " +
                    "LEFT JOIN solution ON exercise.id = solution.exercise_id " +
                    "WHERE users_id = ? AND solution.description IS NULL";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idFromConsole);
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("----------Unsolved exercises list (user id: " + idFromConsole + ")---------------------");
            System.out.printf("%-5s %-20s %-20s  \n","id", "title", "date of create");
            System.out.println("---------------------------------------------------------------------------------------");
            while(rs.next()) {
                int exercise_id = rs.getInt("exercise.id");
                String exercise_title = rs.getString("title");
                java.sql.Date created = rs.getDate("created");
                System.out.printf("%-5s %-20s %-20s  \n",exercise_id, exercise_title, created);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void processAction(int idFromConsole) {
        boolean action = true;
        while (action) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" * add solution");
            System.out.println(" * view solutions");
            System.out.println(" * quit");
            System.out.println("--------------------------------------------------------------------------------------");
            String choice = getStringFromUser("Please type one out of above actions: ");

            switch(choice) {
                case "add":
                    displayNonSolvedExercise(idFromConsole);

                    System.out.println("--------------------------------------------------------------------------------------");
                    int choice_exercise = getIntFromUser("Please type number of exercise you want to add solution: ");

                    ArrayList<Solution> solutionsToUpdate = Solution.loadAllByExerciseId(choice_exercise);
                    Solution firstSolutionToUpdate = solutionsToUpdate.get(0);

                    firstSolutionToUpdate.setDescription(getStringFromUser("Please type your solution: "));

                    java.util.Date current = new java.util.Date();
                    long milis = current.getTime();
                    java.sql.Date updated = new java.sql.Date(milis);

                    firstSolutionToUpdate.setUpdated(updated);
                    firstSolutionToUpdate.saveToDB();
                    break;
                case "view":
                    ArrayList<Solution> solutions = Solution.loadAllByUserId(idFromConsole);
                    System.out.println("-------------------Solutions list (user id: " + idFromConsole + ")---------------------");
                    System.out.printf("%-5s %-20s %-20s %-60s \n","id", "date of create", "date of update");
                    System.out.println("---------------------------------------------------------------------------------------");
                    for(Solution solution : solutions) {
                        int solution_id = solution.getId();
                        java.sql.Date solution_created = solution.getCreated();
                        java.sql.Date solution_updated = solution.getUpdated();
                        String solution_description = solution.getDescription();
                        System.out.printf("%-5s %-20s %-20s %-60s \n",solution_id, solution_created.toString(), solution_updated.toString(), solution_description);
                    }
                    System.out.println("--------------------------------------------------------------------------------------");
                    break;
                case "quit":
                    System.out.println("quit");
                    action = false;
                    break;
                default:
                    System.out.println("Please enter valid action!");
            }
        }
    }


}
