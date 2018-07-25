package com.coderslab.admin;

import com.coderslab.model.Exercise;
import com.coderslab.model.Solution;

import java.util.ArrayList;
import java.util.Scanner;

public class SolutionAdministration {

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

    public static void processAction() {
        boolean action = true;
        while (action) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" * add");
            System.out.println(" * view");
            System.out.println(" * quit");
            System.out.println("--------------------------------------------------------------------------------------");
            String choice = getStringFromUser("Please type one out of above actions: ");

            switch(choice) {
                case "add":
                    UserAdministration.displayAllUser();
                    int user_id_add = getIntFromUser("Please type id of user from the above list: ");
                    ExerciseAdministration.displayAllExercise();
                    int exercise_id_add = getIntFromUser("Please type id of exercise from the above list: ");

                    java.util.Date current = new java.util.Date();
                    long milis = current.getTime();
                    java.sql.Date created = new java.sql.Date(milis);

                    Solution solutionAdd = new Solution(created, null, null,
                            exercise_id_add, user_id_add);
                    solutionAdd.saveToDB();
                    break;
                case "view":
                    UserAdministration.displayAllUser();
                    int user_id_view = getIntFromUser("Please type user's id to show his all solutions: ");
                    ArrayList<Solution> solutions = Solution.loadAllByUserId(user_id_view);
                    System.out.println("-------------------Solutions list (user id: " + user_id_view + ")---------------------");
                    System.out.printf("%-5s %-20s \n","id", "date of create");
                    System.out.println("--------------------------------------------------------------------------------------");
                    for(Solution solution : solutions) {
                        int solution_id = solution.getId();
                        java.sql.Date solution_created = solution.getCreated();
                        System.out.printf("%-5s %-20s \n",solution_id, solution_created.toString());
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
