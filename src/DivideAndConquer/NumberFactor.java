package DivideAndConquer;

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

/*
 * Time Complexity - O(3^n)
 * 	Branching Factor - 3
 * 		int sub1 = FindWaysToGetN(n-1);
		int sub2 = FindWaysToGetN(n-3);
		int sub3 = FindWaysToGetN(n-4);
 * 
 *  Depth - n
 *  TimeComplexity - Branching Factor ^ Depth
 * 
 * Space Complexity - O(n)
 * 
 */

public class NumberFactor {
	public static void main(String[] args) {
		WaysToGetN nf = new WaysToGetN();
		System.out.println(nf.FindWaysToGetN(5));// O/P- 6
	}
}

class WaysToGetN{
	public  int FindWaysToGetN(int n) {
		if(n==0 || n==1 || n==2) {
			return 1;
		}
		if(n==3) {
			return 2;
		}
		
		int sub1 = FindWaysToGetN(n-1);
		int sub2 = FindWaysToGetN(n-3);
		int sub3 = FindWaysToGetN(n-4);
		
		return sub1+sub2+sub3;
	}
}

