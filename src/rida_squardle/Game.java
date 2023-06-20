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
	
	private String [][] grid;
	private ArrayList<Location> moves;
	
	public static Scanner fin;
	
	public Game(String inputFile) {
		words = new ArrayList<String>();
		bonusWords = new ArrayList<String>();
		moves = new ArrayList<Location>();
		getInput(inputFile);
		
		//Testing
		for(String s : words)System.out.print(s + " ");
		System.out.println();
		Tasks.sortAndPrint(words);
		for(String s : bonusWords)System.out.print(s + " ");
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
		}
		catch(InputMismatchException e) {
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
		}
		catch(InputMismatchException e) {
			System.out.println("Grid size must be an int");
		}
		
		//get rid of the seperator
		getSep();
		
		grid = new String[gridSize][gridSize];
		String str = "";
		for(int row = 0; row < gridSize; row++) {
			for(int col = 0; col < gridSize; col++) {
				str = fin.next();
				if(str.equals("#")) {
					System.out.println("Format error - not enough chars for the grid.");
					System.exit(40);
				}
				grid[row][col] = str;
			}
		}
	}
	
	//testing
	private void showGrid() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				System.out.printf("| %s ", grid[i][j]);
			}
			System.out.println("|");
		}
	}
	
	private void getLocations() {
		//location must be a pair of ints
		int r, c;
		while(fin.hasNextInt()) {
			r = fin.nextInt();
			if(!fin.hasNextInt()) {
				System.out.println("Input error in moves list");
				System.exit(40);
			}
			c = fin.nextInt();
			moves.add(new Location(r,c));
		}
	}
	
	private void getInput(String fileName) {
		
		initializeFile(fileName);
		getTaskNum();
		getSep();
		getWords();	
		populateGrid();
		getSep();
		getLocations();
		
	}
}
