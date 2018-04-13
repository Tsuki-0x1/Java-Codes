import Helpers.ElementNotFoundException;		// RuntimeException Class
import Helpers.EmptyCollectionException;		// RuntimeException Class
/**
 * Standard linked BinarySearchTree (BST) structure using links.
 * This class inherits BinaryTree<T> class.
 * @author Tsuki
 *
 */
public class BinarySearchTree<T> extends BinaryTree<T>
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
	 * Constructor(1) used to create an empty BST (BinarySearchTree).
	 */
	public BinarySearchTree()
	{
		super();
	}
	
	
	/**
	 * Constructor(2) used to create a new BST with the specified element as its root.
	 * @param element Specified element
	 */
	public BinarySearchTree(T element)
	{
		super(element);
	}
	
	
	/**
	 * Inserts a new node with given element at the correct position.
	 * @param element Specified element
	 */
	public void addElement(T element)
	{
		BinaryTreeNode<T> node = new BinaryTreeNode<T>(element);
		
		if (isEmpty())
		{
			root = node;
			numberOfNodes++;
			return;
		}
		
		Comparable<T> target = (Comparable<T>)element;
		BinaryTreeNode<T> current = root;
			
		while (true)
		{
			// check if node should go into left subtree
			if (target.compareTo(current.getElement()) < 0)
			{
				if (current.getLeft() == null)
				{
					current.setLeft(node);	// add if space is free
					break;
				}
				else
					current = current.getLeft();
			}
			// node should go into right subtree
			else
			{
				if (current.getRight() == null)
				{
					current.setRight(node);	// add if space is free
					break;
				}
				else
					current = current.getRight();
			}
		}
		numberOfNodes++;
	}
	
	
	/**
	 * @param targetElement Specified element
	 * @return a reference to the first element that matches the specified target element
	 * @throws ElementNotFoundException if specified element does not exist in this tree
	 */
	public T removeElement(T targetElement) throws ElementNotFoundException
	{
		if (isEmpty())
			throw new ElementNotFoundException("Binary Search Tree");
		
		Comparable<T> target = (Comparable<T>) targetElement;
		BinaryTreeNode<T> current = root;
		
		// 1. Check if the root is the target.
		if (target.compareTo(root.getElement()) == 0)
		{
			root = replacement(root);	// replace root with appropriate node in this tree
			numberOfNodes--;
			return current.getElement();
		}
		
		// 2. If the root isn't the target, set current node to either its left or right child.
		if (target.compareTo(current.getElement()) < 0)
			current = current.getLeft();
		else
			current = current.getRight();
		
		// 3. Traverse the BinarySearchTree until target is found.
		BinaryTreeNode<T> parent = root;
		while (current != null)
		{
			// check if target is found
			if (target.compareTo(current.getElement()) == 0)
			{
				if (current.equals(parent.getLeft()))
					parent.setLeft(replacement(current));	// replace current node's spot
				else
					parent.setRight(replacement(current));	// replace current node's spot
				
				numberOfNodes--;
				return current.getElement();
			}
			// check if target is in left subtree
			else if (target.compareTo(current.getElement()) < 0)
			{
				parent = current;
				current = current.getLeft();
			}
			// target is in right side subree
			else
			{
				parent = current;
				current = current.getRight();
			}
		}
		// 4. Automatically throw exception if target is not in the tree.
		throw new ElementNotFoundException("Binary Search Tree");
	}
	
	
	/**
	 * Removes every node with an element that matches the specified target in this tree
	 * @param targetElement Specified element
	 * @throws ElementNotFoundException if specified element does not exist in this tree
	 */
	public void removeAllOccurrences(T targetElement) throws ElementNotFoundException
	{
		removeElement(targetElement);
		
		try
		{
			while (!isEmpty())
				removeElement(targetElement);
		}
		catch (ElementNotFoundException e)
		{
			// ignore since at least one node was removed
		}
	}
	
	
	/**
	 * @return a reference to the first element with the least value in this tree
	 * @throws EmptyCollectionException if tree is empty
	 */
	public T removeMin() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("Binary Search Tree");
		
		BinaryTreeNode<T> current = root;
		
		// check if root is min
		if (root.getLeft() == null)
		{
			root = root.getRight();
			numberOfNodes--;
			return current.getElement();
		}
		
		// perform iterative traversal in the left subtree
		BinaryTreeNode<T> parent = root;
		current = current.getLeft();
		while (current.getLeft() != null)
		{
			parent = current;
			current = current.getLeft();
		}
		
		parent.setLeft(current.getRight());
		numberOfNodes--;
		return current.getElement();
	}
	
	
	/**
	 * @return a reference to the first element with the highest value in this tree
	 * @throws EmptyCollectionException if tree is empty
	 */
	public T removeMax() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("Binary Search Tree");
		
		BinaryTreeNode<T> current = root;
		
		// check if root is max
		if (root.getRight() == null)
		{
			root = root.getLeft();
			numberOfNodes--;
			return current.getElement();
		}
		
		// perform iterative traversal in the right subtree
		BinaryTreeNode<T> parent = root;
		current = current.getRight();
		while (current.getRight() != null)
		{
			parent = current;
			current = current.getRight();
		}
		
		parent.setRight(current.getLeft());
		numberOfNodes--;
		return current.getElement();
	}
	
	
	/**
	 * @return a reference to the first element with the least value in this tree
	 * @throws EmptyCollectionException if tree is empty
	 */
	public T findMin() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("Binary Search Tree");
		
		// check if root is min
		if (root.getLeft() == null)
			return root.getElement();
		
		// perform iterative traversal in the left subtree
		BinaryTreeNode<T> current = root;
		while (current.getLeft() != null)
		{
			current = current.getLeft();
		}
		
		return current.getElement();
	}
	
	
	/**
	 * @return a reference to the first element with the highest value in this tree
	 * @throws EmptyCollectionException if tree is empty
	 */
	public T findMax() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("Binary Search Tree");
		
		// check if root is max
		if (root.getRight() == null)
			return root.getElement();
		
		// perform iterative traversal in the right subtree
		BinaryTreeNode<T> current = root;
		while (current.getRight() != null)
		{
			current = current.getRight();
		}
		
		return current.getElement();
	}
	
	
	/**
	 * Checks to see if a node containig the specified element exists in this tree or not.
	 * @param targetElement Specified element
	 * @return true if this tree contains a node with specified element; false otherwise
	 */
	public boolean contains(T targetElement)
	{
		try
		{
			find(targetElement);
		}
		catch (ElementNotFoundException e)
		{
			return false;
		}
		return true;
	}
	
	
	/**
	 * @param targetElement Specified element
	 * @return a reference to a node which contains matching element with the specified
	 * @throws ElementNotFoundException if specified element does not exist in this tree
	 */
	public T find(T targetElement) throws ElementNotFoundException
	{
		if (!isEmpty())
		{
			BinaryTreeNode<T> node = findAgain(targetElement, root);
			if (node != null)
				return node.getElement();
		}
		throw new ElementNotFoundException ("binarytree");
	}
	
	
	/**
	 * Helper method for find().
	 * @param targetElement Specified element
	 * @param node Current node
	 * @return a reference to a node which contains matching element with the specified
	 */
	protected BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> node)
	{
		Comparable<T> target = (Comparable<T>) targetElement;
		
		// check if the node is the target
		if (target.compareTo(node.getElement()) == 0)
			return node;
		
		// check if the target should be in the left subtree
		if (target.compareTo(node.getElement()) < 0 && node.getLeft() != null)
			return findAgain(targetElement, node.getLeft());
		
		// check if the target should be in the right subtree
		if (target.compareTo(node.getElement()) > 0 && node.getRight() != null)
			return findAgain(targetElement, node.getRight());
		
		// target is not in the tree
		return null;
	}
	
	
	/**
	 * Helper method for removeElement().
	 * @param node Specified node
	 * @return a reference to a node which will replace the specified node (if the specified
	 * 		   node has two children, the in-order successor will be the replacement node)
	 */
	protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node)
	{
		// given node has no children
		if (node.getLeft() == null && node.getRight() == null)
			return null;
		// given node only has left child
		if (node.getLeft() != null && node.getRight() == null)
			return node.getLeft();
		// given node only has right child
		if (node.getLeft() == null && node.getRight() != null)
			return node.getRight();
		// given node has both children
		// -> Find the smallest node in its right subtree (to keep BST sorted)
		
		// 1. Perform iterative traversal in right subtree.
		BinaryTreeNode<T> parent = node;
		BinaryTreeNode<T> current = node.getRight();
		while (current.getLeft() != null)
		{
				parent = current;
				current = current.getLeft();
		}

		// 2. Replace given node with the smallest node (current) in its right subtree.
		if (node.getRight().equals(current))
				current.setLeft(node.getLeft());
		else
		{
				parent.setLeft(current.getRight());
				current.setLeft(node.getLeft());
				current.setRight(node.getRight());
		}

		// 3. Return the reference to the replaced node (current).
		return current;
		}
	
}
