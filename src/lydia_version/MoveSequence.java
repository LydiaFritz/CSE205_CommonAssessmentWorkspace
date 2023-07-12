package lydia_version;

import java.util.ArrayList;

public class MoveSequence {
	
	private ArrayList<Position> moveList;
	
	public MoveSequence() {
		moveList = new ArrayList<Position>();
	}
	
		
	@Override
	public String toString() {
		
		return "MoveSequence " + moveList + "";
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

}
