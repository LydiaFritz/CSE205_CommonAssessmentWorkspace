package rida_squardle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private int taskNum;
	
	private ArrayList<String> words;
	private ArrayList<String> bonusWords;
	
	private String [][] grid;
	
	public static Scanner fin;
	
	public Game(String inputFile) {
		getInput(inputFile);
	}
	
	private void getInput(String fileName) {
		try {
			fin = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.printf("%s\n", "File not found.");
			e.printStackTrace();
		}
	}
}
