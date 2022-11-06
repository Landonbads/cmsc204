/**
 * @author Landon Badstibner
 * CourseDBElement class to represent each course
 */

public class CourseDBElement implements Comparable{
	
	//instance variables
	private String courseID;
	private int crn;
	private int numCredits;
	private String roomNum;
	private String instructorName;

	/**
	 * CourseDBElement constructor to initialize course variables
	 * @param String courseID
	 * @param int crn
	 * @param int numCredits
	 * @param String roomNum
	 * @param String instructorName
	 */
	public CourseDBElement(String courseID, int crn, int numCredits, String roomNum, String instructorName) {
		this.courseID = courseID;
		this.crn = crn;
		this.numCredits = numCredits;
		this.roomNum = roomNum;
		this.instructorName = instructorName;
	}
	
	/**
	 * Empty CourseDBElement constructor
	 */
	public CourseDBElement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * sets the course number or updates course number of a CourseDBElement object
	 * @param crn - course number to be set
	 */
	public void setCRN(int crn) {
		this.crn = crn;	
	}

	/**
	 * returns the CRN of the CourseDBElement object referenced
	 * @return crn - returns course number as int
	 */
	public int getCRN(){
		return crn;
	}
	
	/**
	 * returns the ID of the CourseDBElement object referenced
	 * @return courseID - returns course ID as String
	 */
	public String getID() {
		return courseID;
	}

	/**
	 * returns the room number of the CourseDBElement object referenced
	 * @return roomNum - room number of course
	 */
	public String getRoomNum() {
		return roomNum;
	}
	
	/**
	 * returns the number of credits of the CourseDBElement object referenced
	 * @return numCredits - number of credits course has
	 */
	public int getNumCredits() {
		return numCredits;
	}
	
	/**
	 * returns the instructor name of the CourseDBElement object referenced
	 * @return instructorName - the name of the instructor of the course
	 */
	public String getInstructorName() {
		return instructorName;
	}
	
	/**
	 * toString method for easy access in the CourseDBStructure class
	 * @return CourseDBElement as a readable String
	 */
	public String toString() {
		return "\nCourse:" + courseID + " CRN:" + crn + " Credits:" + numCredits + " Instructor:" + instructorName + " Room:" + roomNum;
	}

	/**
	 * toString method for easy access in the CourseDBStructure class
	 * @param Object o - compare one CourseDBElement to another
	 * @return negative value if referenced courseDBElement CRN is < Other and positive if CRN is greater
	 */
	public int compareTo(Object o) {
		return getCRN() - CourseDBElement.class.cast(o).getCRN();
	}

}
