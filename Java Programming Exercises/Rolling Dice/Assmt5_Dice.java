/* Assignment: 5
 * Assignment Title: Dice Roller Program 
 * Program Author: Alexander Moulton
 * Due: Thursday 8 November, 2018
 
 * This program simulates the actions of rolling dice.
 * It has rudimentary visuals and handles input from the user adequately.
 * There are several combinations of dice that have slang terms
 * that will be generated upon the conditions required for them to exist.
 *
 * I originally planned on providing a tertiary function that would create
 * a collection of a user-supplied number of dice with the same number of sides
 * but ran out of time.
 *
 * Please let me know what you think. I enjoy hearing about improvements I could have made.
 */

package assmt5_dice;

import java.util.Scanner;

public class Assmt5_Dice {

static Scanner sc = new Scanner(System.in);
    
public static void main(String[] args) {
    menu(); 
    System.out.println("\nNow Exiting...");
    }//main

    private static void menu(){
        System.out.println("                                ______________");
        System.out.println("                               / __  __  __  /|");
        System.out.println("___        ___                /_____________/ |");
        System.out.println("\\  \\  /\\  /  /ELCOME TO       |            |  |");
        System.out.println(" \\  \\/  \\/  / THE DICE        |  |     |   | ||");
        System.out.println("  \\___/\\___/  SIMULATOR       |     |      | ||");     
        System.out.println("                              |  |     |   |  |");
        System.out.println("  ______________              |Moulton Inc.| / ");
        System.out.println(" /     __      /|             |____________|/");
        System.out.println("/_____________/ |");  
        System.out.println("|            | ||   PLEASE CHOOSE AN OPTION:");
        System.out.println("|  |     |   ||||");
        System.out.println("|            ||||   1. Roll a single die");
        System.out.println("|  |     |   || |   2. Roll a pair of dice");
        System.out.println("|            | /    3. QUIT");
        System.out.println("|____________|/");
        System.out.println("");

        int menuChoice = Validator.getIntMenu(
                Validator.sc, 
                " Your choice: ",
                1, 3
        );
    
        switch(menuChoice){
            case 1: 
                System.out.println("\nYou chose to roll a single die.\n");
                singleDie();
                break;
            case 2: 
                System.out.println("\nYou chose to roll a pair of dice.\n");
                dicePair();
                break;
            case 3: 
                System.out.println("\nYou chose to QUIT.");
                System.out.println("\nThank you for using the Dice Simulator!");
                break;
        }//menuChoice switch
            
     
    }//menu()
    
    private static void singleDie(){
        int numberSides = Validator.getInt(
                Validator.sc, 
                "Enter number of sides on the die and then press ENTER...\n",
                2, Integer.MAX_VALUE
            );// get number sides from user
        
        Die die = new Die(numberSides); //create die of specified sides
        
        System.out.println("Would you like to roll the die? Y/N"); // roll option
        
        for(; ;){ // roll decision loop
            String rollChoice = sc.next();
            if (rollChoice.equalsIgnoreCase("y")){
            
                for(; ;){//shake decision loop
                    System.out.println("Would you like to use a lucky number of shakes before rolling? Y/N");
                    
                    String shakesChoice = sc.next();
                    
                    if (shakesChoice.equalsIgnoreCase("y")){// if user wants to shake
                        int shakes = Validator.getInt(
                            Validator.sc, 
                            "Enter a lucky number of shakes then press ENTER...\n",
                            0, Integer.MAX_VALUE
                        );//get number shakes from user
                        die.roll(shakes);
                        System.out.println("You rolled a " + die.getFaceValue()+"!");
                        break;
                    }// if user wants to shake
                    else if(shakesChoice.equalsIgnoreCase("n")){// if user doesn't want to shake
                        die.roll();
                        System.out.println("You rolled a " + die.getFaceValue()+"!");
                        break;
                    }// if user doesn't want to shake
                    else{//improper entry catch
                    System.out.println("Please Enter \"Y\" or \"N\" and then press ENTER");
                    }//improper entry catch
                }// shake decision loop
                
                System.out.println("Would you like to roll the same die again? Y/N");
            
            }// if user wants to roll
            else if(rollChoice.equalsIgnoreCase("n")){// no roll --> Main Menu
                System.out.println("Returning to Main Menu...");
                break;
            }// no roll --> Main Menu
            else{//improper entry catch
                System.out.println("Please Enter \"Y\" or \"N\" and then press ENTER");
            }//improper entry catch
        }// roll decision loop
        menu();
    } // singleDie()
    
