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

	public static int task2(ArrayList<Location> moveList, int gridSize) {

		int errorCode = 0;

		// iterate over the moves
		for (Location l : moveList) {
			if (isOutOfBounds(l, gridSize)) {
				errorCode = 1;
			}
		}

		if (errorCode == 0) {
			// iterate over the moves
			for (int i = 0; i < moveList.size() - 1; i++) {
				if (!areAdjacent(moveList.get(i), moveList.get(i + 1))) {
					errorCode = 2;
				}
			}
		}
		
		if(errorCode == 0) {
			//check for duplicate moves
			for(int i = 0; i < moveList.size(); i++) {
				for(int j = 0; j < moveList.size() && errorCode == 0; j++) {
					if( i != j && moveList.get(i).equals(moveList.get(j))) {
						errorCode = 3;
					}
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
