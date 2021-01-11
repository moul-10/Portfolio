/*

Alexander J. Moulton
Homework 4, Problem 1
Due November 14, 2019

ORIGINAL ASSIGNMENT
Write a C program to keep track of 2(subjects)-3(students)-4(exams) array (2x3x4 array). The
functions must perform:
a) Printing an average of all grades
b) Finding average grade for each subject for each student
c) Finding the average grade for each student in both subjects
You CANNOT pass the whole array to any of the functions. You CANNOT pass the same dimensions
to more than one function. Therefore, determine how many dimensions need to be passed to each
function and write the appropriate code.
Each function should return its result to main() for printing or additional processing and then printing.
The output should state what the printed number is and then display it.
Then, reconfigure the array to be 4(exams) - 3(students) - 2(subjects) (4x3x2 array) and write the code
for the same functionality as above, again in three different functions with various dimensions passed.
As above, you CANNOT pass the whole array to any of the functions. You CANNOT pass the same
dimensions to more than one function.
Submit two versions of the code for the two different array configurations.

*/

#include<stdio.h>
#include<stdlib.h>
#define SUBJECTS 2
#define STUDENTS 3
#define EXAMS 4
#define ARRAY_LENGTH 64

//function prototypes

int getGrade(void);
void aveAll(int grades[STUDENTS][EXAMS], int* totalSum, int* totalCount);
void aveAllInv(int grades[STUDENTS][SUBJECTS], int* totalSum, int* totalCount); //used for inverse array
void aveStudent(int grades[EXAMS], int* studentSum, int* studentCount);
void aveStudentInv(int grades[SUBJECTS], int* studentSum, int* studentCount); //used for inverse array
void aveSingleSubject(int grade, int* sum, int* count);

