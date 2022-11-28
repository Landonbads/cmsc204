/**
 * @author Landon Badstibner
 * A generic linked binary tree which inherits from the LinkedConverterTreeInterface.  
 * The class uses an external generic TreeNode class parameterized as a String
 */
import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
	TreeNode<String> root = new TreeNode<String>("");
	
	public MorseCodeTree() {
		buildTree();
	}

	
	/**
	 * Returns a reference to the root
	 * @return reference to root
	 */
	public TreeNode<String> getRoot() {
		TreeNode<String> returnNode = root;
		return returnNode;
	}

	
	/**
	 * sets the root of the Tree
	 * @param newNode a TreeNode<T> that will be the new root
	 */
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}

	
	/**
	 * Adds result to the correct position in the tree based on the code
	 * This method will call the recursive method addNode
	 * 
	 * @param code the code for the new node to be added
	 * 
	 */
	public void insert(String code, String letter) {
		addNode(getRoot(),code,letter);
		
	}

	
	/**
	 * This is a recursive method that adds element to the correct position 
	 * in the tree based on the code.
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 */
	public void addNode(TreeNode<String> root, String code, String letter) {
		if(code.length() == 1) {
			if(code.equals("-")) {
					root.right = new TreeNode<String>(letter);	
			}
			if(code.equals(".")) {
					root.left = new TreeNode<String>(letter);			
			}
			return;
		}
		else if(code.length()>1) {
			if(code.substring(0,1).equals(".")) {
				addNode(root.left,code.substring(1),letter);
			}
			else if(code.substring(0,1).equals("-")) {
				addNode(root.right,code.substring(1),letter);
			}
		}
	}
	

	/**
	 * Fetch the data in the tree based on the code
	 * This method will call the recursive method fetchNode
	 * 
	 * @param code the code that describes the traversals within the tree
	 * @return the result that corresponds to the code
	 */
	public String fetch(String code) {
		return fetchNode(getRoot(),code);
	}

	
	/**
	 * This is the recursive method that fetches the data of the TreeNode
	 * that corresponds with the code
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of fetchNode
	 * @return the data corresponding to the code
	 */
	public String fetchNode(TreeNode<String> root, String code) {
		String letter = "";
		if(code.length() == 1) {
            if (code.equals(".")){
            	return root.left.data;
            }
            else if (code.equals("-")){
            	return root.right.data;
            }
        }
		else {
            if(code.substring(0,1).equals(".")) {
            	letter = fetchNode(root.left,code.substring(1));
            } 
            else if(code.substring(0,1).equals("-")){
            	letter = fetchNode(root.right,code.substring(1));
            }
        }
		return letter;
	}

	
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * This method builds the LinkedConverterTree by inserting TreeNodes<T>
	 * into their proper locations
	 * 
	 */
	public void buildTree() {
		// TODO Auto-generated method stub
	      insert(".", "e");
	        insert("-", "t");
	        insert("..", "i");
	        insert(".-", "a");
	        insert("-.", "n");
	        insert("--", "m");
	        insert("...", "s");
	        insert("..-", "u");
	        insert(".-.", "r");
	        insert(".--", "w");
	        insert("-..", "d");
	        insert("-.-", "k");
	        insert("--.", "g");
	        insert("---", "o");
	        insert("....", "h");
	        insert("...-", "v");
	        insert("..-.", "f");
	        insert(".-..", "l");
	        insert(".--.", "p");
	        insert(".---", "j");
	        insert("-...", "b");
	        insert("-..-", "x");
	        insert("-.-.", "c");
	        insert("-.--", "y");
	        insert("--..", "z");
	        insert("--.-", "q");
		}
		

	/**
	 * Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */
	public ArrayList<String> toArrayList() {
		ArrayList<String> output = new ArrayList<>();
		LNRoutputTraversal(getRoot(),output);
		return output;
	}

	/**
	 * The recursive method to put the contents of the linked converter tree in an ArrayList<T> 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if(root == null) {
			return;
		}
		LNRoutputTraversal(root.left,list);
		list.add(root.data);
		LNRoutputTraversal(root.right,list);
	}
}