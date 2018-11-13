package com.company;


import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static List<GameResult> results = new ArrayList<>(); // ArrayList<>() = ArrayList<GameResult>()

    public static void main(String[] args) {
        do {
            System.out.println("What is your name?");
            String name = scan.next();
            System.out.println("Hello, " + name + "!");
            System.out.println("Guess my number from 1 to 10");

            int myNum = rand.nextInt(10) + 1;
            long t1 = System.currentTimeMillis();
            for (int i = 0; i <= 10; i++) {
                int userNum = readUserNum();
                if (myNum == userNum) {
                    System.out.println("You are WINNER, my number is " + myNum);
                    GameResult r = new GameResult();
                    r.name = name; //Pervij name idjot iz gameresult, a vtoroj, kotorij iz main
                    r.triesCount = i;
                    r.duration = System.currentTimeMillis() - t1;
                    results.add(r); //posle vijgriwa sozdali result, napi4kali dannimi i sohranili rezuljtat
                    break;
                } else if (myNum > userNum) {
                    System.out.println("My number is greater");
                } else {
                    System.out.println("My number is lesser");
                }
            }
            System.out.println("Wanna play again?");
        } while ("y".equals(scan.next()));

        for (GameResult r : results) {
            System.out.println("Player $s has done %d tries and it took $.2f sec\n"),
                    r.name,
                    r.triesCount,
                    r.duration / 1000.0);
        }
    }

    private static int readUserNum() {
        while (true) {
            try {
                int userNum = scan.nextInt();
                if (userNum < 1 || userNum > 10) {
                    System.out.println("Your number is incorrect, please enter number from 1 to 10");
                    continue;
                }
                return userNum;

            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("You are cheater! Please enter the number from 1 to 10");

            }
        }
    }
}


