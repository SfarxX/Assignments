/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}
	private void playGame() {
		scoreBoard = new int[nPlayers][N_CATEGORIES+1];
		for(int i=0;i<nPlayers;i++){
			for(int j=0;j<N_CATEGORIES+1;j++){
				if(j!=UPPER_SCORE && j!= LOWER_SCORE) scoreBoard[i][j]=BLANK;
				else scoreBoard[i][j]=0;
			}
		}
		for(int turns=0; turns < 13 ; turns++){
			for(int Player=1;Player<nPlayers+1;Player++){
				makeTurn(Player);
			}
		}
		for(int Player=0;Player<nPlayers;Player++){
			collectAllPlayerScores(Player);
		}
		declareTheWinner();
	}
	private void declareTheWinner(){
		int maxTotal = 0;
		for(int i = 0; i<nPlayers; i++){
			if(scoreBoard[i][TOTAL]>maxTotal) maxTotal=scoreBoard[i][TOTAL];
		}
		for(int i=0;i<nPlayers;i++){
			if(scoreBoard[i][TOTAL]==maxTotal) display.printMessage(playerNames[i]+" is the winner! Congrats!");
		}
	}
	private void collectAllPlayerScores(int Player){
		int upperScore= scoreBoard[Player][UPPER_SCORE] ;
		display.updateScorecard(UPPER_SCORE, Player+1, upperScore);
		if(scoreBoard[Player][UPPER_SCORE]>63){
			scoreBoard[Player][UPPER_BONUS]=35;
			display.updateScorecard(UPPER_BONUS, Player+1, 35);
		}
		int lowerScore = scoreBoard[Player][LOWER_SCORE];
		display.updateScorecard(LOWER_SCORE, Player+1, lowerScore);
		scoreBoard[Player][LOWER_SCORE]=lowerScore;
		int total=upperScore+lowerScore;
		scoreBoard[Player][TOTAL]=total;
		display.updateScorecard(TOTAL, Player+1, total);
		
	}
	private void makeTurn(int Player){
		display.printMessage("Now is "+ playerNames[Player-1]+"'s turn! Click Roll!");
		int [] dice = new int[N_DICE];
		for(int i=0;i<N_DICE;i++){
			dice[i]=rgen.nextInt(0,6);
		}
		display.waitForPlayerToClickRoll(Player);
		display.displayDice(dice);
		for(int i = 0; i < 2; i++){
			display.waitForPlayerToSelectDice();
			update(dice);
			display.waitForPlayerToClickRoll(Player);
			display.displayDice(dice);
		}
		depositeScore(Player,dice);
	}
	private void depositeScore(int Player, int [] dice){
		display.printMessage("Select your category");
		while(true){
			int category = display.waitForPlayerToSelectCategory();
			if(scoreBoard[Player-1][category]==BLANK){
				int sum;
				if(checkCategory(dice,category)){
					sum = countScore(dice,category);
				}
				else sum=0;
				scoreBoard[Player-1][category]=sum;
				display.updateScorecard(category,Player,sum);
				if(category<UPPER_SCORE){
					scoreBoard[Player-1][UPPER_SCORE]+=sum;
					display.updateScorecard(UPPER_SCORE, Player, scoreBoard[Player-1][UPPER_SCORE]);
				}
				break;
			}
			else
				display.printMessage("This field is filled already. Select another category.");
		}
	}
	private boolean checkCategory(int [] dice, int category){
		switch(category){
		case ONES:
			return searchForOnes(dice);
		case TWOS:
			return searchForTwos(dice);
		case THREES:
			return searchForThrees(dice);
		case FOURS:
			return searchForFours(dice);
		case FIVES:
			return searchForFives(dice);
		case SIXES:
			return searchForSixes(dice);
		case THREE_OF_A_KIND:
			return searchForAThree(dice);
		case FOUR_OF_A_KIND: 
			return searchForAFour(dice);
		case FULL_HOUSE:
			return searchFullHouse(dice);
		case SMALL_STRAIGHT:
			return searchSmallStraight(dice);
		case LARGE_STRAIGHT:
			return searchLargeStraight(dice);
		case YAHTZEE:
			return checkYahtzee(dice);
		case CHANCE:
			return true;
		default:
			return false;
		}
	}
	private boolean searchForOnes(int [] dice){
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==1) return true;
		}
		return false;
	}
	private boolean searchForTwos(int [] dice){
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==2) return true;
		}
		return false;
	}
	private boolean searchForThrees(int [] dice){
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==3) return true;
		}
		return false;
	}
	private boolean searchForFours(int [] dice){
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==4) return true;
		}
		return false;
	}
	private boolean searchForFives(int [] dice){
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==5) return true;
		}
		return false;
	}
	private boolean searchForSixes(int [] dice){
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==6) return true;
		}
		return false;
	}
	private boolean searchForAThree(int []dice){
		int matches;
		for(int i=0;i<N_DICE/2+1;i++){
			matches = 1;
			for(int j=i+1; j < N_DICE; j++){
				if(dice[i]==dice[j]) matches++;
				if(matches==3) return true;
			}
		}
		return false;
	}
	private boolean searchForAFour(int [] dice){
		int matches;
		for(int i=0;i<N_DICE/2;i++){
			matches = 1;
			for(int j=i+1; j < N_DICE; j++){
				if(dice[i]==dice[j]) matches++;
				if(matches==4) return true;
			}
		}
		return false;
	}
	private boolean searchFullHouse(int [] dice){
		if(searchForAThree(dice) && !searchForAFour(dice)){
			int twos = 0;
			for(int i = 0; i < N_DICE; i++){
				int matches = countForMatches(dice, dice[i]);
				if(matches == 2){ 
					twos++;
				}
			}
			if(twos==2) return true;
			else return false;
		}
		return false;
	}
	private int countForMatches(int [] dice, int number){
		int counter = 0;
		for(int i = 0; i<N_DICE;i++){
			if(dice[i]==number) counter++;
		}
		return counter;
	}
	private boolean searchSmallStraight(int [] dice){
		if(boardIsAppropriate(dice,SMALL_STRAIGHT)){
			int maxNumber = searchForMax(dice);
			int minNumber = searchForMin(dice);
			int middleNumber = (maxNumber+minNumber)/2;
			if(isInPresence(dice,middleNumber)&& (isInPresence(dice,middleNumber+1)||isInPresence(dice,middleNumber-1)))
					return true;
			
		}
		return false;
	}
	private boolean searchLargeStraight(int [] dice){
		if(boardIsAppropriate(dice,LARGE_STRAIGHT)){
			int maxNumber = searchForMax(dice);
			int minNumber = searchForMin(dice);
			int middleNumber = (maxNumber+minNumber)/2;
			if(isInPresence(dice,middleNumber)&& isInPresence(dice,middleNumber+1) && isInPresence(dice,middleNumber-1))
					return true;
		}
		return false;
	}
	private boolean checkYahtzee(int [] dice){
		if(countForMatches(dice,dice[0])==5) return true;
		return false;
	}
	private boolean boardIsAppropriate(int [] dice, int category){
		int counter=0;
		for(int i=0;i<N_DICE;i++){
			int matches = countForMatches(dice,dice[i]);
			if(matches>1) counter++;
		}
		switch (category){
			case SMALL_STRAIGHT:
				if(counter>1) return false;
				return true;
			case LARGE_STRAIGHT:
				if(counter>0) return false;
				return true;
			default:
				return false;
		}
	}
	private int searchForMax(int [] dice){
		int max=0;
		for(int i=0;i<N_DICE;i++){
			if(dice[i]>max) max=dice[i];
		}
		return max;
	}
	private int searchForMin(int [] dice){
		int min = 7;
		for(int i=0; i<N_DICE;i++){
			if(dice[i]<min) min = dice[i];
		}
		return min;
	}
	private boolean isInPresence(int [] dice, int number){
		for(int i = 0; i< N_DICE; i++){
			if(dice[i]==number) return true;
		}
		return false;
	}
	private int countScore(int [] dice, int category){
		int Score=0;
		switch(category){
		case ONES:
			return Score = collectOnes(dice);
		case TWOS:
			return Score = collectTwos(dice);
		case THREES:
			return Score = collectThrees(dice);
		case FOURS:
			return Score = collectFours(dice);
		case FIVES:
			return Score = collectFives(dice);
		case SIXES:
			return Score = collectSixes(dice);
		case THREE_OF_A_KIND:
			return Score = sumOfAll(dice);
		case FOUR_OF_A_KIND:
			return Score = sumOfAll(dice);
		case FULL_HOUSE:
			return Score = 25;
		case SMALL_STRAIGHT:
			return Score = 30;
		case LARGE_STRAIGHT:
			return Score = 40;
		case YAHTZEE:
			return Score = 50;
		case CHANCE:
			return Score=sumOfAll(dice);
		default:
			return Score;
		}
		
	}
	private int collectOnes(int [] dice){
		int result = 0;
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==1) result++;
		}
		return result;
	}
	private int collectTwos(int [] dice){
		int result = 0;
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==2) result++;
		}
		return result*2;
	}
	private int collectThrees(int [] dice){
		int result = 0;
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==3) result++;
		}
		return result*3;
	}
	private int collectFours(int [] dice){
		int result = 0;
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==4) result++;
		}
		return result*4;
	}
	private int collectFives(int [] dice){
		int result = 0;
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==5) result++;
		}
		return result*5;
	}
	private int collectSixes(int [] dice){
		int result = 0;
		for(int i=0;i<N_DICE;i++){
			if(dice[i]==6) result++;
		}
		return result*6;
	}
	private int sumOfAll(int [] dice){
		int result = 0;
		for(int i=0; i < N_DICE; i++){
			result+=dice[i];
		}
		return result;
	}
	private void update(int [] dice){
		for(int i=0; i < N_DICE; i++){
			if(display.isDieSelected(i))
					dice[i]=rgen.nextInt(0,6);
		}
	}
	private int BLANK=-1;
	private int nPlayers;
	private String[] playerNames;
	private int[][] scoreBoard;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
}
