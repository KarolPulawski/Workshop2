package com.coderslab;

public class Main {

    public static void main(String[] args) {

        User karol = new User("karolp", "abcpass", "karol3@gmail.com", 2);
        karol.saveToDB();

//        Group group1 = new Group("group_first");
//        group1.saveToDB();
    }
}
