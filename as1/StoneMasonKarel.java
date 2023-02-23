/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run()
	{
		while(frontIsClear()){
			CheckTheColumn();
			move();
		}
		CheckTheColumn();
		
		
		
		
	}
private void BuildColumn()
{
	turnLeft();
	while(frontIsClear()){
	if(noBeepersPresent())
		putBeeper();
	move();
	}
	if(noBeepersPresent())
		putBeeper();
}

	
private void CheckTheColumn()
	{
		turnLeft();
		while(frontIsClear() ) 
		{
			if(noBeepersPresent())
			{
				move();
			}
			else
			{
				GoDown();
				BuildColumn();
			}
		}
		GoDown();
		
		
	}

  
	private void GoDown(){
		if(facingNorth()){
			turnLeft();
			turnLeft();
			while(frontIsClear() ) 
			{
				move();
			}
			turnLeft();
		}
	}
}
