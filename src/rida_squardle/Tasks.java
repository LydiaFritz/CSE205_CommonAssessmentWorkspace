package rida_squardle;

import java.util.ArrayList;
import java.util.Collections;

//class to define the Tasks

public class Tasks {
	
	public static void sortAndPrint(ArrayList<String> mylst) {
		Collections.sort(mylst);
		for(String str : mylst) {
			System.out.printf("%s ", str);
		}
		System.out.println();
	}

}
