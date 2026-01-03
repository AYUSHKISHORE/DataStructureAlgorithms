package DivideAndConquer;
/*
 * Problem
 * 	- S1 & S2 are given strings
 *  - Find the length of the longes subsequence which is common in both string
 *  
 *  - subsequence : a sequence that can be driven from another by deleting some elements without changing the other
 *   	- eg 
 *   		ABCDE => ACE, ADE, ACD, ABCE, ABDE
 *   
 *   eg 
 *    S1 = ELEPHANT
 *    S2 = EREPAT
 *    O/P = EEPAT = 5
 *    
 *    
 *    Subproblems
 *    
 *    option1 = 1 + f(index_1+1,length_1 : index_2+1,length_2) //[Note f(Index_1, length_1 : Index_2, length_2)]
 *    option2 = 0 + f(index_1,length_1 : index_2+1,length_2 )
 *    option3 = 0 + f(index_1+1,length_1 : index_2,length_2)
 *    max(option1,option2,option3)
 * 
 */

public class LongestCommonSubsequence {
	public static void main(String []args) {
		LCS lcs = new LCS();
		System.out.println(lcs.compute("ELEPHANT","EREPAT"));
		System.out.println(lcs.getComputedString("ELEPHANT","EREPAT"));
	}
}

class LCS {
	private int compute(String s1, String s2, int i1, int i2) {
		if(i1==s1.length() || i2==s2.length()) {
			return 0;
		}
		int c1=0;
		if(s1.charAt(i1)==s2.charAt(i2)) {
			c1=1+compute(s1,s2,i1+1,i2+1);
		}
		int c2 = compute(s1,s2,i1+1,i2);
		int c3 = compute(s1,s2,i1,i2+1);
		
		return Math.max(c1, Math.max(c2, c3));
	}
	
	public int compute(String s1, String s2) {
		return this.compute(s1, s2, 0, 0);
	}
	
	
	private String getComputedString(String s1, String s2, int i1, int i2) {
		if(i1 == s1.length() || i2==s2.length()) {
			return "";
		}
		if(s1.charAt(i1)==s2.charAt(i2)) {
			return s1.charAt(i1)+getComputedString(s1,s2,i1+1,i2+1);
		}
		
		String option1 = getComputedString(s1,s2,i1+1,i2);
		String option2 = getComputedString(s1,s2,i1,i2+1);
		
		return option1.length()>option2.length()?option1:option2;
	}
	
	public String getComputedString(String s1, String s2) {
		return this.getComputedString(s1,s2,0,0);
	}
}
