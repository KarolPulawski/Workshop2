package com.coderslab.admin;

import com.coderslab.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserAdministration {

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

    protected static void displayAllUser() {
        System.out.println("------------------------------User list-----------------------------------------");
        System.out.printf("%-5s %-20s %-40s %-5s \n", "id", "username", "email", "group_id");
        System.out.println("--------------------------------------------------------------------------------");
        ArrayList<User> users = User.loadAll();
        for(User user : users) {
            int user_id = user.getId();
            String user_username = user.getUsername();
            String user_email = user.getEmail();
            int user_group_id = user.getUser_group_id();
            System.out.printf("%-5d %-20s %-40s %-5d \n", user_id, user_username, user_email, user_group_id);
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void processAction() {
        boolean action = true;
        while(action) {
            displayAllUser();
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println(" * add");
            System.out.println(" * edit");
            System.out.println(" * delete");
            System.out.println(" * quit");
            System.out.println("--------------------------------------------------------------------------------");
            String choice = getStringFromUser("Please type one out of above actions: ");
            switch(choice) {
                case "add":
                    User userAdd = new User();
                    userAdd.setUsername(getStringFromUser("Please type username: "));
                    userAdd.setPassword(getStringFromUser("Please type password: "));
                    userAdd.setEmail(getStringFromUser("Please email: "));
                    userAdd.setUser_group_id(getIntFromUser("Please group_id: "));
                    userAdd.saveToDB();
                    break;
                case "edit":
                    User userEdit = User.loadById(getIntFromUser("Please type user's id to edit: "));
                    if(userEdit == null) {
                        System.out.println("User id does not exist and can not be midified!");
                    } else {
                        userEdit.setUsername(getStringFromUser("Please type username: "));
                        userEdit.setPassword(getStringFromUser("Please type password: "));
                        userEdit.setEmail(getStringFromUser("Please email: "));
                        userEdit.setUser_group_id(getIntFromUser("Please group_id: "));
                        userEdit.saveToDB();
                    }
                    break;
                case "delete":
                    User userDelete = User.loadById(getIntFromUser("Please type user's id to delete: "));
                    if(userDelete == null) {
                        System.out.println("User id does not exist and can not be deleted!");
                    } else {
                        userDelete.delete();
                    }
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
