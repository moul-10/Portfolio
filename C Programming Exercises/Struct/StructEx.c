/*

Alexander J. Moulton
Homework 6
Due December 8, 2019

ORIGINAL ASSIGNMENT:
Implement a Car Distribution problem where your program should be able to input the information
from “car.txt” into an array of structures and then give users the option to search for a particular car,
view the entire inventory or place an order. The program’s interface should be such that a user can
perform either one of these tasks as much as they want until they decide to quit the program.
1. Searching for a car should prompt the user for an ID number and the result should display its
color and price, if it is in your inventory.
2. A view of the entire inventory will display all the cars with their ID number, price and quantity
in ascending order by price. This sorting should be done using Quick Sort from Homework 5.
3. When placing an order an invoice of the order should be printed to the screen.
Please note you MUST use the car.txt file provided:
The file structure is set up such that the first element of the file is a number containing the total number
cars. With this number you must once again dynamically allocate (malloc or calloc) the appropriate
array size. After that each beer will be listed in the following format
1. Car Name
2. Car ID (7 digits)
3. Car Color
4. Car Price


DEV NOTES: Programs works as assigned. The use of quickSort and the idea of fflush
were gathered from geeksforgeeks.org. The user can perform functions until they
decide to quit.

The one notable "bug" is that the id number is stored as int. For better
performance, this probably should have been a string so that every digit was
considered. In other words, the ID number 0000000 will come up if you simply search
for "0".


*/


#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define MAX_NAME 14
#define MAX_COLOR 14
#define BUFFER_SIZE 32

typedef struct{
	char name[MAX_NAME];
	int idNum;
	char color[MAX_COLOR];
	double price;
}CAR;

void printCar(CAR);
void searchCar(int, CAR**);
void sortCars(int, CAR**);
void menu(int, CAR**);
void sortCars(int, CAR**);
void swap( CAR*, CAR*);
int partition(CAR**, int, int);
void quickSort(CAR**, int, int);
void placeOrder(int*, CAR**);

int main(){
	int i;
	int numCars;
	char buffer[BUFFER_SIZE];
	
	FILE* input;
	input = fopen("car.txt", "r");
	
	if(input == NULL){
		printf("\nERROR! FILE NOT FOUND!\n");
		return 0;
	}
	
	fgets(buffer, BUFFER_SIZE, input);
	numCars = atoi(buffer);
	CAR* cars = (CAR*)calloc(numCars, sizeof(CAR));
	
	for(i =0; i<numCars; i++){
		fgets(cars[i].name, MAX_NAME,input);
		strtok(cars[i].name, "\n"); //to take out the new line character and smoother printing
		
		fgets(buffer, BUFFER_SIZE, input);
		cars[i].idNum = atoi(buffer);
		
		fgets(cars[i].color, MAX_COLOR, input);
		strtok(cars[i].color, "\n"); //again used here
		
		fgets(buffer, BUFFER_SIZE, input);
		cars[i].price = atof(buffer);
		
	}
	fclose(input);
	menu(numCars, &cars);
	
	free(cars);
}

void menu(int numCars, CAR** cars){
	int choice = 4;// intiaily set choice to non-valid, non-zero number
	int i; //used for looping
	
	char search[MAX_NAME];
	char choiceSTR[4];
	
	
	
	while(choice != 0){
		printf("\nPlease enter the number of your choice and then press ENTER. (Enter 0 to exit)\n\t1. Print Car Listing\n\t2. Place Order\n\t3. Search Car By ID\n\n-->");
		fflush(stdin);
		fgets(choiceSTR, 4, stdin); //using fgets helps avoid skipping entry with scanf
		choice = atoi(choiceSTR);
		if(choice == 0){
			printf("\nGOODBYE!\n");
			return;
		}else if(choice == 1){
			sortCars(numCars, cars);//sort cars before printing one by one
			for(i=0; i<numCars ; i++){ //start at last index to print in ASCENDING order
				printCar((*cars)[i]);
			}
		}else if(choice == 2){
			placeOrder(&numCars, cars);
		}else if(choice == 3){
			searchCar(numCars, cars);
			
		}
		
	}
}

