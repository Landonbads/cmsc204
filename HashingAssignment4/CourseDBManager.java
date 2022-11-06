import java.io.*; 
import java.util.ArrayList;

/**
 * CourseDBManager class to manage database of courses
 * @author Landon Badstibner
 */
public class CourseDBManager implements CourseDBManagerInterface {

	CourseDBStructure structure = new CourseDBStructure(500); //random constant for estimated # of courses
	
	/**
	 * Add method to create and add CourseDBElement to hashtable
	 * @param String id
	 * @param int crn
	 * @param int credits
	 * @param String roomNum
	 * @param String instructor
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		structure.add(new CourseDBElement(id,crn,credits,roomNum,instructor));
	}

	/**
	 * readFile method to read input of a file and add the corresponding courses to hashtable
	 * @param File input - file to read
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(input));
		String[] str;
		try {
			String line;
			while((line = reader.readLine()) != null) {
				String id;
				int crn;
				int credits;
				String roomNum;
				String instructor;
				str = line.split(" ",5);
				id = str[0];
				crn = Integer.parseInt(str[1]);
				credits = Integer.parseInt(str[2]);
				roomNum = str[3];
				instructor = str[4];
				add(id,crn,credits,roomNum,instructor);
			}
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * showAll() method to display all courses in the hashtable as a readable list of strings
	 * @return ArrayList containing all CourseDBElements in the hashtable
	 */
	@Override
	public ArrayList<String> showAll() {
		return structure.showAll();
	}

	/**
	 * get method to find and return course with referenced crn in hashtable
	 * @param int crn - crn to be found in hashtable
	 * @return CourseDBElement in the hashtable that corresponds to crn parameter, IOException if doesn't exist
	 */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return structure.get(crn);
		}
		catch(IOException e){
			return null;
		}
	}//get method ends
}//CourseDBManager class ends
