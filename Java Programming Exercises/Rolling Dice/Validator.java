/*
 * @topic T00840 Using DicePair with rollSeries() Nov 10 2015
 * @brief Validator class: user input handling and validation
*/
package assmt5_dice;

import java.util.Scanner;

public class Validator
{   
    public static Scanner sc = new Scanner( System.in );

    public static int getInt(Scanner sc, String prompt)
    {
        int i = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            System.out.print(prompt);
            if (sc.hasNextInt())
            {
                i = sc.nextInt();
                isValid = true;
            }
            else
            {
                System.out.println("ERROR! Invalid integer value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return i;
    }

    public static int getInt(Scanner sc, String prompt,
    int min, int max)
    {
        int i = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            i = getInt(sc, prompt);
            if (i < min)
                System.out.println(
                    "Error! Minimum number is" + (min) + ".");
            else if (i > max)
                System.out.println(
                    "Error! Maximum number is" + (max) + ".");
            else
                isValid = true;
        }
        return i;
    }
    
public static int getIntMenu(Scanner sc, String prompt,
    int min, int max)
    {
        int i = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            i = getInt(sc, prompt);
            if (i < min)
                System.out.println(
                    "ERROR: Please choose a valid menu option!");
            else if (i > max)
                System.out.println(
                    "ERROR: Please choose a valid menu option!");
            else
                isValid = true;
        }
        return i;
    }
}