package rida_squardle;

public class Location {
	//defines a Location on the grid
	private int row, col;
	
	Location(int r, int c){row = r; col = c;}
	
	int getRow() {return row;}
	int getCol() {return col;}

	@Override
	public String toString() {
		return "[" + row + ", " + col + "]";
	}
	
	
}
