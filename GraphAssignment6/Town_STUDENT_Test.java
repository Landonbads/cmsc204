import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Landon Badstibner
 * Student test for Town class
 */
public class Town_STUDENT_Test {

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

	}

	@Test
	public void testGetName() {
		assertEquals("Town_1",town1.getName());
	}
	
	@Test
	public void testToString() {
		assertEquals("Town_2",town2.toString());
	}
	
	@Test
	public void testAdjacencyMethods() {
		town1.addAdjacentTown(town2);
		town2.addAdjacentTown(town1);
		
		assertEquals(true,town2.adjacencyCheck(town1));
		assertEquals(false,town2.adjacencyCheck(town3));
		
		town2.removeAdjacentTown(town1);
		
		assertEquals(false,town2.adjacencyCheck(town1));
	}

	
	@After
	public void tearDown() throws Exception {
		town1 = null;
		town2 = null;
		town3 = null;
		town4 = null;
	}	
}