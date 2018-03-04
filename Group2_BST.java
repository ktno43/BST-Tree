
/*
 *****************************************
 * Group 2
 * Kyle Nguyen
 * 
 * COMP 282
 * Spring 2018
 * Dr. Wen-Chin Hsu
 * M/W 9:30 A.M - 10:45 A.M
 * 
 * Project 1: 
 * Phase 1 (BST Implementation), 
 * Phase 2 (Question 1), 
 * Phase 3 (Question 2), &
 * Phase 4 (Question 3)
 * 
 * Group2_BST.java
 * Version 18.0
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
import java.util.LinkedList;
import java.util.Queue;

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
	 * Get height of tree
	 *******************************/
	public int getHeight() {
		if (this.root == null) // If there's no tree, height is 0
			return 0;

		return getHeight(this.root); // Get the height of the tree
	}

	private int getHeight(Group2_BST_Node<E> node) {
		if (node == null) // If the
			return 0;

		return 1 + Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode())); // Get the height max of the children + 1
	}

	/*******************************
	 * Is tree empty?
	 *******************************/
	@Override
	public boolean isEmpty() { // getSize() == 0, tree is empty, return true, else false
		return getSize() == 0; // Method call to get size
	}

	/*************************************
	 * Question 2: Get # of non-leaf nodes
	 *************************************/
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
	 * Get middle element
	 *******************************/
	public E getMiddle() {
		if (this.root == null) // If root is empty, nothing to search
			return null;

		ArrayList<E> mahList = new ArrayList<E>(); // List to put elements when traversed into

		inorderIterator(this.root, mahList); // Iterate through the tree and store in the array list

		int middle = mahList.size() / 2; // Integer division (floor)

		if (mahList.size() % 2 == 1) // Odd so print out element at the index middle (offset by 1 already)
			return mahList.get(middle);

		else
			return mahList.get(middle - 1); // Even so print out element to the left of the "center"
	}

	/*******************************
	 * Find kth smallest node
	 *******************************/
	public E getKthSmallest(int k) {
		if (this.root == null || k <= 0 || k > getSize()) // If there's no tree, or search element is < 0 or bigger than the tree nothing to search for
			return null;

		return getKthSmallest(this.root, k); // Get the kth smallest element in the tree
	}

	private E getKthSmallest(Group2_BST_Node<E> current, int k) { // Inorder traversal of the tree
		ArrayList<E> mahList = new ArrayList<E>(); // Array list for traversal of the tree

		helper(mahList, current, k); // Helper method to find kth smallest element

		if (mahList.size() < k) // If the list is smaller than the k, it wasn't found
			return null;

		return mahList.get(k - 1); // K was found, so fix the offset by 1
	}

	private void helper(ArrayList<E> mahList, Group2_BST_Node<E> current, int k) { // Helper method of inorder iterator
		if (mahList.size() == k) // Match found so stop the list here
			return;

		if (current.getLeftNode() != null) // If the left reference is not null
			helper(mahList, current.getLeftNode(), k); // Add to the array list

		mahList.add(current.getData()); // Add the current (middle)

		if (current.getRightNode() != null) // If the right reference is not null
			helper(mahList, current.getRightNode(), k); // Add to the array list
	}

	/*******************************
	 * Find kth smallest node V2
	 *******************************/
	public E getKthSmallest2(int k) {
		if (this.root == null || k <= 0 || k > getSize()) // If there's no tree, or search element is < 0 or bigger than the tree nothing to search for
			return null;

		return getKthSmallest2(k, this.root);  // Get the kth smallest element
	}

	private E getKthSmallest2(int k, Group2_BST_Node<E> current) {
		Group2_BST_Node<E> A = current.getLeftNode(); // Left reference of current
		Group2_BST_Node<E> B = current.getRightNode(); // Right reference of current

		// In the notes AVL Deletion
		if ((A == null) && (k == 1)) {
			return current.getData();
		}

		else if ((A == null) && (k == 2)) {
			return B.getData();
		}

		else if (k <= getSize(A)) {
			return getKthSmallest2(k, A);
		}

		else if (k == getSize(A) + 1) {
			return current.getData();
		}

		else {
			return getKthSmallest2(k - getSize(A) - 1, B);
		}
	}

	/*******************************
	 * Find kth largest node
	 *******************************/
	public E getKthLargest(int k) {
		if (this.root == null || k <= 0 || k > getSize(this.root)) // If there's no tree, or search element is < 0 or bigger than the tree nothing to search for
			return null;

		ArrayList<E> mahList = new ArrayList<E>(); // List for inorder traversal

		inorderIterator(this.root, mahList); // Inorder iterator for tree

		return mahList.get(mahList.size() - k); // Get the difference between the size and K, it will give the kth largest element
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
	 * Level-order traversal of BST
	 *******************************/
	public void levelorder() {
		int currentLevel = 1; // Current level starts at 1

		System.out.print("Level-order:  "); // String for display

		for (currentLevel = 1; currentLevel <= getHeight(); currentLevel += 1) { // For all of the element
			levelorder(this.root, currentLevel);
		}
	}

	private void levelorder(Group2_BST_Node<E> current, int currentLevel) {
		if (current == null)
			return;

		if (currentLevel == 1)
			System.out.print(current.getData() + "  ");

		else if (currentLevel > 1) {
			levelorder(current.getLeftNode(), currentLevel - 1);
			levelorder(current.getRightNode(), currentLevel - 1);
		}
	}

	/*****************************************************
	 * Level-order traversal of BST w/o recursion
	 *****************************************************/
	public void levelOrder2() {
		if (this.root == null)
			return;

		Queue<Group2_BST_Node<E>> myQueue = new LinkedList<Group2_BST_Node<E>>();

		System.out.print("Level-order w/o recursion: ");

		myQueue.add(this.root);

		while (myQueue.size() > 0) {
			Group2_BST_Node<E> currentNode = myQueue.poll();
			System.out.print(currentNode.getData() + " ");

			if (currentNode.getLeftNode() != null)
				myQueue.add(currentNode.getLeftNode());

			if (currentNode.getRightNode() != null)
				myQueue.add(currentNode.getRightNode());
		}
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

			if (node.getLeftNode() != null)
				preorderIterator(node.getLeftNode(), preorderList); // Traverse left subtree

			if (node.getRightNode() != null)
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
			if (node.getLeftNode() != null)
				inorderIterator(node.getLeftNode(), inorderList); // Traverse left-subtree

			inorderList.add(node.getData()); // Add the node to the inorderList

			if (node.getRightNode() != null)
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
			if (node.getLeftNode() != null)
				postorderIterator(node.getLeftNode(), postorderList); // Traverse the left-subtree

			if (node.getRightNode() != null)
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