package Tree;


//POSTORDER TRAVERSAL USING  LINKEDLIST
//TIME COMPLEXITY - O(N)
//SPACE COMPLEXTIY - O(N) (AS RECURSION CAUSES INSERTION IN STACK MEMORY)
public class PostorderTraversalRecursion {

	public static void main(String []args) {
		BinaryTreePostOrderLL Bt = new BinaryTreePostOrderLL();
		
		BinaryNodePostorderTraversal N1 = new BinaryNodePostorderTraversal();
		N1.value="N1";
		
		BinaryNodePostorderTraversal N2 = new BinaryNodePostorderTraversal();
		N2.value="N2";
		
		BinaryNodePostorderTraversal N3 = new BinaryNodePostorderTraversal();
		N3.value="N3";
		
		BinaryNodePostorderTraversal N4 = new BinaryNodePostorderTraversal();
		N4.value="N4";
		
		BinaryNodePostorderTraversal N5 = new BinaryNodePostorderTraversal();
		N5.value="N5";
		
		BinaryNodePostorderTraversal N6 = new BinaryNodePostorderTraversal();
		N6.value="N6";
		
		BinaryNodePostorderTraversal N7 = new BinaryNodePostorderTraversal();
		N7.value="N7";
		
		BinaryNodePostorderTraversal N8 = new BinaryNodePostorderTraversal();
		N8.value="N8";
		
		BinaryNodePostorderTraversal N9 = new BinaryNodePostorderTraversal();
		N9.value="N9";
		
		N1.left = N2;
		N1.right = N3;
		
		N2.left = N4;
		N2.right = N5;
		
		N3.left = N6;
		N3.right = N7;
		
		N4.left = N8;
		N4.right = N9;
		
		Bt.root = N1;
		
		printPostorderTraversal(Bt.root); // N8 N9 N4 N5 N2 N6 N7 N3 N1
		
	}
	
	static void printPostorderTraversal(BinaryNodePostorderTraversal root) {
		if(root == null)
			return;
		
		printPostorderTraversal(root.left);
		printPostorderTraversal(root.right);
		System.out.print(root.value+" ");
			
	}
}

class BinaryNodePostorderTraversal{
	String value;
	BinaryNodePostorderTraversal left; // This is class type variable
	BinaryNodePostorderTraversal right; // // This is class type variable
	int height;
}


class BinaryTreePostOrderLL{
	BinaryNodePostorderTraversal root;
	BinaryTreePostOrderLL(){
		this.root = null;
	}
}