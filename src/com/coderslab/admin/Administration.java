package com.coderslab.admin;

import java.util.Scanner;

public class Administration {

    private static Scanner scanner = new Scanner(System.in);

    private static int getIntFromUser() {
        Integer number = null;
        while(number == null || number < 1) {
            try {
                number = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid option from the list.");
            }
        }
        return number;
    }

    public static void main(String[] args) {

        if(args.length == 0) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("------WELCOME IN ADMINISTATION MENU OF PROGRAMMING SCHOOL-------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("------Please choose specific position by type its number:-------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("*** (1) for users administration");
            System.out.println("*** (2) for exercises administration");
            System.out.println("*** (3) for groups administration");
            System.out.println("*** (4) for solution administration");

            switch(getIntFromUser()) {
                case 1:
                    UserAdministration.processAction();
                    break;
                case 2:
                    ExerciseAdministration.processAction();
                    break;
                case 3:
                    GroupAdministration.processAction();
                    break;
                case 4:
                    SolutionAdministration.processAction();
                    break;
                default:
                    System.out.println("Please type correct position's number.");
            }
        } else if(args.length == 1) {
            Integer user_id_from_args = null;
            try {
                user_id_from_args = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Console's argument is not a number.");
            }
            System.out.println("----------------------------------------------------------------------");
            System.out.println("------WELCOME IN TASKS ADMINISTRATION APPLICATION---------------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("------Please type name of specific action for user: ( " + user_id_from_args+" ):-----------");
            System.out.println("----------------------------------------------------------------------");
            SolutionUpdateAdministration.processAction(user_id_from_args);
        }
        System.out.println("App is closing...");
    }
}
