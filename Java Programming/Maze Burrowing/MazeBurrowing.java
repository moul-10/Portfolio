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

This project is designed to demonstrate an understanding of recursive methods.
The generateMaze method calls upon four methods in order to create the maze in 
its entirety and takes a 2-d array and two ints, which will be the starting point
of the burrowing. The program begins by filling a 2-d array with a certain character.
Then the createStart method is passed the two ints to create a starting point along
the perimeter of the maze. This is important because, otherwise, the user can only
aimlessly wander through the maze without bearing as to the goal. The main 
MazeGenerator method, burrowMaze, is recursive as it calls upon itself until a 
terminating condition is reached. The implicit terminating condition in
this case is when there is nowhere else to borrow (hasNoNeighbors). Once this 
condition is met, the burrowing is complete and the createEnd method is called.
This method searches for the starting point and then randomly selects a valid end
point on the opposite side of the maze.

Randomness is a key aspect of this program, and its only weakness is in that.
There exists an extremely small possibility (mathematically approaching zero)that
because of the way the burrowMaze method is written to be random, the program will
be caught in an infinite loop, constantly trying and failing to burrow in one
direction. This probability is extremely low, but should be a consideration for 
further applications.
 */

import java.util.Scanner;


public class MazeBurrowing {


	public static void main(String[] args) {
            
            
		Scanner kb = new Scanner(System.in);
		
                System.out.println("How high would you like the maze to be? (Even numbers will be converted to odd)");
		int x = kb.nextInt();
                
		System.out.println("How wide would you like the maze to be? (Even numbers will be converted to odd)");
		int y = kb.nextInt();
		
		
		
		//Make sure the sizes are not negative
		x = Math.abs(x);
                y = Math.abs(y);
		
		
		//and make sure they are odd values
		if(x%2 == 0)
			x++;
		
                if(y%2 == 0)
			y++;
		
                //Create the grid
		char [][] grid = new char[x][y];
		MazeGenerator.generateMaze(grid, 1, 1);

		//Print the grid to a textfile or "" to print to screen
		//MazeGenerator.printMatrix(grid,"Maze3.txt"); //commented out to prevent overwriting already generated mazes
                MazeGenerator.printMatrix(grid,"");
		
		System.out.println("Done!");
		
	}

}
