package squardle_v2;

import java.util.ArrayList;

public class SquaredleGame {

	private ArrayList< ArrayList<Cell> > grid;
	
	public SquaredleGame(int size) {
		//allocate the grid
		grid = new ArrayList<ArrayList<Cell>>();
		buildGrid(size);
		
	}
	
	private void buildGrid(int size) {
		for(int row = 0; row < size; row++) {
			grid.add(new ArrayList<Cell>());
			for(int col = 0; col < size; col++) {
				grid.get(row).add(new Cell(row, col));				
			}
		}
		
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
