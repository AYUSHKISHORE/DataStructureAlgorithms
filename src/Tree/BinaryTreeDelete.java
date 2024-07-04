package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeDelete {
	public static void main(String[]args) {
		
		BinaryTreeDeleteFull bt = new BinaryTreeDeleteFull();
		
		BinaryTreeDeleteNode N1 = new BinaryTreeDeleteNode();
		BinaryTreeDeleteNode N2 = new BinaryTreeDeleteNode();
		BinaryTreeDeleteNode N3 = new BinaryTreeDeleteNode();
		BinaryTreeDeleteNode N4 = new BinaryTreeDeleteNode();
		BinaryTreeDeleteNode N5 = new BinaryTreeDeleteNode();
		N1.Value="N1";
		N2.Value="N2";
		N3.Value="N3";
		N4.Value="N4";
		N5.Value="N5";
		
		N1.left=N2;
		N1.right=N3;
		N2.left=N4;
		N2.right=N5;
		bt.root=N1;
		
		LevelOrderTraversal(bt.root);
		bt.root=null;
		LevelOrderTraversal(bt.root);
				
		
	}
	
	static void LevelOrderTraversal(BinaryTreeDeleteNode root) {
		System.out.println("Print Level Order");
		Queue<BinaryTreeDeleteNode> queue = new LinkedList<BinaryTreeDeleteNode>();
		if(root!=null) {
			queue.add(root);
		}
		
		
		while(!queue.isEmpty()) {
			BinaryTreeDeleteNode node=queue.poll();
			System.out.print(node.Value+" ");
			if(node.left!=null) {
				queue.add(node.left);
			}
			
			if(node.right!=null) {
				queue.add(node.right);
			}

		}
	}
}

class BinaryTreeDeleteNode{
	String Value;
	BinaryTreeDeleteNode left;
	BinaryTreeDeleteNode right;
}

class BinaryTreeDeleteFull{
	BinaryTreeDeleteNode root;
	BinaryTreeDeleteFull(){
		this.root = null;
	}
}
