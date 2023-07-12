package lydia_version;

import java.util.ArrayList;

public class MoveSequence {
	
	private ArrayList<Position> moveList;
	
	public MoveSequence() {
		moveList = new ArrayList<Position>();
	}
	
		
	@Override
	public String toString() {
		
		return "" + moveList + "";
	}



	public void addMove(Position p) {
		moveList.add(p);
	}
	
	public boolean isUnique() {
		//returns true if this sequence contains only
		//unique moves
		boolean unique = true;
		for(int i = 0; i < moveList.size(); i++) {
			for(int j = 0; j < moveList.size(); j++) {
				if(i == j)continue;
				else if(moveList.get(i).equals(moveList.get(j)))
					return false;
				
			}
		}
		return unique;
	}
	
	public boolean inBounds(int gridSize) {
		boolean ok = true;
		//all indicies n: 0 >= n > gridSize
		for(int i = 0; i < moveList.size() && ok; i++) {
			if(moveList.get(i).column < 0 ||
					moveList.get(i).column >= gridSize)
				ok = false;
			else if(moveList.get(i).row >= gridSize ||
					moveList.get(i).row < 0)
				ok = false;
		}
		return ok;
	}

}
