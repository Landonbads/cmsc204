import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A generic double-linked list class with an iterator
 * @author Landon Badstibner
 */
public class BasicDoubleLinkedList<T> implements Iterable<T>{
	//attributes
	Node head;
	Node tail;
	int size = 0;
	
	/**
	 * constructor to initialize head, tail, and size to null,null, and 0 respectively
	 */
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Adds an element to the end of the list and updates the size of the list
	 * @param data - the data to be added to the list
	 */
	public void addToEnd(T data) {
		Node newNode = new Node(data);
		if(size==0) {
			tail = newNode;
			head = newNode;
		}
		else {
			Node temp = tail;
			tail = newNode;
			temp.next = tail;
			tail.prev = temp;
		}
		size++;
	}
	
	/**
	 * Adds element to the front of the list and updates the size of the list
	 * @param data - the data to be added to the list
	 */
	public void addToFront(T data) {
		Node newNode = new Node(data);
		if(size==0) {
			tail = newNode;
			head = newNode;
		}
		else {
			Node temp = head;
			newNode.next = head;
			head = newNode;
			temp.prev = newNode;
		}
		size++;
	}
	
	/**
	 * Returns but does not remove the first element from the list. 
	 * @return the data element or null
	 */
	public T getFirst() {
		return head.data;
	}
	
	/**
	 * Returns but does not remove the last element from the list. 
	 * @return the data element or null
	 */
	public T getLast() {
		return tail.data;
	}

	/**
	 * Returns the number of nodes in the list.
	 * @return the size of the linked list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Removes and returns the first element from the list.
	 * @return data element or null
	 */
	public T retrieveFirstElement() {
		if(size==0) {
            return null;
        }
        else {
        	Node n = head;
        	head = n.next;
        	if(n==tail) {
        		tail=null;
        	}
        	else {
        		n.next = null;
        		head.prev = null;
        	}
        	size--;
        	return n.data;
        }
	}
	
	/**
	 * Removes and returns the last element from the list.
	 * @return data element or null
	 */
	public T retrieveLastElement() {
		if(size==0) {
            return null;
        }
		else {
			Node n = tail;
			tail = n.prev;
			if(n==head) {
				head = null;
			}
			else {
				tail.next=null;
				n.prev=null;
				}
			size--;
			return n.data;
		}
	}
	
	/**
	 * Returns an arraylist of all the items in the Double Linked list
	 * @return an arraylist of all the items in the list
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> returnList = new ArrayList<>(); 
		if(size==0) {
			return returnList;
		}
		else {
			Node n = head;
			returnList.add(n.data);
			while(n.next!=null) {
				returnList.add(n.next.data);
				n = n.next;
			}
		}
		return returnList;
	}
	
	/**
	 * Removes the first instance of the targetData from the list.
	 * @param targetData - the data element to be removed
	 * @param comparator - the comparator to determine equality of data elements
	 * @return a node containing the targetData or null
	 */
	public Node remove​(T targetData, Comparator<T> comparator) {
		//wow.
		 if(size==0) {
	            return null;
	        }
	        else {
	            Node n = head;
	            while(n!=null) {
	                if(comparator.compare(targetData, n.data)==0) {
	                    if(n == head){
	                    	head = n.next;
	                    	if(n==tail) {
	                    		tail=null;
	                    	}
	                    	else {
	                    		n.next = null;
	                    		head.prev = null;
	                    	}
	                    }
	              else{
	                n.prev.next = n.next;
	                	if(n==tail) {
	                		tail=n.prev;
	                	}
	                	else {
	                		n.next.prev = n.prev;
	                		n.next = null;
	                	}
	                	n.prev=null;
	              }
	                size--;
	                return n;
	                }
	                n = n.next;
	            }
	            return n;
	           }
	}
	
	/**
	 * This method returns an object of the DoubleLinkedListIterator. 
	 * @return a ListIterator object
	 */
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}
	
	
	/**
	 * A generic inner class Node
	 */
	public class Node {
		T data;
		Node prev;
		Node next;
		public Node(T dataNode) {
			data = dataNode;
		}
	}//Node class ends
	
	
	/**
	 * A generic inner class that implements java’s ListIterator interface
	 */
	public class DoubleLinkedListIterator implements ListIterator<T>{
		private Node nextNode;
		private Node prevNode;
		
		/**
		 * Constructor to initialize the current pointer to the head of the BasicDoubleLinkedList.
		 */
		public DoubleLinkedListIterator() {
			nextNode = head;
			prevNode = null;
		}
		
		/**
		 * Checks if there is an element in front of current pointer
		 */
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		/**
		 * Moves pointer forward one and returns data of traversed node
		 */
		@Override
		public T next(){
			T result;
			if(hasNext()) {
				result = nextNode.data;
				prevNode = nextNode;
				nextNode = nextNode.next;
			}
			else {
				throw new NoSuchElementException();
			}
			return result;
		}
		
		/**
		 * Checks if there is an element behind current pointer
		 */
		@Override
		public boolean hasPrevious() {
			return prevNode !=null;
		}

		/**
		 * Moves pointer backward one and returns data of traversed node
		 */
		@Override
		public T previous() {
			T result;
			if(hasPrevious()) {
				result = prevNode.data;
				nextNode = prevNode;
				prevNode = prevNode.prev;
			}
			else {
				throw new NoSuchElementException();
			}
			return result;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
		}
	}//DoubleLinkedListIterator class ends
}//BasicDoubleLinkedList class ends
