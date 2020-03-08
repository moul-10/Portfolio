package mazegenerator;

/*
 * This code was created by Clinton Rogers for the specific intent of being used by his students.
 * Derivative works based on this code may only be submitted to myCourses, and a copy local to the students
 * own computer. At no time should this code, derivative or otherwise, be shared with other students,
 * people, or posted online.

Name:       Alexander J. Moulton
Assignment: Project 1: MazeGenerator
Date:       03/07/2019
CIS 181



 */
import java.io.PrintWriter;
import java.util.Random;

public class MazeGenerator {

    private static Random rand = new Random();
    private static void fillMatrix(char[][] matrix) {
        //Fill matrix with non-space characters
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 'O';
            }
        }
    }

    private static void burrowMaze(char[][] matrix, int x, int y) {
        int choice = 0;
        matrix[x][y] = ' '; //clear space where the "worm" currently is
        while (hasNeighbors(matrix, x, y)) {
            choice = 1 + rand.nextInt(4);//randomly generate a choice of direction
            switch (choice) { //try moving north
                case 1:
                    if (hasNorthNeighbor(matrix, x, y)) {//only move north if possible
                        matrix[x - 1][y] = ' ';// erase character directly to north
                        burrowMaze(matrix, x - 2, y);// new coordinates 2 over, keep burrowing
                        break;
                    }
                    break;// if its' not possible to move in direction, generate new rand
                case 2: // try moving east
                    if (hasEastNeighbor(matrix, x, y)) {//only move east if possible
                        matrix[x][y + 1] = ' ';// erase character directly to east
                        burrowMaze(matrix, x, y + 2);// new coordinates 2 over, keep burrowing
                        break;
                    }
                    break;// if its' not possible to move in direction, generate new rand
                case 3: //try moving south
                    if (hasSouthNeighbor(matrix, x, y)) { //only move south if possible
                        matrix[x + 1][y] = ' '; // erase character directly to south
                        burrowMaze(matrix, x + 2, y); // new coordinates 2 over, keep burrowing
                        break;
                    }
                    break; // if its' not possible to move in direction, generate new rand
                case 4: //try moving west
                    if (hasWestNeighbor(matrix, x, y)) {//only move west if possible
                        matrix[x][y - 1] = ' '; // erase character directly to west
                        burrowMaze(matrix, x, y - 2);// new coordinates 2 over, keep burrowing
                        break;
                    }
                    break;// if its' not possible to move in direction, generate new rand
            }

        }

    }
        /*
        These methods (below) have been changed to be consistent with the format of
        array[row][column]. This format is used in order to remain structured and
        consistent throughout the program in its entirety. The names have been changed
        to reflect the functions of the methods as they pertain to their implementation
        The overall functionality is unchanged.
        */
    
    
    private static boolean hasNorthNeighbor(char[][] matrix, int x, int y) {
        return (x - 2) >= 1 && matrix[x - 2][y] != ' ';
    }

    private static boolean hasSouthNeighbor(char[][] matrix, int x, int y) {
        return (x + 2) < matrix.length && matrix[x + 2][y] != ' ';
    }

    private static boolean hasWestNeighbor(char[][] matrix, int x, int y) {
        return (y - 2) >= 1 && matrix[x][y - 2] != ' ';
    }
    

    private static boolean hasEastNeighbor(char[][] matrix, int x, int y) {
        return (y + 2) < matrix[x].length && matrix[x][y + 2] != ' ';
    }

    /*
    private static boolean hasNoNeighbors(char[][] matrix, int x, int y) {
        return !hasNorthNeighbor(matrix, x, y) && !hasEastNeighbor(matrix, x, y) && !hasNorthNeighbor(matrix, x, y) && !hasSouthNeighbor(matrix, x, y);
    }
    
    This method can be simplified as hasNeighbors, which uses less code and eliminates double negatives.
    while(!hasNoNeighbors(matrix, x, y) is changed to while(hasNeighbors( matrix, x, y)
    
    */

    private static boolean hasNeighbors(char[][] matrix, int x, int y) {
        return hasNorthNeighbor(matrix, x, y) || hasEastNeighbor(matrix, x, y) || hasWestNeighbor(matrix, x, y) || hasSouthNeighbor(matrix, x, y);
    }

    public static void generateMaze(char[][] matrix, int x, int y) {

        fillMatrix(matrix); //create matrix of a certain character
        createStart(matrix, x, y);
        burrowMaze(matrix, x, y);
        createEnd(matrix);
    }

    public static void createStart(char[][] matrix, int x, int y) {
        Random rand = new Random();
        int direction = 0;
        if (x - 1 == 0 && y - 1 == 0) { //create start or end point of maze
            direction = 1 + rand.nextInt(2);
            switch (direction) {
                case 1:
                    matrix[x - 1][y] = 'S';
                    break;
                case 2:
                    matrix[x][y - 1] = 'S';
                    break;
            }
        }
        if (x - 1 == 0 && y + 1 == matrix[x].length) {
            direction = 1 + rand.nextInt(2);
            switch (direction) {
                case 1:
                    matrix[x - 1][y] = 'S';
                    break;
                case 2:
                    matrix[x][y + 1] = 'S';
                    break;
            }
        }
        if (x + 1 == matrix.length && y + 1 == matrix[x].length) {
            direction = 1 + rand.nextInt(2);
            switch (direction) {
                case 1:
                    matrix[x + 1][y] = 'S';
                    break;
                case 2:
                    matrix[x][y + 1] = 'S';
                    break;
            }
        }
        if (x + 1 == matrix.length && y - 1 == 0) {
            direction = 1 + rand.nextInt(2);
            switch (direction) {
                case 1:
                    matrix[x + 1][y] = 'S';
                    break;
                case 2:
                    matrix[x][y - 1] = 'S';
                    break;
            }
        }
        if (x - 1 == 0 && (y - 1 != 0 && y + 1 != matrix[x].length)) {
            matrix[x - 1][y] = 'S';
        }
        if (y - 1 == 0 && (x - 1 != 0 && x + 1 != matrix.length)) {
            matrix[x][y - 1] = 'S';
        }
        if (x + 1 == matrix.length && (y - 1 != 0 && y + 1 != matrix[x].length)) {
            matrix[x + 1][y] = 'S';
        }
        if (y + 1 == matrix[x].length && (x - 1 != 0 && x + 1 != matrix.length)) {
            matrix[x][y + 1] = 'S';
        }
    }

    public static void createEnd(char[][] matrix) {
        /*
        The idea behind this method is to check the matrix and find where the
        starting point is. Once found, the method will randomly choose an exit
        point on the opposite side of the maze. If the selected point in the array has ' ' to the
        inside neighbor, the character of end point will be changed. Otherwise,
        the method searches for a new exit point.
         */
        int exit = 0;
        for (int x = 0; x < matrix.length; x++) {
            if (matrix[x][0] == 'S') { //if start is found on left side
                while (exit != 1) {
                    x = rand.nextInt(matrix.length);
                    if (matrix[x][matrix[x].length - 2] == ' ') {
                        matrix[x][matrix[x].length - 1] = 'E';
                        //exit is randomly generated on the right
                        exit = 1; //exit
                    }
                }
            }
        }

        if (exit != 1) { // if we already created an exit, skip
            for (int y = 0; y < matrix[0].length; y++) {// search top for the start
                if (matrix[0][y] == 'S') {
                    while (exit != 1) {
                        y = rand.nextInt(matrix[0].length);
                        if (matrix[matrix.length - 2][y] == ' ') {
                            matrix[matrix.length - 1][y] = 'E';
                            //exit is randomly generated on the bottom
                            exit = 1; //exit
                        }
                    }
                }
            }
        }
        if (exit != 1) { // if we already created an exit, skip
            for (int x = 0; x < matrix.length; x++) {
            if (matrix[x][matrix[x].length-1] == ' ') { //if start is found on right side
                while (exit != 1) {
                    x = rand.nextInt(matrix.length);
                    if (matrix[x][1] == 'S') {
                        matrix[x][0] = 'E';
                        //exit is randomly generated on the left
                        exit = 1; //exit
                    }
                }
            }
        }
        }
        if (exit != 1) { // if we already created an exit, skip
            for (int y = 0; y < matrix[0].length; y++) {// search bottom for the start
                if (matrix[matrix[0].length-1][y] == 'S') {
                    while (exit != 1) {
                        y = rand.nextInt(matrix[0].length);
                        if (matrix[1][y] == ' ') {
                            matrix[0][y] = 'E';
                            //exit is randomly generated on the top
                            exit = 1; //exit
                        }
                    }
                }
            }
        }
    }

    public static void printMatrix(char[][] matrix, String toFile) {
        //if a file name is provided, print to the file
        /*
        This method was changed to be consistent with the utilization of the
        burrowMaze method and getting the maze dimensions from the user.
        The for loops were simply flipped to make the structure of the code
        consistent with how the rest of the program is written. The overall
        functionality is unchanged.
        */
        if (toFile != "") {
            try {
                PrintWriter writer = new PrintWriter(toFile);
                //Fill matrix with non-space characters
                for (int x = 0; x < matrix.length; x++) {
                    for (int y = 0; y < matrix[x].length; y++) {
                        writer.print(matrix[x][y]);
                    }
                    writer.println();
                }
                writer.close();
            } catch (Exception e) {
                System.out.println("Exception e caught:" + e.getMessage());
            }
        } else //otherwise, print to the screen
        {
            //Fill matrix with non-space characters
            /*
            NOTE: This method was originally written with the terminating
            condition of x < matrix[0].length in the nested for loop.
            This caused an overflow error if the input
            values for x and y are such that x != y. It has been corrected.
             */
            for (int x = 0; x < matrix.length; x++) {
                for (int y = 0; y < matrix[x].length; y++) {
                    System.out.print(matrix[x][y]);
                }
                System.out.println();
            }
        }
    }
}