int main(){
	
	srand((unsigned)time(NULL));
	
	int i, j, k; //for loops
	int grades[SUBJECTS][STUDENTS][EXAMS] = {
	/*
		***USED FOR TESTING, KEPT IN FOR EASE OF GRADING***
		
		{{87, 79, 82, 91}, //subject 1, student 1, exams 1-4
		{94, 97, 89, 92},  	//subject 1, student 2, exams 1-4
		{72, 77, 79, 83}}, //subject 1, student 3, exams 1-4
		
		{{89, 91, 90, 82},	//subject 2, student 1, exams 1-4
		{98, 100, 96, 96},  	//subject 2, student 2, exams 1-4
		{69, 72, 80, 83}}	//subject 2, student 3, exams 1-4
	*/	
	
		//RANDOM GRADES GENERATED TO MAKE PROGRAM MORE INTERESTING
		{{getGrade(), getGrade(), getGrade(), getGrade()},	//subject 1, student 1, exams 1-4
		{getGrade(), getGrade(), getGrade(), getGrade()},  	//subject 1, student 2, exams 1-4
		{getGrade(), getGrade(), getGrade(), getGrade()}},	//subject 1, student 3, exams 1-4
		
		{{getGrade(), getGrade(), getGrade(), getGrade()},	//subject 2, student 1, exams 1-4
		{getGrade(), getGrade(), getGrade(), getGrade()},  	//subject 2, student 2, exams 1-4
		{getGrade(), getGrade(), getGrade(), getGrade()}}	//subject 2, student 3, exams 1-4
	};
	
	int gradesInverse[EXAMS][STUDENTS][SUBJECTS];
	for(i = 0; i < EXAMS; i++){
		for(j = 0; j < STUDENTS; j++){
			for(k = 0; k < SUBJECTS; k++){
				gradesInverse[i][j][k] = grades[k][j][i];
			}
		}
	}
		
	
	
	
	
	int singleSubTotal = 0, singleSubCount = 0; //used for student subject average, function passed element by element
	int student0Sum = 0, student0Count = 0, student1Sum = 0, student1Count = 0, student2Sum = 0, student2Count = 0;
	int totalSum = 0, totalCount = 0;
	
	
	for(k=0; k < SUBJECTS; k++){
		printf("\nSubject %d:\n", k);
		for(j=0; j < STUDENTS; j++){
			printf("\n\tStudent %d:\n",j);
			for(i=0; i < EXAMS; i++){
				printf("\t\tExam %d:\t%d\n", i, grades[k][j][i]);
				aveSingleSubject(grades[k][j][i], &singleSubTotal, &singleSubCount); //each exam score called individually
			}
			printf("\n\t\tSTUDENT-SUBJECT %d AVERAGE: %d\n", k, singleSubTotal/singleSubCount);
			singleSubTotal = 0, singleSubCount = 0; //reset for next student
			switch(j){
				// aveStudent passed row of exams depending on which student it is
				case 0: aveStudent(grades[k][j], &student0Sum, &student0Count); break;
				case 1: aveStudent(grades[k][j], &student1Sum, &student1Count); break;
				case 2: aveStudent(grades[k][j], &student2Sum, &student2Count); break;
			}
			
		}
		aveAll(grades[k], &totalSum, &totalCount); //aveAll passed a stack  of rows and columns
	}
	printf("\n\tSTUDENT 0 AVERAGE: %d", student0Sum/student0Count);
	printf("\n\tSTUDENT 1 AVERAGE: %d", student1Sum/student1Count);
	printf("\n\tSTUDENT 2 AVERAGE: %d", student2Sum/student2Count);
	printf("\n\nTOTAL AVERAGE: %d\n", totalSum/totalCount); //average all the averages to get the average of all grades
	
	
	// INVERSE ARRAY BEGINS HERE
	printf("\n\n*******NOW LET'S PRINT THE INVERSE ARRAY AND ITS RESULTS!*******\n\n");

	//first reset all sums and counters
	singleSubTotal = 0, singleSubCount = 0; //used for student subject average, function passed element by element
	student0Sum = 0, student0Count = 0, student1Sum = 0, student1Count = 0, student2Sum = 0, student2Count = 0;
	totalSum = 0, totalCount = 0;
	
	for(i=0; i < SUBJECTS; i++){
		printf("\nSubject %d:\n", i);
		for(j=0; j < STUDENTS; j++){
			printf("\n\tStudent %d:\n",j);
			for(k=0; k < EXAMS; k++){
				printf("\t\tExam %d:\t%d\n", k, gradesInverse[k][j][i]);
				//indices are simply used in the opposite places as the 2x3x4 array to print the same way
				//and retain same functionality
				aveSingleSubject(gradesInverse[k][j][i], &singleSubTotal, &singleSubCount); //each exam score called individually
				if(i == 0){
					switch(j){
						// aveStudent passed row of exams depending on which student it is
						case 0: aveStudentInv(gradesInverse[k][j], &student0Sum, &student0Count); break;
						case 1: aveStudentInv(gradesInverse[k][j], &student1Sum, &student1Count); break;
						case 2: aveStudentInv(gradesInverse[k][j], &student2Sum, &student2Count); break;
					}
					aveAllInv(gradesInverse[k], &totalSum, &totalCount); //aveAll passed a stack  of rows and columns
				}
			}
			printf("\n\t\tSTUDENT-SUBJECT %d AVERAGE: %d\n", i, singleSubTotal/singleSubCount);
			singleSubTotal = 0, singleSubCount = 0; //reset for next student
			
		}
		
	}
	printf("\n\tSTUDENT 0 AVERAGE: %d", student0Sum/student0Count);
	printf("\n\tSTUDENT 1 AVERAGE: %d", student1Sum/student1Count);
	printf("\n\tSTUDENT 2 AVERAGE: %d", student2Sum/student2Count);
	printf("\n\nTOTAL AVERAGE: %d\n", totalSum/totalCount); //average all the averages to get the average of all grades
	
	
	
	
	return 0;
	
	//*/
}



int getGrade(){
	return ((rand() % 51) + 50); //give a random grade between 50 and 100 (to keep the grades realistic)
}



void aveSingleSubject(int grade, int* sum, int* count){
	*sum = *sum + grade;
	*count = *count + 1;
}



void aveStudent(int grades[EXAMS], int* studentSum, int* studentCount){
	int i = 0;
	for(i; i< EXAMS; i++){
		*studentSum = *studentSum + grades[i];
		*studentCount = *studentCount + 1;
	}
}


void aveStudentInv(int grades[SUBJECTS], int* studentSum, int* studentCount){
	int i = 0;
	for(i; i< SUBJECTS; i++){
		*studentSum = *studentSum + grades[i];
		*studentCount = *studentCount + 1;
	}
}


void aveAll(int grades[STUDENTS][EXAMS], int* totalSum, int* totalCount){//pass in two dimensions of the array
	int i, j;
	
	for( j = 0; j < STUDENTS; j++){
		for( i = 0; i< EXAMS; i++){
			*totalSum = *totalSum + grades[j][i];
			*totalCount = *totalCount + 1;
		}
	}
}

void aveAllInv(int grades[STUDENTS][SUBJECTS], int* totalSum, int* totalCount){
	int i, j;
	
	for( j = 0; j < STUDENTS; j++){
		for( i = 0; i< SUBJECTS; i++){
			*totalSum = *totalSum + grades[j][i];
			*totalCount = *totalCount + 1;
		}
	}
}