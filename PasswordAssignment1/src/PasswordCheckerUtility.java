import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * Several methods used to check if password is valid, weak, or invalid
 * @author Landon Badstibner
 */

public class PasswordCheckerUtility extends PasswordMain{



	/**
	 * Method that checks for invalid passwords
	 * @param passwords ararylist of String passwords
	 * @return an ArrayList with the status of any invalid passwords and their exception message
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		ArrayList<String> checkPasswords = new ArrayList<String>();
		
		for(String str : passwords) {
			try {
				if(!isValidPassword(str)) {
				}
			}
			
			catch(Exception e) {
				checkPasswords.add(str + " -> " + e.getMessage());
			}
			
		}
		
		return checkPasswords;
	}
	
	
	
	
	/**
	 * Method that checks if password is valid
	 * @param password that user enters
	 * @return true if valid password, return false if invalid
	 */
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException{
		if(isValidLength(password)) {
			if(hasUpperAlpha(password)) {
				if(hasLowerAlpha(password)) {
					if(hasDigit​(password)) {
						if(hasSpecialChar(password)) {
							if(noSameCharInSequence(password)) {
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	
	
	/**
	 * Checks if password length is between 6-9 characters and if password is valid
	 * @param password that user enters
	 * @return false if password is not weak, throw exception if password is weak AND valid
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException, LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException{
		if(hasBetweenSixAndNineChars(password) && isValidPassword(password)) {
			throw new WeakPasswordException();
		}
		return false;
	}
	
	
	
	/**
	 * Compare equality of two passwords
	 * @param password that user enters in first part of GUI
	 * @param password that user enters in second part of GUI
	 * @return true if passwords equal each other and false if they do not
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		if(password.equals(passwordConfirm)) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * Compare equality of two passwords
	 * @param password that user enters in first part of GUI
	 * @param password that user enters in second part of GUI
	 * @return unMatchedException if passwords do not equal, void otherwise
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{
		if(!password.equals(passwordConfirm)) {
			throw new UnmatchedException();
		}
	}
	
	
	
	/**
	 * checks if the password contains 6 to 9 characters
	 * @param password string that user enters
	 * @return true if password is between 6 and 9 characters, false otherwise
	 */
	private static boolean hasBetweenSixAndNineChars(String password) {
		if(password.length()>=6 && password.length()<=9) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * checks password contains at least one lowercase alpha character
	 * @param password string that user enters
	 * @return true if password has lowerchase alpha character, noLowerAlphaException otherwise
	 */
	private static boolean hasLowerAlpha(String password) throws NoLowerAlphaException{
		for(int i=0;i<password.length();i++) {
			char c = password.charAt(i);
			if(Character.isLowerCase(c)) {
				return true;
			}
		}
		throw new NoLowerAlphaException();
	}
	
	
	

	/**
	 * checks password contains a Special Character
	 * @param password string that user enters
	 * @return true if password has special character, NoSpecialCharacterException otherwise
	 */
	private static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		if(!matcher.matches())
			return true;
		else
			throw new NoSpecialCharacterException();
	}
	
	
	

	/**
	 * checks password contains an uppercase alpha character
	 * @param password string that user enters
	 * @return true if password has uppercase alpha character, NoUpperAlphaException otherwise
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
		for(int i=0;i<password.length();i++) {
			char c = password.charAt(i);
			if(Character.isUpperCase(c)) {
				return true;
			}
		}
		throw new NoUpperAlphaException();
	}
	
	
	
	/**
	 * checks password is at least 6 characters long
	 * @param password string that user enters
	 * @return true if password is more than 6 characters, LengthException otherwise
	 */
	public static boolean isValidLength(String password) throws LengthException{
		if(password.length()<6) {
			throw new LengthException();
		}
		return true;
	}
	
	
	
	/**
	 * checks password for more than 2 of same character in sequence
	 * @param password string that user enters
	 * @return true if password does not have more than 2 characters in sequence, InvalidSequenceException otherwise
	 */
	private static boolean noSameCharInSequence(String password) throws InvalidSequenceException{
		for(int i = 0;i<password.length()-2;i++) {
			String character = password.substring(i,i+1);
			String character2 = password.substring(i+1,i+2);
			String character3 = password.substring(i+2,i+3);
			if(character.equals(character2) && character.equals(character3)) {
				throw new InvalidSequenceException();
			}
		}
		return true;
	}
	
	
	
	/**
	 * checks password contains a digit
	 * @param password string that user enters
	 * @return true if password contains a digit, NoDigitException otherwise
	 */
	private static boolean hasDigit​(String password) throws NoDigitException {
		for(int i=0;i<password.length();i++) {
			char c = password.charAt(i);
			if(Character.isDigit(c)) {
				return true;
			}
		}
		throw new NoDigitException();
	}	
}
