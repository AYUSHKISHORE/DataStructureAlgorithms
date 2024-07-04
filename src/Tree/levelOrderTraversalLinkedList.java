package Tree;

import java.util.*;

//LEVEL ORDER TRAVERSAL USING LINKEDLIST (i.e QUEUE)
//TIME COMPLEXITY - O(N)
//SPACE COMPLEXITY - O(N)
public class levelOrderTraversalLinkedList {
	public static void main(String []args) {
		BinaryTreeLevelOrder Bt = new BinaryTreeLevelOrder();
		
		BinaryNodeLevelOrder N1 = new BinaryNodeLevelOrder();
		N1.Val = "N1";
		
		BinaryNodeLevelOrder N2 = new BinaryNodeLevelOrder();
		N2.Val = "N2";
		
		BinaryNodeLevelOrder N3 = new BinaryNodeLevelOrder();
		N3.Val = "N3";
		
		BinaryNodeLevelOrder N4 = new BinaryNodeLevelOrder();
		N4.Val = "N4";
		
		
		BinaryNodeLevelOrder N5 = new BinaryNodeLevelOrder();
		N5.Val = "N5";
		
		
		BinaryNodeLevelOrder N6 = new BinaryNodeLevelOrder();
		N6.Val = "N6";
		
		BinaryNodeLevelOrder N7 = new BinaryNodeLevelOrder();
		N7.Val = "N7";
		
		BinaryNodeLevelOrder N8 = new BinaryNodeLevelOrder();
		N8.Val = "N8";
		
		BinaryNodeLevelOrder N9 = new BinaryNodeLevelOrder();
		N9.Val = "N9";
		
		N1.left=N2;
		N1.right=N3;
		
		N2.left=N4;
		N2.right=N5;
		
		N3.left=N6;
		N3.right=N7;
		
		N4.left=N8;
		N4.right=N9;
		
		Bt.root=N1;
		
		LevelOrderTraversal(Bt.root);
	}
	
	static void LevelOrderTraversal(BinaryNodeLevelOrder root) {
		System.out.println("Print Level Order");
		Queue<BinaryNodeLevelOrder> queue = new LinkedList<BinaryNodeLevelOrder>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			BinaryNodeLevelOrder node=queue.poll();
			System.out.print(node.Val+" ");
			if(node.left!=null) {
				queue.add(node.left);
			}
			
			if(node.right!=null) {
				queue.add(node.right);
			}

		}
	}
}

class BinaryNodeLevelOrder{
	String Val;
	BinaryNodeLevelOrder left;
	BinaryNodeLevelOrder right;
	int height;
}

class BinaryTreeLevelOrder{
	BinaryNodeLevelOrder root;
	BinaryTreeLevelOrder(){
		this.root=null;
	}
}
