/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		/* You fill this in. */
		double OuterRadius = 72;
		double OuterStartPosition = 250;
		GOval OuterCircle = new GOval(OuterStartPosition,OuterStartPosition, OuterRadius*2,OuterRadius*2);
		OuterCircle.setFilled(true); OuterCircle.setColor(Color.RED);
		double WhiteRadius = OuterRadius * 0.65; 
		double WhiteStartPosition = OuterStartPosition + OuterRadius-WhiteRadius;
		GOval WhiteCircle = new GOval(WhiteStartPosition,WhiteStartPosition, WhiteRadius*2, WhiteRadius*2);
		WhiteCircle.setFilled(true); WhiteCircle.setColor(Color.WHITE);
		double InnerRadius = OuterRadius * 0.3;
		double InnerStartPosition = WhiteStartPosition + WhiteRadius-InnerRadius;
		GOval InnerCircle = new GOval(InnerStartPosition, InnerStartPosition, InnerRadius*2, InnerRadius*2);
		InnerCircle.setFilled(true); InnerCircle.setColor(Color.RED);
		add(OuterCircle); add(WhiteCircle); add(InnerCircle);
	}
}
