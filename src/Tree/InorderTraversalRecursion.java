package Tree;

//INORDER TRAVERSAL
//TIME COMPLEXITY - O(N)
//SPACE COMPLEXTIY - O(N) (AS RECURSION CAUSES INSERTION IN STACK MEMORY)
public class InorderTraversalRecursion {
	
	public static void main(String[]args) {
		BinaryTreeInorderLL Bt = new BinaryTreeInorderLL();
		BinaryNodeInorder N1 = new BinaryNodeInorder();
		N1.value="N1";
		
		BinaryNodeInorder N2 = new BinaryNodeInorder();
		N2.value = "N2";
		
		BinaryNodeInorder N3 = new BinaryNodeInorder();
		N3.value = "N3";
		
		
		BinaryNodeInorder N4 = new BinaryNodeInorder();
		N4.value = "N4";
		
		BinaryNodeInorder N5 = new BinaryNodeInorder();
		N5.value = "N5";
		
		BinaryNodeInorder N6 = new BinaryNodeInorder();
		N6.value = "N6";
		
		BinaryNodeInorder N7 = new BinaryNodeInorder();
		N7.value = "N7";
		
		BinaryNodeInorder N8 = new BinaryNodeInorder();
		N8.value = "N8";
		
		BinaryNodeInorder N9 = new BinaryNodeInorder();
		N9.value = "N9";
		
		N1.left=N2;
		N1.right=N3;
		N2.left=N4;
		N2.right=N5;
		N3.left=N6;
		N3.right=N7;
		N4.left=N8;
		N4.right=N9;
		Bt.root = N1;
		
		//N8 -> N4 -> N9 -> N2 -> N1 -> N6 -> N3 -> N7
		printInorderTraversalRecursion(Bt.root);
	}
	
	static void printInorderTraversalRecursion(BinaryNodeInorder root) {
		if(root == null) {
			return;
		}
		printInorderTraversalRecursion(root.left);
		System.out.print(root.value+" ");
		printInorderTraversalRecursion(root.right);

	}
	
}




 class BinaryNodeInorder{
	String value;
	BinaryNodeInorder left; // This is class type variable
	BinaryNodeInorder right; // This is class type variable
	int height;
}

 class BinaryTreeInorderLL{
	BinaryNodeInorder root;
	BinaryTreeInorderLL(){
		this.root=null;
	}
}