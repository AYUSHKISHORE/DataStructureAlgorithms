package DivideAndConquer;

/*
 * Problem 
  * We need count zeros in given array
 * -> zeros can be anywhere
 * Eg [0,1,0,0,1,0]
 * 
 * 
 * Time complexity = O(n) (as we are breaking it sub part).
 * Space complexity = O(logn) 
 * 		n → n/2 → n/4 → n/8 → ... → 1 (as due to recursion stack it breaking into half)
 */

public class CountZero2 {

	public static void main(String []args) {
		
		int[] array = {0,1,0,0,1,0};
		CtZero ct = new CtZero();
		System.out.println("Total Zeros = "+ct.compute(array,0,array.length-1));
	}
	
}

class CtZero{
	public int compute(int[] array, int left, int right) {
		
		if(left>right) {
			return 0;
		}
		
		if(left==right) {
			return array[left]==0 ? 1 : 0;
		}
		
		int mid = left + (right - left) / 2;
		
		int option1 = compute(array,left,mid);
		int option2 = compute(array,mid+1,right);
		
		return option1 + option2;
	}
}
