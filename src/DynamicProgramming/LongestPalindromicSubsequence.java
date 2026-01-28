package DynamicProgramming;

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



/*
	Method					|What it returns	|Time	Space
	computeTopDown			|length				|O(n^2)		|O(n^2) (+ stack O(n))
	computeBottomUp			|length				|O(n^2)		|O(n^2)
	getLPSStringTopDownWay1	|string				|O(n^3)		|O(n^3) (+ stack O(n))
	getLPSStringTopDownWay2	|string				|O(n^2)		|O(n^2)
 *  
 */


/*
	O/P
		TopDown = 5
		BottomUp = 5
		LPS string topDown way1 = EMEME
		LPS string topDown way2 = EMEME
 */

public class LongestPalindromicSubsequence {

	public static void main(String []args) {
		
		String s1 = "ELRMENMET";
		LPS lps = new LPS();
		System.out.println("TopDown = "+lps.computeTopDown(s1)); //5
		System.out.println("BottomUp = "+lps.computeBottomUp(s1)); //5
		System.out.println("LPS string topDown way1 = "+lps.getLPSStringTopDownWay1(s1)); //EMEME
		System.out.println("LPS string topDown way2 = "+lps.getLPSStringTopDownWay2(s1)); //EMEME
	}
}

class LPS{
	
	//topdown
	public int computeTopDown(String s) {
		Integer[][] dp = new Integer[s.length()+1][s.length()+1];
		return this.computeTopDown(s,dp,0,s.length()-1);
	}
	
	private int computeTopDown(String s, Integer[][]dp , int i , int j) {
		
		if(dp[i][j]==null) {
			
			if(i>j) {
				return 0;
			}
			
			if(i==j) {
				return 1;
			}
			
			int way1=0;
			if(s.charAt(i)==s.charAt(j)) {
				 way1=2+computeTopDown(s,dp,i+1,j-1);
			}
			int fwdWay = computeTopDown(s,dp,i+1,j);
			int bckWay = computeTopDown(s,dp,i,j-1);
			
			dp[i][j]=Math.max(way1, Math.max(fwdWay, bckWay));
		}
		return dp[i][j];
	}
	
	//bottomup
	
	public int computeBottomUp(String s) {
		return this.computeBottomUp(s,0,s.length()-1);
	}
	
	private int computeBottomUp(String s , int i , int j) {
		
		int dp[][] = new int[s.length()+1][s.length()+1];
		
		for(int len=1;len<=s.length();len++) {//this decide the substring length
			for(int row=0;row+len-1<s.length();row++) {
				int col=row+len-1;
				if(row==col) {
					dp[row][col]=1;
				}
				else if(s.charAt(row)==s.charAt(col)){
					dp[row][col]=2+dp[row+1][col-1];
				}
				else {
					dp[row][col]=Math.max(dp[row+1][col], dp[row][col-1]);
				}
			}
		}
		return dp[0][s.length()-1];
	}
	
	//Outerloop decides substring length
	//inner loop decides the window left -> right
	/*
	{0,0}, {1,1} , {2,2} , {3,3} , {4,4}
    {0,1}, {1,2} , {2,3} , {3,4}
    {0,2}, {1,3} , {2,4}
    {0,3}, {1,4}
    {0,4}
	
	only these index are touched as row>length = 0
	
	row + len - 1 
	
	window 1,2,3,....
	*/
	
	
	//LPS String way1
	public String getLPSStringTopDownWay1(String s1) {
		String[][] strArr = new String[s1.length()+1][s1.length()+1];
		return this.getLPSStringTopDownWay1(s1,strArr,0,s1.length()-1);
	}
	
	private String getLPSStringTopDownWay1(String s, String[][] dp, int i , int j) {
		
		if(dp[i][j]==null) {
			
			if(i>j) {
				dp[i][j]="";
				return "";
			}
			if(i==j) {
				dp[i][j]=String.valueOf(s.charAt(i));
				return String.valueOf(s.charAt(i));
			}
			
			if(s.charAt(i)==s.charAt(j)) {
				String mid = getLPSStringTopDownWay1(s,dp,i+1,j-1);
				dp[i][j]=s.charAt(i)+mid+s.charAt(j);
			}else {
				String wayFwd = getLPSStringTopDownWay1(s,dp,i+1,j);
				String wayBck = getLPSStringTopDownWay1(s,dp,i,j-1);
				dp[i][j]=wayFwd.length()>wayBck.length()?wayFwd:wayBck;
			}
			
		}
		return dp[i][j];
	}
	
	//LPS String Way 2
	
	public String getLPSStringTopDownWay2(String s) {
		Integer[][] dp = new Integer[s.length()+1][s.length()+1];
		
		this.computeTopDown(s, dp, 0, s.length()-1);
		return buildLPSString(s,dp,0,s.length()-1);
	}
	
	public String buildLPSString(String s, Integer[][]dp, int i, int j) {
		
		StringBuilder left = new StringBuilder();
		StringBuilder right = new StringBuilder();
		
		while(i<=j) {
			
			if(i==j) {
				left.append(s.charAt(i));
				break;
			}
			
			if(s.charAt(i)==s.charAt(j)) {
				left.append(s.charAt(i));
				right.append(s.charAt(j));
				i++;
				j--;
			}else {
				int moveRight = dp[i+1][j]==null?0:dp[i+1][j];
				int moveLeft = dp[i][j-1]==null?0:dp[i][j-1];
				
				if(moveRight>moveLeft)i++;
				else j--;
			}
		}
		return left.append(right.reverse()).toString();
		
		
	}

}
