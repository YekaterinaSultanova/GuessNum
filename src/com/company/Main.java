package com.company;


import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("What is your name?");
        String name = scan.next();
        System.out.println("Hello, " + name + "!");
        System.out.println("Guess my number from 1 to 10");

        int myNum = rand.nextInt(10) + 1;
        for (int i = 0; i <= 10; i++) {
            int userNum;// = scan.nextInt();
            try {
                userNum = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You are cheater");
                return;
            }
            if (myNum == userNum) {
                System.out.println("You are WINNER, my number is " + myNum);
                break;
            } else if (myNum > userNum) {
                System.out.println("My number is greater");
            } else {
                System.out.println("My number is lesser");
            }
        }
    }
}

