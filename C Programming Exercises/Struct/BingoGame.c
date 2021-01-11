/*

Alexander J. Moulton
Homework 5, Problem 1
Due November 27, 2019

ORIGINAL ASSIGNMENT:
Write an interactive C program that will simulate a game of BINGO. Print each letter-number
combination as it is drawn (randomly generated). Be sure that no combination is drawn more than
once. Remember that each of the letters B-I-N-G-O corresponds to a certain range of numbers, as
indicated below.
B: 1-15
I: 16-30
N: 31-45
G: 46-60
O: 61-75
Each player will have a card with 5 rows, labeled B-I-N-G-O. Each letter must serve as a pointer to an
array of numbers – the 5 numbers within the range indicated above. No two players will have the same
card. The first player to have one entire row of numbers drawn (either vertically, horizontally or
diagonally) wins.
Implementation details: there are to be 3 players. Do not convert text to numbers or vise versa. Read
each type as it is intended to be.

*/


#include <stdio.h>
#include <stdlib.h>




//Bingo Card Struct
typedef struct {
	int card[5][5]; //5 "Letters" pointing to 5 integers each, initialized to zero for uniqueness checking in populateCards
	char markers[5][5]; // keeps track of what positions on the card have been called
	int playerNum;
}BingoCard;

//global constants
const char letters[5] = {'B', 'I', 'N', 'G', 'O'};

//function prototypes
void populateCards(BingoCard*, BingoCard*, BingoCard*, int***);
void printCards(BingoCard, BingoCard, BingoCard, int[75], int[75]);
void updateCards(BingoCard*, BingoCard*, BingoCard*, int***, int, int, int(*)[75], int(*)[75]);
int drawBall(int***);
int isWinner(BingoCard*);
void playBingo(BingoCard*, BingoCard*, BingoCard*, int***);
int getLetter(int);

//MAIN
int main(){
	srand(time(NULL));
	
	BingoCard player1, player2, player3;
	player1.playerNum = 1;
	player2.playerNum = 2;
	player3.playerNum = 3;
	int** ballsDrawn = (int**) calloc(5, sizeof(int*));
	
	int i;
	for(i = 0; i < 5; i++){
		ballsDrawn[i] = (int*) calloc(15, sizeof(int));
	}
	
	
	playBingo(&player1, &player2, &player3, &ballsDrawn);
	
	
	for(i = 0; i < 5; i++){
		free(ballsDrawn[i]);
	}
	free(ballsDrawn);
	return 0;
}//END MAIN


void populateCards(BingoCard* one, BingoCard* two, BingoCard* three, int*** ballsDrawn){
	
	int i, j, k;
	
	for (i=0; i<5; i++){
		for(j=0; j<5; j++){
			if( j > 0 ){ //after creating the first numbers for each letter, ensure subsequent numbers are unique
				k = j-1;
				one->card[i][j] = (rand() % ((i+1)*15 - (i*15))+i*15+1);
				one->markers[i][j] =' ';
				do{
					if(one->card[i][j] == one->card[i][k]){
						one->card[i][j] = (rand() % ((i+1)*15 - (i*15))+i*15+1);
						k=j-1;
					}else{k--;}
				}while(k > -1);
				
				k = j-1;
				two->card[i][j] = (rand() % ((i+1)*15 - (i*15))+i*15+1);
				two->markers[i][j] =' ';
				do{
					if(two->card[i][j] == two->card[i][k]){
						two->card[i][j] = (rand() % ((i+1)*15 - (i*15))+i*15+1);
						k=j-1;
					}else{k--;}
				}while(k > -1);
				
				k = j-1;
				three->card[i][j] = (rand() % ((i+1)*15 - (i*15))+i*15+1);
				three->markers[i][j] =' ';
				do{
					if(three->card[i][j] == three->card[i][k]){
						three->card[i][j] = (rand() % ((i+1)*15 - (i*15))+i*15+1);
						k=j-1;
					}else{k--;}
				}while(k > -1);
			
			}else{ //for first case, just generate a random number a piece
			one->card[i][j] = (rand() % ((i+1)*15 - (i*15))+i*15+1);
			one->markers[i][j] =' ';
			two->card[i][j] = (rand() % ((i+1)*15 - (i*15))+i*15+1);
			two->markers[i][j] =' ';
			three->card[i][j] = (rand() % ((i+1)*15 - (i*15))+i*15+1);
			three->markers[i][j] =' ';
			}
		}
	}
	k = 0;
	for(i=0; i<5; i ++){
		for(j=0; j<15; j++){
			(*ballsDrawn)[i][j] = ++k; //populate each element of the ballsDrawn array
		}
	}
	
}

void printCards(BingoCard one, BingoCard two, BingoCard three, int pulledLetters[75], int pulledNumbers[75]){
	int i, j;
	printf("\n _______________________________________");
	printf("\n|               CARD ONE                |");
	printf("\n|_______________________________________|");
	for( i = 0; i<5; i++){
		printf("\n|\t\t\t\t\t|\n|");
		for(j=0; j<5; j++){
			printf("%c|%c%d|\t", one.markers[i][j], letters[i], one.card[i][j]);
		}
		printf("|");
	}
	printf("\n|_______________________________________|");
	
	printf("\n _______________________________________");
	printf("\n|               CARD TWO                |");
	printf("\n|_______________________________________|");
	for( i = 0; i<5; i++){
		printf("\n|\t\t\t\t\t|\n|");
		for(j=0; j<5; j++){
			printf("%c|%c%d|\t", two.markers[i][j], letters[i], two.card[i][j]);
		}
		printf("|");
	}
	printf("\n|_______________________________________|");
	
	printf("\n _______________________________________");
	printf("\n|              CARD THREE               |");
	printf("\n|_______________________________________|");
	for( i = 0; i<5; i++){
		printf("\n|\t\t\t\t\t|\n|");
		for(j=0; j<5; j++){
			printf("%c|%c%d|\t", three.markers[i][j], letters[i], three.card[i][j]);
		}
		printf("|");
	}
	printf("\n|_______________________________________|");
	
	printf("\nBalls picked: ");
	for(i=0; i<75; i++){
		if(pulledNumbers[i] == 0){
			break;
		}
		printf("%c%d, ", letters[pulledLetters[i]], pulledNumbers[i]);
	}
	printf("\n\n");
	
}

