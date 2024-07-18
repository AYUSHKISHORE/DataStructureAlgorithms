package BinarySearchTree;

import java.util.*;
import java.util.ArrayList;
import java.util.Queue;

public class BinarySearchTreeCreationTraversal {
	public static void main(String[]args) {
		BST bst = new BST();
		bst.insert(70);
		bst.insert(50);
		bst.insert(90);
		bst.insert(30);
		bst.insert(60);
		bst.insert(80);
		bst.insert(100);
		bst.insert(20);
		bst.insert(40);
		
		System.out.println("\nInorder Traversal");
		bst.inorderTraversal(bst.root);
		
		System.out.println("\nPreorder Traversal");
		bst.preOrderTraversal(bst.root);
		
		System.out.println("\nPostorder Traversal");
		bst.postOrderTraversal(bst.root);
		
		System.out.println("\nLevelOrder Traversal");
		bst.levelOrderTraversal(bst.root);
				
	}
}

class BinaryNode{
	int height;
	int value;
	BinaryNode left;
	BinaryNode right;
}

class BST{
	BinaryNode root;
	BST(){
		this.root=null;
	}
	
	//time-complexity - O(logn) -> because we traverse
	private BinaryNode insertNode(BinaryNode node,int value) {//Private access because we want it to access within same class
		if(node == null) {
			BinaryNode current = new BinaryNode();
			current.value=value;
			return current;
		}
		if(value<=node.value) {// (O(N/2)
			node.left=insertNode(node.left,value);
			return node;
		}
		if(value>node.value) {// (O(N/2)
			node.right=insertNode(node.right,value);
			return node;
		}
		return null;
	}
	//5 4 6
	public BinaryNode insert(int value) {
		root = insertNode(root, value);
		return root;
	}
	
	public void inorderTraversal(BinaryNode node) {
		//left root right
		if(node == null) {
			return;
		}
		inorderTraversal(node.left);
		System.out.print(node.value+" ");
		inorderTraversal(node.right);
	}
	
	public void preOrderTraversal(BinaryNode node) {
		//root left right
		if(node == null) {
			return;
		}
		System.out.print(node.value+" ");
		preOrderTraversal(node.left);
		preOrderTraversal(node.right);
	}
	
	public void postOrderTraversal(BinaryNode node) {
		//left right root
		if(node == null) {
			return;
		}
		postOrderTraversal(node.left);
		postOrderTraversal(node.right);
		System.out.print(node.value+" ");

	}
	
	public void levelOrderTraversal(BinaryNode node) {
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			BinaryNode newNode = queue.poll();
			System.out.print(newNode.value+" ");
			if(newNode.left!=null) {
				queue.add(newNode.left);
			}
			if(newNode.right!=null) {
				queue.add(newNode.right);
			}
			
		}
		
	}
}
