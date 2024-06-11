package Tree;

class BinaryTree {
	public static void main(String[]args) {
		BinaryTreeLL Bt = new BinaryTreeLL();
		BinaryNode N1 = new BinaryNode();
		N1.value = "N1";
		BinaryNode N2 = new BinaryNode();
		N2.value = "N2";
		BinaryNode N3 = new BinaryNode();
		N3.value = "N3";
		BinaryNode N4 = new BinaryNode();
		N4.value = "N4";
		BinaryNode N5 = new BinaryNode();
		N5.value = "N5";
		BinaryNode N6 = new BinaryNode();
		N6.value = "N6";
		BinaryNode N7 = new BinaryNode();
		N7.value = "N7";
		BinaryNode N8 = new BinaryNode();
		N8.value = "N8";
		BinaryNode N9 = new BinaryNode();
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
		printTreeHelper(Bt.root,0);
		
	}
	
	 public static void printTreeHelper(BinaryNode node, int indent) {
		 if (node == null)
	            return;

	        // Print right subtree
	        if (node.right != null) {
	            printTreeHelper(node.right, indent + 4);
	        }

	        // Print current node with indentation
	        for (int i = 0; i < indent; i++) {
	        		System.out.print(" ");
	            
	        }
	        System.out.println(node.value);

	        // Print left subtree
	        if (node.left != null) {
	            printTreeHelper(node.left, indent + 4);
	        }
	 }
}

 class BinaryNode{
	public String value;
	public BinaryNode left;
	public BinaryNode right;
	public int height;
}


  class BinaryTreeLL{
	BinaryNode root;
	public BinaryTreeLL() {
		this.root = null;
	}
}




			