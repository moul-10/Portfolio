/*
 * @topic T00840 Using DicePair with rollSeries() Nov 10 2015
 * @brief Validator class: user input handling and validation
*/
package airtrafficcontrol;

import java.util.Scanner;

public class Validator
{   
    public static Scanner sc = new Scanner( System.in );

    public static void pause()
    {
        System.out.println("Press Enter to continue...");
        Validator.sc.nextLine();
    }
    
    public static String getString(Scanner sc, String prompt)
    {
        String s = null;
        do
        {
            System.out.print(prompt);
            s = sc.nextLine();  // read user entry
            if (s.equals(""))
            {
                System.out.println("String cannot be left blank!");
            }
        } while (s.equals(""));

        return s;
    }
    
    public static char getLetterChar( Scanner sc, String prompt ){
        char c;
        for( ; ; ){
            System.out.print(prompt);
            c = sc.nextLine().toUpperCase().charAt(0);
            if ( c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F' || c == 'G' || c == 'H' || c == 'I' || c == 'J' || c == 'K' || c == 'L' || c == 'M' || c == 'N' || c == 'O' || c == 'P' || c == 'Q' || c == 'R' || c == 'S' || c == 'T' || c == 'U' || c == 'V' || c == 'W' || c == 'X' || c == 'Y' || c == 'Z'){
                break;
            }else{
                System.out.println("ERROR: Please enter a single letter. Try again.");
            }
            
        }
        
        return c;
    }
    
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
                    "Error! Minimum number is " + (min) + ".");
            else if (i > max)
                System.out.println(
                    "Error! Maximum number is " + (max) + ".");
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

//Shortcuts
public static char getLetterChar( String prompt ){
    return getLetterChar( sc, prompt );
}

public static int getInt(String prompt)
    { 
        return getInt(sc, prompt);
    }
   
public static int getInt(String prompt, int min, int max)
    {
        return getInt(sc, prompt, min, max);
    }

public static String getString( String prompt )
    {
        return getString(sc, prompt);
    }
public static int getIntMenu(String prompt,
    int min, int max)
{
    return getIntMenu(sc, prompt, min, max);
}
}