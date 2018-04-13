/**
 * Standard node structure for a binary tree.
 * @author Tsuki
 * 
 */
public class BinaryTreeNode<T>
{
	/**
	 * Element of this node
	 */
	private T element;
	/**
	 * Reference to the left child of this node
	 */
	private BinaryTreeNode<T> left;
	/**
	 * Reference to the right child of this node
	 */
	private BinaryTreeNode<T> right;
	
	
	/**
	 * Constructor used to create a non-empty BinaryTreeNode.
	 * Left and right children are set as null by default.
	 * @param element Specified element
	 */
	public BinaryTreeNode(T element)
	{
		this.element = element;
		this.left = null;
		this.right = null;
	}
	
	
	/**
	 * @return number of descendants of this node
	 */
	public int getNumberOfDescendants()
	{
		int n = 0;
		
		if (this.left != null)
			n += 1 + this.left.getNumberOfDescendants();
		
		if (this.right != null)
			n += 1 + this.right.getNumberOfDescendants();
			
		return n;
	}
	
	
	/**
	 * @return the element of this node
	 */
	public T getElement()
	{
		return this.element;
	}
	
	
	/**
	 * @return a reference to the left child of this node
	 */
	public BinaryTreeNode<T> getLeft() 
	{
		return this.left;
	}
	
	
	/**
	 * @return a reference to the right child of this node
	 */
	public BinaryTreeNode<T> getRight()
	{
		return this.right;
	}
	
	
	/**
	 * Assigns a new element for this node
	 * @param element Specified element
	 */
	public void setElement(T element) 
	{
		this.element = element;
	}
	
	
	/**
	 * Assigns a new reference to the left child for this node
	 * @param node Specified node
	 */
	public void setLeft(BinaryTreeNode<T> node) 
	{
		this.left = node;
	}
	
	
	/**
	 * Assigns a new reference to he right child for this node
	 * @param node Specified node
	 */
	public void setRight(BinaryTreeNode<T> node) 
	{
		this.right = node;
	}

}
