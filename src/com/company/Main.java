package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static List<GameResult> results = new ArrayList<>(); // ArrayList<>() = ArrayList<GameResult>()
    public static final File RESULTS_FILE = new File("results.txt");

    public static void main(String[] args) {
        loadResults();

        do {
            System.out.println("What is your name?");
            String name = scan.next();
            System.out.println("Hello, " + name + "!");
            System.out.println("Guess my number from 1 to 10");

            int myNum = rand.nextInt(10) + 1;
            long t1 = System.currentTimeMillis();
            for (int i = 1; i <= 10; i++) {
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


        showResults();
        saveResults();
    }

    private static void showResults() {
        results.stream()
                .sorted(Comparator.<GameResult>comparingInt(r-> r.triesCount)
                                  .thenComparingLong(r -> r.duration))

                .limit(3)
                .forEach(r-> System.out.printf("Player %s has done %d tries and it took %.2f sec\n",
                        r.name,
                        r.triesCount,
                        r.duration / 1000.0));
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

    private static void saveResults() {
        try (PrintWriter fileOut = new PrintWriter(RESULTS_FILE)) {
            int skipCount = results.size() - 5; //pervije 5 zapisej mi propuskajem
            for (GameResult r : results) {
                if (skipCount <= 0) {
                    fileOut.printf("%s %d %d\n", r.name, r.triesCount, r.duration);

                }
                skipCount--; //umenwaem skipcount na 1
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadResults() {
        try (Scanner in = new Scanner(RESULTS_FILE)) {
            while (in.hasNext()) {

                GameResult gr = new GameResult();
                gr.name = in.next();
                gr.triesCount = in.nextInt();
                gr.duration = in.nextLong();
                results.add(gr);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}



