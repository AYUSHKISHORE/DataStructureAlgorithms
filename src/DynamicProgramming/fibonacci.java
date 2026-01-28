package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;

/* Logic				| TopDown		| BottomUp	
 * -------------------------------------------------
 * Time Complexity		| O(n)			| O(n)		
 * Space Complexity		| O(n)			| O(n)		
 * Uses Recursion		| Yes			| No
 */

/*
 	O/P
 	5
	5
 */

public class fibonacci {

	public static void main(String []args) {
		
		int n=6;
		
		//top-down memoization
		HashMap<Integer, Integer> hm = new HashMap<>();
		System.out.println(fiboTopDown(n,hm)); //5
		
		//bottom-up tabulation
		System.out.println(fiboBottomUp(n));//5
		
	}
	
	public static int fiboTopDown(int n , HashMap<Integer, Integer> hm) {
		
		if(n==1) {
			return 0;
		}
		if(n==2) {
			return 1;
		}
		if(!hm.containsKey(n)) {
			hm.put(n, fiboTopDown(n-1,hm)+fiboTopDown(n-2,hm));
		}
		return hm.get(n);
	}
	
	public static int fiboBottomUp(int n) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(0);
		arr.add(1);
		
		for(int i=2;i<=n-1;i++) {
			arr.add(arr.get(i-1)+arr.get(i-2));
		}
		return arr.get(n-1);
		
	}
}


