/**
 * @author Landon Badstibner
 * MorseCodeConverter class to convert morse code to english
 */
import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MorseCodeConverter {
	static MorseCodeTree test1 = new MorseCodeTree();


	/**
	 * Converts Morse code into English. Each letter is delimited by a space (‘ ‘).
	 * Each word is delimited by a ‘/’.
	 * @param code - the morse code
	 * @return the English translation
	 */
	public static String convertToEnglish(String code) {
		String returnString = "";
		String[] test = code.split(" ");
		for(int i = 0;i < test.length;i++) {
			if(test[i].equals("/"))
				returnString += " ";
			else
				returnString += test1.fetch(test[i]);	
		}
		return returnString;
	}
	

	/**
	 * Converts file of Morse code into English. Each letter is delimited by a space (‘ ‘).
	 * Each word is delimited by a ‘/’.
	 * @param codeFile - name of the File that contains Morse Code
	 * @return the English translation of the file
	 * @throws java.io.FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		String returnString = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(codeFile));
			returnString = reader.readLine();
			String[] test = returnString.split(" ");
			returnString = "";
			for(int i = 0;i < test.length;i++) {
				if(test[i].equals("/"))
					returnString += " ";
				else
					returnString += test1.fetch(test[i]);	
			}
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	
	/**
	 * returns a string with all the data in the tree in LNR order with a space in between them.
	 * @return the data in the tree in LNR order separated by a space.
	 */
	public static String printTree() {
		String output = "";
		for(int i = 0;i < test1.toArrayList().size();i++) {
			if(i!=test1.toArrayList().size()-1)
				output += test1.toArrayList().get(i) + " ";
			else
				output += test1.toArrayList().get(i);
		}
		return output;
	}
}
