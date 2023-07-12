package lydia_version;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Squaredle {

	// static class variables
	public static Scanner fin;

	// Squardle instance variables
	private int taskNum;
	private ArrayList<String> regularWords;
	private ArrayList<String> bonusWords;
	private String[][] grid;
	private ArrayList<MoveSequence> move_sequences;

	/**
	 * 
	 * @param fileName
	 * @throws FileNotFoundException initialize Squardle with file contents
	 */
	public Squaredle(String fileName) throws FileNotFoundException {
		fin = new Scanner(new File(fileName));
		regularWords = new ArrayList<String>();
		bonusWords = new ArrayList<String>();
		move_sequences = new ArrayList<MoveSequence>();
		getInput();
		doTask();
	}
	
	public void doTask() {
		if(taskNum == 1) taskOne();
	}

	
	public void taskOne() {
		//sort and display the words each list
		Collections.sort(bonusWords);
		Collections.sort(regularWords);
		for(String str : regularWords) {
			System.out.printf("%s ", str);
		}
		for(String str : bonusWords) {
			System.out.printf("%s ", str);
		}
		System.out.println();		
	}
	
	public void taskTwo() {
		for(MoveSequence ms : this.move_sequences) {
			ms.isValid(grid.length);
		}
	}
	
	
	@Override
	public String toString() {
		String theGrid = "";
		theGrid += "----------------\n";
		for(int i = 0; i < grid.length; i++) {
			//start each line
			theGrid +="|";
			for(int j = 0; j < grid.length; j++) {
				theGrid += String.format(" %s |", grid[i][j]);
			}
			theGrid += "\n----------------\n";
		}
		
		String moves = "";
		int count = 0;
		for(MoveSequence ms : this.move_sequences) {
			//for ea move sequence, print the number
			moves += "" + (++count) +". "+ ms + "\n";
		}
				
		return "Squardle\n\ntaskNum=" + taskNum 
				+ "\n\nregularWords=" + regularWords 
				+ "\n\nbonusWords=" + bonusWords
				+ "\n\nthe grid\n" + theGrid 
				+ "\nthe moves\n" + moves; 
				
	}


	/**
	 * get and validate input from file
	 */
	private void getInput() {

		String str = "";
		while (fin.hasNextLine())
			str += fin.nextLine();

		// str is input file
		String[] substrings = str.split(" # ");

		// taskNum is first in file
		getTaskNum(substrings[0]);
		
		// words are second
		getWords(substrings[1].split(" "));
		
		// grid size is third
		getGridSize(substrings[2]);
		
		// letters are fourth
		getLetters(substrings[3].split(" "));

		// substrings[4] is the list of moves
		// moves are separated by &
		String[] moves = substrings[4].split("& ");	
		
		getMoves(moves);
		
		//see if move sequences are valid
		for(MoveSequence ms : this.move_sequences) {
			ms.isValid(grid.length);
		}
	}

	private void getTaskNum(String str) {
		// validation
		try {
			taskNum = Integer.parseInt(str);
		} catch (InputMismatchException mismatch) {
			System.out.println(mismatch.getLocalizedMessage());
		}
	}

	private void getWords(String[] strArr) {
		// sort the words into regular and bonus
		for (int i = 0; i < strArr.length - 1; i += 2) {
			if (strArr[i + 1].equals("R"))
				this.regularWords.add(strArr[i]);
			else
				this.bonusWords.add(strArr[i]);
		}
	}

	/**
	 * 
	 * @param str set the size of the grid from str
	 */
	private void getGridSize(String str) {
		int sz = -1;
		try {
			sz = Integer.parseInt(str);
		} catch (InputMismatchException mismatch) {
			System.out.println(mismatch.getLocalizedMessage());
		}
		grid = new String[sz][sz];
	}

	private void getLetters(String[] letters) {
		// substrings[3] is the letters in the grid
		int index = 0;
		for (int col = 0; col < grid.length; col++) {
			for (int row = 0; row < grid.length; row++) {
				grid[col][row] = letters[index++];
			}
		}
	}

	private void getMoves(String [] moves) {
		
		//moves[0]: 1 1 1 2 2 3 3 2
		//moves[1]: 2 1 1 1 1 3
		//etc.
		
		for(int i = 0; i < moves.length; i++) {
			
			//parse the string at this index
			String [] thisMove = moves[i].split(" ");
			//make a list for the moves
			MoveSequence curr = new MoveSequence();
			//for each element in this move, parse as int and
			//put on the list of positions
			for(int j = 0; j < thisMove.length; j+=2) {
				int col = Integer.parseInt(thisMove[j]);
				int row = Integer.parseInt(thisMove[j+1]);
				curr.addMove(new Position(col, row));
			}
			this.move_sequences.add(curr);
		}		
	}
}