void updateCards(BingoCard* one, BingoCard* two, BingoCard* three, int*** ballsDrawn, int letter, int number, int (*pulledLetters)[75], int (*pulledNumbers)[75]){
	int i, count = 0;
	for(i = 0; i < 5; i++){
		if( one->card[letter][i] == number){
			one->markers[letter][i] = 'X';
		}
		if( two->card[letter][i] == number){
			two->markers[letter][i] = 'X';
		}
		if( three->card[letter][i] == number){
			three->markers[letter][i] = 'X';
		}
	}
	for(i=0; i<75; i++){
		if((*pulledNumbers)[i] == 0){
			(*pulledNumbers)[i] = number;
			(*pulledLetters)[i] = letter;
			break;
		}
		
	}
}

int drawBall(int*** ballsDrawn){
	int letter, index, ball;
	for(;;){
		letter = rand() % 5; // generate random integer between 0 and 4
		index = rand() %15; // generate random integer between 0 and 14 for index of number in letter row
		if( (*ballsDrawn)[letter][index] != 0 ){
			ball = (*ballsDrawn)[letter][index]; //set the ball equal to the array
			(*ballsDrawn)[letter][index] = 0; // set array location to 0 so its not reused
			return ball; //return the ball
		}
	}
}

int isWinner(BingoCard* bCard){
	int i, j, markerCounterR, markerCounterC; //markerCounter used to check if all elements in a row or column are X'd
	if ((bCard->markers[0][0] == 'X' && bCard->markers[1][1] == 'X' && bCard->markers[2][2] == 'X' && bCard->markers[3][3] == 'X' && bCard->markers[4][4] == 'X') || (bCard->markers[0][4] == 'X' && bCard->markers[1][3] == 'X' && bCard->markers[2][2] == 'X' && bCard->markers[3][1] == 'X' && bCard->markers[4][0] == 'X')){ //cases to check for diagonals
		return 1;
	}
	for(i=0; i<5; i++){ // check each row and column to see if 
		markerCounterR = 0; //reset numbers after every row/ column
		markerCounterC = 0;
		for(j=0; j<5; j++){
			if(bCard->markers[i][j] == 'X'){ //check row
				markerCounterR++;
			}
			if(bCard->markers[j][i] == 'X'){ //check column
				markerCounterC++;
			}
		}
		if(markerCounterC == 5 || markerCounterR == 5){ //check to see if a whole row and column are X'd
			return 1;
		}
	}
	return 0;
}


void playBingo(BingoCard* one, BingoCard* two, BingoCard* three, int*** ballsDrawn){
	char choice;
	int letter, number, i;
	
	printf("\nWELCOME TO BINGO!");
	
	for(;;){
		do{
		printf("\nWould you like to play? y/n\n");
		scanf(" %c", &choice);
		}while((choice != 'y') && (choice != 'Y') && (choice != 'n') && (choice != 'N'));
		if( choice == 'n' ){
			printf("\nTHANK YOU FOR PLAYING BINGO WITH US! COME BACK SOON!\n");
			break;
		} //if not 'n' then its y, so continue below
		int pulledNumbers[75];
		int pulledLetters[75];
		for(i = 0; i < 75; i++){
			pulledNumbers[i] = 0;
		}
		
		populateCards(one, two, three, ballsDrawn);
		printCards(*one, *two, *three, pulledLetters, pulledNumbers);
		while((getchar()) != '\n');
		while( !(isWinner(one) || isWinner(two) || isWinner(three)) ){
			printf("\nPress ENTER for next drawing...\n ");
			getchar();
			number = drawBall(ballsDrawn);
			letter = getLetter(number);
			updateCards(one, two, three, ballsDrawn, letter, number, &pulledLetters, &pulledNumbers);
			printCards(*one, *two, *three, pulledLetters, pulledNumbers);
		
		}
		
		if( isWinner(one) ){
			printf("\nBINGO!!!!! PLAYER 1 WINS!!!!!!!!!!!\n");
		}
		else if( isWinner(two) ){
			printf("\nBINGO!!!!! PLAYER 2 WINS!!!!!!!!!!!\n");
		}
		else if( isWinner(three) ){
			printf("\nBINGO!!!!! PLAYER 3 WINS!!!!!!!!!!!\n");
		}
	}
	
}

//function for returning the letter row for use with printing letter number combination
int getLetter( int number ){
	if( number > 0 && number < 16 ){
		return 0;
	}
	if( number > 15 && number < 31){
		return 1;
	}
	if( number > 30 && number < 46){
		return 2;
	}
	if( number > 45 && number < 61){
		return 3;
	}
	if( number > 60 && number < 76){
		return 4;
	}
}







