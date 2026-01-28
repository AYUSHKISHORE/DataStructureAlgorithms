package DynamicProgramming;

/*
 * Question
 *  - Given N numbers of houses along the street with some amount of money;
 *  - Adjacent houses cannot be stolen
 *  - Find the max amount that can be stolen
 *  
 *   Eg - 6 7 1 30 8 2 4
 *   Ans = 7 + 30 + 4 = 41
 *   
 *   
 *   Rule1 = if we take first element then compute 3rd (i+2th element)
 *   Rule2 = if we take skip first element the take 2nd element (i+1th element)
 *   
 *   Option1 = 0 + F(Any seven elements)
 *   Option2 = 6 + F(Next 5 element excluding 7)
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
   41
   41
 */

public class HouseRobber {

	public static void main(String []args) {
		int houses[] = {6,7,1,30,8,2,4};
		
		//Top-Down memoization
		Integer dp[] = new Integer[houses.length+1];
		System.out.println(maxMoneyTopDown(dp,houses,0)); //41
		
		//Bottom-Up tabulation
		System.out.println(maxMoneyBottomUp(houses,0));//41
		
	}
	
	public static int maxMoneyTopDown(Integer dp[], int[] houses, int currIdx) {
		
		if(currIdx>=houses.length) {
			return 0;
		}
		if(dp[currIdx]==null) {
			int currentHouse = houses[currIdx]+maxMoneyTopDown(dp,houses,currIdx+2);
			int skipCurrentHouse = maxMoneyTopDown(dp,houses,currIdx+1);
			dp[currIdx]=Math.max(currentHouse, skipCurrentHouse);
		}
		return dp[currIdx];
		
	}
	
	public static int maxMoneyBottomUp(int[] houses, int currIdx) {
		int dp[] = new int[houses.length+2];
		
		for(int i=houses.length-1;i>=0;i--) {
			dp[i]=Math.max(houses[i]+dp[i+2], dp[i+1]);
		}
		
		return dp[0];
	}
}
