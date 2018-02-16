/*-
 ****************************************
 * Kyle Nguyen
 * 
 * COMP 282
 * Spring 2018
 * Dr. Wen-Chin Hsu
 * M/W 9:30 a.M - 10:45 A.M
 * 
 * Project 1: 
 * Phase 2 (Question 1)
 * Group2_Driver_Q1.java
 * 
 * The program displays the correct
 * results for postorder & inorder
 * traversal given a populated tree.
 ****************************************/
public class Group2_Driver_Q1 {

	public static void main (String[] args) {
		Group2_Q1<Integer> tree = new Group2_Q1<Integer>();
		Integer[] populateData = { 1, 2, 3, 4, 5, 6, 7 };
		tree.populate(populateData);
		/*-
		 * 				1
		 * 			  /   \
		 * 			 2     3
		 * 	        / \   / \
		 * 		   4   5 6   7
		 * 
		 * Inorder: 4 2 5 1 6 3 7
		 * Postorder: 4 5 2 6 7 3 1
		 */

		tree.inorder();
		System.out.println();
		System.out.println();
		tree.postorderNoRecursion3();
	}
}