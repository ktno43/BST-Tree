/*-
 * ***************************************
 * Kyle Nguyen
 * 
 * COMP 282
 * Spring 2018
 * Dr. Wen-Chin Hsu
 * M/W 9:30 a.M - 10:45 A.M
 * 
 * Project 1: 
 * Phase 1 (BST Implementation), 
 * Phase 2 (Question 1), 
 * Phase 3 (Question 2), &
 * Phase 4 (Question 3)
 * 
 * Group2_BST_Node.java
 * 
 * BST Node for the BST class to use.
 * The node contains the most basic
 * necessities on what encapsulates 
 * a node.  
 * 
 * The insertion, deletion, and searching
 * algorithms are also included in the
 * most basic node class.
 * 
 * Also implemented is the comparable
 * interface which allows for the 
 * comparison of two nodes.
 ****************************************/
public class Group2_BST_Node<E extends Comparable<E>> implements Comparable<Group2_BST_Node<E>> {
	private E element; // Data for node
	private Group2_BST_Node<E> left; // Reference to left child
	private Group2_BST_Node<E> right; // Reference to right child

	public Group2_BST_Node(E e) { // Initialization constructor
		this.element = e; // Assign data to node
	}

	/*******************************
	 * Attempt to search for a node
	 *******************************/
	protected boolean search(E e) {
		Group2_BST_Node<E> currentNode = this; // Current reference for node

		while (true) {
			if (currentNode == null) // Reached the end and no results
				break;

			if (e.compareTo(currentNode.element) == 0) // Match found
				return true; // Search successful

			boolean goLeft = e.compareTo(currentNode.element) < 0; // Traverse left?

			currentNode = goLeft ? currentNode.left : currentNode.right; // If true go left, else go right
		}
		return false; // Search unsuccessful
	}

	/*******************************
	 * Attempt to insert a node
	 *******************************/
	protected boolean insert(E e) {
		Group2_BST_Node<E> currentNode = this; // Current reference for node

		while (true) { // Find a location to insert node into the BST
			Group2_BST_Node<E> parentNode = currentNode; // Reference to parent node

			if (e.compareTo(currentNode.element) == 0) // No duplicates allowed
				return false; // Didn't insert anything so return false

			boolean goLeft = e.compareTo(currentNode.element) < 0; // Traverse left?

			currentNode = goLeft ? currentNode.left : currentNode.right; // If true go left, else go right

			if (currentNode == null) { // Found an empty space to put new node
				if (goLeft)
					parentNode.left = new Group2_BST_Node<E>(e);

				else
					parentNode.right = new Group2_BST_Node<E>(e);

				break; // New node was created, stop while loop
			}
		}
		return true; // Insert was successful
	}

	/*******************************
	 * Attempt to delete a node
	 *******************************/
	protected boolean delete(E e, Group2_BST_Node<E> parent) { // Delete node with value e, parent also passed in to fix references
		boolean goLeft = e.compareTo(this.element) < 0; // Traverse left?

		if (e.compareTo(this.element) == 0) { // Match found
			if (this.left != null && this.right != null) { // Case 2: Current node has two children
				this.element = this.right.getSucessor(); // Find the inorder successor and swap

				this.right.delete(this.element, this); // Two nodes with the same value now, now remove duplicate from the left subtree
			}

			// Find node to be deleted
			// Case 0: No children, parent's left/right reference will be null
			// (Assign parent's left/right reference to null)

			// Case 1: 1 Child
			// Parent's left/right reference will set reference to null or to child's left/right subtree
			// (Assign parent's left/right reference to null or to child's left/right subtree)

			// Case 2: 2 Children
			// Swap has already happened, reference between parent & duplicate node will be changed
			// Parent's left/right reference will set reference to null or to child's left/right subtree
			// (Assign parent's left/right reference to null or to child's left/right subtree)

			else if (parent.left == this) // Node to be deleted found, does it have any children?
				parent.left = (this.left == null) ? this.right : this.left; // Has children, so left parent will take care of left/right child's subtree
																			 // No kids, "this" reference to left/right will be null by default
			else if (parent.right == this) // Node to be deleted found, does it have any children?
				parent.right = (this.left == null) ? this.right : this.left; // Has children, so right parent will take care of left/right child's subtree
																			 // No kids, "this" reference to left/right will be null by default
			return true; // Return true since deletion was successful
		}

		else if (goLeft && this.left != null) // Match wasn't found, traverse left
			return this.left.delete(e, this);

		else if (!goLeft && this.right != null) // Match wasn't found, traverse right
			return this.right.delete(e, this);

		else
			return false; // Deletion unsuccessful
	}

	/*******************************
	 * Get data
	 *******************************/
	protected E getData() { // Return node's value
		return this.element;
	}

	/*******************************
	 * Get left reference
	 *******************************/
	protected Group2_BST_Node<E> getLeftNode() { // Return node's left reference
		return this.left;
	}

	/*******************************
	 * Get right reference
	 *******************************/
	protected Group2_BST_Node<E> getRightNode() { // Return node's right reference
		return this.right;
	}

	/*******************************
	 * Get inorder successor
	 *******************************/
	private E getSucessor() { // Method called from this.right, keep going left to find inorder successor
		if (this.left == null) // Found inorder successor
			return this.element;

		else
			return this.left.getSucessor(); // Recursively call to find successor
	}

	/*******************************
	 * Implement Comparable Interface
	 *******************************/
	@Override
	public int compareTo(Group2_BST_Node<E> otherNode) { // Implement the comparable interface
		return this.element.compareTo(otherNode.element);
	}
}
