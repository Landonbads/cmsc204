/**
 * @author Landon Badstibner
 * TreeNode class to represent each tree node
 */
public class TreeNode<T> {
	//instance variables for left node, right node, and data
	TreeNode<T> left;
	TreeNode<T> right;
	T data;
	
	/**
	 * Create a new TreeNode with left and right child set to null and data set to the dataNode
	 * @param dataNode - the data to be stored in the TreeNode
	 */
	public TreeNode(T dataNode) {
		data = dataNode;
	}
	
	
	/**
	 * Constructor used for making deep copies
	 * @param node - node to make copy of
	 */
	public TreeNode(TreeNode<T> node) {
		left = node.left;
		right = node.right;
		data = node.data;
	}
	
	/**
	 * Return the data within this TreeNode
	 * @return the data within the TreeNode
	 */
	public T getData() {
		return data;
	}
}
