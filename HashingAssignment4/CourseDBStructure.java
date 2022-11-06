import java.io.IOException;
import java.util.ArrayList;

/**
 * CourseDBStructure class to represent Hashtable of courses - array of linked lists
 * @author Landon Badstibner
 */

public class CourseDBStructure {
	//couple instance variables
	private int tableSize;
	private Node hashTable[];	
	
	
	/**
	 * Node class to represent linked list part of every index in hashtable as needed
	 */
	public class Node{
		int key;
		CourseDBElement data;
		Node next;
		/**
		 * Node constructor to set key and data of node
		 */
		public Node(int key, CourseDBElement d) {
			data = d;
			this.key = key;
		}
	}
	
	
	/**
	 * CourseDBStructure constructor to construct hashtable 
	 * @param int n - estimated number of courses
	 */
	public CourseDBStructure(int n) {
		Double division = n/1.5; //333
		boolean eligiblePrime = true;
		int counter = 0;
		while(tableSize<division || eligiblePrime == false) {
			eligiblePrime = true;
			tableSize = counter * 4 + 3;
			if(tableSize>division) {
				for(int i = 2; i<tableSize/2;i++) {
					if(tableSize%i==0) {
						eligiblePrime=false;
					}
				}
		}
			counter++;
		}//while loop ends
		hashTable = new Node[tableSize];
	}//courseDBStructure constructor ends
	

	/**
	 * CourseDBStructure constructor strictly for testing purposes
	 * @param String testString - testing purposes
	 * @param int size - estimated num of courses
	 */
	public CourseDBStructure(String testString, int size) {
		tableSize = size; 
		hashTable = new Node[tableSize];
	}
	
	
	/**
	 * gets size of hashtable
	 * @return size of hashtable
	 */
	public int getTableSize() {
		return tableSize;
	}

	
	/**
	 * add method - calculates hashcode using crn of parameter, turns hashcode into an index so CourseDBElement can be inserted into the hashtable
	 * @param CourseDBElement cde1 - course to be added to hash table
	 */
	public void add(CourseDBElement cde1) {
		int index = String.valueOf(cde1.getCRN()).hashCode() % tableSize;
		boolean found = false;
		if(hashTable[index]==null) {
			hashTable[index] = new Node(cde1.getCRN(), cde1);
		}
		else {
			Node n = hashTable[index];
			if(n.key==cde1.getCRN()) {
				found = true;
				n.data = cde1;
			}
			while(n.next!=null) {
				if(n.next.key==cde1.getCRN()) {
					found = true;
					n.next.data = cde1;
				}
				n = n.next;
			}
			if(!found) {
				n.next = new Node(cde1.getCRN(),cde1);
			}
			
		}
	}
	

	/**
	 * get method to find and return course with referenced crn in hashtable
	 * @param int crn - crn to be found in hashtable
	 * @return CourseDBElement in the hashtable that corresponds to crn parameter, IOException if doesn't exist
	 */
	public CourseDBElement get(int crn) throws IOException {
		int index = String.valueOf(crn).hashCode() % tableSize;
		
		Node n = hashTable[index];
		if(n!=null) {
			if(n.key==crn) {
				return n.data;
			}
			while(n.next!=null) {
				if(n.next.key==crn) {
					return n.next.data;
				}
				n = n.next;
			}
	}
		throw new IOException();
	}

	/**
	 * showAll() method to display all courses in the hashtable as a readable list of strings
	 * @return ArrayList containing all CourseDBElements in the hashtable
	 */
	public ArrayList<String> showAll() {
		ArrayList<String> allElements = new ArrayList<>();
		Node n;
		for(int i = 0;i<tableSize;i++) {
			n = hashTable[i];
			if(n!=null) {
				allElements.add(n.data.toString());
				while(n.next!=null) {
					allElements.add(n.next.data.toString());
					n = n.next;
				}
			}
		}
		return allElements;
	}

}
