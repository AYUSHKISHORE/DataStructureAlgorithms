package AVL;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

/* information - AVL tree is used to balance the BST (which helps in faster searching and deletion and insertion)
/*
 * TimeComplexity
 *  Creation - O(1)
 *  Searching - O(logN)
 *  Traversal - O(N)
 *  Insertion - O(logN)
 *  Deletion a Node - O(logN)
 *  Deletion of complete AVL - O(1)
 */

/*NOTE - In this code we will do the implementation of AVL Tree along with that we will perform :-
	creation - Same as BST
	Searching - Same as BST
	insertion 
	traversal - Same as BST
	deletion of node
	deletion of whole tree
*/
public class AVLTreeImplementation {
	public static void main(String[]args) {
		AVLTree avl = new AVLTree();
		avl.insert(10);
		avl.insert(9);
		avl.insert(8);
		avl.insert(7);
		avl.SearchNode(7);
		avl.SearchNode(100);
		avl.levelOrderTraverse(avl.root);
		avl.delete(7);
		avl.levelOrderTraverse(avl.root);
		avl.deleteWholeAVLTree();
		avl.levelOrderTraverse(avl.root);
		
		AVLTree avl2 = new AVLTree();
		avl2.insert(5);
		avl2.insert(10);
		avl2.insert(15);
		avl2.insert(20);
		avl2.levelOrderTraverse(avl2.root);
		avl2.delete(5);
		avl2.levelOrderTraverse(avl2.root);
		
	}
}


class BinaryNode{
	int value;
	int height;
	BinaryNode left;
	BinaryNode right;
	BinaryNode(){
		this.height=0;
	}
}

class AVLTree{
	
	BinaryNode root;
	AVLTree(){
		root = null;
	}
	
	/*
	 * 
	 * Search in AVL Tree
	 * 
	 */
	public void SearchNode(int value) {
		Search(root,value);
	}
	
	private void Search(BinaryNode node , int value) {
		if(node == null) {
			System.out.println("Value = "+value+" is not found!!!");
			return;
		}
		if(node.value==value) {
			System.out.println("Value = "+value+" is found!!! ");
			return;
		}
		if(node.value>value) {
			Search(node.left,value);
		}else if(node.value<value) {
			Search(node.right,value);
		}
		return;
		
	}
	
	/*
	 * 
	 * Traversal in AVL Tree
	 * 
	 */
	
	//Level order traversal
		void levelOrderTraverse(BinaryNode node) {	
			System.out.println();
			if(node==null) {
				System.out.println("AVL tree is null");
				return;
			}
			Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
			queue.add(node);
			while(!queue.isEmpty()) {
				BinaryNode curr = queue.poll();
				System.out.print(curr.value+" ");
				
				if(curr.left!=null) {
					queue.add(curr.left);
				}
				if(curr.right!=null) {
					queue.add(curr.right);
				}
			}
		}

	
	/*
	 * 
	 * Insertion in AVL Tree
	 * 
	 */
	//Insert a node in AVL tree 
	private BinaryNode insertNode(BinaryNode node, int value) {
		//if node is null means AVL tree is empty
		if(node==null) {
			BinaryNode newNode = new BinaryNode();
			newNode.value=value;
			newNode.height=1;
			return newNode;
		}
		if(value<=node.value) {
			node.left=insertNode(node.left,value);
		}
		if(value>node.value) {
			node.right=insertNode(node.right,value);
		}
		
		//Get the height of that node -> (1+ represent the node itself)
		node.height=1+Math.max(getHeight(node.left), getHeight(node.right));
		int balance = getBalance(node);
		
		//For LL condition do right rotation of disbalanceNode and return newRoot
		if(balance>1 && value<node.left.value) {
			return rotateRight(node);
		}
		
		//LR condition
		//For LR condition do left rotation of disbalanceNode.left and then right rotation of disbalanceNode and return newRoot
		if(balance>1 && value>node.left.value) {
			node.left=rotateLeft(node.left);
			return rotateRight(node);
		}
		
		//RR condition
		//For RR condition do left rotation of disbalanceNode and return newRoot
		if(balance<-1 && value>node.right.value) {
			return rotateLeft(node);
		}
		
		//RL condition
		//For RL condition do right rotation of disbalanceNode.right and the left rotation of disbalanceNode and return newRoot
		if(balance<-1 && value<node.right.value) {
			node.right=rotateRight(node.right);
			return rotateLeft(node);
		}
		return node;
	}
	void insert(int value) {
		root = insertNode(root,value);
	}
	
