package DivideAndConquer;

/*
 * Problem
 * 	-> 2D matrix is given
 *  -> Each cell has a associated cost
 *  -> We need to start from (0,0) cell and go to (n-1,n-1)
 *  -> We can only go right or down
 *  	-> subProblem1) -> Find the number of paths we can reach end of matrix at given cost
 *      -> subProblem2) -> Find the number of paths we can reach end of matrix within given cost
 *      
 *      cost = 25
 *      
 *      4 7 1 6
 *      5 7 3 9
 *      3 2 1 2
 *      7 1 6 3
 *      
 *      
 *      subProblem1) O/P = 2
 *      			Explanation=  4->7->1->3->1->6->3
 *      						  4->5->7->3->1->2->3
 *      
 *      option1= f(3,4,cost-(4,4))
 *      option2= f(4,3,cost-(4,4)) 
 *      
 *      subProblem2) O/P = 7 {There are 7 paths we can have within cost 25}
 *      option1= f(3,4,cost-(4,4))
 *      option2= f(4,3,cost-(4,4)) 
 *      
 *      only difference is when 
 *      if(row == 0 && col == 0){
 *      	return array[row][col]-cost==0?1:0 //Subproblem1
 *      	return array[row][col]-cost<=0?1:0 //Subproblem2
 *      }
 * 
 * 
 */

/*
 * Time Complexity - O(2^(m+n))
 * 	Branching Factor - 2
 * 		int nosOfPathsFromPrevRow = computeAtWithinCost(array, row-1, col, cost-array[row][col]);
		int nosOfPathsFromPrevCol = computeAtWithinCost(array, row, col-1, cost-array[row][col]);
	Depth - m+n
	
	Time Complexity - BranchingFactor ^ Depth
	
	Space Complexity - O(m+n)
 * 
 */

public class NumberOfPathsToReachLastCellWithinOrAtGivenCost {
	public static void main(String []args) {
		NumberOfPaths np = new NumberOfPaths();
		int array[][]= {
				{4, 7, 1, 6},
				{5, 7, 3, 9},
				{3, 2, 1, 2},
				{7, 1, 6, 3}};
		
		int cost=25;
		System.out.println(np.computeAtGivenCost(array,array.length-1, array[0].length-1,cost));
		System.out.println(np.computeAtWithinCost(array,array.length-1, array[0].length-1,cost));
		//printing the path at a given cost
		np.printTDPathsDuringRecursion(array, cost);
	}
}

class NumberOfPaths{
	public int computeAtGivenCost(int[][]array, int row, int col, int cost) {
		if(cost<0 || row<0 || col<0) {
			return 0;
		}
		
		if(row==0&&col==0) {
			return array[row][col]-cost == 0 ? 1:0;
		}
		int nosOfPathsFromPrevRow = computeAtGivenCost(array, row-1, col, cost-array[row][col]);
		int nosOfPathsFromPrevCol = computeAtGivenCost(array, row, col-1, cost-array[row][col]);
		
		return nosOfPathsFromPrevRow + nosOfPathsFromPrevCol;
	}
	
	public int computeAtWithinCost(int[][]array, int row, int col, int cost) {
		if(cost<0) {
			return 0;
		}
		
		if(row==0&&col==0) {
			return array[row][col]-cost <= 0 ? 1:0; //ONLY CHANGE IS HERE
		}
		/*
		 * we can have 
		 * if(row == 0 || col == 0){
		 * 	return max
		 * }
		 * 
		 * because it add max value & we need to compute in min cost
		 */
		
		if(row == 0) {
			return computeAtWithinCost(array, row, col-1, cost-array[row][col]);
		}
		
		if(col == 0) {
			return computeAtWithinCost(array, row-1, col, cost-array[row][col]);
		}
		
		int nosOfPathsFromPrevRow = computeAtWithinCost(array, row-1, col, cost-array[row][col]);
		int nosOfPathsFromPrevCol = computeAtWithinCost(array, row, col-1, cost-array[row][col]);
		
		return nosOfPathsFromPrevRow + nosOfPathsFromPrevCol;
	}
	
	public void printTDPathsDuringRecursion(int[][] array, int cost) {
		printDuringTD(array, array.length - 1, array[0].length - 1, cost, "");
	}

	private void printDuringTD(int[][] array, int r, int c, int cost, String path) {

		if (r < 0 || c < 0 || cost < 0) {
			return;
		}

		String newPath = array[r][c] + (path.isEmpty() ? "" : " -> " + path);

		if (r == 0 && c == 0) {
			if (array[0][0] == cost) {
				System.out.println(newPath);
			}
			return;
		}

		int remainingCost = cost - array[r][c];

		printDuringTD(array, r - 1, c, remainingCost, newPath);
		printDuringTD(array, r, c - 1, remainingCost, newPath);
	}
}
