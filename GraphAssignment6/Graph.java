import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
/**
 * @author Landon Badstibner
 * A graph implemented via adjacency list, representing a network of towns and the roads which connect them. 
 * Uses dijkstra's shortest path algorithm to compute shortest distance between every town. 
 */
public class Graph implements GraphInterface<Town, Road>{
	
	private HashMap<String, Town> adjacencyList = new HashMap<>(); // adjacency list for towns and their adjacent towns
	private HashSet<Road> roadSet = new HashSet<>(); // set of roads
	private TreeMap<Town,Integer> dist = new TreeMap<>();
	private Queue<pQueueObject> pQueue = new PriorityQueue<>();
	private HashMap<Town,Boolean> visited = new HashMap<>();
	private HashMap<Town,ArrayList<String>> paths;

    /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null.
     */
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if(containsVertex(sourceVertex) & containsVertex(destinationVertex)) {
			for(Road road : edgesOf(sourceVertex)) {
				if(road.contains(destinationVertex))
					return road;
			}
		}
		return null;
	} // getEdge ends

	
    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(sourceVertex==null || destinationVertex==null)
			throw new NullPointerException();
		if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex))
			throw new IllegalArgumentException();
		
		Road newRoad = new Road(sourceVertex,destinationVertex,weight,description);
		roadSet.add(newRoad);

		adjacencyList.get(sourceVertex.getName()).addAdjacentTown(destinationVertex);
		adjacencyList.get(destinationVertex.getName()).addAdjacentTown(sourceVertex);
		return newRoad;
	}// addEdge ends

	
	/**
     * Adds the specified vertex to this graph if not already present.
     */
	@Override
	public boolean addVertex(Town v) {
		if(v==null)
			throw new NullPointerException();
		
		if(containsVertex(v)) 
			return false;
		
		adjacencyList.put(v.getName(), v);
		return true;
	}// addVertex ends

	
	
    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. 
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex))
			return false;
		
		return adjacencyList.get(sourceVertex.getName()).adjacencyCheck(destinationVertex);
	}

	
	/**
     * Returns true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		if(v==null)
			return false;
		
		return adjacencyList.containsKey(v.getName());
	}// containsVertex

	
    /**
     * Returns a set of the edges contained in this graph. 
     */
	@Override
	public Set<Road> edgeSet() {
		return roadSet;
	}// edgeSet ends

    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		if(vertex==null)
			throw new NullPointerException();
		if(!containsVertex(vertex))
			throw new IllegalArgumentException();
		
		HashSet<Road> edgeSet = new HashSet<>();
		for(Road road : roadSet) {
			if(road.getSource().equals(vertex))
				edgeSet.add(road);
			if(road.getDestination().equals(vertex))
				edgeSet.add(new Road(vertex,road.getSource(),road.getWeight(),road.getName()));
		}
		return edgeSet;
	}// edgesOf ends
	

    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex))
			return null;
		
		Road roadie = null;
		
		for(Road road : roadSet) {
			if(road.contains(destinationVertex) & road.contains(sourceVertex)) {
				roadie = road;
				adjacencyList.get(sourceVertex.getName()).removeAdjacentTown(destinationVertex);
				adjacencyList.get(destinationVertex.getName()).removeAdjacentTown(sourceVertex);
			}
		}
		roadSet.remove(roadie);
		return roadie;
	}// removeEdge ends

	
    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. 
     */
	@Override
	public boolean removeVertex(Town v) {
		if(!containsVertex(v))
			return false;
		Iterator<Road> iterator = roadSet.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().contains(v))
				iterator.remove();
		}
		adjacencyList.remove(v.getName());
		return true;
	}// removeVertex ends

	 /**
     * Returns a set of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() {
		HashSet<Town> townSet = new HashSet<>();
		for(Town v : adjacencyList.values()) {
			townSet.add(v);
		}
		return townSet;
	}// vertexSet ends

	
	
    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     */   
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {

		dijkstraShortestPath(sourceVertex);
		
		//DISJOINT GRAPH FIX
		if (dist.get(destinationVertex) == Integer.MAX_VALUE)
			return new ArrayList<>();
		
		return paths.get(destinationVertex);
	}// shortestPath ends

	
    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		paths = new HashMap<>();
		
		// initialize all dist values to very high num
		for(Town town : vertexSet()) {
			dist.put(town, Integer.MAX_VALUE);
			visited.put(town, false);
		}
		// change sourcevertex dist value to 0
		dist.put(sourceVertex, 0);
		pQueueObject mooofala;
		Town currTown;
		int newDist;
		pQueue.add(new pQueueObject(sourceVertex, dist.get(sourceVertex)));
		
		while(pQueue.size()!=0) {
			mooofala = pQueue.poll();
			currTown = mooofala.getTown();
			visited.replace(currTown,true);
			
			for(Road road : edgesOf(currTown)) {
				
				if(visited.get(road.getDestination()))
					continue;
				
				newDist = dist.get(currTown) + road.getWeight(); 	
				
				if(newDist < dist.get(road.getDestination())) {
					dist.replace(road.getDestination(),newDist);
					pQueue.add(new pQueueObject(road.getDestination(),newDist));
					
					if (paths.get(currTown) == null) 
						paths.put(road.getDestination(),new ArrayList<>());
					else 
						paths.put(road.getDestination(),new ArrayList<>(paths.get(currTown)));
						
					paths.get(road.getDestination()).add(currTown.getName() + " via " + road.toString() + " to " + road.getDestination().getName() + " " + road.getWeight() + " mi");
					
				}// if statement ends
			}// enhanced for loop ends
		}// while loop ends
	}// dijkstraShortestPath ends. nice. nice.
	
	
    /**
     * innerClass pQueueObject to be stored in priority queue
     */
	private class pQueueObject implements Comparable<pQueueObject>{
		private Town town;
		private int distance;
		
		public pQueueObject(Town town, int distance) {
			this.town = town;
			this.distance = distance;
		}
		
		public Town getTown(){
			return town;
		}
		public int getDistance(){
			return distance;
		}


		@Override
		public int compareTo(pQueueObject o) {
			return this.distance - o.getDistance();
		}	
	}// inner class pQueueObject ends
}// Graph class ends
