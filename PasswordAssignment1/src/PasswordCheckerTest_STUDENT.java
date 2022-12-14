
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Landon Badstibner
 */
public class PasswordCheckerTest_STUDENT {
	
	ArrayList<String> studentPasswords;
	String p1 = "afF8#lOaAzm";
	String p2 = "mOo9*lzai#1daA";
	String shortPass = "abcAB";
	String noUpperAlpha = "123456";
	String noLowerAlpha = "123456A";
	String weak = "1234@aA";
	String sequence = "123@4aaAAA";
	String noDigit = "pilotProject";
	
	
	
	@Before
	public void setUp() throws Exception {
		studentPasswords = new ArrayList<String>();
		String[] p = {p1,p2,"334455BB#", "george2ZZZ#", "4Sal#", "bertha22", "august30", "a2cDe", 
				"ApplesxxxYYzz#", "aa11Bb", "pilotProject", "AAAbb@123"};
		studentPasswords.addAll(Arrays.asList(p));
	}

	@After
	public void tearDown() throws Exception {
		studentPasswords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 * @throws LengthException 
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword(p1));
			assertTrue(PasswordCheckerUtility.isValidPassword(shortPass));
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword(p1));
			assertTrue(PasswordCheckerUtility.isValidPassword(noUpperAlpha));
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaException",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException",false);
		}
	}
	
	
	
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword(p1));
			assertTrue(PasswordCheckerUtility.isValidPassword(noLowerAlpha));
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaException",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoLowerAlphaException",false);
		}
	}
	/**
	 * Test if the password is weak
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			assertTrue(PasswordCheckerUtility.isWeakPassword(weak));
			assertTrue(PasswordCheckerUtility.isWeakPassword(p1));
			assertTrue("Did not throw WeakPassword Exception",false);
		}
		catch(WeakPasswordException e)
		{
			assertTrue("Successfully threw a WeakPasswordException",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides WeakPasswordException",false);
		}
	}
	
	
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword(p1));
			assertTrue(PasswordCheckerUtility.isValidPassword(sequence));
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw a InvalidSequenceExcepetion",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides InvalidSequenceExcepetion",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword(p1));
			assertTrue(PasswordCheckerUtility.isValidPassword(noDigit));
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw a NoDigitException",true);
		}
		 
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoDigitException",false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword(p1));
			assertTrue(PasswordCheckerUtility.isValidPassword(p2));
		}
		catch(Exception e)
		{
			assertTrue("Threw an exception",false);
		}
	}
	
	
	
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 * @throws InvalidSequenceException 
	 * @throws NoSpecialCharacterException 
	 * @throws NoDigitException 
	 * @throws NoLowerAlphaException 
	 * @throws NoUpperAlphaException 
	 * @throws LengthException 
	 */
	@Test
	public void testInvalidPasswords() throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(studentPasswords);
		
		
		
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), "334455BB#");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));
		
		scan = new Scanner(results.get(1));  
		assertEquals(scan.next(), "george2ZZZ#");
		nextResults = scan.nextLine().toLowerCase(); 
		assertTrue(nextResults.contains("sequence"));
		
		 
		scan = new Scanner(results.get(2));  
		assertEquals(scan.next(), "4Sal#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long"));
		
		scan = new Scanner(results.get(3));  
		assertEquals(scan.next(), "bertha22");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		
		scan = new Scanner(results.get(4));  
		assertEquals(scan.next(), "august30");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase") );
		
		scan = new Scanner(results.get(5));  
		assertEquals(scan.next(), "a2cDe");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long") );
		
		scan = new Scanner(results.get(6));  
		assertEquals(scan.next(), "ApplesxxxYYzz#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );
		
		scan = new Scanner(results.get(7));  
		assertEquals(scan.next(), "aa11Bb");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("special") );
		
		
		scan = new Scanner(results.get(8)); 
		assertEquals(scan.next(), "pilotProject");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );
		
		scan = new Scanner(results.get(9));  
		assertEquals(scan.next(), "AAAbb@123");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("sequence") );
	}
	
}
