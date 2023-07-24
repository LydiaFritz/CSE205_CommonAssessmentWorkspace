package squardle_v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		SquaredleGame game = new SquaredleGame(4);
		game.initializeGame(getData("test.in"));
	}
	
	//return a string containing the data in a file
	public static String getData(String filename) {
		//initially, just read in letters that populate the grid
		Scanner fin = null;
		String theData = "";
		try {
			fin = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		//read in the letters and build a string
		while(fin.hasNext()) {
			theData += fin.next();
		}
		return theData;
	}
}
