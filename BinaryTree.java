import java.util.Iterator;
import Helpers.EmptyCollectionException;	// Runtime Exception Class
import Helpers.LinkedQueue;				        // Linked Queue Data Structure
import Helpers.ArrayUnorderedList;		    // Unordered ArrayList Data Structure
/**
 * Standard linked binary tree structure using links.
 * 
 * @author Tsuki
 *
 * @param <T>
 */
public class BinaryTree<T>
{
	private BinaryTreeNode<T> root;
	private int numberOfNodes;
	
	/**
	 * Constructor(1) used to create an empty binary tree.
	 */
	public BinaryTree()
	{
		this.root = null;
		this.numberOfNodes = 0;
	}
	
	/**
	 * Constructor(2) used to create a new binary tree with the specified element as its root.
	 * @param element
	 */
	public BinaryTree(T element)
	{
		this.root = new BinaryTreeNode<T>(element);
		this.numberOfNodes = 1;
	}
	
	/**
	 * Constructor(3) used to create a new binary tree with the specified element as its root
	 * and two provided binary subtrees as left and right subtrees of root.
	 * @param element
	 * @param leftSubtree
	 * @param rightSubtree
	 */
	public BinaryTree(T element, BinaryTree<T> leftSubtree, BinaryTree<T> rightSubtree)
	{
		this.root = new BinaryTreeNode<T>(element);
		this.numberOfNodes = 1 + leftSubtree.size() + rightSubtree.size();
		
		if (!leftSubtree.isEmpty())
			this.root.setLeft(new BinaryTreeNode<T>(leftSubtree.getRoot()));
		
		if (!rightSubtree.isEmpty())
			this.root.setRight(new BinaryTreeNode<T>(rightSubtree.getRoot()));
	}
	
	/**
	 * Returns a reference to the element at the root.
	 * @return
	 * @throws
	 */
	public T getRoot() throws EmptyCollectionException
	{
		if (this.root == null)
			throw new EmptyCollectionException("BinaryTree");
		
		return this.root.getElement();
	}
	
	/**
	 * Returns the total number of nodes in this tree.
	 * @return
	 */
	public int size() 
	{
		return this.numberOfNodes;
	}
	
	/**
	 * Returns whether this tree is empty or not.
	 * @return
	 */
	public boolean isEmpty()
	{
		return this.numberOfNodes == 0;
	}
	
	/**
	 * Returns whether this tree contains a node which contains an element that matches with
	 * the target element or not.
	 * @param targetElement
	 * @return
	 */
	public boolean contains(T targetElement)
	{
		return findNode(targetElement, this.root);
	}

	/**
	 * Helper method for contains().
	 * Traverses left and right subtrees of current node recursively. 
	 * @param targetElement
	 * @param next
	 * @return
	 */
	private boolean findNode(T targetElement, BinaryTreeNode<T> node)
	{
		if (node == null)
			return false;
		
		if (node.getElement().equals(targetElement))
			return true;
		
		// Traverse left subtree first
		if (findNode(targetElement, node.getLeft()))
			return true;
		// If not found, then traverse right subtree
		else
			return findNode(targetElement, node.getRight());
	}

	/**
	 * Returns the height of the tree.
	 * @return
	 */
	public int height() 
	{
		return findHeight(this.root);
	}
	
	/**
	 * Helper method for height().
	 * Traverses left and right subtress of current node recursively.
	 * @return
	 */
	private int findHeight(BinaryTreeNode<T> node)
	{
		if (node == null)
			return -1;
		
		return Math.max(findHeight(node.getLeft()), findHeight(node.getRight())) + 1;
	}
	
	/**
	 * Returns a pre-order iterator over this tree.
	 * @return
	 */
	public Iterator<T> iteratorPreOrder()
	{
		ArrayUnorderedList<T> list = new ArrayUnorderedList<T>(); 
		preorder(this.root, list);
		
		return list.iterator();
	}
	
	/**
	 * Helper method for iteratorPreOrder().
	 * Performs a recursive pre-order traversal.
	 * @param node
	 * @param list
	 */
	private void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> list)
	{
		if (node == null)
			return;
		
		list.addToRear(node.getElement());
		preorder(node.getLeft(), list);
		preorder(node.getRight(), list);
	}

	/**
	 * Returns an in-order iterator over this tree.
	 * @return
	 */
	public Iterator<T> iteratorInOrder() 
	{
		ArrayUnorderedList<T> list = new ArrayUnorderedList<T>();
		inorder(this.root, list);
		
		return list.iterator();
	}

	/**
	 * Helper method for iteratorInOrder()
	 * Performs a recursive in-order traversal.
	 * @param node
	 * @param list
	 */
	private void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> list)
	{
		if (node == null)
			return;
		
		inorder(node.getLeft(), list);
		list.addToRear(node.getElement());
		inorder(node.getRight(), list);
	}

	/**
	 * Returns a post-order iterator over this tree.
	 * @return
	 */
	public Iterator<T> iteratorPostOrder() 
	{
		ArrayUnorderedList<T> list = new ArrayUnorderedList<T>();
		postorder(this.root, list);
		
		return list.iterator();
	}
	
	/**
	 * Helper method for iteratorPostOrder().
	 * Performs a recursive post-order traversal.
	 * @param node
	 * @param list
	 */
	private void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> list)
	{
		if (node == null)
			return;
		
		postorder(node.getLeft(), list);
		postorder(node.getRight(), list);
		list.addToRear(node.getElement());
	}

	/**
	 * Returns a level-order iterator over this tree.
	 * @return
	 */
	public Iterator<T> iteratorLevelOrder() 
	{
		ArrayUnorderedList<T> list = new ArrayUnorderedList<T>();
		
		if (isEmpty())
		{
			list.addToFront(null);
		}
		else
		{
			BinaryTreeNode<T> current = this.root;
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
		}
		
		return list.iterator();
	}
	
	/**
	 * Retruns String representation of this tree.
	 * @return
	 */
	public String toString() 
	{
		ArrayUnorderedList<T> list = new ArrayUnorderedList<T>();
		inorder(this.root, list);
		
		return list.toString();
	}

}
