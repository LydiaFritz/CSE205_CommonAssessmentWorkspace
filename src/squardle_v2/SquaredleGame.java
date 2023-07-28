package squardle_v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SquaredleGame {

	private ArrayList< ArrayList<Cell> > grid;
	public static Scanner scanner = null;
	private ArrayList<String> bonusWords = new ArrayList<String>();
	private ArrayList<String> regWords = new ArrayList<String>();

	
	//constructor
	public SquaredleGame() {
		//allocate the grid
		grid = new ArrayList<ArrayList<Cell>>();		
	}
	
	//for testing
	private void showGrid() {
		for(int r = 0; r < grid.size(); r++) {
			for(int c = 0; c < grid.size(); c++) {
				System.out.printf("| %s ", grid.get(r).get(c).value);
			}
			System.out.println("|");
		}
		System.out.println();
	}
	
	//allocate and populate grid with cells
	private void buildGrid(int size) {

		for(int row = 0; row < size; row++) {
			grid.add(new ArrayList<Cell>());
			for(int col = 0; col < size; col++) {
				grid.get(row).add(new Cell(row, col));				
			}
		}
		
	}
	public void getInput(String str) throws FileNotFoundException {
		scanner = new Scanner(new File(str));
		
		//get the size first
		int size = scanner.nextInt();
		
		//extract the #
		String sep = scanner.next();
		getWords();
		
		System.out.println("Testing the word extraction");
		System.out.println(bonusWords);
		System.out.println(regWords);
		
		//build the grid
		buildGrid(size);
		
		//put the letters in the grid
		String data = "";
		while(scanner.hasNext()) {
			data += scanner.next();
		}
		initializeGame(data);
		
	}
	//get the words from the input
	private void getWords() {
		String word="", type="";
		while(scanner.hasNext()) {
			word = scanner.next();
			if(word.equals("#"))break;
			type = scanner.next();
			if(type.equals("B"))
				bonusWords.add(word);
			else
				regWords.add(word);
		}
	}
	
	public void initializeGame(String data) {
		//read the data and initialize the game
		int i = 0;
		for(int r = 0; r < grid.size(); r++) {
			for(int c = 0; c < grid.size(); c++) {
				grid.get(r).get(c).value = "" + data.charAt(i++);
			}
		}
		showGrid();
		
	}

	
}

class Cell{
	public int row, col;
	public boolean visited;
	public String value;
	
	public Cell(){
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
