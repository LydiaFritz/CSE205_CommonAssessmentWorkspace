package rida_squardle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

	private int taskNum, gridSize;

	private ArrayList<String> words;
	private ArrayList<String> bonusWords;

	private String[][] grid;
	private ArrayList<ArrayList<Location>> moves;

	public static Scanner fin;

	public Game(String inputFile) {

		words = new ArrayList<String>();
		bonusWords = new ArrayList<String>();
		moves = new ArrayList<ArrayList<Location>>();

		getInput(inputFile);

		// Testing
		// testInitialization();

	}

	public void doTask() {
		switch (taskNum) {
		case 1:
			Tasks.sortAndPrint(words);
			Tasks.sortAndPrint(bonusWords);
			break;

		case 2:
			int error = Tasks.task2(moves, gridSize);
			if (error == 0)
				System.out.println("YES");
			else if (error == 1)
				System.out.println("NO 1");
			else if (error == 2)
				System.out.println("NO 2");
			else if (error == 3)
				System.out.println("NO 3");
			break;
		}

	}

	private void getInput(String fileName) {

		initializeFile(fileName);
		getTaskNum();
		getSep();
		getWords();
		populateGrid();
		getSep();
		getMoves();

	}

	private void testInitialization() {

		for (String s : words)
			System.out.print(s + " ");
		System.out.println();
		Tasks.sortAndPrint(words);
		for (String s : bonusWords)
			System.out.print(s + " ");
		System.out.println();
		System.out.println("Checking the grid");
		showGrid();
		System.out.println("Show moves list");
		System.out.println(moves);
	}

	private void initializeFile(String fileName) {
		try {
			fin = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.printf("%s\n", "File not found.");
			e.printStackTrace();
		}
	}

	private void getTaskNum() {
		try {
			taskNum = fin.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Task num is not an integer");
		}
	}

	private void getWords() {
		String word, type;
		word = fin.next();
		while (!word.equals("#")) {
			type = fin.next();
			if (type.equals("#")) {
				System.out.println("Incorrect format");
				System.exit(10);
			}
			// still here, so add word to appropriate list
			if (type.equals("R"))
				words.add(word);
			else if (type.equals("B"))
				bonusWords.add(word);
			else {
				System.out.println("illegal type");
				System.exit(20);
			}
			word = fin.next();

		}
	}

	private void getSep() {
		// get #
		String sep = fin.next();
		if (!sep.equals("#")) {
			System.out.println("Incorrect format");
			System.exit(10);
		}
	}

	private void populateGrid() {

		try {
			gridSize = fin.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Grid size must be an int");
		}

		// get rid of the seperator
		getSep();

		grid = new String[gridSize][gridSize];
		String str = "";
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				str = fin.next();
				if (str.equals("#")) {
					System.out.println("Format error - not enough chars for the grid.");
					System.exit(40);
				}
				grid[row][col] = str;
			}
		}
	}

	// testing
	private void showGrid() {
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				System.out.printf("| %s ", grid[i][j]);
			}
			System.out.println("|");
		}
	}

	// get the list of moves from input
	private void getMoves() {
		// moves come in sets, delimited by &
		ArrayList<Location> lst = new ArrayList<Location>();
		//read values until & or eof

		while(fin.hasNext()) {
			String sep = "";
			int row = -1, col = -1;
			try {
				row = fin.nextInt();
				col = fin.nextInt();
			}catch(InputMismatchException e) {
				e.getLocalizedMessage();
			}
			//add this location to the lst
			lst.add(new Location(row, col));
		}
		
	}

}
