package lydia_version;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Squardle {

	// static class variables
	public static Scanner fin;

	// Squardle instance variables
	private int taskNum;
	private ArrayList<String> regularWords;
	private ArrayList<String> bonusWords;
	private String[][] grid;
	private ArrayList<ArrayList<Position>> positions;

	/**
	 * 
	 * @param fileName
	 * @throws FileNotFoundException initialize Squardle with file contents
	 */
	public Squardle(String fileName) throws FileNotFoundException {
		fin = new Scanner(new File(fileName));
		regularWords = new ArrayList<String>();
		bonusWords = new ArrayList<String>();
		positions = new ArrayList<ArrayList<Position>>();
		getInput();
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
		
		// each element of moves is a sequence of moves to be evaluated
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
			//make the array list for this move
			positions.add(new ArrayList<Position>());
			
			//for each element in this move, parse as int and
			//put on the list of positions
			for(int j = 0; j < thisMove.length; j+=2) {
				int col = Integer.parseInt(thisMove[j]);
				int row = Integer.parseInt(thisMove[j+1]);
				positions.get(i).add(new Position(col, row));
			}
		}		
	}
}

//position class
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
