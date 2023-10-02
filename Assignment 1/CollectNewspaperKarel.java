/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	

	// You fill in this part
	public void run(){
		MoveToNewspaper();
		PickItUp();
		ReturnToStart();
	}
	private void MoveToNewspaper(){
		while(noBeepersPresent())
		{
			if(frontIsClear())
			{
				move();
			}
			else
			{
				turnRight();
				if(frontIsClear()) 
				{
					move();
					turnLeft();
				}
				else
				{
					turnLeft();
					turnLeft();
					move();
					turnRight();
				}
			}
		}
	}
	private void PickItUp(){
		if(beepersPresent())
		{
			pickBeeper();
			turnRight();
			turnRight();
		}
	}
	private void ReturnToStart(){
		while(frontIsClear())
		{
			move();
			
		}
		if(frontIsBlocked())
		{
			turnRight();
			move();
		}
	}
}
