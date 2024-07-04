package Tree;

import java.util.*;

public class InsertNodeTreeUsingLinkedList {
	public static void main(String []args) {
		
		BinaryTreeInsert Bt = new BinaryTreeInsert();
		BinaryNodeInsert N1 = new BinaryNodeInsert();
		BinaryNodeInsert N2 = new BinaryNodeInsert();
		BinaryNodeInsert N3 = new BinaryNodeInsert();
		BinaryNodeInsert N4 = new BinaryNodeInsert();
		BinaryNodeInsert N5 = new BinaryNodeInsert();
		BinaryNodeInsert N6 = new BinaryNodeInsert();
		BinaryNodeInsert N7 = new BinaryNodeInsert();
		BinaryNodeInsert N8 = new BinaryNodeInsert();
		BinaryNodeInsert N9 = new BinaryNodeInsert();
		N1.Value="N1";
		N2.Value="N2";
		N3.Value="N3";
		N4.Value="N4";
		N5.Value="N5";
		N6.Value="N6";
		N7.Value="N7";
		N8.Value="N8";
		N9.Value="N9";
		
		N1.left = N2;
		N1.right = N3;
		
		N2.left=N4;
		N2.right=N5;
		
		N3.left=N6;
		N3.right=N7;
		
		N4.left=N8;
		N4.right=N9;
		
		Bt.root=N1;
		
		BinaryNodeInsert newNode = new BinaryNodeInsert();
		newNode.Value="N10";
		System.out.println("Before Insertion");
		LevelOrderTraversal(Bt.root);
		InsertNode(Bt.root, newNode);
		System.out.println("After Insertion");
		LevelOrderTraversal(Bt.root);
	}
	
	static void LevelOrderTraversal(BinaryNodeInsert root) {
		System.out.println("Print Level Order");
		Queue<BinaryNodeInsert> queue = new LinkedList<BinaryNodeInsert>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			BinaryNodeInsert node=queue.poll();
			System.out.print(node.Value+" ");
			if(node.left!=null) {
				queue.add(node.left);
			}
			
			if(node.right!=null) {
				queue.add(node.right);
			}

		}
	}
	
	static void InsertNode(BinaryNodeInsert root, BinaryNodeInsert newNode) {
		Queue<BinaryNodeInsert> queue = new LinkedList<BinaryNodeInsert>();
		queue.add(root);
		while(!queue.isEmpty()) {
			BinaryNodeInsert currNode = queue.poll();
			if(currNode.left == null) {
				currNode.left=newNode;
				return;
			}
			
			if(currNode.right == null) {
				currNode.right=newNode;
				return;
			}
			
			if(currNode.left!=null) {
				queue.add(currNode.left);
			}
			if(currNode.right!=null) {
				queue.add(currNode.right);
			}
		}
	}

	
}


class BinaryNodeInsert{
	BinaryNodeInsert left;
	BinaryNodeInsert right;
	String Value;
}

class BinaryTreeInsert{
	BinaryNodeInsert root;
	BinaryTreeInsert(){
		this.root=null;
	}
}