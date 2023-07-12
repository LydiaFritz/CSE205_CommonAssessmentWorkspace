package lydia_version;

import java.util.ArrayList;

public class MoveSequence {
	
	private ArrayList<Position> moveList;
	
	public MoveSequence() {
		moveList = new ArrayList<Position>();
	}
	
		
	@Override
	public String toString() {
		
		String str = "";
		for(int i = 0; i < moveList.size(); i++) {
			str+=moveList.get(i) + ",";
		}
		return str.substring(0,str.length()-1);
	}

	public void isValid(int gridSize) {
		int errCode = 0;
		errCode = inBounds(gridSize);
		if(errCode != 0) {
			System.out.println("NO " + errCode);
			return;
		}
		errCode = areAdjacent();
		if(errCode != 0) {
			System.out.println("NO " + errCode);
			return;
		}
		errCode = isUnique();
		if(errCode != 0) {
			System.out.println("NO " + errCode);
			return;
		}
		System.out.println("YES");
	}

	public void addMove(Position p) {
		moveList.add(p);
	}
	
	private int isUnique() {
		//returns true if this sequence contains only
		//unique moves
		boolean unique = true;
		for(int i = 0; i < moveList.size(); i++) {
			for(int j = 0; j < moveList.size() && unique; j++) {
				if(i == j)continue;
				else if(moveList.get(i).equals(moveList.get(j))) {
					unique = false;
					System.out.println("There are repeated positions in this sequence.");
				}
					
			}
		}
		if(!unique)return 3;
		return 0;
	}
	
	private int inBounds(int gridSize) {
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
		if(!ok) {
			return 1;
		}
		return 0;
	}

	private int areAdjacent() {
		boolean ok = true;
		//make sure moves are adjacent
		//(m,n) has the following adjacent moves
		//(m+1,n-1), (m+1,n), (m+1,n+1), 
		//(m,n-1), (m,n+1)
		//(m-1,n-1), (m-1,n), (m-1,n+1)
		for(int i = 0; i < moveList.size()-1 && ok; i++) {
			//check all directions
			Position curr = moveList.get(i);
			Position next = moveList.get(i+1);
			if(!areAdjacent(curr, next)) ok = false;
		}
		if(!ok) {
			return 2;
		}
		return 0;
	}
	
	private boolean areAdjacent(Position a, Position b) {
		//a.column must be +-1 b.column
		//a.row must be +- b.row
		return(a.row == b.row+1 ||
				a.column == b.column+1 ||
				a.row == b.row-1 ||
				a.column == b.column-1);
	}
}
