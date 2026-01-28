package DynamicProgramming;

/*
 * Problem
 * 	- S1 & S2 are given strings
 *  - Find the length of the longest subsequence which is common in both string
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
 */

/*
 * 
 * Ways					| TimeComplexity			| Space Complexity		| Note
 * 
 * computeDPTopDown		| O(n x m)					| O(n x m)				| In DnC takes O(2^(n+m)) as time because  for 2 ways we go to n&m if anyone comes to end recursion closes where as in DP we just i->n and j->m as we are storing the other value
 * computeDPBottomUp	| O(n x m)					| O(n x m)				| In DnC takes O(2^(n+m)) as time because  for 2 ways we go to n&m if anyone comes to end recursion closes where as in DP we just i->n and j->m as we are storing the other value
 * getLRSStringWay1		| O(n x m x L)				| O(n x m x L)			| Time - O(nml) as every time we are concating a new string of l (worst case) and Space - O(nml) as each cell can store l of size data ;
 * getLRSStringWay2		| O(n^m) 					| O(n x m)	+ O(l)		|
 */


/*
  O/P
	5
	5
	EEPAT
	EEPAT
 */

public class LongestCommonSubsequence {
	public static void main(String []args) {
		
		String s1 = "ELEPHANT";
		String s2 = "EREPAT";
		
		LCS lcs = new LCS();
		System.out.println(lcs.computeTD(s1,s2));//5
		System.out.println(lcs.computeBU(s1, s2));//5
		
		System.out.println(lcs.getLCSStringTDWay1(s1,s2));//EEPAT
		System.out.println(lcs.getLCSStringTDWay2(s1,s2));//EEPAT
				
	}
}

class LCS{
	
	//TD
	public int computeTD(String s1, String s2) {
		Integer[][] dp = new Integer[s1.length()+1][s2.length()+1];
		return this.computeTD(s1, s2,0,0,dp);
	}
	
	private int computeTD(String s1, String s2, int i, int j ,Integer[][] dp) {
		
		if(dp[i][j]==null) {
			
			if(i>=s1.length() || j>=s2.length()) {
				dp[i][j]=0;
				return 0;
			}
			
			int way1=0;
			if(s1.charAt(i)==s2.charAt(j)) {
				way1= 1+computeTD(s1,s2,i+1,j+1,dp); 
			}
			int way2 = computeTD(s1,s2,i+1,j,dp);
			int way3 = computeTD(s1,s2,i,j+1,dp);
			
			dp[i][j]=Math.max(way1, Math.max(way2, way3));
		}
		return dp[i][j];
		
	}
	
	//BU
	public int computeBU(String s1, String s2) {
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		
		for(int i=1;i<=s1.length();i++) {
			for(int j=1;j<=s2.length();j++) {
				if(s1.charAt(i-1)==s2.charAt(j-1)) {
					dp[i][j]=1+dp[i-1][j-1];
				}else {
					dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}
	
	//LCS string way1
	public String getLCSStringTDWay1(String s1, String s2) {
		String[][] dp = new String[s1.length()+1][s2.length()+1];
		return this.getLCSStringTDWay1(s1,s2,dp,0,0);
	}
	
	private String getLCSStringTDWay1(String s1, String s2, String[][] dp, int i, int j) {
		
		if(dp[i][j]==null) {
			
			if(i>=s1.length()||j>=s2.length()) {
				return "";
			}
			
			String way1 = "";
			if(s1.charAt(i)==s2.charAt(j)) {
				way1=s1.charAt(i)+getLCSStringTDWay1(s1,s2,dp,i+1,j+1);
			}
			
			String way2 = getLCSStringTDWay1(s1,s2,dp,i+1,j);
			String way3 = getLCSStringTDWay1(s1,s2,dp,i,j+1);
			
			String max1=way2.length()>way3.length()?way2:way3;
			dp[i][j]=way1.length()>max1.length()?way1:max1;
		}
		return dp[i][j];
	}
	
	
	//LCS string way2
	public String getLCSStringTDWay2(String s1, String s2) {
		Integer[][] dp = new Integer[s1.length()+1][s2.length()+1];
		this.computeTD(s1, s2, 0, 0, dp);
		return buildString(s1,s2,dp,0,0);
		
	}
	
	private String buildString(String s1, String s2, Integer[][]dp,int i, int j) {
		
		int n=s1.length();
		int m=s2.length();
		StringBuilder sb = new StringBuilder();
		
		while(i<n && j<m) {
			if(s1.charAt(i)==s2.charAt(j)) {
				int curr = (dp[i][j] == null) ? 0 : dp[i][j];
				int diag = (dp[i + 1][j + 1] == null) ? 0 : dp[i + 1][j + 1];
				
				if(curr==1+diag) {
					sb.append(s1.charAt(i));
					i++;
					j++;
					continue;
				}
			}
			
			int down = dp[i+1][j]==null?0:dp[i+1][j];
			int right = dp[i][j+1]==null?0:dp[i][j+1];
			
			if(down>right)i++;
			else j++;
		}
		return sb.toString();
	}
}
