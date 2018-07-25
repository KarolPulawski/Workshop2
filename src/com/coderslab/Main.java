package com.coderslab;

public class Main {

    public static void main(String[] args) {

        User karol = new User("karolpass", "abcpass", "karol34@gmail.com", 1);
        karol.saveToDB();

//        Group group1 = new Group("group_first");
//        group1.saveToDB();
    }
}
