/**
 * @author Landon Badstibner
 * The class Road that can represent the edges of a Graph of Towns.
 */
public class Road implements Comparable<Road>{
	
	// instance variables
	private String name;
	private int weight;
	private Town source, destination;

	/**
	 * Constructor
	 * @param source - One town on the road
	 * @param destination - Another town on the road
	 * @param degrees - Weight of the edge, i.e., distance from one town to the other
	 * @param name - Name of the road
	 */
	public Road(Town source, Town destination, int degrees, String name) {
		this.name = name;
		this.source = new Town(source);
		this.destination = new Town(destination);
		weight = degrees;
	}// overloaded road constructor ends
	
	
	/**
	 * Constructor with weight preset at 1
	 * @param source - One town on the road
	 * @param destination - Another town on the road
	 * @param name - Name of the road
	 */
	public Road(Town source, Town destination, String name) {
		weight = 1;
		this.source = new Town(source);
		this.destination = new Town(destination);
		this.name = name;
	}// overloaded road constructor ends

	
	/**
	 * Returns true only if the edge contains the given town
	 * @param town - a vertex of the graph
	 * @return true only if the edge is connected to the given vertex
	 */
	public boolean contains(Town town) {
		if(source.equals(town) || destination.equals(town))
			return true;
		else
			return false;
	}// contains method ends
	
	/**
	 * Returns the road name
	 * @return The name of the road
	 */
	public String getName() {
		return name;
	}// getName method ends
	
	/**
	 * Returns the second town on the road
	 * @return A town on the road
	 */
	public Town getDestination() {
		return destination;
	}// getDestinatino method ends
	
	/**
	 * Returns the first town on the road
	 * @return A town on the road
	 */
	public Town getSource() {
		return source;
	}// getSource method ends
	
	/**
	 * Returns the distance of the road
	 * @return the distance of the road
	 */
	public int getWeight() {
		return weight;
	}// getWeight method ends
	
	/**
	 * To string method
	 * @Override toString in class java.lang.Object
	 */
	public String toString() {
		return name;
	}// toString method ends
	
	/**
	 * compareTo in interface java.lang.Comparable<Road>
	 * @return 0 if the road names are the same, a positive or negative number 
	 * if the road names are not the same
	 */
	public int compareTo(Road o) {
		return name.compareTo(o.name);
	}// compareTo method ends
	
	/**
	 * Returns true if each of the ends of the road r is the same as the ends of this road. 
	 * Remember that a road that goes from point A to point B is the same as a road that goes 
	 * from point B to point A.
	 * @Override equals in class java.lang.Object
	 * @param r - road object to compare it to
	 */
	public boolean equals(Object r) {
		Road rd = (Road) r;
		if(r==null)
			return false;
		
		if( source.equals(rd.getSource()) & destination.equals(rd.getDestination()) )
			return true;
		else if ( source.equals(rd.getDestination()) & destination.equals(rd.getSource()) )
			return true;
		else
			return false;
	}// equals method ends

}// Road class ends
