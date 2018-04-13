import java.util.Iterator;
import Helpers.EmptyCollectionException;	// Runtime Exception Class
import Helpers.LinkedQueue;			// Linked Queue Data Structure
import Helpers.ArrayUnorderedList;		// Unordered ArrayList Data Structure
/**
 * Standard linked BinaryTree structure using links.
 * @author Tsuki
 * 
 */
public class BinaryTree<T>
{
	/**
	 * Root node of this tree.
	 */
	private BinaryTreeNode<T> root;
	/**
	 * Number of nodes in this tree that are not null.
	 */
	private int numberOfNodes;
	
	
	/**
	 * Constructor(1) used to create an empty binary tree.
	 */
	public BinaryTree()
	{
		root = null;
		numberOfNodes = 0;
	}
	
	
	/**
	 * Constructor(2) used to create a new binary tree with the specified element as its root.
	 * @param element Specified element
	 */
	public BinaryTree(T element)
	{
		root = new BinaryTreeNode<T>(element);
		numberOfNodes = 1;
	}
	
	
	/**
	 * Constructor(3) used to create a new binary tree with the specified element as its root
	 * and two provided binary subtrees as left and right subtrees of root.
	 * @param element Specified element
	 * @param leftSubtree Specified subtree
	 * @param rightSubtree Specified subtree
	 */
	public BinaryTree(T element, BinaryTree<T> leftSubtree, BinaryTree<T> rightSubtree)
	{
		root = new BinaryTreeNode<T>(element);
		numberOfNodes = 1 + leftSubtree.size() + rightSubtree.size();
		
		if (!leftSubtree.isEmpty())
			root.setLeft(new BinaryTreeNode<T>(leftSubtree.getRoot()));
		
		if (!rightSubtree.isEmpty())
			root.setRight(new BinaryTreeNode<T>(rightSubtree.getRoot()));
	}
	
	
	/**
	 * @return a reference to the element at the root
	 * @throws EmptyCollectionException if tree is empty
	 */
	public T getRoot() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("BinaryTree");
		
		return root.getElement();
	}
	
	
	/**
	 * @return number of nodes this tree contains
	 */
	public int size() 
	{
		return numberOfNodes;
	}
	
	
	/**
	 * @return true if this tree is empty; false otherwise
	 */
	public boolean isEmpty()
	{
		return numberOfNodes == 0;
	}
	
	
	/**
	 * Checks whether this tree contains a node which contains an element that matches with
	 * the target element or not.
	 * @param targetElement Target element to be sought
	 * @return true if targetElement exists in this tree; false otherwise
	 */
	public boolean contains(T targetElement)
	{
		return searchNode(targetElement, root);
	}
	

	/**
	 * Helper method for contains().
	 * Performs recursive traversal over left and right subtrees of a node.
	 * @param targetElement Target element to be sought
	 * @param node Current node
	 * @return true if targetElement exists in current node's subtrees; false otherwise
	 */
	protected boolean searchNode(T targetElement, BinaryTreeNode<T> node)
	{
		if (node == null)
			return false;
		
		if (node.getElement().equals(targetElement))
			return true;
		
		// Traverse left subtree first
		if (searchNode(targetElement, node.getLeft()))
			return true;
		// If not found, then traverse right subtree
		else
			return searchNode(targetElement, node.getRight());
	}
	

	/**
	 * @return the height of this tree
	 */
	public int height() 
	{
		return findHeight(root);
	}
	
	
	/**
	 * Helper method for height().
	 * Performs a recursive traversal over left and right subtress of a node.
	 * @param node Current node
	 * @return current node's height
	 */
	protected int findHeight(BinaryTreeNode<T> node)
	{
		if (node == null)
			return -1;
		
		return Math.max(findHeight(node.getLeft()), findHeight(node.getRight())) + 1;
	}
	
	
	/**
	 * @return a pre-order iterator over this tree
	 */
	public Iterator<T> iteratorPreOrder()
	{
		ArrayUnorderedList<T> list = new ArrayUnorderedList<T>(); 
		preorder(root, list);
		
		return list.iterator();
	}
	
	
	/**
	 * Helper method for iteratorPreOrder().
	 * Performs a recursive pre-order traversal.
	 * @param node Current node
	 * @param list Temporary list storing nodes in pre-order traversal
	 */
	protected void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> list)
	{
		if (node == null)
			return;
		
		list.addToRear(node.getElement());
		preorder(node.getLeft(), list);
		preorder(node.getRight(), list);
	}

	
	/**
	 * @return Returns an in-order iterator over this tree.
	 */
	public Iterator<T> iteratorInOrder() 
	{
		ArrayUnorderedList<T> list = new ArrayUnorderedList<T>();
		inorder(root, list);
		
		return list.iterator();
	}

	
	/**
	 * Helper method for iteratorInOrder()
	 * Performs a recursive in-order traversal.
	 * @param node Current node
	 * @param list Temporary list storing nodes in in-order traversal
	 */
	protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> list)
	{
		if (node == null)
			return;
		
		inorder(node.getLeft(), list);
		list.addToRear(node.getElement());
		inorder(node.getRight(), list);
	}
	

	/**
	 * @return a post-order iterator over this tree
	 */
	public Iterator<T> iteratorPostOrder() 
	{
		ArrayUnorderedList<T> list = new ArrayUnorderedList<T>();
		postorder(root, list);
		
		return list.iterator();
	}
	
	
	/**
	 * Helper method for iteratorPostOrder().
	 * Performs a recursive post-order traversal.
	 * @param node Current node
	 * @param list Temporary list storing nodes in post-order traversal
	 */
	protected void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> list)
	{
		if (node == null)
			return;
		
		postorder(node.getLeft(), list);
		postorder(node.getRight(), list);
		list.addToRear(node.getElement());
	}
	

	/**
	 * @return a level-order iterator over this tree
	 */
	public Iterator<T> iteratorLevelOrder() 
	{
		ArrayUnorderedList<T> list = new ArrayUnorderedList<T>();
		
		if (isEmpty())
		{
			list.addToFront(null);
			return list.iterator();
		}
		
		BinaryTreeNode<T> current = root;
		LinkedQueue<BinaryTreeNode<T>> queue = new LinkedQueue<BinaryTreeNode<T>>();
		queue.enqueue(current);
			
		while (!queue.isEmpty())
		{
			current = queue.dequeue();
			list.addToRear(current.getElement());
				
			if (current.getLeft() != null)
				queue.enqueue(current.getLeft());
				
			if (current.getRight() != null)
				queue.enqueue(current.getRight());
		}
		return list.iterator();
	}
	
	
	/**
	 * @return In-order String representation of this binary tree
	 */
	public String toString() 
	{
		ArrayUnorderedList<T> list = new ArrayUnorderedList<T>();
		inorder(root, list);
		
		return list.toString();
	}

}
