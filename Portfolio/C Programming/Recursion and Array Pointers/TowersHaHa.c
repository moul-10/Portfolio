/*

Alexander J. Moulton
HW 3, Problem 2
Due Saturday, November 2nd

ORIGINAL ASSIGNMENT:
The Towers of HaHa problem is the same as the Towers of Hanoi problem. However, the disks are
numbered 1 through n; odd-numbered disks are red, and even-numbered ones are yellow. The disks are
initially on tower 1 in the order 1 through n from top to bottom. The disks are to be moved to tower 2,
and at no time should a disk sit on top of a disk that has the same color. The initial and final disk orders
are the same. Write a program to move the disks from tower 1 to tower 2 using tower 3 for
intermediate storage. Base it on the recursive Towers of Hanoi below, but make the necessary changes
required by the Towers of HaHa. The code should remain recursive.

*/


#include<stdio.h>

void towersHaha(int, int, int, int);


int main(){
	int numDisks;
	printf("Please enter the number of disks then press ENTER:\t");
	scanf("%d", &numDisks);
	printf("Here is the list of moves to solve the puzzle:\n\n");
	towersHaha(numDisks, 1, 2, 3);
	printf("\n");
	return 0;
}

void towersHaha(int numDisks, int startPeg, int endPeg, int auxPeg){
	/*
	
	1.	Terminating condition is if the disk is the top disk.
		This represents the bottom condition of the initial function call,
		which represents the first move in the sequence.
	
	2. 	All of the middle disks are then moved over to aux peg via the
		first recursive call.
		
	3.	We can add in a single print statement for the final disk as it's only
		moved once.
		
	4. 	The first disk is moved to final peg via the second recursive call
		using the starting peg as the aux.
	
	5.	It's important to not that the color requirement is a red herring and does
		not alter the program requirements.
	
	*/
	if (numDisks == 1){
		printf("Move disk 1 from peg %d to peg %d.\n", startPeg, endPeg);
		return;
	}
	towersHaha(numDisks-1, startPeg, auxPeg, endPeg);
	printf("Move disk %d from peg %d to peg %d.\n", numDisks, startPeg, endPeg);
	towersHaha(numDisks-1, auxPeg, endPeg, startPeg);
	
	
}