	private BinaryNode deleteNode(BinaryNode node, int value) {
		if(node == null) {
			System.out.println("value = "+value+" not found in AVL!!!");
			return node;
		}
		if(value<node.value) {
			node.left=deleteNode(node.left,value);
		}else if(value>node.value) {
			node.right=deleteNode(node.right,value);
		}else {
			if(node.left!=null && node.right!=null) {
				BinaryNode minRightNode = minRightNode(node.right);
				node.value=minRightNode.value;
				node.right=deleteNode(node.right,minRightNode.value);
			}
			else if(node.left!=null) {
				node=node.left;
			}
			else if(node.right!=null) {
				node=node.right;
			}else {
				node = null;
			}
		}
			
			//GETBALANCE
			//Note incase of deletion we check the parent node balance of the node which is to be deleted
			int balance = getBalance(node);
			//LL condition -> Right rotation
			//Inorder to find the condition we are checking node.left 
			if(balance>1 && getBalance(node.left)>=0) {
				return rotateRight(node);
			}
			
			//LR condition
			//Inorder to find the condition we are checking node.left 
			if(balance>1 && getBalance(node.left)<0) {
				node.left=rotateLeft(node.left);
				return rotateRight(node);
			}
			
			//RR condition -> Left Rotation
			//Inorder to find the condition we are checking node.right 
			if(balance<-1 && getBalance(node.right)<=0) {
				return rotateLeft(node);
			}
			
			//RL condition 
			//Inorder to find the condition we are checking node.right
			if(balance<-1 && getBalance(node.right)>0) {
				node.right=rotateRight(node.right);
				return rotateLeft(node);
			}
			
		return node;
		
	}
	
	
	void delete(int value) {
		root = deleteNode(root,value);
	}
	
	void deleteWholeAVLTree() {
		root = null;
	}
	
	
	
	
	BinaryNode minRightNode(BinaryNode root) {
		if(root.left == null) {
			return root;
		}
		return minRightNode(root.left);
	}
	
	
	
	int getHeight(BinaryNode node) {
		if(node==null) {
			return 0;
		}
		return node.height;
	}
	
	int getBalance(BinaryNode node) {
		if(node==null) {
			return 0;
		}
		return getHeight(node.left)-getHeight(node.right);
	}
	
	// mostly used in LL condition
	BinaryNode rotateRight(BinaryNode disbalancedNode) { 
		BinaryNode newRoot = disbalancedNode.left;
		disbalancedNode.left = disbalancedNode.left.right;
		newRoot.right=disbalancedNode;
		//update height of newRoot and disbalancedNode
		newRoot.height=1+Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
		disbalancedNode.height=1+Math.max(getHeight(disbalancedNode.left), getHeight(disbalancedNode.right));
		return newRoot;
	}
	
	//mostly  used in RR condition
	BinaryNode rotateLeft(BinaryNode disbalancedNode) {
		BinaryNode newRoot = disbalancedNode.right;
		disbalancedNode.right=disbalancedNode.right.left;
		newRoot.left=disbalancedNode;
		//update height of newRoot and disbalancedNode
		newRoot.height=1+Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
		disbalancedNode.height=1+Math.max(getHeight(disbalancedNode.left), getHeight(disbalancedNode.right));
		return newRoot;
	}
	
	
		
	
}