/*

Alexander J. Moulton
Homework 4, Problem 2
Due November 14, 2019

ORIGINAL ASSIGNMENT
Spring break is a semester away. You are planning a fabulous trip around Europe – map is shown
below. Your trip will only be conducted via Rail (you refuse to sail or use the bus, which will
eliminate some cities, but such is life). Being the awesome softwarer you are, you write a recursive
program to calculate the paths and distances between the cities connected by rail. Your program is to
ask the user for a start city and end city and print all possible paths between them with their lengths
utilizing recursion. As part of the submission provide a few runs for different start and finish cities.
Enjoy the trip!
Example: Berlin – Budapest
Berlin, Warsaw, Krakow, Budapest – 185m (only the number before the decimal point is considered,
so this can be an integer)
Berlin, Warsaw and points beyond paths contain many other possibilities going through Moscow and
further.
Berlin, Prague, Budapest – 160m
Berlin, Prague, Krakow, Budapest – 235m
Berlin, Prague, Vienna, Budapest – 185m
Berlin, Prague, Vienna, Zagreb, Belgrade, Budapest – 285m
Berlin, Prague, Vienna, Zagreb, Belgrade, Bucharest, Budapest – 360m
Berlin, Prague, Vienna, Zagreb, Belgrade, Sofia, Bucharest, Budapest – 385m


Dev Notes:

I used global constants in order speed program and abbreviate
function prototype. These 2D arrays should never be changed
in the rest of the program either as the map itself is constant.
Extra elements in the map were added for complexity and interest.

The recursive function takes in a starting position which jumps
to that respective row and traverses each element skipping those
that are 0's. Once a non-zero element is reached, the function 
recurses and jumps to the row of the element its currently in, 
passing the total distance plus the distance in the array. A 
path is only printed once the end is reached. If a path is invalid,
the program removes the last element from the visited array before
moving on to the next index in the row.

The paths are verbose and ay be hard to check.
For that I apologize.

-Alexander




*/


#include<stdio.h>
#include<stdlib.h>
#define MAX_CITY_NAME_LENGTH 20
#define NUMBER_OF_CITIES 20

//Global Constants 
const char cityNames[NUMBER_OF_CITIES][MAX_CITY_NAME_LENGTH] = {
		"Moscow", 		//0 (index)
		"Warsaw", 		//1
		"Krakow",		//2
		"Budapest",		//3
		"Prague",		//4
		"Vienna",		//5
		"Munich",		//6
		"Frankfurt",	//7
		"Zurich",		//8
		"Geneva",		//9
		"Venice",		//10
		"Cologne",		//11
		"Amsterdam",	//12
		"Paris",		//13
		"London",		//14
		"Nice",			//15
		"Genoa",		//16
		"Barcelona",	//17
		"Madrid",		//18
		"Granada"		//19
	};
	
	const int distances[NUMBER_OF_CITIES][NUMBER_OF_CITIES]={
	//	 	  0		1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19		
	/*0*/	{ 0,   125,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0	}, //Moscow
	/*1*/	{ 125,	0,	30,	0,	75,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0	}, //Warsaw
	/*2*/	{ 0,	30,	0,	85,	75,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0	}, //Krakow
	/*3*/	{ 0,	0,	85,	0,	85,	40,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0	}, //Budapest
	/*4*/	{ 0,	75,	75,	85,	0,	70,	75,100, 0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0	}, //Prague
	/*5*/	{ 0,	0,	0,	40,	70, 0, 100,	0, 130,	0, 100,	0,	0,	0,	0,	0,	0,	0,	0,	0	}, //Vienna
	/*6*/	{ 0,	0,	0,	0,	75,100, 0,110, 90,	0, 100,	0,	0, 155,	0,	0,	0,	0,	0,	0	}, //Munich
	/*7*/	{ 0,	0,	0,	0,	0, 100,110,	0, 125, 0,	0,	75,	0, 145,	0,	0,	0,	0,	0,	0	}, //Frankfurt
	/*8*/	{ 0,	0,	0,	0,	0, 130, 90,125,	0, 90,	0,	0,	0, 150,	0,	0,	0,	0,	0,	0	}, //Zurich
	/*9*/	{ 0,	0,	0,	0,	0,	0,	0,	0, 90,	0,	0, 220,	0, 125,	0, 110,	0, 125,	0,	0	}, //Geneva
	/*10*/	{ 0,	0,	0,	0,	0, 100,100,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0	}, //Venice
	/*11*/	{ 0,	0,	0,	0,	0,	0,	0,	75,	0, 220,	0,	0, 70, 105, 0,	0,	0,	0,	0,	0	}, //Cologne
	/*12*/	{ 0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	70,	0, 190,	0,	0,	0,	0,	0,	0	}, //Amsterdam
	/*13*/	{ 0,	0,	0,	0,	0,	0,155,145,150,125,	0,105,190,	0, 95,125,	0,140,	0,	0	}, //Paris
	/*14*/	{ 0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0, 95,	0,	0,	0,	0,	0,	0	}, //London
	/*15*/	{ 0,	0,	0,	0,	0,	0,	0,	0,	0,110,	0,	0,	0,125,	0,	0, 35,130,	0,	0	}, //Nice
	/*16*/	{ 0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0, 35,	0,	0,	0,	0	}, //Genoa
	/*17*/	{ 0,	0,	0,	0,	0,	0,	0,	0,	0,125,	0,	0,	0,140,	0,130,	0,	0,120,130	}, //Barcelona
	/*18*/	{ 0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,120,	0, 75	}, //Madrid
	/*19*/	{ 0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,130, 75,	0	},  //Granada
	};

