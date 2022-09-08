import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GradeBookTest {

	GradeBook g1;
	GradeBook g2;
	
	@Before
	public void setUp() throws Exception {
		g1 = new GradeBook(5);
		g2 = new GradeBook(5);
		g1.addScore(50);
		g1.addScore(99);
		g2.addScore(70);
		g2.addScore(75);
	}
	
	@After
	public void tearDown() throws Exception {
		g1 = null;
		g2 = null;
		
	}
	
	@Test
	public void testAddScore() {
		assertTrue(g1.toString().equals("50.0 99.0 "));
		assertTrue(g2.toString().equals("70.0 75.0 "));
	}
	
	@Test
	public void testSum() {
		assertEquals(149, g1.sum(), .0001);
		assertEquals(145, g2.sum(), .0001);
	}
	
	@Test
	public void testMinimum() {
		assertEquals(50.0, g1.minimum(), .001);
		assertEquals(70.0,g2.minimum(), .001);
	}
	
	@Test
	public void testFinalScore() {
		assertEquals(99.0, g1.finalScore(), .001);
		assertEquals(75.0, g2.finalScore(), .001);
	}
	

}
