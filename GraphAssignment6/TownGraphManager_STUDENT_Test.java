import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Landon Badstibner
 * Student test with new data for TownGraphManager class
 */

public class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  town = new String[12];
		  
		  for (int i = 1; i < 12; i++) {
			  town[i] = "Town_" + i;
			  graph.addTown(town[i]);
		  }
		  
		  graph.addRoad(town[1], town[10], 8, "Road_1");
		  graph.addRoad(town[1], town[3], 8, "Road_2");
		  graph.addRoad(town[1], town[2], 3, "Road_3");
		  graph.addRoad(town[3], town[5], 3, "Road_4");
		  graph.addRoad(town[3], town[11], 7, "Road_5");
		  graph.addRoad(town[4], town[1], 6, "Road_6");
		  graph.addRoad(town[6], town[7], 1, "Road_7");
		  graph.addRoad(town[9], town[8], 2, "Road_8");
		  graph.addRoad(town[8], town[1], 4, "Road_9");
		  graph.addRoad(town[5], town[4], 3, "Road_10");
		  graph.addRoad(town[10], town[9], 5, "Road_11");
		  graph.addRoad(town[2], town[3], 1, "Road_12");
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}
	
	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_12", roads.get(3));
		graph.addRoad(town[4], town[11], 1,"Road_13");
		roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_12", roads.get(3));
		assertEquals("Road_13", roads.get(4));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("Road_1", graph.getRoad(town[1], town[10]));
		assertEquals("Road_12", graph.getRoad(town[2], town[3]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_12"));
		graph.addTown("Town_12");
		assertEquals(true, graph.containsTown("Town_12"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Town_12"));
		graph.addTown("Town_12");
		ArrayList<String> path = graph.getPath(town[1],"Town_12");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		assertEquals(false, graph.containsTown("Town_12"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[1], town[2]));
		assertEquals(false, graph.containsRoadConnection(town[3], town[8]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_8", roads.get(10));
		assertEquals("Road_9", roads.get(11));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[3], town[11]));
		graph.deleteRoadConnection(town[3], town[11], "Road_5");
		assertEquals(false, graph.containsRoadConnection(town[3], town[11]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		graph.deleteTown(town[2]);
		assertEquals(false, graph.containsTown("Town_2"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Town_1", roads.get(0));
		assertEquals("Town_10", roads.get(1));
		assertEquals("Town_11", roads.get(2));
		assertEquals("Town_2", roads.get(3));
		assertEquals("Town_8", roads.get(9));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[6],town[7]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_6 via Road_7 to Town_7 1 mi",path.get(0).trim());
	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(town[4],town[2]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_4 via Road_10 to Town_5 3 mi",path.get(0).trim());
		  assertEquals("Town_5 via Road_4 to Town_3 3 mi",path.get(1).trim());
		  assertEquals("Town_3 via Road_12 to Town_2 1 mi",path.get(2).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(town[3],town[2]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_3 via Road_12 to Town_2 1 mi",path.get(0).trim());

	}
	
}
