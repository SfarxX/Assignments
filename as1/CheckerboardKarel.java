/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;


public class CheckerboardKarel extends SuperKarel {

	// You fill in this part
	
	public void run(){
		
		
		if(frontIsBlocked())
		{
			buildColumn();
		}
		else
		{
			if(leftIsBlocked())
			{
				drawLine();
			}
			else
			{
				while(frontIsClear())
				{
					drawLine();
					turnLeft();
					CheckUp();
					if(frontIsBlocked())
						turnRight();
					CheckDown();
					if(beepersPresent())
					{
						move();
						if(frontIsClear())
							move();
					}
					else{
						move();
					}
					drawLine();
					turnRight();
					CheckUp();
					if(frontIsBlocked()){
						//turnLeft();
						turnAround();
						move();
						if(noBeepersPresent())
						{
							turnAround();
							move();
							turnRight();
							drawLine();
							turnLeft();
						}
						
					}
					
					turnRight();
					
				}
			}
		}
		
		
		
		
		
		
		
	}
	private void CheckUp()
	{
		
		
		
		
		
		if(frontIsClear()){
			move();
			
		}
		
		
		
	}
	private void buildColumn()
	{
		putBeeper();
		turnLeft();
		while(frontIsClear())
		{
			move();
			if(frontIsClear()){
				move();
			putBeeper();}
		}
	}
	
	private void drawLine()
	{
		putBeeper();
		while(frontIsClear())
		{
			move();
			if(frontIsClear())
			{
				move();
				putBeeper();
			}
		}	
	}
	private void CheckDown()
	{
		while(notFacingSouth())
			turnLeft();
		if(frontIsClear() )
		{
			move();
			if(beepersPresent())
			{
				turnLeft();
				turnLeft();
				move();
				
			}
			else
			{
				turnLeft();
				turnLeft();
				move();
				putBeeper();
				
			}
		}
		if(rightIsBlocked())
			turnLeft();
		else
			turnRight();
	}
	
	
}
	

