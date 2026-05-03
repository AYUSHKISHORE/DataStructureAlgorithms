package DynamicProgramming;

/*
 * * In this problem, we need a 3D DP array because the subproblem is not defined only by row and col; 
 * it also depends on cost. The state dp[row][col][cost] means the number of ways to reach cell (row, col) with exact total cost. 
 * A 2D DP like dp[row][col] is not enough because the same cell can be reached with different costs. 
 * For example, in matrix 1 2 3 / 4 5 6 / 7 8 9, cell (1,1) with value 5 can be reached as 1 -> 2 -> 5 = 8 and also as 1 -> 4 -> 5 = 10, so we need separate states like dp[1][1][8] and dp[1][1][10]. 
 * 
 * * In Min Cost Path, cost is the answer, so 2D DP is enough, but in this problem, cost is part of the question, so we need 3D DP. 
 * 
 * * We take cost + 1 because costs from 0 to cost are valid indexes. 
 * 
 * 
 *TD exact cost
computeTDAtGivenCost()

time  = O(m * n * C)
reason = Each unique state (row, col, cost) is computed once because of memoization.

space = O(m * n * C) + O(m + n)
reason = O(m * n * C) for 3D memo array + O(m + n) recursion stack.

 
 
 BU exact cost
computeAtGivenCost()

time  = O(m * n * C)
reason = buildExactCostDp() fills every dp[row][col][cost] state once.

space = O(m * n * C)
reason = Uses a 3D DP array of size rows * cols * (cost + 1).


BU within given cost
computeAtWithinGivenCost()

time  = O(m * n * C) + O(C)
      = O(m * n * C)

reason = First builds exact-cost DP in O(m * n * C), then sums dp[lastRow][lastCol][0...C] in O(C).

space = O(m * n * C)
reason = Uses the same 3D exact-cost DP array.


Where:
m = number of rows
n = number of columns
C = target cost



TD print paths
printExactPath()

time  = O(m * n * C) + O(P * (m + n))
reason = DP memo computes each state once, then printing takes path length for each valid path.

space = O(m * n * C) + O(m + n)
reason = O(m * n * C) for memo array + O(m + n) recursion stack.
 * 
 */

public class NumberOfPathsToReachLastCellWithinOrAtGivenCost {

	public static void main(String []args) {
		 int[][] array = {
	                {4, 7, 1, 6},
	                {5, 7, 3, 9},
	                {3, 2, 1, 2},
	                {7, 1, 6, 3}
	        };

		 NumberofPathsTD np = new NumberofPathsTD();
		 int cost = 25;
		 System.out.println("TD at given cost = "+ np.computeTDAtGivenCost(array,cost));
		
		 NumberofPathsBU nbup = new NumberofPathsBU();
		 System.out.println("BU at given cost = "+ nbup.computeAtGivenCost(array, cost));
		 System.out.println("BU within given cost = "+nbup.computeAtWithinGivenCost(array, cost));
	}
}

class NumberofPathsTD{
	
	public int computeTDAtGivenCost(int[][]array, int cost) {
		
		Integer dp[][][] = new Integer[array.length][array[0].length][cost+1];
		int ways = computeTDExactCost(array,array.length-1,array[0].length-1,cost, dp);
		//printing the paths
		System.out.println("Printing the paths");
		printExactPath(array,array.length-1,array[0].length-1,cost,dp,"");
		return ways;
	}
	
	public int computeTDExactCost(int[][]array, int r, int c, int cost, Integer [][][]dp) {
		
		if(r<0 || c<0 || cost<0) {
			return 0;
		}
		
		if(dp[r][c][cost]!=null) {
			return dp[r][c][cost];
		}
		
		if(r==0 && c==0) {
			return array[r][c] == cost ? 1: 0; //O/P - 2
			//Instead of exact cost if you want within this cost just comment above line
			//and uncomment below line
			//return array[r][c]<=cost? 1:0; //O/P - 7
		}
		
		int remainingCost = cost - array[r][c];
		
		int moveUp = computeTDExactCost(array,r-1,c,remainingCost, dp);
		int moveLeft = computeTDExactCost(array,r,c-1,remainingCost,dp);
		
		dp[r][c][cost]=moveUp + moveLeft;
		return dp[r][c][cost];
		
	}
	
	public void printExactPath(int[][]array, int r, int c , int cost, Integer [][][]dp, String path) {
		if(r<0 || c<0 || cost<0) {
			return;
		}
		
		String newPath = array[r][c] + (path.isEmpty()? "":"->") + path;
		
		if(r==0 && c==0) {
			if(array[r][c]==cost) {
				System.out.println(newPath);
			}
			return;
		}
		
		int remainingCost = cost - array[r][c];
		// why did we called computeTDExactCost instead of dp array because
		//BU dp  = int[][][] fully filled table
		//TD dp  = Integer[][][] memo table, filled only for visited states
		if(r>0 && computeTDExactCost(array,r-1,c,remainingCost,dp)>0) {
			printExactPath(array,r-1,c,remainingCost,dp, newPath);
		}
		if(c>0 && computeTDExactCost(array,r,c-1,remainingCost,dp)>0) {
			printExactPath(array,r,c-1,remainingCost,dp, newPath);
		}
		
	}
	
	
}


class NumberofPathsBU{
	
	public int computeAtGivenCost(int array[][], int cost) {
		int[][][]dp = buildExactCostDp(array, cost);
		
		int row = array.length;
		int col = array[0].length;
		
		return dp[row-1][col-1][cost];
	}
	
	public int computeAtWithinGivenCost(int array[][], int cost) {
		int[][][]dp = buildExactCostDp(array, cost);
		
		int row = array.length;
		int col = array[0].length;
		
		int totalPaths = 0;
		
		for(int k = 0;k<=cost;k++) {
			totalPaths += dp[row-1][col-1][k];
		}
		
		return totalPaths;
	}
	
	
	public int[][][] buildExactCostDp(int array[][], int cost){
		
		int rows = array.length;
		int cols = array[0].length;
		
		int dp[][][] = new int[rows][cols][cost+1];
		
		if(array[0][0]<=cost) {
			dp[0][0][array[0][0]]=1;
		}
		
		for(int r = 0;r<rows;r++) {
			for(int c = 0;c<cols;c++) {
				
				if(r==0 && c == 0) {
					continue;
				}
				int cellValue = array[r][c];
				
				for(int currCost = cellValue; currCost<=cost; currCost++) {
					
					int prevCost = currCost - cellValue;
					int left = 0;
					int up = 0;
					
					if(r>0) {
						up = dp[r-1][c][prevCost];
					}
					if(c>0) {
						left = dp[r][c-1][prevCost];
					}
					
					dp[r][c][currCost]=up + left;
				}
			}
		}
		return dp;
	}
}
