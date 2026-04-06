package DynamicProgramming;

/*
 * 
 * Longest Palindromic Substring - The longest continuous part of a string which reads the same forward and backward.
 * Substring vs Subsequence (Core Difference)
 * 
 *  Substring
	Continuity = 	Must be continuous
	Order	= Must keep order
	Example from "abca" = "abc", "bca", "a"
	
	Subsequence
	Continuity = Can skip characters
	Order	= Must keep order
	Example from "abca" = "aca", "aba", "aa", "ac"
 * 
 * 
 * Ways					| TimeComplexity			| Space Complexity		| 
 * 
 * computeTopDown		| O(n^2)					| O(n^2)				| 
 * computeBottomUp		| O(n^2)					| O(n^2)				| 
 */

public class LongestPalindromicSubstring {

	public static void main(String []args) {
		
		String[] tests = {
	            "babad",      // "bab" or "aba"
	            "cbbd",       // "bb"
	            "a",          // "a"
	            "ac",         // "a" or "c"
	            "forgeeksskeegfor", // "geeksskeeg"
	            "abba",       // "abba"
	            "aaaabaaaa",   // "aaaabaaaa"
	            "abcd"// a or b or c or d
	        };
		
		LPSubstring lps = new LPSubstring();
		for(int i=0;i<tests.length;i++) {
			System.out.println("TopDown Compute: input = "+tests[i]+" | output = "+lps.computeTopDown(tests[i]));
			System.out.println("BottomUp Compute: input = "+tests[i]+" | output = "+lps.computeBottomUp(tests[i]));

		}
		
	}
}


class LPSubstring{
	
	public String computeTopDown(String str) {

		Boolean memo[][] = new Boolean[str.length()][str.length()];
		int bestLen = 1;
		int startIndex = 0;
		if(str==null || str.length()==0) {
			return "";
		}
		int n = str.length();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				int len = j - i + 1;
				if(bestLen<len && isPalindrome(i,j, memo,str)) {
					bestLen=len;
					startIndex = i;
				}
			}
		}
		return str.substring(startIndex,startIndex+bestLen);
		
	}
	
	public boolean isPalindrome(int i, int j, Boolean[][]memo, String str) {
		
		if(i>=j) {
			return true;
		}
		
		if(memo[i][j]!=null) {
			return memo[i][j];
		}
		
		if(str.charAt(i)!=str.charAt(j)) {
			memo[i][j]=false;
			return memo[i][j];
		}
		if(str.charAt(i)==str.charAt(j)) {
			memo[i][j]=isPalindrome(i+1,j-1,memo,str);
		}
		return memo[i][j];
	}
	
	
	public String computeBottomUp(String str) {
		
		if(str==null||str.length()==0) {
			return "";
		}
		int n = str.length();
		boolean dp[][] = new boolean[n][n];
		
		int bestStart = 0;
		int bestLen = 1;//inorder to support string of length = 1
		
		//Len = 1;
		for(int i=0;i<n;i++) {
			dp[i][i]=true;
		}
		
		
		//Len = 2
		for(int i=0; i<n-1;i++) {
			if(str.charAt(i)==str.charAt(i+1)) {
				dp[i][i+1]=true;
				bestStart = i;
				bestLen = 2;
			}
		}
		
		
		//Len = 3
		for(int len=3;len<=n;len++) {
			for(int i=0;i+len-1<n;i++) {
				int  j = i+len-1;
				
				if(str.charAt(i)==str.charAt(j) && dp[i+1][j-1]) {
					dp[i][j]=true;
					if(len>bestLen) {
						bestStart = i;
						bestLen = len;
					}
				}
			}
		}
		
		return str.substring(bestStart, bestStart+bestLen);
	}
}