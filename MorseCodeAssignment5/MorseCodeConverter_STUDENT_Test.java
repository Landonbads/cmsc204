/**
 * @author Landon Badstibner
 * This is the student test file for the MorseCodeConverter class
 */
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MorseCodeConverter_STUDENT_Test {
	
	
	/**
	 * Testing for correct implementation of convertToEnglish(String)
	 */
	@Test
	public void testConvertToEnglishString() {	
		String converter = MorseCodeConverter.convertToEnglish("- .... .. ... / .. ... / - .- -.- .. -. --. / - --- --- / .-.. --- -. --.");
		assertEquals("this is taking too long",converter);
	}
	
	
	/**
	 * Testing for correct implementation of convertToEnglish(File)
	 */
	@Test
	public void testConvertMorseFileToEnglishString() {	
		File file = new File("src/LoveLooksNot.txt"); 
		try {
			assertEquals("love looks not with the eyes but with the mind", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("An unwanted exception was caught", false);
		}
	}
	
}
