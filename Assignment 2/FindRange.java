/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private int inputNumber;//readInt("? ");
	private int sentinel = 0;
	public void run() {
		println("This program finds the largest and smallest numbers.");
		inputNumber = readInt("? ");
		if(inputNumber == sentinel)
			println("No values have been entered.");
		else
			{
			startSearching();
			}
	}
	private void startSearching(){
		int largest = inputNumber;
		int smallest = inputNumber;
		while(inputNumber != sentinel){
			inputNumber = readInt("? ");
			if(inputNumber > largest && inputNumber != 0)
				largest = inputNumber;
			if(inputNumber < smallest && inputNumber!=0)
				smallest = inputNumber;
		}
		println("smallest: "+smallest); 
		println("largest: "+largest);
	}		
}

