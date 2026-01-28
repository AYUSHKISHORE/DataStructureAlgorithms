package DynamicProgramming;

/*
 * 
 * Ways					| TimeComplexity			| Space Complexity		| Note
 * 
 * computeDPTopDown		| O(n^2)					| O(n^2)				| In DnC takes O(3^n) as time because  for 3 ways we go to n where as in DP we just i->n and j->n as we are storing the other value
 * computeDPBottomUp	| O(n^2)					| O(n^2)				| In DnC takes O(3^n) as time because  for 3 ways we go to n where as in DP we just i->n and j->n as we are storing the other value
 * getLRSStringWay1		| O(n^3)					| O(n^3)				| Time - O(n^3) as every time we are concating a new string of n (worst case) and Space - O(n^3) as each cell can store O(n) of size data ; Total space = n² × n = O(n³)
 * getLRSStringWay2		| O(n^2) = O(n^2)+O(n)		| O(n^2)				|
 */


/*
	O/P
		LRS TopDown = 4
		LRS BottomUp = 4
		LRS string Way 1 = ATKG
		LRS string Way 2 = ATKG
 */

public class LongestRepeatedSubsequence {

	public static void main(String []args) {
	
		LRS lrs = new LRS();
		String s1 = "ATAKTKGGA";
		System.out.println("LRS TopDown = "+lrs.computeDPTopDown(s1)); //4
		System.out.println("LRS BottomUp = "+lrs.computeDPBottomUp(s1)); //4
		System.out.println("LRS string Way 1 = " + lrs.getLRSStringWay1(s1)); //ATKG
		System.out.println("LRS string Way 2 = " + lrs.getLRSStringWay2(s1)); //ATKG
	}
}

class LRS{
	
	//TopDown
	public int computeDPTopDown(String s) {
		Integer dp[][] = new Integer[s.length()+1][s.length()+1];
		return this.computeDPTopDown(s,dp,0,0);
	}
	
	private int computeDPTopDown(String s,Integer[][] dp,int i, int j) {
		
		if(dp[i][j]==null) {
			if(i>=s.length() || j>=s.length()) {
				return 0;
			}
			
			if(s.charAt(i)==s.charAt(j) && i!=j) {
				dp[i][j]=1+computeDPTopDown(s,dp,i+1,j+1);
			}else {
				dp[i][j]=Math.max(computeDPTopDown(s,dp,i+1,j),computeDPTopDown(s,dp,i,j+1));
			}
		}
		return dp[i][j];
	}
	
	
	//BottomUP 
	public int computeDPBottomUp(String s) {
		return this.computeDPBottomUpLRS(s);
	}
	
	private int computeDPBottomUpLRS(String s) {
		int dp[][] = new int[s.length()+1][s.length()+1];
		
		for(int i=1;i<=s.length();i++) {
			for(int j=1;j<=s.length();j++) {
				if(s.charAt(i-1)==s.charAt(j-1) && i!=j) {
					dp[i][j]=1 + dp[i-1][j-1];
				}else {
					dp[i][j]=Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		return dp[s.length()][s.length()];
	}
	
	
	//TopDown string get way1
	public String getLRSStringWay1(String s) {
		String[][] dpStr = new String[s.length()+1][s.length()+1];
		return this.getLRSStringWay1(s,dpStr,0,0);
	}
	
	private String getLRSStringWay1(String s, String[][] dpStr, int i, int j) {
		
		if(dpStr[i][j]==null) {
			
			if(i>=s.length() || j>=s.length()) {
				return "";
			}
			
			if(s.charAt(i)==s.charAt(j) && i!=j) {
				dpStr[i][j]=s.charAt(i)+getLRSStringWay1(s,dpStr,i+1,j+1);
			}else {
				String s1=getLRSStringWay1(s,dpStr,i+1,j);
				String s2=getLRSStringWay1(s,dpStr,i,j+1);
				dpStr[i][j]=s1.length()>s2.length()?s1:s2;
			}
		}
		return dpStr[i][j];
	}
	
	//TopDown string get way2
	public String getLRSStringWay2(String s) {
		Integer[][] dp = new Integer[s.length()+1][s.length()+1];
		computeDPTopDown(s,dp,0,0);
		
		return buildLRS(s,0,0,dp);
	}
	
	public String buildLRS(String s, int i, int j , Integer[][]dp) {

		StringBuilder str = new StringBuilder();
		int n = s.length();
		
		while(i < n && j < n) {
			if(s.charAt(i)==s.charAt(j) && i != j) {
				int diag = (dp[i + 1][j + 1] == null) ? 0 : dp[i + 1][j + 1];
	            int curr  = (dp[i][j] == null) ? 0 : dp[i][j];
	            
	            if(curr==1+diag) {
					str.append(s.charAt(i));
					i++;
					j++;
					continue;
				}
			}
		
		
			int down = dp[i+1][j]==null?0:dp[i+1][j];
			int right= dp[i][j+1]==null?0:dp[i][j+1];
			
			if(down>right)i++;
			else j++;
		}
		
		return str.toString();
		
	}
	
}


