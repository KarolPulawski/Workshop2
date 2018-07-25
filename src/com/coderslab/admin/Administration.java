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

        System.out.println("----------------------------------------------------------------------");
        System.out.println("------WELCOME IN ADMINISTATION MENU OF PROGRAMMING SCHOOL-------------");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("------Please choose specific position by type its number:-------------");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("*** (1) for users administration");
        System.out.println("*** (2) for exercises administration");
        System.out.println("*** (3) for groups administration");
        System.out.println("*** (4) for tasks schedule");

        switch(getIntFromUser()) {
            case 1:
                UserAdministration.processAction();
                break;
            case 2:
                ExerciseAdministration.processAction();
                break;
            default:
                System.out.println("Please type correct position's number.");
        }



        System.out.println("App is closing...");
    }
}
