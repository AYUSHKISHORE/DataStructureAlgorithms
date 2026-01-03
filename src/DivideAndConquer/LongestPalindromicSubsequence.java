package DivideAndConquer;

/*
 * Problem
 * 	- S is a given string
 *  - Find the longest palindromic subsequence (LPS)
 *  - Subsequence: a sequence that be driven from another sequence by deleting some elements without changing the order
 *  
 *  
 *  Example
 *   S = "ELRMENMET"
 *   O/P = 5
 *   LPS = EMEME
 * 
 */

public class LongestPalindromicSubsequence {
	public static void main(String []args) {
		LPS lps = new LPS();
		System.out.println(lps.compute("ELRMENMET"));
		System.out.println(lps.getLPS("ELRMENMET"));
	}
}

class LPS{
	private int compute(String str, int i1, int j1) {
		if(i1>j1) {
			return 0;
		}
		if(i1==j1) {
			return 1;
		}
		int subsequence1 = 0;
		if(str.charAt(i1)==str.charAt(j1)) {
			subsequence1 = 2 + compute(str, i1+1, j1-1);
		}
		int subsequence2 = compute(str,i1+1,j1);
		int subsequence3 = compute(str,i1,j1-1);
		return Math.max(subsequence1, Math.max(subsequence2, subsequence3));
	}
	
	public int compute(String str) {
		return this.compute(str,0,str.length()-1);
	}
	
	
	private String getLPS(String str, int i1, int j1) {
		
		if(i1>j1) {
			return "";
		}
		
		if(i1==j1) {
			return String.valueOf(str.charAt(i1));
		}
		
		if(str.charAt(i1)==str.charAt(j1)) {
			String mid = getLPS(str,i1+1,j1-1);
			return str.charAt(i1)+mid+str.charAt(j1);
		}
		
		String s1 = getLPS(str,i1+1,j1);
		String s2 = getLPS(str,i1,j1-1);
		
		return s1.length()>s2.length()?s1:s2;
	}
	
	public String getLPS(String str) {
		return this.getLPS(str,0,str.length()-1);
	}
	
}
