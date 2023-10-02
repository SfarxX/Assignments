/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;


public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		removeAll();
		buildScaffold();
	}
	private void buildScaffold(){
		GLine Scaffold = new GLine(0,SCAFFOLD_OFFSET_FROM_WINDOW,0,SCAFFOLD_HEIGHT);
		add(Scaffold);
		GLine Beam = new GLine(0,SCAFFOLD_OFFSET_FROM_WINDOW,BEAM_LENGTH,SCAFFOLD_OFFSET_FROM_WINDOW);
		add(Beam);
		GLine Rope = new GLine(BEAM_LENGTH,SCAFFOLD_OFFSET_FROM_WINDOW,BEAM_LENGTH,SCAFFOLD_OFFSET_FROM_WINDOW+ROPE_LENGTH);
		add(Rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		clearWordField(WORD_POSITION);
		GLabel WORD = new GLabel(word,0,WORD_POSITION);
		WORD.setFont("SansSerif-36");
		add(WORD);
	}
	private void clearWordField(double y){
		for(double x=0;x<getWidth();x++){
			if(getElementAt(x,420)!=null){
				remove(getElementAt(x,y));
				break;
			}
		}
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		String Letter = Character.toString(Character.toUpperCase(letter));
		GLabel WrongLetter = new GLabel(Letter);
		WrongLetter.setFont("Arial-24");
		double LetterXCoordinate =FAILURES*(WrongLetter.getAscent()/1.5);
		double LetterYCoordinate=WORD_POSITION+50;
		WrongLetter.setLocation(LetterXCoordinate,LetterYCoordinate);
		add(WrongLetter);
		FAILURES++;
		drawNextBodyPart(FAILURES);
	}
	private void drawNextBodyPart(int Step){
		switch(Step){
		case 1:
			drawHead();
			break;
		case 2:
			drawBody();
			break;
		case 3:
			drawArm("left");
			break;
		case 4:
			drawArm("right");
			break;
		case 5:
			drawLeg("left");
			break;
		case 6:
			drawLeg("right");
			break;
		case 7:
			drawFoot("left");
			break;
		case 8:
			drawFoot("right");
			break;
		}
	}
	private void drawHead(){
		double XHead=BEAM_LENGTH-HEAD_RADIUS/2;
		double YHead = SCAFFOLD_OFFSET_FROM_WINDOW+ROPE_LENGTH;
		GOval Head = new GOval(XHead,YHead,HEAD_RADIUS,HEAD_RADIUS);
		add(Head);
	}
	private void drawBody(){
		double X1Body = BEAM_LENGTH;
		double Y1Body = HEAD_RADIUS+ROPE_LENGTH+SCAFFOLD_OFFSET_FROM_WINDOW;
		double Y2Body = HEAD_RADIUS*2+ROPE_LENGTH+BODY_LENGTH;
		GLine Body = new GLine(X1Body,Y1Body,X1Body,Y2Body);
		add(Body);
	}
	private void drawArm(String Direction){
		GLine UpperArm = new GLine(0,0,UPPER_ARM_LENGTH,0);
		GLine LowerArm = new GLine(0,0,0,LOWER_ARM_LENGTH);
		if(Direction.equals("left")){
			double UpperArmXCoordinate=BEAM_LENGTH-UPPER_ARM_LENGTH;
			double UpperArmYCoordinate=HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD;
			UpperArm.setLocation(UpperArmXCoordinate,UpperArmYCoordinate);
			double LowerArmXCoordinate = BEAM_LENGTH-UPPER_ARM_LENGTH;
			double LowerArmYCoordinate = HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD;
			LowerArm.setLocation(LowerArmXCoordinate,LowerArmYCoordinate);
		}
		else if(Direction.equals("right")){
			double UpperArmXCoordinate=BEAM_LENGTH;
			double UpperArmYCoordinate=HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD;
			UpperArm.setLocation(UpperArmXCoordinate,UpperArmYCoordinate);
			double LowerArmXCoordinate = BEAM_LENGTH+UPPER_ARM_LENGTH;
			double LowerArmYCoordinate = HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD;
			LowerArm.setLocation(LowerArmXCoordinate,LowerArmYCoordinate);
		}
		add(UpperArm);
		add(LowerArm);
	}
	private void drawLeg(String Direction){
		GLine Leg = new GLine(0,0,0,LEG_LENGTH);
		GLine Hip = new GLine(0,0,HIP_WIDTH,0);
		if(Direction.equals("left")){
			double HipXCoordinate = BEAM_LENGTH-HIP_WIDTH;
			double HipYCoordinate = ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH;
			Hip.setLocation(HipXCoordinate,HipYCoordinate);
			double LegXCoordinate = BEAM_LENGTH-HIP_WIDTH;
			double LegYCoordinate = HipYCoordinate;
			Leg.setLocation(LegXCoordinate,LegYCoordinate);
		}
		else if(Direction.equals("right")){
			double HipXCoordinate = BEAM_LENGTH;
			double HipYCoordinate = ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH;
			Hip.setLocation(HipXCoordinate,HipYCoordinate);
			double LegXCoordinate = BEAM_LENGTH+HIP_WIDTH;
			double LegYCoordinate = HipYCoordinate;
			Leg.setLocation(LegXCoordinate,LegYCoordinate);
		}
		add(Hip);
		add(Leg);
	}
	private void drawFoot(String Direction){
		GLine Foot = new GLine(0,0,FOOT_LENGTH,0);
		if(Direction.equals("left")){
			double FootXCoordinate = BEAM_LENGTH-HIP_WIDTH-FOOT_LENGTH;
			double FootYCoordinate = ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH;
			Foot.setLocation(FootXCoordinate,FootYCoordinate);
		}
		else if(Direction.equals("right")){
			double FootXCoordinate = BEAM_LENGTH+HIP_WIDTH;
			double FootYCoordinate = ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH;
			Foot.setLocation(FootXCoordinate,FootYCoordinate);
		}
		add(Foot);
	}
	
	private static int FAILURES = 0;
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final double WORD_POSITION = 420;
	private static final int SCAFFOLD_OFFSET_FROM_WINDOW=50;
}
