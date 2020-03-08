/*

Alexander J. Moulton
HW 3, Problem 2
Due Saturday, November 2nd


ORIGINAL ASSIGNMENT:
You just moved in a new house, but the closet is in tragic shape. It is a walk-in closet and you want to
redo it with the wire mesh racks sold in HomeDepot. So, you create your design, measure the needed
rack lengths and go to the store. The racks, however, are sold in one fixed size. Indeed, you can cut the
stock racks to fit your measures. In order to save as much money and material, you must figure out the
minimum number of mesh racks to buy to satisfy your design.
For example, if your design contains [ 4, 3, 4, 1, 7, 8 ] and the stock rack length is 10, you can
purchase three racks and divide them as follows:
Rack 1: 4, 4, 1
Rack 2: 3, 7
Rack 3: 8
Doing so leaves you with two small remnants left over. There are other possible arrangements that also
fit into three racks, but it cannot be done with fewer.
This one is tricky, and we expect you will mull over it for a while before you have a solution.
Here are a few hints and specifications:
• You may assume that all the requests are positive and do not exceed the stock length. In the
worst case, you will need N stock racks, where N is the size of the vector.
• You do not need to report how to cut the racks, just the number of stock racks needed.
• There are several different approaches for decomposing this problem and for managing the
state. As a general hint, given the current state and a request, what options do you have for
satisfying that request and how does choosing that option change the state? Making a choice
should result in a smaller, similar subproblem that can be recursively solved. How can you that
solution to solve the larger problem?
*/


#include<stdio.h>
#define ARRAY_SIZE 32
#define STOCK_LENGTH 10

void cutRacks(int*, int[], int*, int*);
int max(int[], int);


int main(){
	
	int racks = 1;
	//int shelfSizes[ARRAY_SIZE] = {4,3,4,1,7,8}; //STOCK ARRAY COMMENTED OUT TO USE USER-DECIDED ARRAY
	int shelfSizes[ARRAY_SIZE] = {};
	int stockLength = STOCK_LENGTH;
	int isEndRack = 1;
	int i;
	
	//get user input to set array
	printf("Please enter each length followed by ENTER. Enter 0 then ENTER when done.\n");
	for( i=0; i<ARRAY_SIZE; i++ ){
		scanf("%d", &shelfSizes[i]);
		if(shelfSizes[i] == 0){
			break;
		}
	}
	
	cutRacks(&stockLength, shelfSizes, &racks, &isEndRack);
	printf("\n");
	
	
}



void cutRacks(int* remaining, int lengths[], int* racks, int* isEndRack){
	
	int i = 0; //reassigned for looping for each condition
	int size = 0; //used as the total number of elements and counted to use for terminating condition
	int maxIndex;
	while( lengths[i] != 0){ //count the number of elements in the array
		i++;
		size++;
	}
	
	int copy[size];
	
	if(*isEndRack){
		if(*racks> 1){
			printf("\t");//used for formatting output
		}
		printf("Rack %d: ",*racks);
	}
	if( max(lengths, *remaining) == -1){
		if(size == 0){ //terminating condition is when there are no more elements left in the array
			return;//in other words, when the array is all zeros, and thus the size = 0
		}
		*racks = *racks + 1;
		*remaining = 10;
		*isEndRack = 1; //mark end of rack so rack number is printed at the beginning of each sequence
		cutRacks(remaining, lengths, racks, isEndRack); 
	}
	else if(*remaining == 10){
		*remaining = *remaining - lengths[0]; // if its a new rack, use the first element as the first size
		for( i = 0; i < size; i++){
			copy[i] = lengths[i+1]; // set new array to the old array without first element
		}
		
		printf("%d ", lengths[0]); //print number
		*isEndRack = 0; //it is not the end of rack, set "false"
		cutRacks(remaining, copy, racks, isEndRack);

	
	}
	else if(*remaining < 10 && size > 0){ 
		
		maxIndex = max(lengths, *remaining); // since we check if the max is under the remaining length in the first
						     // condition, we know that it isn't a new rack but we can still cut
		*remaining = *remaining - lengths[maxIndex];
		//printf("remaining: %d ", *remaining);
		for( i = 0; i < maxIndex; i++){
			copy[i] = lengths[i]; // set new array to the old array without the largest element
		}
		for(i = maxIndex; i < size; i++){ //skip the largest element
			copy[i] = lengths[i+1];
		}
		printf("%d ", lengths[maxIndex]); // print the largest number in the array that is still under the remaining length
		*isEndRack = 0;// not end of rack, set "false"
		cutRacks(remaining, copy, racks, isEndRack);
		
	}
}


//A function that returns the index of the largest number in an array thats also less than a constraint
int max(int lengths[], int bound){
	int i;
	int maxNum = 0; //maxNum assigned to 0 if no numbers are less than bound
	int maxIndex = -1;
	
	for(i=0; i < ARRAY_SIZE; i++){
		if(lengths[i] == 0){
			break;
		}
		if(lengths[i] > maxNum && lengths[i] <= bound){
			maxNum = lengths[i]; //if number is greater than previous max but less than bound, set the max
			maxIndex = i;
		}
	}
	return maxIndex; //return index of largest element if there is a largest
	// if no numbers are less than bound, -1 will be returned
}


