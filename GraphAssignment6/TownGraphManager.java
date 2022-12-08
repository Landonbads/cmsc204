import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Landon Badstibner
 * Data manager holding instance of the Graph class. 
 */
public class TownGraphManager implements TownGraphManagerInterface{

	private Graph graph = new Graph(); // create instance of graph
	
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		if (graph.addEdge(new Town(town1), new Town(town2), weight, roadName) == null)
			return false;
	
		return true;
	}

	
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) {
		return graph.getEdge(new Town(town1), new Town(town2)).getName();
	}

	
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {
		if(graph.addVertex(new Town(v)))
			return true;
		
		return false;
	}

	
	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name) {
		for(Town town : graph.vertexSet()) {
			if(town.getName().equals(name))
				return town;
		}
		return null;
	}

	
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		if(graph.containsVertex(new Town(v)))
			return true;
		
		return false;
	}

	
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}
	

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> returnList = new ArrayList<>();
		for(Road road : graph.edgeSet()) {
			returnList.add(road.getName());
		}
		Collections.sort(returnList);
		return returnList;
	}

	
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if(graph.removeEdge(new Town(town1), new Town(town2), 0, road) == null)
				return false;
				
		return true;
	}

	
	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		if(graph.removeVertex(new Town(v)))
			return true;
		
		return false;
	}

	
	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> returnList = new ArrayList<>();
		for(Town town : graph.vertexSet()) {
			returnList.add(town.getName());
		}
		Collections.sort(returnList);
		return returnList;
	}

	
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

	
	/**
	 * Read from data file containing roads. adds corresponding towns and roads to populate graph.
	 * @param file the file being read
	 * @throws FileNotFoundException when file does not exist
	 * @throws IOException when I/O error occurs
	 */
	public void populateTownGraph(File file) throws FileNotFoundException, IOException{
		String[] returnList;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				returnList = line.split(",|;");		
				Town townS = new Town(returnList[2]);
				Town townD = new Town(returnList[3]);
				graph.addVertex(townS);
				graph.addVertex(townD);
				graph.addEdge(townS, townD, Integer.parseInt(returnList[1]), returnList[0]);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}// populateTownGraph ends

}// TownGraphManager ends
