package Tree;


//PREORDER TRAVERSAL USING LINKED LIST
//TIME COMPLEXITY - O(N)
//SPACE COMPLEXTIY - O(N) (AS RECURSION CAUSES INSERTION IN STACK MEMORY)
//This is recursion code
public class PreorderTraversal {

	public static void main(String []args) {
		BinaryTreePreorderLL Bt = new BinaryTreePreorderLL();
		BinaryNodePreorder N1 = new BinaryNodePreorder();
		N1.value = "N1";
		Bt.root = N1;
		
		
		BinaryNodePreorder N2 = new BinaryNodePreorder();
		N2.value = "N2";
		
		BinaryNodePreorder N3 = new BinaryNodePreorder();
		N3.value = "N3";
		
		BinaryNodePreorder N4 = new BinaryNodePreorder();
		N4.value = "N4";
		
		BinaryNodePreorder N5 = new BinaryNodePreorder();
		N5.value = "N5";
		
		BinaryNodePreorder N6 = new BinaryNodePreorder();
		N6.value = "N6";
		
		BinaryNodePreorder N7 = new BinaryNodePreorder();
		N7.value = "N7";
		
		BinaryNodePreorder N8 = new BinaryNodePreorder();
		N8.value="N8";
		
		BinaryNodePreorder N9 = new BinaryNodePreorder();
		N9.value = "N9";
		
		
		N1.left=N2;
		N1.right=N3;
		
		N2.left=N4;
		N2.right=N5;
		
		N3.left=N6;
		N3.right=N7;
		
		
		N4.left=N8;
		N4.right=N9;
		PreorderTraversal pt = new PreorderTraversal();
		pt.preorderTraversal(N1);
		
	}
	
	void preorderTraversal(BinaryNodePreorder Node) {
		if(Node == null) {
			return;
		}
		System.out.print(Node.value+" ");
		preorderTraversal(Node.left);
		preorderTraversal(Node.right);
	}
	
}




class BinaryNodePreorder{
	String value;
	BinaryNodePreorder left;
	BinaryNodePreorder right;
	int height;
}

class BinaryTreePreorderLL{
	BinaryNodePreorder root;
	BinaryTreePreorderLL(){
		this.root= null;
	}
}
