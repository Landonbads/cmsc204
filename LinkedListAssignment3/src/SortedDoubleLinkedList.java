import java.util.Comparator;
import java.util.ListIterator;
import java.lang.UnsupportedOperationException;


/**
 * A generic sorted double-linked list class with an iterator that inherits from the generic double-linked list class.
 * @author Landon Badstibner
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	BasicDoubleLinkedList<T> sortedDoubleLinkedList;
	Comparator<T> comparator;
	
	/**
	 * SortedDoubleLinkedList constructor
	 */
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		sortedDoubleLinkedList = new BasicDoubleLinkedList<>();
		comparator = compareableObject;
	}
	
	/**
	 * Not supported by SortedDoubleLinkedList
	 * @throws UnsupportedOperationException
	 */
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * Not supported by SortedDoubleLinkedList
	 * @throws UnsupportedOperationException
	 */
	public void addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

	/**
	 * Inserts the specified element at the correct position in the sorted list.
	 * @param data the data to be added to the list
	 */
	public void add​(T data) {
		Node newEntry = new Node(data);
		if(head!=null) {
			Node current = head;
			Node previous = null;
			if(comparator.compare(data, current.data)<0) {
				newEntry.next = current;
				newEntry.prev = null;
				current.prev = newEntry;
				head = newEntry;
			}
			else {
				while(current!=null && comparator.compare(data, current.data)>0) {
					previous = current;
					current = current.next;
				}
				if(current == null) {
					newEntry.prev = previous;
					newEntry.next = null;
					previous.next = newEntry;
					tail = newEntry;
				}
				else{
					newEntry.prev = previous;
					newEntry.next = previous.next;
					previous.next = newEntry;
					newEntry.next.prev = newEntry;
				}
		    }
		}
		else {
			head=newEntry;
			tail=newEntry;
		}
		size++;
	}
	
	/**
	 * Implements the iterator by calling the super class iterator method.
	 * @return an iterator positioned at the head of the list
	 */
	public ListIterator<T> iterator() {
		return super.iterator();
	}
	
	/**
	 * Implements the remove operation by calling the super class remove method.
	 * @param data - the data element to be removed
	 * @param comparator - the comparator to determine equality of data elements
	 * @return a node containing the data or null
	 */
	public Node remove​(T data, Comparator<T> comparator) {
		return super.remove​(data, comparator);
	}
	
}//SortedDoubleLinkedList class ends

