/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 *
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	private static final int GUESSES = 8;  //total times for guessing
	private static int guess_number = GUESSES;  //instant variable for guess numbers

    public void main() {
    	//reset the canvas for drawing
		canvas.reset();
    	int length = word.length();
		//generate mask string
		mask = "";
		for (int i = 0; i < length; i++)
		{
			mask += '-';
		}
		//now enters the output
		println("Welcome to Hangman!");
		//loop for game, gameOver() function returns game status
		while (!gameOver())
		{
			canvas.displayWord(mask);
			println("The word now looks like this: "+mask);
			println("You have "+guess_number+" guess(es) left.");
			//ask for user input and check user input validity
			String input = "";
			char key;
			//check user input and convert to character key
			while (true) 
			{
				//check empty input
				do 
				{
					input = readLine("Your guess: ");
				}
				while (input.isEmpty());
				input = input.toUpperCase();
				key = input.charAt(0);
				if (input.length() == 1 && Character.isLetter(key))
					break;
				println("Illegal guess. Please retry.");
			}
			//checkKey() function compares the input key and string word
			//and update the mask string, and return true or false
			if (checkKey(key))
			{
				println("That guess is correct.");
			}
			else
			{
				//guess wrong, add letter and hang man
				canvas.noteIncorrectGuess(key);
				println("There are no "+key+"\'s in the word.");
			}
			
		}
		//game is over, display end message
		if (win)
		{
			//display word on canvas
			canvas.displayWord(word);
			println("You guessed the word: "+word);
			println("You win.");
		}
		else
		{
			println("You're completely hung.");
			//check whether want to play again
			String playAgain = readLine("Play again? (Y/N) ");
			if (playAgain.compareTo("Y")==0 || playAgain.compareTo("y")==0)
			{
				//reset the game
				guess_number = GUESSES;
				this.run();
			}
			else
			{	
				canvas.displayWord(word);
				println("The word was: "+word);
				println("You lose.");
			}
		}
		
	}
    
    public void init()
    {
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
    
    
    //gameover function return the status of the game and also return
    //boolean value for win status
    private boolean gameOver()
    {
    	//lose case
    	if (guess_number == 0)
    	{
    		win = false;
    		return true;
    	}
    	//win case
    	if (mask.compareTo(word) == 0)
    	{
    		win = true;
    		return true;
    	}
    	
    	return false;
    }
    
    //checkKey function check whether character k exists in word
    //if not, return false;else return true, and also update mask
    private boolean checkKey(char k)
    {
    	int index = word.indexOf(k);
    	if (index == -1)
    	{
    		//k doesn't exist in word
    		guess_number--;
    		return false;
    	}
    	else
    	{
    		//k exist in the word
    		while (index != -1)
    		{
    			//update mask
    			mask = mask.substring(0, index)+k+mask.substring(index+1);
    			//update index
    			index = word.indexOf(k, index + 1);
    		}
    		return true;
    	}
    	
    }
    
    private HangmanLexicon lex = new HangmanLexicon();
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private String word = lex.getWord(rgen.nextInt(0, lex.getWordCount() - 1));
    private String mask;  //instance for mask and word
    private boolean win = false;  //status of game ending
    private HangmanCanvas canvas;
    
}
