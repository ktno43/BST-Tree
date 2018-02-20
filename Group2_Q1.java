
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
 * Group2_Q1.java
 * 
 * The program works as expected and
 * follows the specification of using
 * a single stack and implementing a
 * method where postorder & inorder
 * traversal are possible without using 
 * recursion.
 ****************************************/
import java.util.Stack;

public class Group2_Q1<E extends Comparable<E>> {
	Node<E> root;

	public static class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
		E element;
		Node<E> left;
		Node<E> right;

		public Node(E e) {
			this.element = e;
		}

		@Override
		public int compareTo(Node<E> o) {
			return this.element.compareTo(o.element);
		}
	}

	/*******************************
	 * Populate for testing
	 *******************************/
	public void populate(E[] e) {
		this.root = new Node<E>(e[0]);
		this.root.left = new Node<E>(e[1]);
		this.root.right = new Node<E>(e[2]);
		this.root.left.left = new Node<E>(e[3]);
		this.root.left.right = new Node<E>(e[4]);
		this.root.right.left = new Node<E>(e[5]);
		this.root.right.right = new Node<E>(e[6]);
	}

	/*******************************
	 * Inorder Version 1
	 *******************************/
	public void inorder() {
		if (this.root == null)
			return;

		Stack<Node<E>> stacky = new Stack<Node<E>>();
		Node<E> current = this.root;

		while (current != null) {
			stacky.push(current);
			current = current.left;
		}

		while (stacky.size() > 0) {
			current = stacky.pop();
			System.out.print(current.element + " ");

			if (current.right != null) {
				current = current.right;

				while (current != null) {
					stacky.push(current);
					current = current.left;
				}
			}
		}
	}

	/*******************************
	 * Inorder Version 2
	 *******************************/
	public void inorder2() {
		Stack<Node<E>> stacky = new Stack<Node<E>>();
		Node<E> current = this.root;

		while (stacky.size() > 0 || current != null) {
			if (current != null) {
				stacky.push(current);
				current = current.left;
			}

			else {
				Node<E> popVal = stacky.pop();
				System.out.print(popVal.element + " ");

				current = popVal.right;
			}
		}
	}

	/*******************************
	 * Postorder Version 2, 2 stacks
	 *******************************/
	public void postorderNoRecursion2() {
		Stack<Node<E>> stack = new Stack<Node<E>>();
		stack.push(this.root);
		Stack<E> out = new Stack<E>();

		while (stack.size() > 0) {
			Node<E> current = stack.pop();
			out.push(current.element);

			if (current.left != null)
				stack.push(current.left);

			if (current.right != null)
				stack.push(current.right);
		}

		while (out.size() > 0) {
			System.out.print(out.pop() + " ");
		}
	}

	/*******************************
	 * Postorder Version 3, 1 stack
	 *******************************/
	public void postorderNoRecursion3() {
		if (this.root == null) // No root, nothing to traverse
			return;

		Stack<Node<E>> stack = new Stack<Node<E>>(); // Stack for postorder traversal
		Node<E> current = this.root; // Starting position

		while (current != null || stack.size() > 0) { // While there are elements in the stack or if current is not null keep going
														 // Once this while loop fails all the elements have been printed!
			if (current != null) { // If the current element has a value, push it onto the stack
				stack.push(current);
				current = current.left; // Continue traversing down left (postorder: left -> right -> middle)
			}

			else {
				Node<E> temp = stack.peek().right; // Current is null, check to see if there is a right-subtree of the topmost element of the stack

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
}