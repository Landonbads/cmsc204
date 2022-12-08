import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Landon Badstibner
 * Student test for Road class
 */
public class Road_STUDENT_Test {
	Road road1;
	Road road2;
	Town town1;
	Town town2;
	Town town3;
	Town town4;
	
	@Before
	public void setUp() throws Exception {
		town1 = new Town("Town_1");
		town2 = new Town("Town_2");
		town3 = new Town("Town_3");
		town4 = new Town("Town_4");
		road1 = new Road(town1, town2, 400, "I95");
		road2 = new Road(town3, town4, "shortRoad");
	}

	@Test
	public void testContains() {
		assertEquals(true, road1.contains(town1));
		assertEquals(false, road2.contains(town1));
	}
	
	@Test
	public void testGetName() {
		assertEquals("I95", road1.getName());
	}
	
	@Test
	public void testGetDestination() {
		assertEquals(town2, road1.getDestination());
	}
	
	@Test
	public void testGetSource() {
		assertEquals(town1, road1.getSource());
	}
	
	@Test
	public void testGetWeight() {
		assertEquals(400, road1.getWeight());
		assertEquals(1, road2.getWeight());
	}
	
	@Test
	public void testToString() {
		assertEquals("shortRoad", road2.toString());
	}
	@After
	public void tearDown() throws Exception {
		town1 = null;
		town2 = null;
		town3 = null;
		town4 = null;
		road1 = null;
		road2 = null;
	}	
}
