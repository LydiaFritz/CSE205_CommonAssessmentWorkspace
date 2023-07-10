package lydia_version;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Squardle {

	// static class variables
	public static Scanner fin;

	// inner position class
	class Position {

		public int column;
		public int row;

		Position(int c, int r) {
			column = c;
			row = r;
		}

		@Override
		public String toString() {
			return "[col:" + column + " row:" + row + "]";
		}
		
		
	}

	// Squardle instance variables
	private int taskNum;
	private ArrayList<String> regularWords;
	private ArrayList<String> bonusWords;
	private String[][] grid;
	private ArrayList<Position> positions;

	public Squardle(String fileName) throws FileNotFoundException {
		fin = new Scanner(new File(fileName));
		regularWords = new ArrayList<String>();
		bonusWords = new ArrayList<String>();
		getInput();
	}

	private void getInput() {
		String str = "";
		while (fin.hasNextLine())
			str += fin.nextLine();

		// str has entire input file
		String[] substrings = str.split(" # ");

		// validation
		//substrings[0] is taskNum
		try {
			taskNum = Integer.parseInt(substrings[0]);
		} catch (InputMismatchException mismatch) {
			System.out.println(mismatch.getLocalizedMessage());
		}
		
		//get words
		//assuming valid format (word, type)
		//substrings[1] is word list
		String words[] = substrings[1].split(" ");
		//words[0] - word
		//words[1] - type
		for(int i = 0; i < words.length-1; i+=2) {
			if(words[i+1].equals("R"))
				this.regularWords.add(words[i]);
			else
				this.bonusWords.add(words[i]);
		}
		
		//substrings[2] is grid size
		int sz = -1;
		try {
			sz = Integer.parseInt(substrings[2]);
		} catch (InputMismatchException mismatch) {
			System.out.println(mismatch.getLocalizedMessage());
		}
		grid = new String[sz][sz];
		
		//substrings[3] is the letters in the grid
		String letters[] = substrings[3].split(" ");
				
		int index = 0;
		for(int col = 0; col < grid.length; col++) {
			for(int row = 0; row < grid.length; row++) {
				grid[col][row] = letters[index++];
			}
		}
		
		//substrings[4] is the list of moves
		//moves are separated by &
		String [] moves = substrings[4].split("& ");
		//each element of moves is a sequence of moves to be evaluated
	}

}
