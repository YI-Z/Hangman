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
		switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}
	}
	
	/* private instances */
	private ArrayList<String> lex;
}
