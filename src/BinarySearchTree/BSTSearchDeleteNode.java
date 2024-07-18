package BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;


//SPACE COMPLEXITY - O(LogN) and TIME Complexity - O(Logn)
public class BSTSearchDeleteNode {
	public static void main(String []args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(70);
		bst.insert(50);
		bst.insert(90);
		bst.insert(30);
		bst.insert(60);
		bst.insert(80);
		bst.insert(100);
		bst.insert(20);
		bst.insert(40);
		bst.insert(95);
		bst.insert(105);
		bst.insert(97);
		bst.insert(110);
		
		bst.Search(bst.root, 40);
		bst.Search(bst.root, 100);
		bst.Search(bst.root, 500);
		
		System.out.println("\nBefore Deletion of node from BST LevelOrder Traversal");
		bst.levelOrderTraversal(bst.root);
		System.out.println();

		bst.delete(bst.root, 90);
		System.out.println("\nAfter Deletion of 90 from BST LevelOrder Traversal");
		bst.levelOrderTraversal(bst.root);
		
		System.out.println("\nBefore Deletion of whole BST LevelOrder Traversal");
		bst.levelOrderTraversal(bst.root);
		bst.deleteWholeBST();
		System.out.println("\nAfter Deletion of whole BST LevelOrder Traversal");
		bst.levelOrderTraversal(bst.root);


		

		
	}
}


class BinarySearchNode{
	int height;
	int Value;
	BinarySearchNode left;
	BinarySearchNode right;
}


class BinarySearchTree{
	BinarySearchNode root;
	int height=0;
	
	BinarySearchTree(){
		root = null;
	}
	
	private BinarySearchNode insertNode(BinarySearchNode node , int value) {
		if(node == null) {
			BinarySearchNode current = new BinarySearchNode();
			current.Value = value;
			System.out.println("Successfully Inserted in BST "+value);
			return current;
		}
		
		if(value<=node.Value) {
			node.left = insertNode(node.left,value);
			return node;
		}
		
		if(value>node.Value) {
			node.right = insertNode(node.right,value);
			return node;
		}
		return null;
	}
	
	void insert(int value) {
		root = insertNode(root,value);
	}
	
	private void SearchNode(BinarySearchNode node,int value, int height) {
		if(node == null) {
			System.out.println("\nvalue = "+ value +" not found");
			return;
		}
		if(node.Value==value) {
			System.out.print("\nvalue = "+value+" is found at height of "+height);
			return;
		}
		if(value<=node.Value) {
			height++;
			SearchNode(node.left,value,height);
		}
		
		if(value>node.Value) {
			height++;
			SearchNode(node.right,value,height);
		}
	}
	
	void Search(BinarySearchNode node , int value) {
		SearchNode(node,value, height=0);
	}
	
	/*Deletion we 3 things 
		1. If it is a leaf node just remove the node
		2. If the node has 1 child then replace with child
		3. If it has 2 children then remove the node and replace with the smallest node in the right of that node.
	*/
	
	public BinarySearchNode delete(BinarySearchNode node, int value) { // TIME and SPACE - O(LOGN)
		
		if(node == null) {
			System.out.println("value = "+value+" not found in BST");
			return null;
		}
		if(value<node.Value) {
			node.left = delete(node.left,value);
		}else if(value>node.Value) {
			node.right = delete(node.right,value);
		}else {
			if(node.left!=null && node.right!=null) {
				BinarySearchNode temp = node;// create temp node
				BinarySearchNode minRightNode = findMinRightNode(temp.right); // find min right node of that specific node
				node.Value=minRightNode.Value; // replace the value
				node.right = delete(node.right,minRightNode.Value); // delete the minRightValue
				
			}else if(node.left!=null) { // when there single child  of node and it is a left child then replace the node with the child
				node = node.left;
			}else if (node.right!=null) { // when there single child  of node and it is a right child then replace the node with the child
				node = node.right;
			}else { // leaf node removal
				node = null;
			}
		}
		return node;
	}
	
	private BinarySearchNode findMinRightNode(BinarySearchNode node) {
		if(node == null) {
			return null;
		}else if(node.left==null){
			return node;
		}else {
		}
			return findMinRightNode(node.left);
	}
	public void levelOrderTraversal(BinarySearchNode node) {
		if(node == null) {
			System.out.println("BST is empty");
			return;
		}
		Queue<BinarySearchNode> queue = new LinkedList<BinarySearchNode>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			BinarySearchNode newNode = queue.poll();
			System.out.print(newNode.Value+" ");
			if(newNode.left!=null) {
				queue.add(newNode.left);
			}
			if(newNode.right!=null) {
				queue.add(newNode.right);
			}	
		}	
	}
		
	
	//to delete complete binary search tree
	void deleteWholeBST() { // TIME and SPACE - O(1)
		root = null;
		System.out.println("\nBST is deleted");
	}
}

