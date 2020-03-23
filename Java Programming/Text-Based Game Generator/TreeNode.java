/* 
 * Instructor/Programmer: Clinton Rogers
 * Date: 4/2/2019
 * Any documents, source code, or work you create/modify as a result of this project is the 
 * property of the University of Massachusetts Dartmouth.  This document and any and all source 
 * code cannot be shared with anyone except: University of Massachusetts Dartmouth faculty 
 * (including TA�s), and in a private digital portfolio (public access online is prohibited) 
 * with the intention of applying to jobs and internships. These exceptions are non-transferable. 
 * Failure to comply is, at the very least, an academic infraction that could result in dismissal 
 * from the university. 
 * 
 * Student Name: Alexander J. Moulton
 * Date: 04/16/19

This program demonstrates a binary tree reference-based data structure. It recursively creates nodes
that point to two different nodes. Each path is created until an ending node is reached, at which point
the last unfilled pointer is filled out. This process is completed until the entire story is filled, 
which then prompts for the title of the file and saves the file. To play the game, that has been
written, the program uses a simple while loop that traverses through the story until an ending is
reached. Input validation has been added to methods that ask for user choices in order to ensure
that the program doesn't throw an IndexOutOfBounds exception and crash if the input is left blank.


 */
package project_2;

public class TreeNode {
	private TreeNode option1;
	private TreeNode option2;
	
	private String message;
	private boolean isEnding;
	
	public TreeNode getOption1() {
		return option1;
	}

	public void setOption1(TreeNode option1) {
		this.option1 = option1;
	}

	public TreeNode getOption2() {
		return option2;
	}

	public void setOption2(TreeNode option2) {
		this.option2 = option2;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isEnding() {
		return isEnding;
	}

	public void setEnding(boolean isEnding) {
		this.isEnding = isEnding;
	}



}