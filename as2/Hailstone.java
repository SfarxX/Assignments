/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		int Number = readInt("Enter a number: ");
		int Counter = 0;
		if(Number == 1){
			print("The process took "+Counter+" to reach 1");	
		}
		else if(Number<0){
			print("The process can't begin if n is negative");
		}
		else if(Number==0){
			print("The process can't begin if n is zero");
		}
		else{
			while(Number != 1){
				if(Number%2==0){
				println(Number + " is even, so I take half: " + Number/2 );
				Number /=2;
				}
				else{
				println(Number + " is odd, so I make 3n+1: " + (Number*3+1));
				Number = Number*3+1;
				}
				Counter++;
			}
			print("The process took "+Counter+" to reach 1");
		}
	}
}

