package rida_squardle;

import java.util.Objects;

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

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Location)) {
			return false;
		}
		Location other = (Location) obj;
		return col == other.col && row == other.row;
	}
	
	
	
	
}
