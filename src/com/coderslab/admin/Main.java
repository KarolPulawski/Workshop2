package com.coderslab.admin;


import com.coderslab.model.Solution;

public class Main {

    public static void main(String[] args) {

//        User karol = new User("karolpass", "abcpass", "karol34@gmail.com", 1);
//        karol.saveToDB();

//        Group group1 = new Group("group_first");
//        group1.saveToDB();

//        Exercise exer1 = new Exercise("exercise1", "code app Hello World");
//        exer1.saveToDB();


        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date current = new java.util.Date();
        long milis = current.getTime();
        java.sql.Date sqlDate = new java.sql.Date(milis);
        System.out.println(sqlDate);

//
        Solution solution1 = new Solution(sqlDate, sqlDate, "this is my solution",1,6);
        solution1.saveToDB();


    }
}
