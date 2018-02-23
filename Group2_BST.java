/*-
*****************************************
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
* Group2_BST.java
* Version 10.0
* 
* The program works as expected, and
* follows specifications.  All the
* methods are implemented from the 
* interface given and work correctly.
* 
* The postorder & inorder traversal
* without recursion is implemented
* and working as it follows the 
* specification by using a stack.
* 
* Also implemented is the method that
* calculates the number of non-leaf
* nodes within the tree.
* 
* All of the methods work as expected
* and return the correct results.
****************************************/
import java.util.Stack;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Group2_BST<E extends Comparable<E>> implements Tree<E> {
	private Group2_BST_Node<E> root; // Root Node

	/*******************************
	 * Get size of tree
	 *******************************/
	@Override
	public int getSize() { // Get the size starting from the root
		if (this.root == null) // If there's no root, there's no tree, size = 0
			return 0;

		return getSize(this.root);
	}

	private int getSize(Group2_BST_Node<E> currentNode) {
		if (currentNode == null) // Terminal condition, when there's no other nodes, size = 0
			return 0;

		else // Root + everything left of root + everything right of root
			return (1 + getSize(currentNode.getLeftNode()) + getSize(currentNode.getRightNode())); // Recursively call
	}

	/*******************************
	 * Is tree empty?
	 *******************************/
	@Override
	public boolean isEmpty() { // getSize() == 0, tree is empty, return true, else false
		return getSize() == 0; // Method call to get size
	}

	/*******************************
	 * Question 2: Get # of non-leaf nodes
	 *******************************/
	public int getNumberofNonLeaves() {
		return getNumberofNonLeaves(this.root); // Start at root
	}

	private int getNumberofNonLeaves(Group2_BST_Node<E> n) {
		if (n == null || (n.getLeftNode() == null && n.getRightNode() == null)) // Terminal condition, leaf node or you've reached the end of left/right subtrees
			return 0;

		else
			return 1 + getNumberofNonLeaves(n.getLeftNode()) + getNumberofNonLeaves(n.getRightNode()); // Recursively call to get number of non-leaf nodes in left & right subtrees
	}

	/*******************************
	 * Attempt to search for a node
	 *******************************/
	@Override
	public boolean search(E e) {
		if (this.root == null) // If root is empty, nothing to search
			return false; // Search unsuccessful

		else
			return this.root.search(e); // Attempt to search for element
	}

	/*******************************
	 * Attempt to insert a node
	 *******************************/
	@Override
	public boolean insert(E e) {
		if (this.root == null) { // If root is null, make the node to-insert as root
			this.root = new Group2_BST_Node<E>(e);

			return true; // Return true since insertion was successful
		}

		else
			return this.root.insert(e); // Method call to insert node
	}

	/*******************************
	 * Attempt to delete a node
	 *******************************/
	@Override
	public boolean delete(E e) {
		if (this.root == null)  // If there's no root, there's nothing to delete
			return false; // Return false since nothing was deleted

		else
			return this.root.delete(e, null); // Method to delete "e" node, parent = null since we do not know the parent nor do we know it exists
	}

	/*******************************
	 * Preorder traversal of BST
	 *******************************/
	@Override
	public void preorder() { // Middle -> Left -> Right
		ArrayList<E> resultList = new ArrayList<E>(); // Array List to store result
		String listAsString = "Preoder:  "; // Array list to string

		preorderIterator(this.root, resultList); // Method call, pass in the root and list to be modified

		for (E e : resultList) { // For every element in resultList, put into e and add it to the string
			listAsString += e + "  ";
		}

		System.out.println(listAsString); // Print string out
	}

	private void preorderIterator(Group2_BST_Node<E> node, ArrayList<E> preorderList) { // Pass in the node and list to be modified
		if (node != null) { // Keep going until there are no nodes left
			preorderList.add(node.getData()); // Add node to preorderList

			preorderIterator(node.getLeftNode(), preorderList); // Traverse left subtree

			preorderIterator(node.getRightNode(), preorderList); // Traverse right subtree
		}
	}

	/*******************************
	 * Inorder traversal of BST
	 *******************************/
	@Override
	public void inorder() { // Left -> Middle -> Right
		ArrayList<E> resultList = new ArrayList<E>(); // Array list to store result
		String listAsString = "Inorder:  "; // Array list to string

		inorderIterator(this.root, resultList); // Method call, pass in the root and list to be modified

		for (E e : resultList) { // For every element in resultList, put into e and add it to the string
			listAsString += e + "  ";
		}

		System.out.println(listAsString); // Print string out
	}

	private void inorderIterator(Group2_BST_Node<E> node, ArrayList<E> inorderList) { // Pass in the node and the list to be modified
		if (node != null) {
			inorderIterator(node.getLeftNode(), inorderList); // Traverse left-subtree

			inorderList.add(node.getData()); // Add the node to the inorderList

			inorderIterator(node.getRightNode(), inorderList); // Traverse the right-subtree
		}
	}

	/*****************************************************
	 * Question 3: Inorder traversal of BST w/o recursion
	 *****************************************************/
	public void inorderNoRecursion() { // Inorder traversal without recursion
		Stack<Group2_BST_Node<E>> stacky = new Stack<Group2_BST_Node<E>>(); // Create stack
		Group2_BST_Node<E> current = this.root; // Current position in tree (start at the root)

		while (stacky.size() > 0 || current != null) { // While there are elements in the stack or if there are more nodes to traverse
			if (current != null) { // If there is a node, push it onto the stack and check to see if it has a left or right subtree
				stacky.push(current);

				current = current.getLeftNode(); // Check to see if it has a left-subtree
			}

			else { /*
					 * At this point it does not have: 1) A left-subtree => Reached the end of the left-subtree, pop and display the data 2) Left/right-subtrees => Reached the middle (parent), pop and display the data 3) A right-subtree => Reached the end of the right-subtree, pop and and display the data
					 */
				Group2_BST_Node<E> popVal = stacky.pop(); // Pop the element off the stack and display it's data

				System.out.print(popVal.getData() + "  ");

				current = popVal.getRightNode(); // Check to see if it has a right-subtree
			}
		}
	}

	/*******************************
	 * Postorder traversal of BST
	 *******************************/
	@Override
	public void postorder() {  // Left -> Right -> Middle
		ArrayList<E> resultList = new ArrayList<E>(); // Array list to store result
		String listAsString = "Postorder:  "; // Array list to string

		postorderIterator(this.root, resultList); // Method call, pass in the root and the list to be modified

		for (E e : resultList) { // For every element in resultList, put into e and add it to the string
			listAsString += e + "  ";
		}

		System.out.println(listAsString); // Print string out
	}

	private void postorderIterator(Group2_BST_Node<E> node, ArrayList<E> postorderList) { // Pass in the node and the list to be modified
		if (node != null) {
			postorderIterator(node.getLeftNode(), postorderList); // Traverse the left-subtree

			postorderIterator(node.getRightNode(), postorderList); // Traverse the right-subtree

			postorderList.add(node.getData()); // Add the node to the postorderList
		}
	}

	/*******************************************************
	 * Question 1: Postorder traversal of BST w/o recursion
	*******************************************************/
	public void postorderNoRecursion() {
		if (this.root == null) // No root, nothing to traverse
			return;

		Stack<Group2_BST_Node<E>> stack = new Stack<Group2_BST_Node<E>>(); // Stack for postorder traversal
		Group2_BST_Node<E> current = this.root; // Starting position

		while (current != null || stack.size() > 0) { // While there are elements in the stack or if current is not null keep going
														 // Once this while loop fails all the elements have been printed
			if (current != null) { // If the current element has a value, push it onto the stack
				stack.push(current);

				current = current.getLeftNode(); // Continue traversing down left (postorder: left -> right -> middle)
			}

			else {
				Group2_BST_Node<E> temp = stack.peek().getRightNode(); // Current is null, check to see if there is a right-subtree of the topmost element of the stack

				if (temp == null) { // Topmost element does not have a right child, pop the right-subtree
					temp = stack.pop();

					System.out.print(temp.getData() + "  "); // Print out the last value pop'd

					while (stack.size() > 0 && temp == stack.peek().getRightNode()) { // While the stack is not empty and check to see if temp was the right child of the topmost element of the stack
						// If the while condition fails it means there is another right-subtree

						temp = stack.pop(); // The right-subtree is finished, and now pop the parent node

						System.out.print(temp.getData() + "  "); // Print out the last value pop'd
					}
				}

				else
					current = temp; // The topmost element has a right child, current is that element now
			}
		}
	}

	/*******************************
	 * Print tree vertically
	 *******************************/
	public void printTree() {
		this.root.printNode();
	}
	
	/*******************************
	 * Print tree horizontally
	 *******************************/
	public void printTree2(OutputStreamWriter out) throws IOException {
		this.root.printTree(out);
	}
}