/*-
 ****************************************
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
 * Group2_Driver2.java
 * Version 17.0
 * 
 * Driver class to test various methods
 * implemented in the BST class.
 * 
 * Also displayed is the implementation
 * of Phase 2,3 & 4.  Postorder &
 * inorder traversal without recursion
 * and the number of non-leaf nodes 
 * within the tree.
 * 
 * All of the displays are properly 
 * displaying the correct results as 
 * well as performing the correct tasks.
 ****************************************/
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Group2_Driver2 {

	public static void main(String[] args) throws IOException {
		OutputStream treeStream = new FileOutputStream("Tree.txt");
		OutputStreamWriter treeWriter = new OutputStreamWriter(treeStream);
		FileInputStream in = new FileInputStream("Tree.txt");

		Group2_BST<String> tree = new Group2_BST<String>();

		displaySize(tree);
		insert(tree, treeWriter, in, 0);
		delete(tree, "Tom", treeWriter, in, 0);
		delete(tree, "Daniel", treeWriter, in, 0);
		delete(tree, "George", treeWriter, in, 0);

	}

	/******************************
	 * Display tree
	 ******************************/
	public static void displayTree(Group2_BST<String> tree) {
		tree.printTree();
	}

	/******************************
	 * Display size of BST
	 ******************************/
	public static void displaySize(Group2_BST<String> tree) {
		System.out.println("There are currently " + tree.getSize() + " elements in the tree");
	}

	/******************************
	 * Display # of non-leaf nodes
	 ******************************/
	public static void displayNonLeaf(Group2_BST<String> tree) {
		System.out.println("There are currently " + tree.getNumberofNonLeaves() + " non-leaf nodes in the tree");
	}

	/******************************
	 * Display height of BST
	 ******************************/
	public static void displayHeight(Group2_BST<String> tree) {
		System.out.println("The height is currently " + tree.getHeight() + ".");
		System.out.println();
	}

	/******************************
	 * Insert & Display into BST
	 * 
	 * @throws IOException
	 ******************************/
	public static void insert(Group2_BST<String> tree, OutputStreamWriter treeWriter, FileInputStream in, int content)
			throws IOException {
		insertNormal(tree); // Insert fixed values into BST

		tree.printTree2(treeWriter);
		treeWriter.flush();

		while ((content = in.read()) != -1) {
			System.out.print((char) content);
		}

		System.out.println();

		isThere(tree); // Check to see if elements are in the BST
		displaySize(tree); // Get size of tree
		displayNonLeaf(tree); // Get # of non-leaves
		displayHeight(tree); // Get height of tree
		displayTraversal(tree); // Display Traversals
	}

	/******************************
	 * Delete a node & Display BST
	 * 
	 * @throws IOException
	 ******************************/
	public static void delete(Group2_BST<String> tree, String toDelete, OutputStreamWriter treeWriter,
			FileInputStream in, int content) throws IOException {
		System.out.println("Was deleting " + toDelete + " successful?  " + tree.delete(toDelete));

		System.out.println();
		tree.printTree2(treeWriter);
		treeWriter.flush();

		while ((content = in.read()) != -1) {
			System.out.print((char) content);
		}

		System.out.println();

		displaySize(tree); // Get size of tree
		displayNonLeaf(tree); // Get # of non-leaves
		displayHeight(tree); // Get height of tree
		displayTraversal(tree); // Display traversals
	}

	/******************************
	 * Display Search Attempts
	 ******************************/
	public static void isThere(Group2_BST<String> tree) {
		// Attempt a search for a number in BST, should be true
		System.out.println("Searching for Michael in the tree . . .");
		System.out.println("Element Michael is in the BST:  " + tree.search("Michael"));
		System.out.println();

		// Attempt a search for an element not in BST, should be false
		System.out.println("Searching for Kyle in the tree . . .");
		System.out.println("Element Kyle is in the BST:  " + tree.search("Kyle"));
		System.out.println();
	}

	/******************************
	 * Display Tree Traversal
	 ******************************/
	public static void displayTraversal(Group2_BST<String> tree) {
		tree.levelorder();
		System.out.println();
		tree.preorder();
		tree.inorder();
		tree.postorder();

		System.out.println();

		System.out.println("The 4th smallest element in the tree is " + tree.getKthSmallest(4));
		System.out.println(
				"The 4th smallest element (not using inorder) in the tree is " + tree.getKthSmallest2(4));
		System.out.println("The 4th greatest element in the tree is " + tree.getKthLargest(4));
		System.out.println("The middle element is " + tree.getMiddle());

		System.out.println();

		System.out.print("Inorder w/o recursion:  ");
		tree.inorderNoRecursion();
		System.out.println();
		System.out.print("Postorder w/o recursion:  ");
		tree.postorderNoRecursion();

		System.out.println();
		System.out.println();
	}

	/*****************************************************************
	 * Populate BST w/ George, Michael, Tom, Adam, Jone, Peter, Daniel
	 *****************************************************************/
	private static void insertNormal(Group2_BST<String> tree) {
		System.out.println("Inserting George, Michael, Tom, Adam, Jone, Peter, Daniel into BST");

		tree.insert("George");
		tree.insert("Michael");
		tree.insert("Tom");
		tree.insert("Adam");
		tree.insert("Jone");
		tree.insert("Peter");

		checkSearch(tree);
	}

	private static void checkSearch(Group2_BST<String> tree) {
		// Attempt to insert a number, should be true
		System.out.println();
		System.out.println("Was Daniel successfully inserted?  " + tree.insert("Daniel"));
		System.out.println();

		// Attempt to insert a duplicate, should be false
		System.out.println("Attempting to insert Tom, a duplicate into BST . . .");
		System.out.println("Was Tom successfully inserted?  " + tree.insert("Tom"));
		System.out.println();
	}
}