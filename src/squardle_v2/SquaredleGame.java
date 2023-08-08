package squardle_v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class SquaredleGame {

	public static Scanner scanner = null;
	
	private ArrayList<ArrayList<Cell>> grid;
	private ArrayList<String> bonusWords = new ArrayList<String>();
	private ArrayList<String> regWords = new ArrayList<String>();
	private ArrayList<Position> positions = new ArrayList<Position>();
	private int taskNum = 0;

	/**
	 * allocate the grid 
	 */
	public SquaredleGame() {
		// allocate the grid
		grid = new ArrayList<ArrayList<Cell>>();
	}


	/**
	 * run the given task
	 */
	public void runTask() {
		switch (taskNum) {
		case 1:
			taskOne();
			break;

		case 2:
			taskTwo();
			break;
		}
	}

	/**
	 * sort and display words in order
	 * [sorted reg words] [sorted bonus words]
	 */
	private void taskOne() {
		System.out.println("\n--------\nTASK ONE\n--------\n");
		Collections.sort(regWords);
		for (String s : regWords) {
			System.out.print(s + " ");
		}
		Collections.sort(bonusWords);
		for (String s : bonusWords) {
			System.out.print(s + " ");
		}
		System.out.println();
	}

	private void taskTwo() {
		for(Position p : positions) {
			if(!inBounds(p)) {
				System.out.println("NO 1");
				return;
			}
		}
		
		if(!adjacent()) {
			System.out.println("NO 2");			
		}
		else if(repeatedMove()) {
			System.out.println("NO 3");
		}
		else{
			System.out.println("YES");
		}		
	}

	private boolean inBounds(Position p) {
		int length = this.grid.size();
		return p.row >= 0 && p.row < length && p.col >= 0 && p.col < length;
	}

	private boolean adjacent() {
		// return true if sequential positions are adjacent, false otherwise
		for (int i = 0; i < positions.size() - 1; i++) {
			Position curr = positions.get(i), next = positions.get(i + 1);
			if (!(next.row >= curr.row - 1 && next.row <= curr.row + 1 && next.col >= curr.col - 1
					&& next.col <= curr.col + 1))
				return false;
		}
		return true;
	}

	private boolean repeatedMove() {
		boolean dupes = false;
		for (int i = 0; i < positions.size() && !dupes; i++) {
			for (int j = i + 1; j < positions.size(); j++) {
				if (positions.get(i).equals(positions.get(j)))
					dupes = true;
			}
		}
		return dupes;
	}

	// for testing
	private void showGrid() {
		for (int r = 0; r < grid.size(); r++) {
			for (int c = 0; c < grid.size(); c++) {
				System.out.printf("| %s ", grid.get(r).get(c).value);
			}
			System.out.println("|");
		}
		System.out.println();
	}

	// allocate and populate grid with cells
	private void buildGrid(int size) {

		for (int row = 0; row < size; row++) {
			grid.add(new ArrayList<Cell>());
			for (int col = 0; col < size; col++) {
				grid.get(row).add(new Cell(row, col));
			}
		}

	}

	public void getInput(String str) throws FileNotFoundException {
		scanner = new Scanner(new File(str));

		// get the task num first
		taskNum = scanner.nextInt();

		// extract the #
		String sep = scanner.next();
		getWords();
		// get the size
		int size = scanner.nextInt();

		// build the grid
		buildGrid(size);
		// put the letters in the grid
		String data = "";

		while (scanner.hasNext()) {
			String newStr = scanner.next();
			if (newStr.equals("#")) {
				break;
			}
			data += newStr;
		}
		initializeGame(data);
		// get positions
		getPositions();
		System.out.println("Testing position extraction");
		System.out.println(positions);

		System.out.println("Testing the word extraction");
		System.out.println(bonusWords);
		System.out.println(regWords);

	}

	// get the words from the input
	private void getWords() {
		String word = "", type = "";
		while (scanner.hasNext()) {
			word = scanner.next();
			if (word.equals("#"))
				break;
			type = scanner.next();
			if (type.equals("B"))
				bonusWords.add(word);
			else
				regWords.add(word);
		}
	}

	private void getPositions() {
		// positions come in pairs of ints
		// must look for "#"
		String row = "", col = "";
		while (scanner.hasNext()) {
			row = scanner.next();
			if (row.equals("#"))
				break;
			col = scanner.next();
			Position p = new Position();
			p.row = Integer.parseInt(row);
			p.col = Integer.parseInt(col);
			positions.add(p);
		}

	}

	public void initializeGame(String data) {
		// read the data and initialize the game
		int i = 0;
		for (int r = 0; r < grid.size(); r++) {
			for (int c = 0; c < grid.size(); c++) {
				grid.get(r).get(c).value = "" + data.charAt(i++);
			}
		}
		showGrid();

	}

}

class Position {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Position)) {
			return false;
		}
		Position other = (Position) obj;
		return col == other.col && row == other.row;
	}

	public int row = -1, col = -1;

	public String toString() {
		return "(" + row + "," + col + ")";
	}
}

class Cell {
	public int row, col;
	public boolean visited;
	public String value;

	public Cell() {
		row = col = -1;
		visited = false;
		value = "?";
	}

	public Cell(int r, int c) {
		row = r;
		col = c;
		visited = false;
		value = "?";
	}

	public String toString() {
		String str = value;
		return str;
	}
}