    private static void dicePair(){
    System.out.println("Do the Dice have the same number of sides? Y/N");
        TwoDice dice;
        for(;;){//creation of dice
            String sameSidesChoice = sc.next();
            if( sameSidesChoice.equalsIgnoreCase("y")){
                int numberSides = Validator.getInt(
                    Validator.sc, 
                    "Enter number of sides on the dice and then press ENTER...\n",
                    2, Integer.MAX_VALUE
                );// get number sides from user
                dice = new TwoDice(numberSides);
                break;
            }// if same sides on each
            else if( sameSidesChoice.equalsIgnoreCase("n")){
                int numberSides1 = Validator.getInt(
                    Validator.sc, 
                    "Please enter the number of sides on the first die and then press ENTER...\n",
                    2, Integer.MAX_VALUE
                );// get number sides dieOne from user
                int numberSides2 = Validator.getInt(
                    Validator.sc, 
                    "Please enter the number of sides on the second die and then press ENTER...\n",
                    2, Integer.MAX_VALUE
                );// get number sides dieTwo from user
                
                dice = new TwoDice(numberSides1, numberSides2);
                break;
            }// if different sides
            else{//improper entry catch
                System.out.println("Please Enter \"Y\" or \"N\" and then press ENTER");
            }//improper entry catch
        }//creation of dice
        
        System.out.println("Would you like to roll the dice? Y/N"); // roll option
        
        for(;;){
            String rollChoice = sc.next();
            if( rollChoice.equalsIgnoreCase("y")){
                for(; ;){//shake decision loop
                    System.out.println("Would you like to use a lucky number of shakes before rolling? Y/N");
                    
                    String shakesChoice = sc.next();
                    
                    if (shakesChoice.equalsIgnoreCase("y")){// if user wants to shake
                        int shakes = Validator.getInt(
                            Validator.sc, 
                            "Enter a lucky number of shakes then press ENTER...\n",
                            0, Integer.MAX_VALUE
                        );//get number shakes from user
                        dice.roll(shakes);
                        printDice(dice.getFaceValue1(), dice.getFaceValue2());
                        break;
                    }// if user wants to shake
                    else if(shakesChoice.equalsIgnoreCase("n")){// if user doesn't want to shake
                        dice.roll();
                        printDice(dice.getFaceValue1(), dice.getFaceValue2());
                        break;
                    }// if user doesn't want to shake
                    else{//improper entry catch
                        System.out.println("Please Enter \"Y\" or \"N\" and then press ENTER");
                    }//improper entry catch
                    
                }// dice shake decision loop
                System.out.println("Would you like to roll the same dice again? Y/N");
            } //roll choice "y"
            else if( rollChoice.equalsIgnoreCase("n")){
                System.out.println("Returning to Main Menu...");
                break;
            }
            else{//improper entry catch
                System.out.println("Please Enter \"Y\" or \"N\" and then press ENTER");
            }//improper entry catch
        }
        menu();
    } // dicePair()

    private static void printDice(int dieOne, int dieTwo){
        String slang = "!";
        int total = dieOne + dieTwo;
        if (dieOne == 1 && dieTwo == 1 ){
            slang = ", SNAKE EYES!";
        }
        else if( total == 3 ){
            slang = ", ACE DEUCE!";
        }
        else if( total == 4 && ( dieOne == 1 || dieTwo == 1 ) ){
            slang = ", LITTLE JOE FROM KOKOMO!";
        }
        else if( dieOne == 2 && dieTwo == 2 ){
            slang = ", HARD FOUR!";
        }
        else if( total == 5 && ( dieOne == 2 || dieTwo == 2 ) ){
            slang = ", FEVER FIVE!";
        }
        else if( total == 5 && ( dieOne == 1 || dieTwo == 1 ) ){
            slang = ", LITTLE PHEOBE!";
        }
        else if( total == 5 && ( dieOne == 2 || dieTwo == 2 ) ){
            slang = ", FEVER FIVE!";
        }
        else if( dieOne == 3 && dieTwo == 3 ){
            slang = ", HARD SIX!";
        }
        else if( total == 6 && ( dieOne == 2 || dieTwo == 2 ) ){
            slang = ", EASY SIX!";
        }
        else if( total == 6 && ( dieOne == 1 || dieTwo == 1 ) ){
            slang = ", SIXIE FROM DIXIE!";
        }
        else if( total == 7 && ( dieOne == 1 || dieTwo == 1 ) ){
            slang = ", AND UP POPS THE DEVIL!";
        }
        else if( total == 7 && ( dieOne == 2 || dieTwo == 2 ) ){
            slang = ", SKINNY DUGAN!";
        }
        else if( total == 7 && ( dieOne == 3 || dieTwo == 3 ) ){
            slang = ", BIG RED!";
        }
        else if( total == 8 && ( dieOne == 2 || dieTwo == 2 ) ){
            slang = ", EASY EIGHT!";
        }
        else if( total == 8 && ( dieOne == 3 || dieTwo == 3 ) ){
            slang = ", EIGHTER FROM DECATUR!";
        }
        else if( total == 8 && ( dieOne == 2 || dieTwo == 2 ) ){
            slang = ", EASY EIGHT!";
        }
        else if( dieOne == 4 && dieTwo == 4 ){
            slang = ", SQUARE PAIR!";
        }
        else if( total == 9 && ( dieOne == 3 || dieTwo == 3 ) ){
            slang = ", NINA FROM PASSADENA!";
        }
        else if( total == 9 && ( dieOne == 4 || dieTwo == 4 ) ){
            slang = ", JESSE JAMES!";
        }
        else if( total == 10 && ( dieOne == 4 || dieTwo == 4 ) ){
            slang = ", BIG ONE ON THE END!";
        }
        else if( dieOne == 5 && dieTwo == 5 ){
            slang = ", PUPPY PAWS!";
        }
        else if( total == 11 ){
            slang = ", YO-LEVEN!";
        }
        else if( total == 12 ){
            slang = ", BOX CARS!";
        }
        System.out.println("You rolled " + dieOne + " + " + dieTwo + " = " + total + slang);
}
}// Class Assmt5_Dice