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
	private static final double OuterRadius = 72;
	private static final double WhiteRadius = OuterRadius * 0.65;
	private static final double InnerRadius = OuterRadius * 0.3;
	private static final double OuterStartPosition = 250;
	public void run() {
		/* You fill this in. */
		printOuterCircle();
		printWhiteCircle();
		printInnerCircle();
		}
	private void printOuterCircle(){
		GOval OuterCircle = new GOval(OuterStartPosition,OuterStartPosition, OuterRadius*2,OuterRadius*2);
		OuterCircle.setFilled(true); OuterCircle.setColor(Color.RED);
		add(OuterCircle);
	}
	private void printWhiteCircle(){
		double WhiteStartPosition = OuterStartPosition + OuterRadius-WhiteRadius;
		GOval WhiteCircle = new GOval(WhiteStartPosition,WhiteStartPosition, WhiteRadius*2, WhiteRadius*2);
		WhiteCircle.setFilled(true); WhiteCircle.setColor(Color.WHITE);
		add(WhiteCircle);
	}
	private void printInnerCircle(){
		double InnerStartPosition = OuterStartPosition + OuterRadius-InnerRadius;
		GOval InnerCircle = new GOval(InnerStartPosition, InnerStartPosition, InnerRadius*2, InnerRadius*2);
		InnerCircle.setFilled(true); InnerCircle.setColor(Color.RED);
		add(InnerCircle);
	}
	
}
