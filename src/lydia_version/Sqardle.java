package lydia_version;

import java.util.ArrayList;

public class Sqardle {
	
	//inner position class
	class Position{
		
		public int column;		
		public int row;
		
		Position(int c, int r){column = c; row = r;}		
	}
	
	//Squardle instance variables
	private int taskNum;
	private ArrayList<String> regularWords;
	private ArrayList<String> bonusWords;
	private String[][] grid;
	private Position [] positions;
	
	
	
}
