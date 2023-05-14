/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 15;
	
	public void run() {
		int MIDDLE=getWidth()/2 - 15;
		int START_X_COORDINATE = MIDDLE-((BRICKS_IN_BASE-1)*(BRICK_WIDTH/2));
		
		int START_Y_COORDINATE = getHeight()-12;
		for(int i = BRICKS_IN_BASE; i > 0; i--)
		{
			int DYNAMIC_X_COORDINATE = START_X_COORDINATE;
			for(int j = 0; j < i; j++)
			{
				GRect BRICK = new GRect(DYNAMIC_X_COORDINATE,START_Y_COORDINATE,BRICK_WIDTH,BRICK_HEIGHT);
				add(BRICK);
				DYNAMIC_X_COORDINATE += BRICK_WIDTH;
			}
			START_X_COORDINATE += BRICK_WIDTH/2;
			START_Y_COORDINATE -= BRICK_HEIGHT;
		}
	}
}

