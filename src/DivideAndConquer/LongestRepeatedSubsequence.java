package DivideAndConquer;

/*
 * Difference between LRS and LCS is in LRS i!=j
 * 
 * 
 * Method			| time complexity		| space complexity
 * 
 * compute			| O(2^n)				| O(n) stack memory
 * computeString	| O(2^n)				| O(n^2) stack memory + string preparation
 * 
 * time complexity explanation 
 * 	1) Look only at:
		how many recursive calls are made
		how the input size reduces
 * 
 * 	2)3 Recursive call
 		compute(i+1, j+1) - optional
		compute(i,   j+1) - compulsory
		compute(i+1, j) - compulsory
		
		branching factor = 2 (considering only compulsory)
		
	3) Depth 
		i: 0->n
		j: 0->n
		
		depth = n
		
	time complexity = branchingFactor ^ depth = 2^n

 * 
 */

public class LongestRepeatedSubsequence {

	public static void main(String[]args) {
		
		LRS lrs = new LRS();
		String s1 = "ATAKTKGGA";
		System.out.println(lrs.compute(s1)); // 4
		
		System.out.println(lrs.computeString(s1)); //ATKG
	}
}

class LRS{
	
	public int compute(String s1) {
		return this.compute(s1,0,0);
	}
	
	private int compute(String s1, int i, int j) {
		
		if(i>=s1.length()||j>=s1.length()) {
			return 0;
		}
		
		int c1=0;
		if(s1.charAt(i)==s1.charAt(j) && i!=j) {
			c1=1 + compute(s1,i+1,j+1);
		}
		int c2 = compute(s1,i,j+1);
		int c3 = compute(s1,i+1,j);
		
		return Math.max(c1, Math.max(c2, c3));
	}
	
	public String computeString(String s1) {
		return this.computeString(s1,0,0);
	}
	
	private String computeString(String s1, int i, int j) {
		
		if(i>=s1.length()||j>=s1.length()) {
			return "";
		}
		
		String c1="";
		if(s1.charAt(i)==s1.charAt(j) && i!=j) {
			c1=s1.charAt(i)+computeString(s1,i+1,j+1);
		}
		
		String c2 = computeString(s1,i+1,j);
		String c3 = computeString(s1,i,j+1);
		
		String maxIntermediate = (c2.length()>c3.length()?c2:c3);
		
		return c1.length()>maxIntermediate.length()?c1:maxIntermediate;
	}
}
