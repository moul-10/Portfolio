/*

Alexander J. Moulton
Homework 5, Problem 2
Due November 27, 2019

ORIGINAL ASSIGNMENT:
Write a function to generate 30 random floats. Write a function to allocate memory for a jagged array
(as presented in class) – the structure is described two sentences further. Write a function to use the
random numbers to populate the jagged array (as presented in class). The first row should contain 10
numbers, the second row – 5 numbers, third row – 2 numbers, fourth row – 7 numbers, fifth row – 6
numbers. Then, write a function that will sort the first numbers of every row (utilize quick sort – the
sort itself is featured at
https://www.tutorialspoint.com/data_structures_algorithms/quick_sort_algorithm.htm ). Then, write a
function to sort the second numbers of every row (utilize quick sort). Then, utilize the quick sort
function to sort the third numbers in every row. Then, again quick sort for the fourth numbers. Keep
going like this till you reach the 10th number of the rows. Finally, write a function to print the sorted
jagged array row by row.


*/


#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define ROWS 5
#define NUM_FIRST_ROW 10
#define NUM_SEC_ROW 5
#define NUM_THIRD_ROW 2
#define NUM_FOURTH_ROW 7
#define NUM_FIFTH_ROW 6

//function prototypes
float** makeJaggedArray(int, int, int, int, int);
void getFloats(float***, int, int, int, int, int);
void free2D(float**);
void printJags(float**, int, int, int, int, int);
void getColumnArray(float***, int);
void swap( float*, float*);
int partition(float**, int, int);
void quickSort(float**, int, int);


int main(){
	srand(time(NULL));
	int i;
	float** jaggedArray = makeJaggedArray(NUM_FIRST_ROW, NUM_SEC_ROW, NUM_THIRD_ROW, NUM_FOURTH_ROW, NUM_FIFTH_ROW);
	getFloats(&jaggedArray, NUM_FIRST_ROW, NUM_SEC_ROW, NUM_THIRD_ROW, NUM_FOURTH_ROW, NUM_FIFTH_ROW);
	printf("\nUNSORTED:\n");
	printJags(jaggedArray, NUM_FIRST_ROW, NUM_SEC_ROW, NUM_THIRD_ROW, NUM_FOURTH_ROW, NUM_FIFTH_ROW);
	printf("\n\n");
	
	
	for(i=1; i<= NUM_FIRST_ROW; i++){
		getColumnArray(&jaggedArray, i);
		printf("Column %d Sorted:\n", i);
		printJags(jaggedArray, NUM_FIRST_ROW, NUM_SEC_ROW, NUM_THIRD_ROW, NUM_FOURTH_ROW, NUM_FIFTH_ROW);
		printf("\n\n");
	}		
	
	free2D(jaggedArray);
	return 0;
}



float** makeJaggedArray(int first, int sec, int third, int fourth, int fifth){
	float** jag =(float**) calloc(ROWS+1, sizeof(float*));
	jag[0] = (float*) calloc(first, sizeof(float));
	jag[1] = (float*) calloc(sec, sizeof(float));
	jag[2] = (float*) calloc(third, sizeof(float));
	jag[3] = (float*) calloc(fourth, sizeof(float));
	jag[4] = (float*) calloc(fifth, sizeof(float));
	jag[5] = NULL;
	return jag;
	
}



void free2D(float** jags){
	int i;
	for(i=0; i<ROWS; i++){
		free(jags[i]);
	}
	free(jags);
	
}



void getFloats(float*** jags,  int first, int sec, int third, int fourth, int fifth){
	int i, j, k;
	for(i=0;; i++){
		if((*jags)[i]){ 
			if(i == 0){
				k = first;
			}else if(i == 1){
				k = sec;
			}
			else if(i == 2){
				k = third;
			}
			else if(i == 3){
				k = fourth;
			}
			else if(i == 4){
				k = fifth;
			}
			for(j=0; j < k; j++){
				(*jags)[i][j] = 100*((float)rand()/(float)(RAND_MAX));
			}
		}else{break;}
	}
}

