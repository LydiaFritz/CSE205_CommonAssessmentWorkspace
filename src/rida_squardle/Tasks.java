package rida_squardle;

import java.util.ArrayList;
import java.util.Collections;

//class to define the Tasks

public class Tasks {

	public static void sortAndPrint(ArrayList<String> mylst) {
		Collections.sort(mylst);
		for (String str : mylst) {
			System.out.printf("%s ", str);
		}
		System.out.println();
	}

	public static int task2(ArrayList<ArrayList<Location>> moveList, int gridSize) {

		int errorCode = 0;

		// iterate each list of moves
		for (ArrayList<Location> lst : moveList) {
			//for each move in this list
			for (Location loc : lst) {
				if(isOutOfBounds(loc, gridSize))
					return 1;				
			}
			for(int i = 0; i < moveList.size(); i++) {
				for(int j = 0; j < moveList.get(i).size()-1; j++)
					if(!areAdjacent(moveList.get(i).get(j), moveList.get(i).get(j+1))) {
						return 2;
					}
			}
			for(int i = 0; i < moveList.size(); i++) {
				if(repeatedMove(moveList.get(i))) {
					return 3;
				}
			}
		}

		return errorCode;
	}

	private static boolean repeatedMove(ArrayList<Location> lst) {
		boolean repeats = false;
		for (int i = 0; i < lst.size(); i++) {
			for (int j = 0; j < lst.size(); j++) {
				if ((i != j) && (lst.get(i).equals(lst.get(j)))) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean areAdjacent(Location a, Location b) {
		int aRow = a.getRow(), aCol = a.getCol();
		// see if b row is valid
		if (b.getRow() > aRow + 1 || b.getRow() < aRow - 1)
			return false;
		else if (b.getCol() > aCol + 1 || b.getCol() < aCol - 1)
			return false;
		return true;

	}

	private static boolean isOutOfBounds(Location loc, int gridSize) {
		if (loc.getRow() < 0 || loc.getRow() >= gridSize)
			return true;
		if (loc.getCol() < 0 || loc.getCol() >= gridSize)
			return true;
		return false;
	}

}