//function prototypes
void travel(int start, int destination, int totalDistance, int visited[NUMBER_OF_CITIES], int visitedCount);
void printPath(int path[NUMBER_OF_CITIES]);


int main(){
	
	int start, destination, totalDistance = 0, visitedCount = 0;
	//array to keep track of cities visited, initialize all values to -1, a non-valid index value
	int visited[NUMBER_OF_CITIES] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}; 

	
	
	printf("\nPlease enter the corresponding number to where you would like to start:\n");
	printf("1. Moscow\n");
	printf("2. Warsaw\n");
	printf("3. Krakow\n");
	printf("4. Budapest\n");
	printf("5. Prague\n");
	printf("6. Vienna\n");
	printf("7. Munich\n");
	printf("8. Frankfurt\n");
	printf("9. Zurich\n");
	printf("10. Geneva\n");
	printf("11. Venice\n");
	printf("12. Cologne\n");		
	printf("13. Amsterdam\n");
	printf("14. Paris\n");	
	printf("15. London\n");		
	printf("16. Nice\n");			
	printf("17. Genoa\n");		
	printf("18. Barcelona\n");	
	printf("19. Madrid\n");		
	printf("20. Granada\n");
	
	for(;;){ //input validation loop
		scanf("%d", &start);
		if(start > 0 && start < 21){
			start--; //to give the index of choice
			break;
		}
	}
	
	printf("\nPlease enter the corresponding number to where you would like to end:\n");
	printf("1. Moscow\n");
	printf("2. Warsaw\n");
	printf("3. Krakow\n");
	printf("4. Budapest\n");
	printf("5. Prague\n");
	printf("6. Vienna\n");
	printf("7. Munich\n");
	printf("8. Frankfurt\n");
	printf("9. Zurich\n");
	printf("10. Geneva\n");
	printf("11. Venice\n");
	printf("12. Cologne\n");		
	printf("13. Amsterdam\n");
	printf("14. Paris\n");	
	printf("15. London\n");		
	printf("16. Nice\n");			
	printf("17. Genoa\n");		
	printf("18. Barcelona\n");	
	printf("19. Madrid\n");		
	printf("20. Granada\n");
	
	for(;;){ //input validation loop
		scanf("%d", &destination);
		if(destination > 0 && destination < 21 && destination != start+1){ 
		// make sure that the destination isn't the same as start
			destination--; //to give the index of choice
			break;
		}
	}
	
	travel(start, destination, totalDistance, visited, visitedCount);
	return 0;
}

void travel(int start, int destination, int totalDistance, int visited[NUMBER_OF_CITIES], int visitedCount){
	int i, j;
	int isVisited;
	
	visited[visitedCount] = start;
	visitedCount++;
	
	if(distances[start][destination] != 0){ //terminating condition
		printPath(visited);
		printf("%s- %dm", cityNames[destination], totalDistance + distances[start][destination]);
	}
	for(i=0; i < NUMBER_OF_CITIES-1; i++){
		//BEGIN skip visited city logic
		isVisited = 0; //set default visited "boolean" to false
		for(j=0; j < NUMBER_OF_CITIES; j++){ 
			if( visited[j] == -1){ //if not a valid index, exit loop
				break;
			}
			if (i == visited[j]){
				isVisited = 1;
				break;
			}
		}
		if(isVisited || i == destination){
			continue;
		} //skip to top of first for loop
		// END skip visited city logic	
		
		
		if(distances[start][i] != 0){ //if there is a path to take		
			travel(i, destination, (totalDistance + distances[start][i]), visited, visitedCount);
			continue;
		}
	}
	visited[visitedCount] = -1; //take away the last place visited once row iteration is complete
}

void printPath(int path[NUMBER_OF_CITIES]){
	int i;
	printf("\n");
	for(i=0; i < NUMBER_OF_CITIES-1; i++){
		if(path[i] == -1){
			break;
		}
		printf("%s, ", cityNames[path[i]]);
	}
}

