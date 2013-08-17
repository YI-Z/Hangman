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
	private int guess_number = GUESSES;  //instant variable for guess numbers

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
		//now enters the output
		println("Welcome to Hangman!");
		//loop for game, gameOver() function returns game status
		while (!gameOver())
		{
			println("The word now looks like this: "+mask);
			println("You have "+guess_number+" guess(es) left.");
			//ask for user input and check user input validity
			String input;
			char key;
			//check user input and convert to character key
			while (true) 
			{
				input = readLine("Your guess: ");
				input = input.toUpperCase();
				key = input.charAt(0);
				if (input.length() == 1 && Character.isLetter(key))
					break;
				println("Illegal guess. Please retry.");
			}
			
			
		}
		
		
		
	}
    
    RandomGenerator rgen = RandomGenerator.getInstance();
}
