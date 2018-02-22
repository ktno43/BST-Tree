
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
* Phase 1, Phase 2 (Question 1), 
* & Phase 3 (Question 2)
* 
* Group2_BST.java
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
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

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
			return (1 + getSize(currentNode.getLeft()) + getSize(currentNode.getRight())); // Recursively call
	}

	/*******************************
	 * Is tree empty?
	 *******************************/
	@Override
	public boolean isEmpty() { // getSize() == 0, tree is empty, return true, else false
		return getSize() == 0; // Method call to get size
	}

	/*******************************
	 * Question 2:
	 * Get # of non-leaf nodes
	 *******************************/
	public int getNumberofNonLeaves() {
		return getNumberofNonLeaves(this.root); // Start at root
	}

	private int getNumberofNonLeaves(Group2_BST_Node<E> n) {
		if (n == null || (n.getLeft() == null && n.getRight() == null)) // Terminal condition, leaf node or you've reached the end of left/right subtrees
			return 0;

		else
			return 1 + getNumberofNonLeaves(n.getLeft()) + getNumberofNonLeaves(n.getRight()); // Recursively call to get number of non-leaf nodes in left & right subtrees
	}

	/*******************************
	 * Attempt to search node
	 *******************************/
	@Override
	public boolean search(E e) {
		Group2_BST_Node<E> current = this.root; // Current position, start off at the root

		while (current != null) {
			if (e.compareTo(current.getData()) < 0) // If element to-search is less than current, go left
				current = current.getLeft();

			else if (e.compareTo(current.getData()) > 0) // If element to-search is greater than current, go right
				current = current.getRight();

			else
				return true; // Return true, since match was successful
		}
		return false; // Return false since no match was found
	}

	/*******************************
	 * Attempt to insert node
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
	 * Attempt to delete node
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
	public void preorder() {
		ArrayList<E> resultList = new ArrayList<E>(); // Array List to store result
		String listAsString = "Preoder: "; // Array list to string

		preorderIterator(this.root, resultList); // Method call, pass in the root and list to be modified

		for (E e : resultList) { // For every element in resultList, put into e and add it to the string
			listAsString += e + " ";
		}
		System.out.println(listAsString); // Print string out
	}

	private void preorderIterator(Group2_BST_Node<E> node, ArrayList<E> preorderList) { // Pass in the node and list to be modified
		if (node != null) { // Keep going until there are no nodes left
			preorderList.add(node.getData()); // Add node to preorderList

			preorderIterator(node.getLeft(), preorderList); // Traverse left subtree

			preorderIterator(node.getRight(), preorderList); // Traverse right subtree
		}
	}

	/*******************************
	 * Inorder traversal of BST
	 *******************************/
	@Override
	public void inorder() {
		ArrayList<E> resultList = new ArrayList<E>(); // Array list to store result
		String listAsString = "Inorder: "; // Array list to string

		inorderIterator(this.root, resultList); // Method call, pass in the root and list to be modified

		for (E e : resultList) { // For every element in resultList, put into e and add it to the string
			listAsString += e + " ";
		}
		System.out.println(listAsString); // Print string out
	}

	private void inorderIterator(Group2_BST_Node<E> node, ArrayList<E> inorderList) { // Pass in the node and the list to be modified
		if (node != null) {
			inorderIterator(node.getLeft(), inorderList); // Traverse left-subtree

			inorderList.add(node.getData()); // Add the node to the inorderList

			inorderIterator(node.getRight(), inorderList); // Traverse the right-subtree
		}
	}

	/****************************************
	 * Question 3:
	 * Inorder traversal of BST w/o recursion
	 ****************************************/
	public void inorderNoRecursion() { // Inorder traversal without recursion
		Stack<Group2_BST_Node<E>> stacky = new Stack<Group2_BST_Node<E>>();
		Group2_BST_Node<E> current = this.root;

		while (stacky.size() > 0 || current != null) {
			if (current != null) {
				stacky.push(current);
				current = current.getLeft();
			}

			else {
				Group2_BST_Node<E> popVal = stacky.pop();
				System.out.print(popVal.getData() + " ");

				current = popVal.getRight();
			}
		}
	}

	/*******************************
	 * Postorder traversal of BST
	 *******************************/
	@Override
	public void postorder() {
		ArrayList<E> resultList = new ArrayList<E>(); // Array list to store result
		String listAsString = "Postorder: "; // Array list to string

		postorderIterator(this.root, resultList); // Method call, pass in the root and the list to be modified

		for (E e : resultList) { // For every element in resultList, put into e and add it to the string
			listAsString += e + " ";
		}
		System.out.println(listAsString); // Print string out
	}

	private void postorderIterator(Group2_BST_Node<E> node, ArrayList<E> postorderList) { // Pass in the node and the list to be modified
		if (node != null) {
			postorderIterator(node.getLeft(), postorderList); // Traverse the left-subtree

			postorderIterator(node.getRight(), postorderList); // Traverse the right-subtree

			postorderList.add(node.getData()); // Add the node to the postorderList
		}
	}

	/*******************************************
	 * Question 1:
	 * Postorder traversal of BST w/o recursion
	 *******************************************/
	public void postorderNoRecursion() {
		if (this.root == null) // No root, nothing to traverse
			return;

		Stack<Group2_BST_Node<E>> stack = new Stack<Group2_BST_Node<E>>(); // Stack for postorder traversal
		Group2_BST_Node<E> current = this.root; // Starting position

		while (current != null || stack.size() > 0) { // While there are elements in the stack or if current is not null keep going
														 // Once this while loop fails all the elements have been printed
			if (current != null) { // If the current element has a value, push it onto the stack
				stack.push(current);
				current = current.getLeft(); // Continue traversing down left (postorder: left -> right -> middle)
			}

			else {
				Group2_BST_Node<E> temp = stack.peek().getRight(); // Current is null, check to see if there is a right-subtree of the topmost element of the stack

				if (temp == null) { // Topmost element does not have a right child, pop the right-subtree
					temp = stack.pop();
					System.out.print(temp.getData() + " "); // Print out the last value pop'd

					while (stack.size() > 0 && temp == stack.peek().getRight()) { // While the stack is not empty and check to see if temp was the right child of the topmost element of the stack
						// If the while condition fails it means there is another right-subtree
						temp = stack.pop(); // The right-subtree is finished, and now pop the parent node
						System.out.print(temp.getData() + " "); // Print out the last value pop'd
					}
				}

				else
					current = temp; // The topmost element has a right child, current is that element now
			}
		}
	}

	/*******************************
	 * Print Tree
	 *******************************/
	public void printTree() {
		BSTreePrinter.printNode(this.root); // Print tree-like structure
	}

	/*************************************
	 * BST Printer Class @Michal Kreuzman
	 *************************************/
	public static class BSTreePrinter {
		public static <E extends Comparable<E>> void printNode(Group2_BST_Node<E> root) {
			int maxLevel = BSTreePrinter.maxLevel(root);

			printNode(Collections.singletonList(root), 1, maxLevel);
		}

		private static <E extends Comparable<E>> void printNode(List<Group2_BST_Node<E>> nodes, int level,
				int maxLevel) {
			if (nodes.isEmpty() || BSTreePrinter.isAllElementsNull(nodes))
				return;

			int floor = maxLevel - level;
			int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
			int firstSpaces = (int) Math.pow(2, (floor)) - 1;
			int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

			BSTreePrinter.printWhitespaces(firstSpaces);

			List<Group2_BST_Node<E>> newNodes = new ArrayList<Group2_BST_Node<E>>();
			for (Group2_BST_Node<E> node : nodes) {
				if (node != null) {
					System.out.print(node.getData());
					newNodes.add(node.getLeft());
					newNodes.add(node.getRight());
				}

				else {
					newNodes.add(null);
					newNodes.add(null);
					System.out.print(" ");
				}
				BSTreePrinter.printWhitespaces(betweenSpaces);
			}

			System.out.println("");

			for (int i = 1; i <= endgeLines; i++) {
				for (int j = 0; j < nodes.size(); j++) {
					BSTreePrinter.printWhitespaces(firstSpaces - i);

					if (nodes.get(j) == null) {
						BSTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
						continue;
					}

					if (nodes.get(j).getLeft() != null)
						System.out.print("/");

					else
						BSTreePrinter.printWhitespaces(1);

					BSTreePrinter.printWhitespaces(i + i - 1);

					if (nodes.get(j).getRight() != null)
						System.out.print("\\");
					else
						BSTreePrinter.printWhitespaces(1);

					BSTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
				}
				System.out.println("");
			}
			printNode(newNodes, level + 1, maxLevel);
		}

		private static void printWhitespaces(int count) {
			for (int i = 0; i < count; i++)
				System.out.print(" ");
		}

		private static <E extends Comparable<E>> int maxLevel(Group2_BST_Node<E> node) {
			if (node == null)
				return 0;

			else
				return Math.max(BSTreePrinter.maxLevel(node.getLeft()), BSTreePrinter.maxLevel(node.getRight())) + 1;
		}

		private static <E> boolean isAllElementsNull(List<E> list) {
			for (Object object : list) {
				if (object != null)
					return false;
			}
			return true;
		}
	}
}