package DynamicProgramming;

/*
 * Problem
 *  - S1 & S2 are given strings
 *  - Convert S2 to S1 using delete, insert & replace operation
 *  - Find Min count of edit operation
 *  
 *  
 *  Example1)
 *  - S1 = "catch"
 *  - S2 = "carch"
 *  
 *  O/P = 1 {explanation: Replace r with t}
 *  
 *  
 *  
 *  S1 = table
 *  S2 = tgable
 *  deleteOperation =  f(2,3)
 *  
 *  S1 = table
 *  S2 = tble
 *  insertOperation = f(3,2)
 *  
 *  S1 = table
 *  S2 = tcble 
 *  replaceOperation = f(3,3)
 *  
 */

/*
 * Approach					Time		Space
 * Top-Down + memo			O(n·m)		O(n·m) (plus stack O(n+m))
 * Bottom-Up reverse		O(n·m)		O(n·m)
 * Bottom-Up forward		O(n·m)		O(n·m)
 * 
 * 
 */

/*
 O/P
	TD approach 3
	BU approach reverse 3
	BU approach forward 3
 * 
 */


public class ConvertString {
	
	public static void main(String []args) {
		
		String s1 = "table";
		String s2 = "tbres";
		
		ConvertOneStringToAnother ct = new ConvertOneStringToAnother();
		System.out.println("TD approach "+ct.FindMinOprTD(s1,s2)); // 3
		System.out.println("BU approach reverse "+ct.FindMinOprBUReverse(s1,s2)); // 3
		System.out.println("BU approach forward "+ct.FindMinOprBUForward(s1, s2));// 3
		
	}
}

class ConvertOneStringToAnother{
	
	public int FindMinOprTD(String s1, String s2) {
		
		Integer dp[][] = new Integer[s1.length()+1][s2.length()+1];
		return this.FindMinOprTD(s1,s2,dp,0,0);
	}
	
	private int FindMinOprTD(String s1, String s2, Integer[][] dp, int i, int j) {
		
		if(dp[i][j]==null) {
			
			if(i==s1.length()) {
				dp[i][j]=s2.length()-j;
			}
			
			else if(j==s2.length()) {
				dp[i][j]=s1.length()-i;
			}
			
			else if(s1.charAt(i)==s2.charAt(j)) {
				dp[i][j]=FindMinOprTD(s1,s2,dp,i+1,j+1);
			}else {
				dp[i][j]=1+Math.min(FindMinOprTD(s1,s2,dp,i+1,j)
						,Math.min(FindMinOprTD(s1,s2,dp,i,j+1),FindMinOprTD(s1,s2,dp,i+1,j+1)));
			}
			
			
		}
		return dp[i][j];
	}
	
	
	public int FindMinOprBUReverse(String s1, String s2) {
		return this.FindMinOprBUReverse(s1, s2, s1.length(),s2.length());
	}
	
	private int FindMinOprBUReverse(String s1, String s2, int i, int j) {
		int a[][] = new int[s1.length()+1][s2.length()+1];
		
		for(int i1=s1.length();i1>=0;i1--) {
			a[i1][s2.length()]=s1.length()-i1;
		}
		
		for(int j1=s2.length();j1>=0;j1--) {
			a[s1.length()][j1]=s2.length()-j1;
		}
		
		
		for(int i1=s1.length();i1>0;i1--) {
			for(int j1=s2.length();j1>0;j1--) {
				if(s1.charAt(i1-1)==s2.charAt(j1-1)) {
					a[i1-1][j1-1]=a[i1][j1];
				}else {
					a[i1-1][j1-1]=1+Math.min(a[i1][j1-1],Math.min(a[i1-1][j1],a[i1][j1]));
				}
			}
		}
		return a[0][0];
	}
	
	public int FindMinOprBUForward(String s1, String s2) {
		return this.FindMinOprBUForward(s1, s2,0,0);
	}
	
	private int FindMinOprBUForward(String s1, String s2, int i, int j) {
		
		int dp[][] = new int[s1.length()+1][s2.length()+1];
		
		for(int i1=0;i1<=s1.length();i1++) {
			dp[i1][0]=i1;
		}
		
		for(int j1=0;j1<=s2.length();j1++) {
			dp[0][j1]=j1;
		}
		
		for(int i1=1;i1<=s1.length();i1++) {
			for(int j1=1;j1<=s2.length();j1++) {
				if(s1.charAt(i1-1)==s2.charAt(j1-1)) {
					dp[i1][j1]=dp[i1-1][j1-1];
				}else {
					dp[i1][j1]=1+Math.min(dp[i1-1][j1], Math.min(dp[i1][j1-1], dp[i1-1][j1-1]));
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}
}