void printJags(float** jags, int first, int sec, int third, int fourth, int fifth){
	int i, j, k;
	for(i=0;; i++){
		if(jags[i]){
			printf("\nROW %d: ", i+1);
			if(i == 0){
				k = first;
			}else if(i == 1){
				k = sec;
			}
			else if(i == 2){
				k = third;
			}
			else if(i == 3){
				k = fourth;
			}
			else if(i == 4){
				k = fifth;
			}
			for(j=0; j < k-1; j++){
				printf("%lf, ", jags[i][j]);
			}
			printf("%lf\n", jags[i][j]);
		}else{break;}
	}
}	


void getColumnArray(float*** jags, int column){
	int i, k;
	float* columnNums;
	switch(column){
		case 1 ... 2:
			columnNums = (float*)calloc(ROWS, sizeof(float));
			for(i=0; i<ROWS; i++){
				columnNums[i] = (*jags)[i][column-1];
			}
			quickSort(&columnNums, 0, ROWS-1);
			for(i=0; i<ROWS; i++){
				(*jags)[i][column-1] = columnNums[i];
			}
			break;
		case 3 ... 5:
			columnNums = (float*)calloc(ROWS-1, sizeof(float));
			k=0;
			for(i=0; i<ROWS; i++){
				if(i == 2){
					continue;
				}
				columnNums[k++] = (*jags)[i][column-1];
			}
			quickSort(&columnNums, 0, ROWS-2);
			k=0; //reset k 
			for(i=0; i<ROWS; i++){
				if( i == 2 ){
					continue;
				}
				(*jags)[i][column-1] = columnNums[k++];
			}
			break;
		case 6:
			columnNums = (float*)calloc(ROWS-2, sizeof(float));
			k=0;
			for(i=0; i<ROWS; i++){
				if(i == 1 || i == 2){
					continue;
				}
				columnNums[k++] = (*jags)[i][column-1];
			}
			quickSort(&columnNums, 0, ROWS-3);
			k=0; //reset k 
			for(i=0; i<ROWS; i++){
				if( i == 1 || i == 2 ){
					continue;
				}
				(*jags)[i][column-1] = columnNums[k++];
			}
			break;
		case 7:
			columnNums = (float*)calloc(ROWS-2, sizeof(float));
			k=0;
			for(i=0; i<ROWS; i++){
				if(i == 1 || i == 2 || i == 4){
					continue;
				}
				columnNums[k++] = (*jags)[i][column-1];
			}
			quickSort(&columnNums, 0, ROWS-4);
			k=0; //reset k 
			for(i=0; i<ROWS; i++){
				if( i == 1 || i == 2 || i == 4){
					continue;
				}
				(*jags)[i][column-1] = columnNums[k++];
			}
			break;
		case 8 ... 10:
		columnNums = (float*)calloc(ROWS-5, sizeof(float));
			k=0;
			for(i=0; i<ROWS; i++){
				if(i == 1 || i == 2 || i ==3 || i == 4){
					continue;
				}
				columnNums[k++] = (*jags)[i][column-1];
			}
			quickSort(&columnNums, 0, ROWS-5);
			k=0; //reset k 
			for(i=0; i<ROWS; i++){
				if( i == 1 || i == 2 || i == 3 || i == 4){
					continue;
				}
				(*jags)[i][column-1] = columnNums[k++];
			}
			break;
		default: //default should NEVER be reached
			printf("\n...Something went terribly wrong...\n");
	}
	free(columnNums);
}

// Quicksort Functions
void swap( float* one, float* two){
	float temp;
	temp = *one;
	*one = *two;
	*two = temp;
}

int partition(float** array, int lowIndex, int highIndex){
	float pivot;
	int index, i;
	pivot = (*array)[highIndex];
	
	index = (lowIndex-1);
	
	for(i=lowIndex; i<= highIndex-1; i++){
		if((*array)[i] < pivot){
			index++;
			swap(&(*array)[index], &(*array)[i]);
		}
	}
	swap(&(*array)[index + 1], &(*array)[highIndex]);
	return index+1;
}

void quickSort(float** array, int lowIndex, int highIndex){
	if(lowIndex < highIndex){
		int partitionIndex = partition(array, lowIndex, highIndex);
		quickSort(array, lowIndex, partitionIndex-1);
		quickSort(array, partitionIndex+1, highIndex);
	}
}