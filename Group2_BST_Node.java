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
 * Phase 1, Phase 2 (Question 1), 
 * & Phase 3 (Question 2)
 * 
 * Group2_BST_Node.java
 * 
 * Driver class to test various methods
 * implemented in the BST class.
 * 
 * 
 * Also displayed is the implementation
 * of Phase 2 & Phase 3, postorder &
 * inorder traversal without recursion
 * and the number of non-leaf nodes 
 * within the tree.
 * 
 * All of the displays are properly 
 * displaying the correct results as 
 * well as performing the correct tasks.
 ****************************************/

public class Group2_BST_Node<E extends Comparable<E>> implements Comparable<Group2_BST_Node<E>> {
	private E element; // Data for node
	private Group2_BST_Node<E> left; // Reference to left child
	private Group2_BST_Node<E> right; // Reference to right child

	public Group2_BST_Node(E e) { // Initialization constructor
		this.element = e; // Assign data to node
	}

	/*******************************
	 * Attempt to insert node
	 *******************************/
	protected boolean insert(E e) { // Insert node with value e
		if (e.compareTo(this.element) == 0) // No duplicates allowed
			return false; // Didn't insert anything so return false

		else if (e.compareTo(this.element) < 0) { // If to-insert node is smaller than current node, go left
			if (this.left == null) { // If there's a space available put new node here
				this.left = new Group2_BST_Node<E>(e); // Make a new node with value e
				return true; // Return true since we inserted successfully
			}

			else
				return this.left.insert(e); // No space available so recursively call from the current-left node
		}

		else if (e.compareTo(this.element) > 0) { // If to-insert node is greater than current node, go right
			if (this.right == null) { // If there's a space available put new node here
				this.right = new Group2_BST_Node<E>(e);
				return true; // Return true since we inserted successfully
			}

			else
				return this.right.insert(e); // No space available so recursively call from the current-right node
		}
		return false; // Insert was not successful
	}

	/*******************************
	 * Attempt to delete node
	 *******************************/
	protected boolean delete(E e, Group2_BST_Node<E> parent) { // Delete node with value e, parent also passed in to fix references
		if (e.compareTo(this.element) < 0) { // If to-delete node is smaller than current node, go left
			if (this.left != null) // Traverse down the left side until to-delete node is found
				return this.left.delete(e, this);

			else
				return false; // No match found since you traveled down the entire left sub-tree
		}

		else if (e.compareTo(this.element) > 0) { // If to-delete node is greater than current node, go right
			if (this.right != null) // Traverse down the right side until to-delete node is found
				return this.right.delete(e, this);

			else
				return false; // No match found since you traveled down the entire right sub-tree
		}

		else { // Match found since e.compareTo(this.element) = 0
			if (this.left != null && this.right != null) { // Case 2: Current node has two children
				this.element = this.right.getSucessor(); // Find the inorder successor and swap
				// Two nodes with the same value now, now remove 19 from the left subtree
				this.right.delete(this.element, this); //
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
	}

	/*******************************
	 * Get data
	 *******************************/
	protected E getData() {
		return this.element;
	}

	/*******************************
	 * Get left reference
	 *******************************/
	protected Group2_BST_Node<E> getLeft() {
		return this.left;
	}

	/*******************************
	 * Get right reference
	 *******************************/
	protected Group2_BST_Node<E> getRight() {
		return this.right;
	}

	/*******************************
	 * Get inorder successor
	 *******************************/
	private E getSucessor() { // Method called from node.right, keep going left to find inorder successor
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
