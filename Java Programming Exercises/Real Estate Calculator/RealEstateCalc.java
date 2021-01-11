/* Assignment: 3
 * Assignment Title: Real Estate Value Calculator 
 * Program Author: Alexander Moulton
 * Due: Thursday 03 October, 2018
 */

/*
This Program calculates the total property value of 4-6 room properties given the areas of each room.
There are 3 major conditional branches for 4, 5, and 6-room properties respectively.
For both 4 and 5-room properties it gives the user the choice for either a guest bedroom or dining room.
There is also a contingent "else" statement error if the room number does not meet the 4-6 room requirement that returns the user to the beginning of the function.
Enjoy!
*/


package assmt3;

import java.util.Scanner;

public class RealEstateCalc {

    private static void CalcStart() {

        Scanner sc = new Scanner(System.in);

        
        String living = "Living Room";
        String dining = "Dining Room";
        String master = "Master Bedroom";
        String guest = "Guest Bedroom";
        String kitchen = "Kitchen";
        String bathroom = "Bathroom";
        
        System.out.println("Please enter Street Number then press ENTER...");
        String streetNumber = sc.next();

        sc.nextLine(); //remove \n from scanner buffer
        System.out.println("Please enter Street Name then press ENTER...");
        String streetName = sc.nextLine(); //nextLine allows spaces "freemont street"

        System.out.println("Please enter the number of rooms then press ENTER...");
        int numberRooms = sc.nextInt();
        
        if (numberRooms == 4) {//if 4 rooms
            
            System.out.print("Is the fourth room a \n1. Guest Room \n     or  \n2. Dining Room?\n");
            int choice = sc.nextInt();
            
            if (choice == 1) 
            {// if Guest Room )4 rooms)
                
                System.out.println("What is the area of the " + guest + "?");
                int guestArea = sc.nextInt();
                
                System.out.println("What is the area of the " + master + "?");
                int masterArea = sc.nextInt();
                
                System.out.println("What is the area of the " + kitchen + "?");
                int kitchenArea = sc.nextInt();
                
                System.out.println("What is the area of the " + bathroom + "?");
                int bathroomArea = sc.nextInt();
                
                System.out.println("What is the price per square foot?");
                double pricePerSqFt = sc.nextDouble();
                
                int totalArea = masterArea + guestArea + kitchenArea + bathroomArea;
                double avgArea = totalArea / numberRooms;
                double avgRoomValue = avgArea * pricePerSqFt;
                double estimatedPropertyValue = pricePerSqFt * totalArea;
                
                int row = 1;
                    
                System.out.println( row + "\tAdress:  \t\t\t" + streetNumber + " " + streetName);
                System.out.println( ++row + "\tNumber of Rooms:  \t\t" + numberRooms);
                System.out.println( ++row + "\tTotal Area:  \t\t\t" + totalArea);
                System.out.println( ++row + "\tAverage Room Area:  \t\t" + avgArea);
                System.out.println( ++row + "\tPrice per Square Foot:  \t$" + pricePerSqFt);
                System.out.println( ++row + "\tNumber of Rooms:  \t\t" + numberRooms);
                System.out.println( ++row + "\tAverage Estimated Room Value:  \t$" + avgRoomValue);
                System.out.println( ++row + "\tRecommended Property Value:  \t$" + estimatedPropertyValue);
              
            }//if Guest Room (4 rooms)
            else if (choice == 2) {//else if Dining Room (4 rooms)
                System.out.println("What is the area of the " + dining + "?");
                int diningArea = sc.nextInt();
                
                System.out.println("What is the area of the " + master + "?");
                int masterArea = sc.nextInt();
                
                System.out.println("What is the area of the " + kitchen + "?");
                int kitchenArea = sc.nextInt();
                
                System.out.println("What is the area of the " + bathroom + "?");
                int bathroomArea = sc.nextInt();
                
                System.out.println("What is the price per square foot?");
                double pricePerSqFt = sc.nextDouble();
                
                int totalArea = masterArea + diningArea + kitchenArea + bathroomArea;
                double avgArea = totalArea / numberRooms;
                double avgRoomValue = avgArea * pricePerSqFt;
                double estimatedPropertyValue = pricePerSqFt * totalArea;
                
                int row = 1;
                    
                System.out.println( row + "\tAdress:  \t\t\t" + streetNumber + " " + streetName);
                System.out.println( ++row + "\tNumber of Rooms:  \t\t" + numberRooms);
                System.out.println( ++row + "\tTotal Area:  \t\t\t" + totalArea);
                System.out.println( ++row + "\tAverage Room Area:  \t\t" + avgArea);
                System.out.println( ++row + "\tPrice per Square Foot:  \t$" + pricePerSqFt);
                System.out.println( ++row + "\tNumber of Rooms:  \t\t" + numberRooms);
                System.out.println( ++row + "\tAverage Estimated Room Value:  \t$" + avgRoomValue);
                System.out.println( ++row + "\tRecommended Property Value:  \t$" + estimatedPropertyValue);
              
            }//else if Dining Room (4 rooms)
        }//if 4 rooms
        else if (numberRooms == 5) {//if 5 rooms
            System.out.print("Is the fifth room a \n1. Guest Room \n     or  \n2. Dining Room?\n");
            int choice = sc.nextInt();
            
            if (choice == 1) {// if Guest Room (5 rooms)
                
                System.out.println("What is the area of the " + guest + "?");
                int guestArea = sc.nextInt();
                
                System.out.println("What is the area of the " + master + "?");
                int masterArea = sc.nextInt();
                
                System.out.println("What is the area of the " + kitchen + "?");
                int kitchenArea = sc.nextInt();
                
                System.out.println("What is the area of the " + bathroom + "?");
                int bathroomArea = sc.nextInt();
                
                System.out.println("What is the area of the " + living + "?");
                int livingArea = sc.nextInt();
                
                System.out.println("What is the price per square foot?");
                double pricePerSqFt = sc.nextDouble();
                
                int totalArea = masterArea + guestArea + kitchenArea + bathroomArea + livingArea;
                double avgArea = totalArea / numberRooms;
                double avgRoomValue = avgArea * pricePerSqFt;
                double estimatedPropertyValue = pricePerSqFt * totalArea;
                
                int row = 1;
                    
                System.out.println( row + "\tAdress:  \t\t\t" + streetNumber + " " + streetName);
                System.out.println( ++row + "\tNumber of Rooms:  \t\t" + numberRooms);
                System.out.println( ++row + "\tTotal Area:  \t\t\t" + totalArea);
                System.out.println( ++row + "\tAverage Room Area:  \t\t" + avgArea);
                System.out.println( ++row + "\tPrice per Square Foot:  \t$" + pricePerSqFt);
                System.out.println( ++row + "\tNumber of Rooms:  \t\t" + numberRooms);
                System.out.println( ++row + "\tAverage Estimated Room Value:  \t$" + avgRoomValue);
                System.out.println( ++row + "\tRecommended Property Value:  \t$" + estimatedPropertyValue);
              
            }//if Guest Room (5 rooms)
            else if (choice == 2) {//else if Dining Room (5 rooms)
                System.out.println("What is the area of the " + dining + "?");
                int diningArea = sc.nextInt();
                
                System.out.println("What is the area of the " + master + "?");
                int masterArea = sc.nextInt();
                
                System.out.println("What is the area of the " + kitchen + "?");
                int kitchenArea = sc.nextInt();
                
                System.out.println("What is the area of the " + bathroom + "?");
                int bathroomArea = sc.nextInt();
                
                System.out.println("What is the area of the " + living + "?");
                int livingArea = sc.nextInt();
                
                System.out.println("What is the price per square foot?");
                double pricePerSqFt = sc.nextDouble();
                
                int totalArea = masterArea + diningArea + kitchenArea + bathroomArea + livingArea;
                double avgArea = totalArea / numberRooms;
                double avgRoomValue = avgArea * pricePerSqFt;
                double estimatedPropertyValue = pricePerSqFt * totalArea;
                
                int row = 1;
                    
                System.out.println( row + "\tAdress:  \t\t\t" + streetNumber + " " + streetName);
                System.out.println( ++row + "\tNumber of Rooms:  \t\t" + numberRooms);
                System.out.println( ++row + "\tTotal Area:  \t\t\t" + totalArea);
                System.out.println( ++row + "\tAverage Room Area:  \t\t" + avgArea);
                System.out.println( ++row + "\tPrice per Square Foot:  \t$" + pricePerSqFt);
                System.out.println( ++row + "\tNumber of Rooms:  \t\t" + numberRooms);
                System.out.println( ++row + "\tAverage Estimated Room Value:  \t$" + avgRoomValue);
                System.out.println( ++row + "\tRecommended Property Value:  \t$" + estimatedPropertyValue);
              
            }//if dining Room (5 rooms)

        }//else if numberRooms 5

       else if (numberRooms == 6) {//if 6 rooms
            
                System.out.println("What is the area of the " + guest + "?");
                int guestArea = sc.nextInt();
                
                System.out.println("What is the area of the " + dining + "?");
                int diningArea = sc.nextInt();
                
                System.out.println("What is the area of the " + master + "?");
                int masterArea = sc.nextInt();
                
                System.out.println("What is the area of the " + kitchen + "?");
                int kitchenArea = sc.nextInt();
                
                System.out.println("What is the area of the " + bathroom + "?");
                int bathroomArea = sc.nextInt();
                
                System.out.println("What is the area of the " + living + "?");
                int livingArea = sc.nextInt();
                
                System.out.println("What is the price per square foot?");
                double pricePerSqFt = sc.nextDouble();
                
                int totalArea = masterArea + guestArea + diningArea + kitchenArea + bathroomArea + livingArea;
                double avgArea = totalArea / numberRooms;
                double avgRoomValue = avgArea * pricePerSqFt;
                double estimatedPropertyValue = pricePerSqFt * totalArea;
                
                int row = 1;
                    
                System.out.println( row + "\tAdress:  \t\t\t" + streetNumber + " " + streetName);
                System.out.println( ++row + "\tNumber of Rooms:  \t\t" + numberRooms);
                System.out.println( ++row + "\tTotal Area:  \t\t\t" + totalArea);
                System.out.println( ++row + "\tAverage Room Area:  \t\t" + avgArea);
                System.out.println( ++row + "\tPrice per Square Foot:  \t$" + pricePerSqFt);
                System.out.println( ++row + "\tNumber of Rooms:  \t\t" + numberRooms);
                System.out.println( ++row + "\tAverage Estimated Room Value:  \t$" + avgRoomValue);
                System.out.println( ++row + "\tRecommended Property Value:  \t$" + estimatedPropertyValue);
              
            }//else if 6 rooms
       else {// else ERROR
           System.out.println("ERROR: THIS CALCULATOR WAS ONLY DESIGNED FOR 4-6 ROOM PROPERTIES!!!");
           CalcStart();
       } // else ERROR
    }//CalcStart

    public static void main(String[] args) {
        System.out.println("Welcome to the Total Real Estate Value Calculator");
        CalcStart();
        System.out.println("Thank You for using the Total Real Estate Value Calculator! \n(Commissions are accepted!!!)");
    } // main

} // RealEstateCalc
