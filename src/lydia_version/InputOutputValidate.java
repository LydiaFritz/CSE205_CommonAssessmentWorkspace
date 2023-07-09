package lydia_version;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputOutputValidate {

	//Input specification
	/**
	 * type is R (regular) or B (bonus)
	 * x1 y1 coordinates of the first move
	 * 
	 * taskNum # 
	 * word1 type word2 type word3 type word4 type #
	 * gridSize # 
	 * x1 y1 x2 y2 x3 y3 
	 */
	public static Scanner fin;
	private static String rawInput = "";
		
	
	public static void getInput(String filename) throws FileNotFoundException {
		
		//get input
		fin = new Scanner(new File(filename));
		rawInput = "";
		while(fin.hasNext()) {
			rawInput += fin.next() + " ";
		}				
	}
	
	
	
	public static int getTaskNum() {
		Scanner inputScn = new Scanner(rawInput);
		int taskNum = -1;
		//check for task num
		try {
			taskNum = inputScn.nextInt();
		}
		catch(InputMismatchException me) {
			System.out.println(me.getMessage());
		}
		return taskNum;
	}
}
