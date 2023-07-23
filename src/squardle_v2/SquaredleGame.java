package squardle_v2;

import java.util.ArrayList;

public class SquaredleGame {

	private ArrayList< ArrayList<Cell> > grid;
	
	public SquaredleGame(int size) {
		//allocate the grid
		grid = new ArrayList<ArrayList<Cell>>();
		buildGrid(size);
		showGrid();
		
	}
	
	//for testing
	private void showGrid() {
		for(int r = 0; r < grid.size(); r++) {
			for(int c = 0; c < grid.size(); c++) {
				System.out.printf("| %s ", grid.get(r).get(c).value);
			}
			System.out.println("|");
		}
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
