import java.util.ArrayList;


/**generic Stack data structure
 * 
 * @param <T> data type
 */
public class MyStack<T> implements StackInterface<T>{
	private int totalSize = 0;
	Node top;
	
	/**
	 * Node constructor
	 */
	private class Node{
		T data;
		Node next;
		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}
	
	/**
	 * Generic stack constructor
	 * @param totalSize the total size allowed for stack
	 */
	public MyStack(int totalSize) {
		this.totalSize = totalSize;
		this.top = null;
	}
	
	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isEmpty() {
		return top == null;
	
	}
	
	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		if(size()==totalSize) 
			return true;
		else
			return false;
	}
	
	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T pop() throws StackUnderflowException {
		if(!isEmpty()) {
			T temp = top.data;
			top = top.next;
			totalSize--;
			return temp;
		}
			throw new StackUnderflowException();
	}
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T top() throws StackUnderflowException {
		if(!isEmpty()) {
			return top.data;
		}
		throw new StackUnderflowException();
	}
	
	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size() {
		int elementCounter = 0;
		
		if(!isEmpty()) {
			elementCounter = 1; // starts at one to include top node
			Node node = top;
			while(node.next!=null) {
				elementCounter++;
				node = node.next;
			}			
			
			return elementCounter;
			
		}
		return elementCounter;
	}
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	public boolean push(T e) throws StackOverflowException {
		Node node = new Node(e);
		if(!this.isFull()) {
			node.next = top;
			top = node;
			return true;
		}
		throw new StackOverflowException();
	}
	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		ArrayList<T> placeholderArrayList = new ArrayList<>();
		String returnString = "";
		if(!isEmpty()) {
			Node n = top;
			placeholderArrayList.add(n.data);
			while(n.next!=null) {
				n = n.next;
				placeholderArrayList.add(n.data);
			}
		}
		// reverses plaecholder arraylist
		for(int i = 0, j = placeholderArrayList.size() - 1; i < j; i++) {
			placeholderArrayList.add(i, placeholderArrayList.remove(j));
	    }
		// adds arraylist elements to return string
		for(int i = 0; i<placeholderArrayList.size();i++) {
			returnString += placeholderArrayList.get(i);
		}
		return returnString;
	};

	/**
	 * Returns the string representation of the elements in the Stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	public String toString(String delimiter) {
		String returnString = this.toString();
		ArrayList<String> stringArrayList = new ArrayList<>();
		for(int i = 0; i<returnString.length();i++) {
			stringArrayList.add(returnString.substring(i,i+1));
		}
		return String.join(delimiter, stringArrayList); 
	}

	/**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
	public void fill(ArrayList<T> list) {
		ArrayList<T> copy = new ArrayList<>(list);
		try {	
		for(int i = 0; i<copy.size();i++) {
			push(copy.get(i));
		}
		}
		catch (StackOverflowException qfe) {
			qfe.getMessage();
		}
		
	}

}
