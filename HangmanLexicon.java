/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import java.util.*;

import acm.util.*;

public class HangmanLexicon {
	
	//HangmanLexicon constructor
	public HangmanLexicon() {
		//read in a file
		try {
			BufferedReader br = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			//define ArrayList for storage
			lex = new ArrayList<String>();
			while (true)
			{
				String buffer = br.readLine();
				if (buffer == null)
					break;
				lex.add(buffer);
			}
			br.close();
		} catch (IOException e) {
			throw new ErrorException(e); 
		}
	}
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lex.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lex.get(index);
	}
	
	/* private instances */
	private ArrayList<String> lex;
}
