import java.util.ArrayList;


/** Generic Queue data structure
 * 
 *  @param <T> data type
 */
public class MyQueue<T> implements QueueInterface<T>{
	//length instance variable
	private int totalSize = 0;
	Node head;

	/**
	 * Node constructor
	 */
	private class Node{
		T data;
		Node next;
	}

	/**
	 * Generic queue constructor
	 * @param totalSize the total size allowed for queue
	 */
	public MyQueue(int totalSize) {
		this.totalSize = totalSize;
		this.head = null;
	} 

	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty() {
		if(head==null)
			return true;
		else
			return false;
		} //isEmpty method ends

	/**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
	public boolean isFull() {
		if(this.size()==totalSize) 
			return true;
		else
			return false;
	} //isFull method ends
	
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	public T dequeue() throws QueueUnderflowException {
		Node n;
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		else {
			n = head;
			head = head.next;
			n.next=null;
			
		}
		return n.data;
	} //dequeue method ends
	
	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	public int size() {
		int elementCounter = 0;
		if(isEmpty()) {
			return elementCounter;
		}
		else {
			elementCounter = 1; // starts at one to include head node
			Node n = head;
			while(n.next!=null) {
				elementCounter++;
				n = n.next;
			}
		}
		return elementCounter;
	} //size method ends
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	public boolean enqueue(T e) throws QueueOverflowException {
		Node node = new Node();
		node.data = e;
		node.next = null;
		
		if(isEmpty()) {
			head = node;
		}
		else {
			
			if(isFull()) {
				throw new QueueOverflowException();
			}
			else {
				Node n = head;
				while(n.next!=null) {
					n = n.next;
				}
				n.next = node;
			}
		}
		return true;
	} //enqueue method ends
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString() {
		String returnString = "";
		if(isEmpty()) {
			return returnString;
		}
		else {
			Node n = head;
			returnString += n.data;
			while(n.next!=null) {
				returnString += n.next.data;
				n = n.next;
			}
		}
		return returnString;
	} //toString method ends
	
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	public String toString(String delimiter) {
		String returnString = toString();
		ArrayList<String> stringArrayList = new ArrayList<>();
		for(int i = 0; i<returnString.length();i++) {
			stringArrayList.add(returnString.substring(i,i+1));
		}
		return String.join(delimiter, stringArrayList); 
		
	}

	 /**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full
	 
	  */
	public void fill(ArrayList<T> list) {
		ArrayList<T> copy = new ArrayList<>(list); //create shallow copy of arraylist parameter
		try {
			for(int i = 0; i<copy.size();i++) {
				this.enqueue(copy.get(i));
			}
		}
		catch (QueueOverflowException qfe) {
			qfe.getMessage();
		}

	} //fill method ends

} //class ends
