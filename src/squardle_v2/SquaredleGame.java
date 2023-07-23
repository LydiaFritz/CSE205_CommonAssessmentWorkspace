package squardle_v2;

import java.util.ArrayList;

public class SquaredleGame {

	private ArrayList< ArrayList<Cell> > grid;
	
	public SquaredleGame(int size) {
		
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
	
	public String toString() {
		String str = value;
		return str;
	}
}
