package DynamicProgramming;

import java.util.HashMap;

//Question) - Given a number N , find the nos of ways to express N as sum of 1,3,4
/*
* N=0, {} -> 1 way
* N=1, {1} -> 1 way
* N=2, {1,1} -> 1 way
* N=3, {1,1,1},{3} -> 2 way
* N=4, {1,1,1,1},{3,1},{1,3},{4}
* 
* N=5 could be written way
* 	F(5) = F(5-1)+1 = F(5-3) + 3 = F(5-4) + 4
*  F(5) = F(4)+1 = F(2) + 3 = F(1) + 4 
*  F(4)+1 = {1,1,1,1,1},{3,1,1},{1,3,1},{4,1} 
*  F(2)+3 = {1,1,3}
*  F(1)+4 = {1,4}
*  Total = {1,1,1,1,1},{3,1,1},{1,3,1},{4,1},{1,1,3},{1,4}
*  
*/

/* Logic				| TopDown		| BottomUp	
 * -------------------------------------------------
 * Time Complexity		| O(n)			| O(n)		
 * Space Complexity		| O(n)			| O(n)		
 * Uses Recursion		| Yes			| No
 */


/*
 	O/P
 		6
 		6
 */

public class NumberFactor {

	public static void main(String []args) {
		
		int n = 5;
		
		//TopDown memoization
		HashMap<Integer, Integer> hm = new HashMap<>();
		System.out.println(getNumFactorTopDown(n,hm)); //6
		
		
		//BottomUp tabulation
		System.out.println(getNumFactorBottomUp(n));//6
	}
	
	public static int getNumFactorTopDown(int n , HashMap<Integer, Integer> hm) {
		
		if(n==0||n==1||n==2) {
			return 1;
		}
		if(n==3) {
			return 2;
		}
		if(!hm.containsKey(n)) {
			hm.put(n, getNumFactorTopDown(n-1,hm)+getNumFactorTopDown(n-3,hm)+getNumFactorTopDown(n-4,hm));
		}
		return hm.get(n);
	}
	
	public static int getNumFactorBottomUp(int n) {
		int a[] = new int[n+1];
		a[0]=a[1]=a[2]=1;
		a[3]=2;
		
		for(int i=4;i<=n;i++) {
			a[i]=a[i-1]+a[i-3]+a[i-4];
		}
		return a[n];
	}
	
	
	
}


