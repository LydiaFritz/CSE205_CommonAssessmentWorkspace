package lydia_version;

import java.util.Objects;

//position class
public class Position {

	public int column;
	public int row;

	public Position(int c, int r) {
		column = c;
		row = r;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Position)) {
			return false;
		}
		Position other = (Position) obj;
		return column == other.column && row == other.row;
	}

	@Override
	public String toString() {
		return "[" + column + "," + row + "]";
	}

}
