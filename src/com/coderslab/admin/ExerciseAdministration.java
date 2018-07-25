package com.coderslab.admin;

import com.coderslab.model.Exercise;
import com.coderslab.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class ExerciseAdministration {

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

    protected static void displayAllExercise() {
        System.out.println("------------------------------Exercise list-----------------------------------------");
        System.out.printf("%-5s %-20s %-40s \n", "id", "title", "description");
        System.out.println("------------------------------------------------------------------------------------");
        ArrayList<Exercise> exercises = Exercise.loadAll();
        for(Exercise exercise : exercises) {
            int exercise_id = exercise.getId();
            String exercise_title = exercise.getTitle();
            String exercise_description = exercise.getDescription();
            System.out.printf("%-5d %-20s %-40s \n", exercise_id, exercise_title, exercise_description);
        }
    }

    public static void processAction() {
        boolean action = true;
        while(action) {
            displayAllExercise();
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" * add");
            System.out.println(" * edit");
            System.out.println(" * delete");
            System.out.println(" * quit");
            System.out.println("--------------------------------------------------------------------------------------");
            String choice = getStringFromUser("Please type one out of above actions: ");

            switch(choice) {
                case "add":
                    Exercise exerciseAdd = new Exercise();
                    exerciseAdd.setTitle(getStringFromUser("Please type title: "));
                    exerciseAdd.setDescription(getStringFromUser("Please type description: "));
                    exerciseAdd.saveToDB();
                    break;
                case "edit":
                    Exercise exerciseEdit = Exercise.loadById(getIntFromUser("Please type user's id to edit: "));
                    if(exerciseEdit == null) {
                        System.out.println("Exercise id does not exist and can not be midified!");
                        break;
                    }
                    exerciseEdit.setTitle(getStringFromUser("Please type title: "));
                    exerciseEdit.setDescription(getStringFromUser("Please type description: "));
                    exerciseEdit.saveToDB();
                    break;
                case "delete":
                    Exercise exerciseDelete = Exercise.loadById(getIntFromUser("Please type user's id to delete: "));
                    if (exerciseDelete == null) {
                        System.out.println("Exercise id does not exist and can not be deleted!");
                        break;
                    }
                    exerciseDelete.delete();
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
