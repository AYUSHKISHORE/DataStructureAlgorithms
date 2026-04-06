package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/*
 * Approach 				| Time Complexity 	| Space Complexity
 * 
 * TopDown					| O(m x n)		  	| O(m x n) + (recursion stack also)
 * BottomUp					| O(m x n)			| O(m x n) (iterative approach
 * TopDownDetailedValues	| O(m x n)		  	| O(m x n) + (recursion stack also)
 * BottomUpDetailedValues	| O(m x n)			| O(m x n) (iterative approach
 * O/P
 * TopDown = 36
 * BottomUp = 36
 * 
 */
public class MinCostToReachLastCell {
	public static void main(String []args) {
		int [][]array= {
				{4,7,8,6,4},
				{6,7,3,9,2},
				{3,8,1,2,4},
				{7,1,7,3,7},
				{2,9,8,9,3}
		};
		
		MinCost minCost = new MinCost();
		System.out.println("TopDownApproach = "+minCost.computeTopDown(array)); //36
		System.out.println("BottomUpApproach = "+minCost.computeBottomUp(array)); //36
		
		Result rs = minCost.computeTopDownValues(array);
		System.out.println("TopDownApproach GetDetailed Values: minCost "+rs.minCost + " coordinates: "+rs.coord + " values: "+rs.values);;
		System.out.println("BottomUpApproach GetDetailed Values: minCost "+rs.minCost + " coordinates: "+rs.coord + " values: "+rs.values);;

	}
}

class MinCost{

	public int computeTopDown(int[][] grid) {
		Integer[][] dp = new Integer[grid.length][grid[0].length];
		int n = grid.length;
		int m = grid[0].length;
		
		return this.computeTopDown(grid,dp,n-1,m-1);
	}
	
	private int computeTopDown(int[][]grid, Integer[][]dp, int row, int col) {
		
		if(row<0 || col<0) {
			return Integer.MAX_VALUE;
		}
		
		if(row==0 && col == 0) {
			dp[row][col]=grid[row][col];
			return dp[row][col];
		}
		
		if(dp[row][col]==null) {
			
			int up = computeTopDown(grid,dp,row-1,col);
			int left = computeTopDown(grid,dp,row,col-1);
			
			dp[row][col]=grid[row][col]+Math.min(up, left);
			
		}
		
		return dp[row][col];
	}
	
	
	public int computeBottomUp(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		
		int dp[][] = new int[m][n];
		
		
		//fill the last cell
		dp[m-1][n-1]=grid[m-1][n-1];
		
		
		//fill the last col
		for(int i=m-2;i>=0;i--) {
			dp[i][n-1] = dp[i+1][n-1] + grid[i][n-1];
		}
		
		//fill the last row
		for(int j=n-2;j>=0;j--) {
			dp[m-1][j] = dp[m-1][j+1] + grid[m-1][j];
		}
		
		//total compute
		for(int i=m-2;i>=0;i--) {
			for(int j=n-2;j>=0;j--) {
				dp[i][j]=grid[i][j]+Math.min(dp[i+1][j], dp[i][j+1]);
			}
		}
		
		return dp[0][0];
	}
	
	public Result computeTopDownValues(int[][]grid) {
		Integer[][] dp = new Integer[grid.length][grid[0].length];
		char[][] move = new char[grid.length][grid[0].length];
		
		int minCost = this.computeTopDownValues(grid,dp,move,0,0);
		
		List<String> coord = new ArrayList<String>();
		List<Integer> values = new ArrayList<Integer>();
		
		int r=0, c=0;
		
		while(true) {
			coord.add("("+r+", "+c+")");
			values.add(grid[r][c]);
			if(move[r][c]=='E') {
				break;
			}
			if(move[r][c]=='R') {//move right
				c++;
			}else{//move Down
				r++;
			}
		}
		return new Result(minCost,coord,values);
	}
	
	private Integer computeTopDownValues(int[][]grid, Integer[][]dp, char[][]move, int row, int col) {
		
		if(row>=grid.length||col>=grid[0].length) {
			return Integer.MAX_VALUE;
		}
		
		if(row==grid.length-1 && col == grid[0].length-1) {
			dp[row][col]=grid[row][col];
			move[row][col]='E';
			return dp[row][col];
		}
		
		if(dp[row][col]==null) {
			
			int down = computeTopDownValues(grid,dp,move,row+1,col);
			int right = computeTopDownValues(grid,dp,move,row,col+1);
			
			int minVal = 0;
			if(down<right) {
				move[row][col]='D';
				minVal=down;
			}else {
				move[row][col]='R';
				minVal=right;
			}
			dp[row][col]=grid[row][col]+minVal;
			
		}
		
		return dp[row][col];
	}
	
	public Result computeBottomUpValues(int[][] grid) {
		
		int m = grid.length;
		int n = grid[0].length;
		int dp[][] = new int[m][n];
		char move[][] = new char[m][n];
		
		//fill the last cell
		dp[m-1][n-1]=grid[m-1][n-1];
		move[m-1][n-1]='E';
		
		
		//fill the last col
		for(int i=m-2;i>=0;i--) {
			dp[i][n-1] = dp[m-1][n-1] + grid[i][n-1];
		}
		
		
		//fill the last row
		for(int j=n-2;j>=0;j--) {
			dp[m-1][j] = dp[m-1][n-1] + grid[m-1][j];
		}
		
		//total compute
		for(int i=m-2;i>=0;i--) {
			for(int j=n-2;j>=0;j--) {
				
				if(dp[i+1][j]>dp[i][j+1]) {
					move[i][j]='D';
				}else {
					move[i][j]='R';
				}
				
				dp[i][j]=grid[i][j]+Math.min(dp[i+1][j], dp[i][j+1]);
			}
		}
		
		int r=0,c=0;
		List<String> coord = new ArrayList<String>();
		List<Integer> values = new ArrayList<Integer>();
		
		while(true) {
			coord.add("( "+r+", "+c+" )");
			values.add(grid[r][c]);
			if(move[r][c]=='E') {
				break;
			}
			
			if(move[r][c]=='D') {
				r++;
			}else {
				c++;
			}
		}
		
		return new Result(dp[0][0],coord, values);
	}
	
	
	
}

class Result{

	int minCost;
	List<String> coord;
	List<Integer> values;
	
	Result(int minCost, List<String> coord, List<Integer> values){
		this.minCost=minCost;
		this.coord=coord;
		this.values=values;
	}
}
