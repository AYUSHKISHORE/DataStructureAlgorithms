package Tree;

import java.util.*;

public class deleteNodeLinkedList {

	public static void main(String []args) {
		
		BinaryTreeDel Bt = new BinaryTreeDel();

		BinaryNodeDelete N1 = new BinaryNodeDelete();
		BinaryNodeDelete N2 = new BinaryNodeDelete();
		BinaryNodeDelete N3 = new BinaryNodeDelete();
		BinaryNodeDelete N4 = new BinaryNodeDelete();
		BinaryNodeDelete N5 = new BinaryNodeDelete();
		BinaryNodeDelete N6 = new BinaryNodeDelete();
		BinaryNodeDelete N7 = new BinaryNodeDelete();
		BinaryNodeDelete N8 = new BinaryNodeDelete();
		BinaryNodeDelete N9 = new BinaryNodeDelete();

		N1.Value="N1";
		N2.Value="N2";
		N3.Value="N3";
		N4.Value="N4";
		N5.Value="N5";
		N6.Value="N6";
		N7.Value="N7";
		N8.Value="N8";
		N9.Value="N9";
		
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
		DeleteNode(Bt.root,"N3");
		LevelOrderTraversal(Bt.root);

		
		
	}
	
	static void DeleteDeepestNode(BinaryNodeDelete node) {
		System.out.println("DeleteDeepestNode");
		Queue<BinaryNodeDelete> queue = new LinkedList<BinaryNodeDelete>();
		queue.add(node);
		BinaryNodeDelete previous=null;
		while(!queue.isEmpty()) {
			
			BinaryNodeDelete currNode = queue.remove();
			if(currNode.left==null) {
				previous.right=null;
				return;
			}else if(currNode.right==null) {
				currNode.left=null;
				return;
			}
			
			
			queue.add(currNode.left);
			queue.add(currNode.right);
			previous=currNode;
			
		}
		return;
	}
	
	
	static BinaryNodeDelete getDeepestNode(BinaryNodeDelete node) {
		System.out.println("\ngetDeepestNode");
		Queue<BinaryNodeDelete> queue = new LinkedList<BinaryNodeDelete>();
		queue.add(node);
		BinaryNodeDelete presentNode=null;
		while(!queue.isEmpty()) {
			presentNode = queue.remove();
			if(presentNode.left!=null) {
				queue.add(presentNode.left);
			}
			if(presentNode.right!=null) {
				queue.add(presentNode.right);
			}
		}
		return presentNode;
	}
	
	static void DeleteNode(BinaryNodeDelete node, String Value) {
		
		Queue<BinaryNodeDelete> queue = new LinkedList<BinaryNodeDelete>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			BinaryNodeDelete curr = queue.remove();
			if(curr.Value==Value) {
				BinaryNodeDelete DeepestNode = getDeepestNode(node);
				DeleteDeepestNode(node);
				curr.Value=DeepestNode.Value;
			}
			
			if(curr.left!=null) {
				queue.add(curr.left);
			}
			
			if(curr.right!=null) {
				queue.add(curr.right);
			}
		}
	}
	
	static void LevelOrderTraversal(BinaryNodeDelete node) {
		
		Queue<BinaryNodeDelete> queue = new LinkedList<BinaryNodeDelete>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			BinaryNodeDelete curr = queue.remove();
			System.out.print(curr.Value+" ");
			if(curr.left!=null) {
				queue.add(curr.left);
			}
			if(curr.right!=null) {
				queue.add(curr.right);
			}
		}
	}
}





class BinaryNodeDelete{
	String Value;
	BinaryNodeDelete left;
	BinaryNodeDelete right;
}

class BinaryTreeDel{
	BinaryNodeDelete root;
	BinaryTreeDel(){
		this.root = null;
	}
}