void placeOrder(int* numCars, CAR** cars){
	int i, j=0, selected;
	CAR* temp;
	
	
	printf("\nWhat car would you like to purchase? (enter the number that corresponds to vehicle the press ENTER)\n");
	for(i=0; i<(*numCars); i++){ // print out the menu of cars to select from
		printf("\n%d.\tCAR NAME:\t\t%s", i+1, (*cars)[i].name);
		printf("\n\tCAR ID #:\t%07d", (*cars)[i].idNum);
		printf("\n\tCOLOR:\t\t%s", (*cars)[i].color);
		printf("\n\tPRICE:\t\t$ %.2lf\n\n", (*cars)[i].price);
	}
		
	do{
		scanf("%d", &selected);
		selected--;
	}while(selected < 0 || selected > (*numCars));

	//Print invoice to screen
	printf("\n---------------------------------------------------");
	printf("\n-----------------CUSTOMER INVOICE------------------");
	printf("\n---------------------------------------------------");
	printf("\n\n\tCAR NAME:\t%s", (*cars)[selected].name);
	printf("\n\tCAR ID #:\t%07d", (*cars)[selected].idNum);
	printf("\n\tCOLOR:\t\t%s", (*cars)[selected].color);
	printf("\n---------------------------------------------------");
	printf("\n\t\t\tPRICE:\t\t$ %.2lf", (*cars)[selected].price);
	printf("\n\t\t\tTAX:\t\t$ %.2lf", 0.065*(*cars)[selected].price);
	printf("\n\t\t\tTOTAL:\t\t$ %.2lf\n\n", 1.065*(*cars)[selected].price);
	printf("INVENTORY UPDATED\n");
	
	temp = (CAR*)calloc((*numCars)-1, sizeof(CAR));
	
	for(i=0; i<(*numCars); i++){ //loop to update the inventory, since one of the cars "sold" we can't resell it
		if(i == selected){
			continue;//do nothing, skip the element
		}
		temp[j] = (*cars)[i];
		j++;
		
	}
	
	(*numCars)=(*numCars)-1;
	(*cars) = (CAR*)realloc(*cars, (*numCars)*sizeof(CAR)); //set cars to the updated array sizeof
	cars = &temp;
	
}


void printCar(CAR car){ //print all the information for a given car
	printf("\n\nCAR NAME:\t%s", car.name);
	printf("\nCAR ID #:\t%07d", car.idNum);
	printf("\nCOLOR:\t\t%s", car.color);
	printf("\nPRICE:\t\t$ %.2lf\n\n", car.price);
}

void searchCar(int numCars, CAR** cars){
	int searchNum, i, found = 0;
	
	printf("\nPlease enter the ID# for the car you're searching for: ");
	scanf("%d", &searchNum);
	printf("\n");
	for(i=0; i<numCars; i++){ //first count the number of occurences 
		if((*cars)[i].idNum == searchNum){
			found++;
		}
	}
	if(found > 0){ //if theres any matches, go through and print them out
		printf("\n%d RESULT(S) FOUND:\n", found);
		
		for(i=0; i<numCars; i++){
			if((*cars)[i].idNum == searchNum){
				printf("\nCOLOR:\t\t%s", (*cars)[i].color);
				printf("\nPRICE:\t\t$%.2lf\n", (*cars)[i].price);
			}
		}
	}else{ printf("\nNO VEHICLE WITH THAT ID# FOUND!\n"); } //if not print that no vehicle was found
	
	
}




void sortCars(int numCars, CAR** cars){ //shortcut for quicksorting cars
	quickSort(cars, 0, numCars-1);
}

//sorting functions referenced from GeeksForGeeks.org
//code modified to suit usage with cars struct
void swap( CAR* one, CAR* two){
	CAR temp;
	temp = *one;
	*one = *two;
	*two = temp;
}

int partition(CAR** cars, int lowIndex, int highIndex){
	double pivot;
	int index, i;
	pivot = (*cars)[highIndex].price;
	
	index = (lowIndex-1);
	
	for(i=lowIndex; i<= highIndex-1; i++){
		if((*cars)[i].price < pivot){
			index++;
			swap(&(*cars)[index], &(*cars)[i]);
		}
	}
	swap(&(*cars)[index + 1], &(*cars)[highIndex]);
	return index+1;
}

void quickSort(CAR** cars, int lowIndex, int highIndex){
	if(lowIndex < highIndex){
		int partitionIndex = partition(cars, lowIndex, highIndex);
		quickSort(cars, lowIndex, partitionIndex-1);
		quickSort(cars, partitionIndex+1, highIndex);
	}
}