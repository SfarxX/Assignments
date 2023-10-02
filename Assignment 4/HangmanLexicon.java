/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.util.*;
import java.io.*;


public class HangmanLexicon {

	private ArrayList<String> Words= new ArrayList<String>();
	public HangmanLexicon(){
		BufferedReader list = openFile("HangmanLexicon.txt");
		//fillWords(Words, list);
		try{
			while(true){
				String word = list.readLine();
				if(word == null) break;
				Words.add(word);
			}
			list.close();
		}
		catch(IOException ex){
			throw new ErrorException(ex);
		}
	}
	/*private void fillWords(ArrayList<String>array, BufferedReader list){
		for(int i=0;i<array.size();i++){
			array.add(list.readLine());
		}
	}*/
/** Returns the number of words in the lexicon. */
	private BufferedReader openFile(String name){
		BufferedReader file = null;
		while(file == null){
			try{
				file = new BufferedReader(new FileReader(name));
			}
			catch(IOException ex){
				
			}
		}
		return file;
	}
	public int getWordCount() {
		return Words.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return Words.get(index);
	};
}
