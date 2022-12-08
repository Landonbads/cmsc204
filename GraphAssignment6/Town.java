import java.util.ArrayList;
/**
 * @author Landon Badstibner
 * Represents an town as a node of a graph
 */
public class Town implements Comparable<Town>{
	//instance variables
	private String name;
	private ArrayList<Town> adjacentTowns;
	
	/**
	 * Constructor. Requires town's name.
	 * @param name - name of town
	 */
	public Town(String name) {
		this.name = name;
		adjacentTowns = new ArrayList<>();
	}
	
	/**
	 * Copy constructor
	 * @param templateTown - an instance of Town
	 */
	public Town(Town templateTown) {
		name = templateTown.getName();
		adjacentTowns = new ArrayList<>();
		for(Town town : templateTown.adjacentTowns) {
			adjacentTowns.add(town);
		}
	}

	/**
	 * Returns the town's name
	 * @return town's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Compare to method
	 * @return 0 if names are equal, a positive or negative number if the names are not equal
	 */
	@Override
	public int compareTo(Town o) {
            return name.compareTo(o.name);
	}
	
	/**
	 * To string method
	 * @Override toString in class java.lang.Object
	 * @return the town name
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * @Override hashCode in class java.lang.Object
	 * @return the hashcode for the name of the town
	 */
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * @Override equals in class java.lang.Object
	 * @return true if the town names are equal, false if not
	 */
	public boolean equals(Object obj) {
		if(name.equals(((Town)obj).getName())) 
			return true;
		else 
			return false;
	}
	
	
	/**
	 * adds desired town to adjacency list
	 * @param v - an instance of Town
	 */
	public void addAdjacentTown(Town v) {
		adjacentTowns.add(v);
	}
	
	
	/**
	 * checks adjacency between two towns
	 * @param v - an instance of Town
	 * @return true if passed in town is adjacent to this town, false if not. 
	 */
	public boolean adjacencyCheck(Town v) {
		return adjacentTowns.contains(v);
	}
	
	
	/**
	 * removes desired town from adjacency list
	 * @param v - an instance of Town
	 */
	public void removeAdjacentTown(Town v) {
		adjacentTowns.remove(v);
	}
	


}// Town class ends
