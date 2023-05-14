/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	private static final int BOX_WIDTH = 150;
	private static final int BOX_HEIGHT = BOX_WIDTH/4;
	
	public void run() {
		
		drawProgram();
		drawGraphicsProgram();
		drawConsoleProgram();
		drawDialogProgram();
		linkProgToGraphics();
		linkProgToConsole();
		linkProgToDialog();
		
	}
	
	private void drawProgram()
	{	
		double X_Position=getWidth()/2 - BOX_WIDTH/2;
		double Y_Position=200;
		GRect ProgramBox = new GRect(X_Position,Y_Position/2,BOX_WIDTH,BOX_HEIGHT);
		add(ProgramBox);
		GLabel Program = new GLabel("Program",X_Position,Y_Position/2);
		double LabelWidth=Program.getWidth();
		double LabelHeight=Program.getAscent();
		Program.move(BOX_WIDTH/2-LabelWidth/2,BOX_HEIGHT/2+LabelHeight/2);
		add(Program);
	}
	private void drawGraphicsProgram()
	{	
		double X_Position=getWidth()/2 - BOX_WIDTH/2-(BOX_WIDTH+30);
		double Y_Position=200;
		GRect GrphxProgramBox = new GRect(X_Position, Y_Position, BOX_WIDTH, BOX_HEIGHT);
		add(GrphxProgramBox);
		GLabel GraphicsProgram = new GLabel("GraphicsProgram", X_Position, Y_Position);
		double LabelWidth = GraphicsProgram.getWidth();
		double LabelHeight = GraphicsProgram.getAscent();
		GraphicsProgram.move(BOX_WIDTH/2-LabelWidth/2, BOX_HEIGHT/2+LabelHeight/2);
		add(GraphicsProgram);
	}
	private void drawConsoleProgram()
	{	
		double X_Position=getWidth()/2 - BOX_WIDTH/2;
		double Y_Position=200;
		GRect ConsoleProgramBox = new GRect(X_Position, Y_Position, BOX_WIDTH, BOX_HEIGHT);
		add(ConsoleProgramBox);
		GLabel ConsoleProgram = new GLabel("ConsoleProgram", X_Position, Y_Position);
		double LabelWidth = ConsoleProgram.getWidth();
		double LabelHeight = ConsoleProgram.getAscent();
		ConsoleProgram.move(BOX_WIDTH/2-LabelWidth/2, BOX_HEIGHT/2+LabelHeight/2);
		add(ConsoleProgram);
	}
	private void drawDialogProgram()
	{	
		double X_Position=getWidth()/2 - BOX_WIDTH/2+(BOX_WIDTH+30);
		double Y_Position=200;
		GRect DialogProgramBox = new GRect(X_Position, Y_Position, BOX_WIDTH, BOX_HEIGHT);
		add(DialogProgramBox);
		GLabel DialogProgram = new GLabel("DialogProgram", X_Position, Y_Position);
		double LabelWidth = DialogProgram.getWidth();
		double LabelHeight = DialogProgram.getAscent();
		DialogProgram.move(BOX_WIDTH/2-LabelWidth/2, BOX_HEIGHT/2+LabelHeight/2);
		add(DialogProgram);
	}
	private void linkProgToGraphics()
	{	
		double X1_Position=getWidth()/2;
		double Y1_Position=100+BOX_HEIGHT;
		double X2_Position=X1_Position-(BOX_WIDTH+30);
		double Y2_Position=200;
		GLine Link = new GLine(X1_Position, Y1_Position,X2_Position,Y2_Position);
		add(Link);
	}
	private void linkProgToConsole()
	{	
		double X1_Position=getWidth()/2;
		double Y1_Position=100+BOX_HEIGHT;
		double X2_Position=X1_Position;
		double Y2_Position=200;
		GLine Link = new GLine(X1_Position, Y1_Position,X2_Position,Y2_Position);
		add(Link);
	}
	private void linkProgToDialog()
	{	
		double X1_Position=getWidth()/2;
		double Y1_Position=100+BOX_HEIGHT;
		double X2_Position=X1_Position+(BOX_WIDTH+30);
		double Y2_Position=200;
		GLine Link = new GLine(X1_Position, Y1_Position,X2_Position,Y2_Position);
		add(Link);
	}
}

