import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the student test file for the CourseDBManager
 * @author Landon Badstibner
 */
public class CourseDBManager_STUDENT_Test {
	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	/**
	 * Test multiple inputs for the add method
	 */
	@Test
	public void testMultipleAddToDB() {
		try {
			dataMgr.add("CMSC203",30504,4,"SC450","Joey Bag-O-Donuts");
			dataMgr.add("CMSC203",30504,4,"SC450","Joey Bag-O-Donuts");
			dataMgr.add("CMSC204",30599,4,"SC451","Alexander");
			dataMgr.add("CMSC110",20484,3,"HT300","Madhvi A. Shah");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test read method for multiple courses in courses.txt file
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("courses.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC100 21556 2 Distance-Learning Janet E. Joy ");
			inFile.println("CMSC100 22344 2 SW217 Gloria E. Barron");
			inFile.println("CMSC100 22974 2 Distance-Learning Janet E. Joy");
			inFile.println("CMSC110 21561 3 SC451 Rabiha J. Kayed");
			inFile.println("CMSC110 20484 3 HT300 Madhvi A. Shah");
			inFile.println("CMSC110 23363 3 SC451 Sascha Simkanich");
			inFile.println("CMSC110 21565 3 Distance Learning Janet E. Joy");
			inFile.println("CMSC110 21564 3 SC451 Behzad Maghami");
			inFile.print("CMSC110 21560 3 SC450 Behzad Maghami");
			inFile.close();
			dataMgr.readFile(inputFile);
			ArrayList<String> test = new ArrayList<>();
			test = dataMgr.showAll();
			System.out.println(test);
			
			assertEquals("CMSC110",dataMgr.get(20484).getID());
			assertEquals("Janet E. Joy ",dataMgr.get(21556).getInstructorName());
			assertEquals(3,dataMgr.get(21560).getNumCredits());
			assertEquals("SC451",dataMgr.get(21561).getRoomNum());
			assertEquals("SC451",dataMgr.get(21564).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}//testRead() method ends
}//CourseDBManager_STUDENT_Test class ends