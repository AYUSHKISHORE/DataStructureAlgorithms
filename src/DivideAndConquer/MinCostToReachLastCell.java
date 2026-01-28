package DivideAndConquer;
/*
 * Problem
 * 	- 2D matrix is given
 *  - Each cell has a cost associated with it for accessing
 *  - We need to start from (0,0)  go till (n-1, n-1) cell
 *  - We can go only right or down cell from current cell
 *  - Find a way in which cost is minimum
 *  
 *  
 *  4 7 8 6 4
 *  6 7 3 9 2
 *  3 8 1 2 4
 *  7 1 7 3 7
 *  2 9 8 9 3
 *  	
 *  -> Path  = 4 -> 6 -> 7 -> 3 -> 1 -> 2 -> 3 -> 7 -> 3
 *  -> Logic = c -> D -> R -> R -> D -> R -> D -> R -> D (c = current)
 *  -> O/p = 36
 * 
 * 	-> We can develop this 2 way 
 * 			-Either going from top to bottom (0,0) -> (n-1,n-1)
 *  		- Or going from bottom to top (n-1,n-1) -> (0,0)
 *  
 *  LET SAY WE HAVE 4x4 MATRIX 
 *  when going from bottom to top
 *   option1 = f(4,3) -> going left
 *   option2 = f(3,4) -> going up
 *   
 *  when going from top to bottom
 *   option1 = f(0,1) -> going right
 *   option2 = f(1,0) -> going down
 *   
 *   
 *   time complexity = O(2^(n+n)) -> we are going exponentially and why n+n is because once we are going down and once we are going up
 *     -> from [(n-1)(n-1)] to [(0,0)] = n + n -2
 */

/*
 * Time Complexity - O(2^(m+n))
 * 	Branching Factor - 2
 * 		int minCost1 = computeBottomUp(array, row - 1, col);
		int minCost2 = computeBottomUp(array, row , col - 1);
	Depth - m+n
	
	Time Complexity - BranchingFactor ^ Depth
	
	Space Complexity - O(m+n)
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
		System.out.println("BottomUpApproach = "+minCost.computeBottomUp(array, array.length-1,array[0].length-1));
		System.out.println("TopDownApproach = "+minCost.computeTopBottom(array, 0, 0));
	}
}

class MinCost{
	
	public int computeBottomUp(int[][] array, int row, int col) {
		
		if(row <0 || col <0) {
			return Integer.MAX_VALUE;
		}
		
		if(row==0 && col == 0) {
			return array[row][col];
		}
		
		int minCost1 = computeBottomUp(array, row - 1, col);
		int minCost2 = computeBottomUp(array, row , col - 1);
		
		return array[row][col]+ Math.min(minCost1, minCost2);
	}
	
	
	public int computeTopBottom(int[][]array, int row, int col) {
		if(row>=array.length || col>=array[0].length) {
			return Integer.MAX_VALUE;
		}
		if(row == array.length-1 && col == array[0].length-1) {
			return array[row][col];
		}
		
		int minOption1 = computeTopBottom(array,row+1,col);
		int minOption2 = computeTopBottom(array,row,col+1);
		
		return array[row][col]+Math.min(minOption1, minOption2);
	}
	
}
