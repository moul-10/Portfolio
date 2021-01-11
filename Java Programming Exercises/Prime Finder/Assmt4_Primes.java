/* Assignment: 4
 * Assignment Title: Calculating Prime Numbers 
 * Program Author: Alexander Moulton
 * Due: Thursday 18 October, 2018
 
 *This program calculates primes in any range of integers.
 *A prime number is defined as any natural number greater than 1 that is not divisible by any other number than 1 and itself.
 *We can stop checking checking divisors at the square root of each candidate.
 *The program has multiple error handling sections of code for input mismatches.
 *It also contains an endless loop capable of calculating different ranges until the user decides not to calculate further.
 *I also took some liberties with some visual effects (mainly the end program statements).
 */
package assmt4_primes;

import java.util.Scanner;

public class Assmt4_Primes {

    static Scanner sc = new Scanner(System.in);
    static int lowBoundary;
    static int highBoundary;
    static int candidate;

    public static void main(String[] args) {

        System.out.println("------------------------------------------------");
        System.out.println("-------WELCOME TO THE PRIME NUMBER FINDER-------");
        System.out.println("------------------------------------------------");

        printPrimes(lowBoundary, highBoundary);

    }//main

    private static boolean isPrime(int candidate) {
        if (candidate < 2) {
            return false;
        } else if (candidate == 2) {
            return true;
        } else if (candidate % 2 == 0) {//if equally divisible by 2
            return false; // not a prime, it's a composite number
        } //if equally divisible by 2

        for (int divisor = 3; divisor <= Math.sqrt(candidate); divisor = divisor + 2) {//if equally divisible by 3, 5, 7,...sq root of candidate
            if (candidate % divisor == 0) {
                return false; // not a prime, it's a composite number
            }
        }//if equally divisible by 3, 5, 7,...sq root of candidate
        return true;
    } // isPrime

    private static void printPrimes(int lowBoundary, int highBoundary) {

        System.out.println();

        System.out.println("Enter low boundary then press ENTER...");
        for (;;) {
            try {
                lowBoundary = sc.nextInt();
                break;
            } catch (java.util.InputMismatchException ex) {
                System.out.println("An Integer was expected. Please try again...");
                sc.nextLine();
            }
        }// lowBoundary loop

        System.out.println("Enter high boundary then press ENTER...");

        for (;;) {
            try {
                highBoundary = sc.nextInt();
                break;
            } catch (java.util.InputMismatchException ex) {
                System.out.println("An Integer was expected. Please try again...");
                sc.nextLine();
            }
        }// highBoundary loop

        System.out.println("These are all the prime numbers between " + lowBoundary + " and " + highBoundary + ":");

        int row = 0;
        for (int candidate = lowBoundary; candidate <= highBoundary; ++candidate) {
            if (isPrime(candidate)) {
                System.out.println(++row + ".\t" + candidate);
            }//if
        }
        System.out.println("Would you like to try another range? Y/N");

        for (;;) {
            String choice = sc.next();
            if (choice.equalsIgnoreCase("y")) {
                printPrimes(lowBoundary, highBoundary);
            } else if (choice.equalsIgnoreCase("n")) {
                System.out.println("  ________   __   __    _____    ____    __   __   __");
                System.out.println(" |__    __| |  | |  |  /  _  \\  |    \\  |  | |  | /  /");
                System.out.println("    |  |    |  |_|  | |  /_|  | |     \\ |  | |  |/  /");
                System.out.println("====|  |====|   _   |=|   _   |=|  |\\  \\|  |=|   _  \\====");
                System.out.println("    |  |    |  | |  | |  | |  | |  | \\     | |  | \\  \\");
                System.out.println("    |__|    |__| |__| |__| |__| |__|  \\____| |__|  \\__\\");
                System.out.println("  __    __     _______      ___      ___");
                System.out.println(" \\  \\  /  /   /  ____  \\   |   |    |   |");
                System.out.println("  \\  \\/  /   /  |    |  \\  |   |    |   |");
                System.out.println("===\\    /===|   |    |   |=|   |====|   |================");
                System.out.println("   /   /     \\  |___ |  /  |   |____|   |");
                System.out.println("  /___/       \\________/   |____________|");
                System.out.println();
                System.out.println("Exiting...");
                break;
            } else {
                sc.nextLine();
                System.out.println("ERROR: PLEASE TYPE EITHER \"Y\" OR \"N\" THEN PRESS ENTER");
            }
        }// choice to continue
    } // print Primes
}// Assmt4_Primes
