package Tree;

import java.util.Stack;

public class TreeTraversalUsingStack {
	public static void main(String []args) {
		
		BinaryTreeTraversal Bt = new BinaryTreeTraversal();
		
		BinaryTreeNode N1 = new BinaryTreeNode();
		N1.val="N1";
		BinaryTreeNode N2 = new BinaryTreeNode();
		N2.val="N2";
		BinaryTreeNode N3 = new BinaryTreeNode();
		N3.val="N3";
		BinaryTreeNode N4 = new BinaryTreeNode();
		N4.val="N4";
		BinaryTreeNode N5 = new BinaryTreeNode();
		N5.val="N5";
		BinaryTreeNode N6 = new BinaryTreeNode();
		N6.val="N6";
		BinaryTreeNode N7 = new BinaryTreeNode();
		N7.val="N7";
		BinaryTreeNode N8 = new BinaryTreeNode();
		N8.val="N8";
		BinaryTreeNode N9 = new BinaryTreeNode();
		N9.val="N9";
		
		Bt.root=N1;
		N1.left=N2;
		N1.right=N3;
		N2.left=N4;
		N2.right=N5;
		N3.left=N6;
		N3.right=N7;
		N4.left=N8;
		N4.right=N9;
		
		System.out.println();
		InorderTraversalUsingStack(Bt.root);
		System.out.println();
		PreorderTraversalUsingStack(Bt.root);
		System.out.println();
		PostorderTraversalUsingStack(Bt.root);
		System.out.println();
		
	}
	
	static void InorderTraversalUsingStack(BinaryTreeNode root) {
		
		System.out.println("Inorder Traversal");
		//left -> root -> right
		Stack<BinaryTreeNode> stack = new Stack<>();
		BinaryTreeNode current = root;
		
		while(current!=null || !stack.isEmpty()) {
			
			while(current!=null) {
				stack.push(current);
				current = current.left;
			}
			
			current = stack.pop();
			System.out.print(current.val+" ");
			current = current.right;
		}
	}
	
	static void PreorderTraversalUsingStack(BinaryTreeNode root) {
		//root -> left -> right
		System.out.println("Preorder Traversal");
		Stack<BinaryTreeNode> stack = new Stack<>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			BinaryTreeNode current = stack.pop();
			System.out.print(current.val+" ");
			
			if(current.right!=null) {
				stack.push(current.right);
				
			}
			if(current.left!=null) {
				stack.push(current.left);
			}
			
		}
	}
	
	static void PostorderTraversalUsingStack(BinaryTreeNode root) {
		//left -> right -> root
		System.out.println("Postorder Traversal");
		Stack<BinaryTreeNode> s1 = new Stack<>();
		Stack<BinaryTreeNode> s2 = new Stack<>();
		
		s1.push(root);
		
		while(!s1.isEmpty()) {
			BinaryTreeNode current = s1.pop();
			s2.push(current);
			
			if(current.left!=null) {
				s1.push(current.left);
			}
			if(current.right!=null) {
				s1.push(current.right);
			}
		}
		
		while(!s2.isEmpty()) {
			BinaryTreeNode node = s2.pop();
			System.out.print(node.val+" ");
		}
	}
	
	
	
}

class BinaryTreeNode{
	String val;
	BinaryTreeNode left;
	BinaryTreeNode right;
}

class BinaryTreeTraversal{
	BinaryTreeNode root;
	BinaryTreeTraversal(){
		this.root=null;
	}
}
