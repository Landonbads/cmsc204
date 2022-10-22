import static org.junit.Assert.assertEquals;
import java.util.Comparator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	StringComparator comparator;
	DoubleComparator comparatorD;
	

	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Hello");
		linkedString.addToEnd("World");
		comparator = new StringComparator();
		
		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(15.0);
		linkedDouble.addToEnd(100.0);
		comparatorD = new DoubleComparator();
		
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedDouble = null;
		comparator = null;
		comparatorD = null;
	}
	
	@Test
	public void testAll() {
		assertEquals("Hello",linkedString.getFirst());
		assertEquals("World",linkedString.getLast());
		assertEquals("Hello",linkedString.retrieveFirstElement());
		assertEquals("World",linkedString.getFirst());
		linkedString.addToEnd("mino");
		linkedString.addToEnd("daballa");
		linkedString.remove​("daballa", comparator);
		assertEquals("mino",linkedString.getLast());
		
		assertEquals(2,linkedDouble.getSize());
		linkedDouble.remove​(15.0, comparatorD);
		assertEquals(1,linkedDouble.getSize());
	}
	
	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
}
