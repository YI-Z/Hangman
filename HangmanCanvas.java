/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		//remove all objects on the canvas
		for (int x = 0; x < getWidth(); x++)
		{
			for (int y = 0; y < getHeight(); y++)
			{
				GObject obj = getElementAt(x, y);
				if (obj != null)	
					remove(obj);
			}
		}
		//reset wrong string
		wrong = "";
		//add in scaffold
		GLine scaffold = new GLine(getWidth()/2-BEAM_LENGTH, SCAFFOLD_OFFSET,
								getWidth()/2-BEAM_LENGTH, SCAFFOLD_OFFSET+SCAFFOLD_HEIGHT);
		GLine beam = new GLine(getWidth()/2-BEAM_LENGTH, SCAFFOLD_OFFSET,
								getWidth()/2, SCAFFOLD_OFFSET);
		GLine rope = new GLine(getWidth()/2, SCAFFOLD_OFFSET,
							getWidth()/2, SCAFFOLD_OFFSET+ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		//remove old word tag
		GObject old = getElementAt(getWidth()/2,SCAFFOLD_OFFSET+SCAFFOLD_HEIGHT+SPACER);
		if (old != null)	remove(old);
		//add new tag
		GLabel word_tag = new GLabel(word);
		word_tag.setFont("TimesNewRoman-20");
		word_tag.setLocation((getWidth()-word_tag.getWidth())/2, SCAFFOLD_OFFSET+SCAFFOLD_HEIGHT+SPACER);
		add(word_tag);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		//remove old tag
		GObject old = getElementAt(getWidth()/2,getHeight()-BOTTOM_OFFSET);
		if (old != null)	remove(old);
		wrong += letter;
		GLabel wrong_tag = new GLabel(wrong);
		wrong_tag.setLocation(getWidth()/2-wrong_tag.getWidth()/2, getHeight()-BOTTOM_OFFSET);
		add(wrong_tag);
		//create hangman
		GOval head = new GOval(getWidth()/2-HEAD_RADIUS, SCAFFOLD_OFFSET+ROPE_LENGTH, 2*HEAD_RADIUS, 2*HEAD_RADIUS);
		GLine body = new GLine(getWidth()/2, SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS,
								getWidth()/2, SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH);
		GLine left_arm = new GLine(getWidth()/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD,
								getWidth()/2-UPPER_ARM_LENGTH,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD);
		GLine left_hand = new GLine(getWidth()/2-UPPER_ARM_LENGTH,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD,
								getWidth()/2-UPPER_ARM_LENGTH,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
		GLine right_arm = new GLine(getWidth()/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD,
								getWidth()/2+UPPER_ARM_LENGTH,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD);
		GLine right_hand = new GLine(getWidth()/2+UPPER_ARM_LENGTH,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD,
								getWidth()/2+UPPER_ARM_LENGTH,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);	
		GLine left_leg_up = new GLine(getWidth()/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH,
								getWidth()/2-HIP_WIDTH/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH);
		GLine left_leg_down = new GLine(getWidth()/2-HIP_WIDTH/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH,
								getWidth()/2-HIP_WIDTH/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
		GLine right_leg_up = new GLine(getWidth()/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH,
								getWidth()/2+HIP_WIDTH/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH);
		GLine right_leg_down = new GLine(getWidth()/2+HIP_WIDTH/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH,
								getWidth()/2+HIP_WIDTH/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);		
		GLine left_foot = new GLine(getWidth()/2-HIP_WIDTH/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH,
								getWidth()/2-HIP_WIDTH/2-FOOT_LENGTH,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
		GLine right_foot = new GLine(getWidth()/2+HIP_WIDTH/2,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH,
				getWidth()/2+HIP_WIDTH/2+FOOT_LENGTH,SCAFFOLD_OFFSET+ROPE_LENGTH+2*HEAD_RADIUS+BODY_LENGTH+LEG_LENGTH);
		switch (wrong.length())
		{
		case 1:
			add(head);
			break;
		case 2:
			add(body);
			break;
		case 3:
			add(left_arm);
			add(left_hand);
			break;
		case 4:
			add(right_arm);
			add(right_hand);
			break;
		case 5:
			add(left_leg_up);
			add(left_leg_down);
			break;
		case 6:
			add(right_leg_up);
			add(right_leg_down);
			break;
		case 7:
			add(left_foot);
			break;
		case 8:
			add(right_foot);
			break;
		default:
			break;
		}

	}

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
	private static final int SCAFFOLD_OFFSET = 40;
	private static final int SPACER = 30;
	private static final int BOTTOM_OFFSET = 40;
	private String wrong;

}
