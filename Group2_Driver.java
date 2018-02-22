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
 * Group2_Driver.java
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
public class Group2_Driver {

	public static void main(String[] args) {
		Group2_BST<Integer> tree = new Group2_BST<Integer>();

		displaySize(tree);
		insert(tree);
		delete(tree, 12);
		delete(tree, -4);
		delete(tree, 55);
		delete(tree, 21);
		delete(tree, 2);
		delete(tree, 5);
	}

	/******************************
	 * Display tree
	 ******************************/
	public static void displayTree(Group2_BST<Integer> tree) {
		tree.printTree();
	}

	/******************************
	 * Display size of BST
	 ******************************/
	public static void displaySize(Group2_BST<Integer> tree) {
		System.out.println("There are currently " + tree.getSize() + " elements in the tree.");
	}

	/******************************
	 * Display # of non-leaf nodes
	 ******************************/
	public static void displayNonLeaf(Group2_BST<Integer> tree) {
		System.out.println("There are currently " + tree.getNumberofNonLeaves() + " non-leaf nodes in the tree.");
		System.out.println();
	}

	/******************************
	 * Display Tree Traversal
	 ******************************/
	public static void displayTraversal(Group2_BST<Integer> tree) {

		System.out.println("Inorder traversal of the given tree");
		tree.inorder();
		System.out.println();

		System.out.println("Preorder traversal of the given tree");
		tree.preorder();
		System.out.println();

		System.out.println("Postorder traversal of the given tree");
		tree.postorder();
		System.out.println();

		System.out.println("Inorder traversal w/o recursion of the given tree");
		System.out.print("Inorder w/o recursion: ");
		tree.inorderNoRecursion();
		System.out.println();
		System.out.println();

		System.out.println("Postorder traversal w/o recursion of the given tree");
		System.out.print("Postorder w/o recursion: ");
		tree.postorderNoRecursion();

		System.out.println();
		System.out.println();
		System.out.println();
	}

	/******************************
	 * Display Search Attempts
	 ******************************/
	public static void isThere(Group2_BST<Integer> tree) {

		// Attempt a search for a number in BST, should be true
		System.out.println("Searching for 9 in the tree . . .");
		System.out.println("Element 9 is in the BST:  " + tree.search(9));
		System.out.println();

		// Attempt a search for a number not in BST, should be false
		System.out.println("Searching for 55 in the tree . . .");
		System.out.println("Element 55 is in the BST:  " + tree.search(55));
		System.out.println();
	}

	/******************************
	 * Insert & Display into BST
	 ******************************/
	public static void insert(Group2_BST<Integer> tree) {
		insertNormal(tree);

		displayTree(tree);
		isThere(tree);
		displaySize(tree);
		displayNonLeaf(tree);
		displayTraversal(tree);
	}

	private static void insertNormal(Group2_BST<Integer> tree) {
		System.out.println("Inserting 5, 2, -4, 3, 12, 9, 21, 19, 25, 6, 8, 22, -5 into BST");

		tree.insert(5);
		tree.insert(2);
		tree.insert(-4);
		tree.insert(3);
		tree.insert(12);
		tree.insert(9);
		tree.insert(21);
		tree.insert(19);

		// Attempt to insert a number, should be true
		System.out.println("Was 25 successfully inserted?  " + tree.insert(25));
		System.out.println();

		// Attempt to insert a duplicate, should be false
		System.out.println("Attempting to insert 21, a duplicate into BST . . .");
		System.out.println("Was 21 successfully inserted?  " + tree.insert(21));
		System.out.println();
	}

	/******************************
	 * Delete a node & Display BST
	 ******************************/
	public static void delete(Group2_BST<Integer> tree, int toDelete) {
		System.out.println("Was deleting " + toDelete + " successful?  " + tree.delete(toDelete));

		displayTree(tree);
		displaySize(tree);
		displayNonLeaf(tree);
		displayTraversal(tree);
	}
}