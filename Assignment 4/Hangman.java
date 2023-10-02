/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void run() {
		/* You fill this in */
    	init();
    	println("Welcome to Hangman!");
    	HangmanLexicon HL = new HangmanLexicon();
    	String WORD = HL.getWord(rgen.nextInt(0,HL.getWordCount()));
    	canvas.reset();
    	playHangman(WORD);
	}
    private void playHangman(String WORD){
    	String Guessing_Word = ""; 
    	Guessing_Word=initGuessingWord(WORD, Guessing_Word);
    	while(Guesses_Counter>0){
    		describeSituation(Guessing_Word);
    		Guessing_Word=guessing(WORD,Guessing_Word);
    		if(WORD.equals(Guessing_Word)){
    			println("You guessed the word: "+WORD);
    			break;
    		}
    	}
    	printResult(WORD);
    }
    private String guessing(String Origin, String Guessing){
    	char guess=check();
    	guess=Character.toUpperCase(guess);
    	String NewWord=Guessing;
    	if(guess != '\000'){
    		boolean isInPresence = (Origin.contains(Character.toString(guess)));
    		if(! isInPresence){
    			Guesses_Counter--; 
    			println("There are no "+guess+"'s in the word.");
    			canvas.noteIncorrectGuess(guess);
    		}
    		else{
    		if(! NewWord.contains(Character.toString(guess))){	
    			for(int i=0;i<Origin.length();i++){
    				if(Origin.charAt(i)==guess){
    					NewWord=Guessing.substring(0,i);
    					NewWord+=guess;
    					if(NewWord.compareTo(Guessing)>0) NewWord+=Guessing.substring(i+1);
    					Guessing=NewWord;
    					println(NewWord);
    				}
    			}
    			println("That guess is correct");
    		}
    		}
    	}
    	return NewWord; 
    } 
    private void describeSituation(String Guessing_Word){
    	if(Guesses_Counter>1){
    		println("The word now looks like this: "+Guessing_Word);
    		canvas.displayWord(Guessing_Word);
    		println("You have "+Guesses_Counter+" guesses left.");
    	}
    	else if(Guesses_Counter==1){
    		println("The word now looks like this: "+Guessing_Word);
    		canvas.displayWord(Guessing_Word);
    		println("You have only one guess left.");
    	}
    	else{
    		println("You're completely hung.");
    	}
    }
    private char check(){
    	String Guess = readLine("Your guess: ");
    	for(int i=0;i<Guess.length();i++){
    		if(Guess.charAt(i)>='A' && Guess.charAt(i)<='Z' || Guess.charAt(i)>='a' && Guess.charAt(i)<='z'){
    			return Guess.charAt(i);
    		}
    	}
    	return '\000';
    	
    }
    private String initGuessingWord(String Origin, String Guessing){
    	Guessing="";
    	for(int i=0; i<Origin.length();i++){
    		Guessing+="-";
    	}
    	return Guessing;
    }
    private void printResult(String Origin){
    	if(Guesses_Counter==0){
	    	println("You are completely hung.");
			println("The word was: "+Origin);
			println("You lose.");
		}
    	else{
    		println("You win.");
	    	canvas.displayWord(Origin);
    	}
    }
    public void init(){
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
    private HangmanCanvas canvas;
    private static int Guesses_Counter = 8;
    private RandomGenerator rgen = RandomGenerator.getInstance();
}
