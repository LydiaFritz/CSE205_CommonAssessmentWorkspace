package rida_squardle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Driver {

	public static void main(String[] args) throws IOException {
		
		Game g = new Game("input.in");
		//g.doTask();
		
		FileReader f_reader = new FileReader(new File("input.in"));
		BufferedReader reader = new BufferedReader(f_reader);
		
		String str = "";
		while((str = reader.readLine()) != null) {
			System.out.println(str);
		}
	}

}
