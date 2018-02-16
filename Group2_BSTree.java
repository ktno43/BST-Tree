
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
* Group2_BSTree.java
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

public class Group2_BSTree<E extends Comparable<E>> implements Tree<E> {
	private TreeNode<E> root; // Root Node

	private static class TreeNode<E extends Comparable<E>> implements Comparable<TreeNode<E>> {
		private E element; // Data for node
		private TreeNode<E> left; // Reference to left child
		private TreeNode<E> right; // Reference to right child

		public TreeNode (E e) { // Initialization constructor
			this.element = e; // Assign data to node
		}

		/*******************************
		 * Attempt to insert node
		 *******************************/
		protected boolean insert (E e) { // Insert node with value e
			if (e.compareTo(this.element) == 0) // No duplicates allowed
				return false; // Didn't insert anything so return false

			else if (e.compareTo(this.element) < 0) { // If to-insert node is smaller than current node, go left
				if (this.left == null) { // If there's a space available put new node here
					this.left = new TreeNode<E>(e); // Make a new node with value e
					return true; // Return true since we inserted successfully
				}

				else
					return this.left.insert(e); // No space available so recursively call from the current-left node
			}

			else if (e.compareTo(this.element) > 0) { // If to-insert node is greater than current node, go right
				if (this.right == null) { // If there's a space available put new node here
					this.right = new TreeNode<E>(e);
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
		protected boolean delete (E e, TreeNode<E> parent) { // Delete node with value e, parent also passed in to fix references
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
		 * Get inorder successor
		 *******************************/
		private E getSucessor () { // Method called from node.right, keep going left to find inorder successor
			if (this.left == null) // Found inorder successor
				return this.element;

			else
				return this.left.getSucessor(); // Recursively call to find successor
		}

		/*******************************
		 * Implement Comparable Interface
		 *******************************/
		@Override
		public int compareTo (TreeNode<E> otherNode) { // Implement the comparable interface
			return this.element.compareTo(otherNode.element);
		}
	}

	/*******************************
	 * Get size of tree
	 *******************************/
	@Override
	public int getSize () { // Get the size starting from the root
		if (this.root == null) // If there's no root, there's no tree, size = 0
			return 0;

		return getSize(this.root);
	}

	private int getSize (TreeNode<E> currentNode) {
		if (currentNode == null) // Terminal condition, when there's no other nodes, size = 0
			return 0;

		else // Root + everything left of root + everything right of root
			return (1 + getSize(currentNode.left) + getSize(currentNode.right)); // Recursively call
	}

	/*******************************
	 * Is tree empty?
	 *******************************/
	@Override
	public boolean isEmpty () { // getSize() == 0, tree is empty, return true, else false
		return getSize() == 0; // Method call to get size
	}

	/*******************************
	 * Get # of non-leaf nodes
	 *******************************/
	public int getNumberofNonLeaves () {
		return getNumberofNonLeaves(this.root); // Start at root
	}

	private int getNumberofNonLeaves (TreeNode<E> n) {
		if (n == null || (n.left == null && n.right == null)) // Terminal condition, leaf node or you've reached the end of left/right subtrees
			return 0;

		else
			return 1 + getNumberofNonLeaves(n.left) + getNumberofNonLeaves(n.right); // Recursively call to get number of non-leaf nodes in left & right subtrees
	}

	/*******************************
	 * Attempt to search node
	 *******************************/
	@Override
	public boolean search (E e) {
		TreeNode<E> current = this.root; // Current position, start off at the root

		while (current != null) {
			if (e.compareTo(current.element) < 0) // If element to-search is less than current, go left
				current = current.left;

			else if (e.compareTo(current.element) > 0) // If element to-search is greater than current, go right
				current = current.right;

			else
				return true; // Return true, since match was successful
		}
		return false; // Return false since no match was found
	}

	/*******************************
	 * Attempt to insert node
	 *******************************/
	@Override
	public boolean insert (E e) {
		if (this.root == null) { // If root is null, make the node to-insert as root
			this.root = new TreeNode<E>(e);

			return true; // Return true since insertion was successful
		}

		else
			return this.root.insert(e); // Method call to insert node
	}

	/*******************************
	 * Attempt to delete node
	 *******************************/
	@Override
	public boolean delete (E e) {
		if (this.root == null) { // If there's no root, there's nothing to delete
			return false; // Return false since nothing was deleted
		}

		else
			return this.root.delete(e, null); // Method to delete "e" node, parent = null since we do not know the parent nor do we know it exists
	}

	/*******************************
	 * Preorder traversal of BST
	 *******************************/
	@Override
	public void preorder () {
		ArrayList<E> resultList = new ArrayList<E>(); // Array List to store result
		String listAsString = "Preoder: "; // Array list to string

		preorderIterator(this.root, resultList); // Method call, pass in the root and list to be modified

		for (E e : resultList) { // For every element in resultList, put into e and add it to the string
			listAsString += e + " ";
		}
		System.out.println(listAsString); // Print string out
	}

	private void preorderIterator (TreeNode<E> node, ArrayList<E> preorderList) { // Pass in the node and list to be modified
		if (node != null) { // Keep going until there are no nodes left
			preorderList.add(node.element); // Add node to preorderList

			preorderIterator(node.left, preorderList); // Traverse left subtree

			preorderIterator(node.right, preorderList); // Traverse right subtree
		}
	}

	/*******************************
	 * Inorder traversal of BST
	 *******************************/
	@Override
	public void inorder () {
		ArrayList<E> resultList = new ArrayList<E>(); // Array list to store result
		String listAsString = "Inorder: "; // Array list to string

		inorderIterator(this.root, resultList); // Method call, pass in the root and list to be modified

		for (E e : resultList) { // For every element in resultList, put into e and add it to the string
			listAsString += e + " ";
		}
		System.out.println(listAsString); // Print string out
	}

	private void inorderIterator (TreeNode<E> node, ArrayList<E> inorderList) { // Pass in the node and the list to be modified
		if (node != null) {
			inorderIterator(node.left, inorderList); // Traverse left-subtree

			inorderList.add(node.element); // Add the node to the inorderList

			inorderIterator(node.right, inorderList); // Traverse the right-subtree
		}
	}

	/****************************************
	 * Inorder traversal of BST w/o recursion
	 ****************************************/
	public void inorderNoRecursion () { // Inorder traversal without recursion
		Stack<TreeNode<E>> stacky = new Stack<TreeNode<E>>();
		TreeNode<E> current = this.root;

		while (stacky.size() > 0 || current != null) {
			if (current != null) {
				stacky.push(current);
				current = current.left;
			}

			else {
				TreeNode<E> popVal = stacky.pop();
				System.out.print(popVal.element + " ");

				current = popVal.right;
			}
		}
	}

	/*******************************
	 * Postorder traversal of BST
	 *******************************/
	@Override
	public void postorder () {
		ArrayList<E> resultList = new ArrayList<E>(); // Array list to store result
		String listAsString = "Postorder: "; // Array list to string

		postorderIterator(this.root, resultList); // Method call, pass in the root and the list to be modified

		for (E e : resultList) { // For every element in resultList, put into e and add it to the string
			listAsString += e + " ";
		}
		System.out.println(listAsString); // Print string out
	}

	private void postorderIterator (TreeNode<E> node, ArrayList<E> postorderList) { // Pass in the node and the list to be modified
		if (node != null) {
			postorderIterator(node.left, postorderList); // Traverse the left-subtree

			postorderIterator(node.right, postorderList); // Traverse the right-subtree

			postorderList.add(node.element); // Add the node to the postorderList
		}
	}

	/*******************************************
	 * Postorder traversal of BST w/o recursion
	 *******************************************/
	public void postorderNoRecursion () {
		if (this.root == null) // No root, nothing to traverse
			return;

		Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>(); // Stack for postorder traversal
		TreeNode<E> current = this.root; // Starting position

		while (current != null || stack.size() > 0) { // While there are elements in the stack or if current is not null keep going
														 // Once this while loop fails all the elements have been printed
			if (current != null) { // If the current element has a value, push it onto the stack
				stack.push(current);
				current = current.left; // Continue traversing down left (postorder: left -> right -> middle)
			}
			else {
				TreeNode<E> temp = stack.peek().right; // Current is null, check to see if there is a right-subtree of the topmost element of the stack

				if (temp == null) { // Topmost element does not have a right child, pop the right-subtree
					temp = stack.pop();
					System.out.print(temp.element + " "); // Print out the last value pop'd
					while (stack.size() > 0 && temp == stack.peek().right) { // While the stack is not empty and check to see if temp was the right child of the topmost element of the stack
																			 // If the while condition fails it means there is another right-subtree
						temp = stack.pop(); // The right-subtree is finished, and now pop the parent node
						System.out.print(temp.element + " "); // Print out the last value pop'd
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
	public void printTree () {
		BSTreePrinter.printNode(this.root); // Print tree-like structure
	}

	/*******************************
	 * BST Printer Class @Michal Kreuzman
	 *******************************/
	public static class BSTreePrinter {
		public static <E extends Comparable<E>> void printNode (TreeNode<E> root) {
			int maxLevel = BSTreePrinter.maxLevel(root);

			printNode(Collections.singletonList(root), 1, maxLevel);
		}

		private static <E extends Comparable<E>> void printNode (List<TreeNode<E>> nodes, int level, int maxLevel) {
			if (nodes.isEmpty() || BSTreePrinter.isAllElementsNull(nodes))
				return;

			int floor = maxLevel - level;
			int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
			int firstSpaces = (int) Math.pow(2, (floor)) - 1;
			int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

			BSTreePrinter.printWhitespaces(firstSpaces);

			List<TreeNode<E>> newNodes = new ArrayList<TreeNode<E>>();
			for (TreeNode<E> node : nodes) {
				if (node != null) {
					System.out.print(node.element);
					newNodes.add(node.left);
					newNodes.add(node.right);
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

					if (nodes.get(j).left != null)
						System.out.print("/");

					else
						BSTreePrinter.printWhitespaces(1);

					BSTreePrinter.printWhitespaces(i + i - 1);

					if (nodes.get(j).right != null)
						System.out.print("\\");
					else
						BSTreePrinter.printWhitespaces(1);

					BSTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
				}
				System.out.println("");
			}
			printNode(newNodes, level + 1, maxLevel);
		}

		private static void printWhitespaces (int count) {
			for (int i = 0; i < count; i++)
				System.out.print(" ");
		}

		private static <E extends Comparable<E>> int maxLevel (TreeNode<E> node) {
			if (node == null)
				return 0;

			else
				return Math.max(BSTreePrinter.maxLevel(node.left), BSTreePrinter.maxLevel(node.right)) + 1;
		}

		private static <E> boolean isAllElementsNull (List<E> list) {
			for (Object object : list) {
				if (object != null)
					return false;
			}
			return true;
		}
	}
}