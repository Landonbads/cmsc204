 /**
 * @author Landon Badstibner
 * This is the student test file for the MorseCodeTree class
 */
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MorseCodeTree_STUDENT_Test {
	MorseCodeTree testTree = new MorseCodeTree();
	ArrayList<String> testList = new ArrayList<>();

	@Test
	/**
	 * Testing fetch function for letter K
	 */
	public void testFetch() {	
		String test1 = testTree.fetch("-.-");
		assertEquals("k",test1);
	}
	
	
	/**
	 * Testing for correct implementation of tree and in-order traversal method
	 */
	@Test
	public void testTree() {	
		String returnString = "";
		testList = testTree.toArrayList();
		for(int i = 0;i<testTree.toArrayList().size();i++) {
			if(i!=testTree.toArrayList().size()-1)
				returnString+=testTree.toArrayList().get(i) + " ";
			else
				returnString+=testTree.toArrayList().get(i);
		}
		assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", returnString);

	}
	
	

}
