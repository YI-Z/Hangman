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
	
	private static final int GUESSES = 8;  //total times for guessing
	

    public void run() {
    	//initialization of variables
		HangmanLexicon lex = new HangmanLexicon();
		String word = lex.getWord(rgen.nextInt(0, lex.getWordCount() - 1));
		int length = word.length();
		//generate mask string
		String mask = "";
		for (int i = 0; i < length; i++)
		{
			mask += '-';
		}
		println(word);
		println(mask);
		println("Welcome to Hangman!");
		
		
	}
    
    RandomGenerator rgen = RandomGenerator.getInstance();
}
