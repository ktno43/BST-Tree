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
 * Tree.java
 * Version 16.0
 * 
 * The interface to be implemented  by
 * the BSTgiven in the project
 * specifications.
 ****************************************/
public interface Tree<E> {
	/** Return true if the element is in the tree */
	public boolean search (E e);

	/**
	 * Insert element e into the binary search tree. Return true if the element is inserted successfully
	 */
	public boolean insert (E e);

	/**
	 * Delete the specified element from the tree. Return true if the element is deleted successfully
	 */
	public boolean delete (E e);

	/* Inorder traversal from the root */
	public void inorder ();

	/* postorder traversal from the root */
	public void postorder ();

	/* preorder traversal from the root */
	public void preorder ();

	/* Get the number of nodes in the tree */
	public int getSize ();

	/** return true if the tree is empty */
	public boolean isEmpty ();
}