package Tree;

import java.util.*;

//SEARCH A VALUE IN TREE USING LEVEL ORDER
//TIME COMPLEXITY - O(N)
//SPACE COMPLEXITY - O(N)
public class SearchLevelOrderLinkedList {
	public static void main(String []args) {
		BinaryTreeLevelOrderSearch Bt = new BinaryTreeLevelOrderSearch();
		BinaryNodeLevelOrderSearch N1 = new BinaryNodeLevelOrderSearch();
		BinaryNodeLevelOrderSearch N2 = new BinaryNodeLevelOrderSearch();
		BinaryNodeLevelOrderSearch N3 = new BinaryNodeLevelOrderSearch();
		BinaryNodeLevelOrderSearch N4 = new BinaryNodeLevelOrderSearch();
		BinaryNodeLevelOrderSearch N5 = new BinaryNodeLevelOrderSearch();
		BinaryNodeLevelOrderSearch N6 = new BinaryNodeLevelOrderSearch();
		BinaryNodeLevelOrderSearch N7 = new BinaryNodeLevelOrderSearch();
		BinaryNodeLevelOrderSearch N8 = new BinaryNodeLevelOrderSearch();
		BinaryNodeLevelOrderSearch N9 = new BinaryNodeLevelOrderSearch();
		
		
		N1.Val="N1";
		N2.Val="N2";
		N3.Val="N3";
		N4.Val="N4";
		N5.Val="N5";
		N6.Val="N6";
		N7.Val="N7";
		N8.Val="N8";
		N9.Val="N9";

		
		N1.left=N2;
		N1.right=N3;
		
		N2.left=N4;
		N2.right=N5;
		
		N3.left=N6;
		N3.right=N7;
		
		N4.left=N8;
		N4.right=N9;
		Bt.root=N1;
		System.out.println("Value N5 found ? "+SearchValue("N5",Bt.root));
		System.out.println("Value N10 found ? "+SearchValue("N10",Bt.root));

	}
	
	static boolean SearchValue(String Value , BinaryNodeLevelOrderSearch node) {
		Queue<BinaryNodeLevelOrderSearch> queue = new LinkedList<BinaryNodeLevelOrderSearch>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			BinaryNodeLevelOrderSearch newNode = queue.poll();
			if(newNode.Val == Value) {
				return true;
			}
			if(newNode.left!=null) {
				queue.add(newNode.left);
			}
			
			if(newNode.right!=null) {
				queue.add(newNode.right);
			}
		}
		return false;
		
	}
}

class BinaryNodeLevelOrderSearch{
	String Val;
	BinaryNodeLevelOrderSearch left;
	BinaryNodeLevelOrderSearch right;
}

class BinaryTreeLevelOrderSearch{
	BinaryNodeLevelOrderSearch root;
	BinaryTreeLevelOrderSearch(){
		this.root = null;
	}
}
