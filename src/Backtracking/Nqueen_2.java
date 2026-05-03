package Backtracking;

import java.util.Arrays;
/*
 * 
 * 
 * Place N queen on a NxN chess board, in such a manner that no queen(s) can attack each other
 * 
 * Logic: We start from 0th row to ...N-1 row in such a manner that in every row we select the best solution 
 * (by validating whether the cell is safe or not) -by checking upper row, upper left diagonal, upper right diagonal is not attacking the current cell.
 * 
 * 
 * This is more optimized version of Nqueen problem in terms of timecomplexity
 * 
 * Here we have taken there hash - as boolean array colArray - to Store which col is occupied, 
 * leftDiagonal - to store which left diagonal is occupied 
 * rightDiagonal - also called (antidiagonal) to store which right diagonal is occupied
 * 
 * Note - leftDiagonal and rightDiagonal has array of size 2*n - 1 because in a nxn matrix there  are 2*n - 1 diagonal in left and 2*n -1 diagonal in right
 * eg = n=4 then there are 7 left diagonal and 7 right diagonal
 * 
 * 
 * 
 * Important regarding index
 * 			int rightDiagonalIndex = row + col;
 * 			int leftDiagonalIndex = row - col + n - 1;
 * 
 * 		for rightDiagonal , for a particular diagonal we get particular value
 * 		(row, col)
		rightDiagonal[0] -> D0: (0,0) = 0 
		rightDiagonal[1] -> D1: (0,1), (1,0) = 1
		rightDiagonal[2] -> D2: (0,2), (1,1), (2,0) = 2
		rightDiagonal[3] -> D3: (0,3), (1,2), (2,1), (3,0) = 3
		rightDiagonal[4] -> D4: (1,3), (2,2), (3,1) = 4
		rightDiagonal[5] -> D5: (2,3), (3,2) = 5
		rightDiagonal[6] -> D6: (3,3) = 6
 * 
 * 
 * 
 * 		For leftDiagonal , for a particular diagonal we get particular value
 * 
 * 			(if we don't add n - 1) we get -ve index.
 * 			leftDiagonal[0] -> D0: (0,3) = 0 - 3 + 3 = 0

			leftDiagonal[1] -> D1: (0,2), (1,3)
			                = 0 - 2 + 3 = 1
			                = 1 - 3 + 3 = 1
			
			leftDiagonal[2] -> D2: (0,1), (1,2), (2,3)
			                = 0 - 1 + 3 = 2
			                = 1 - 2 + 3 = 2
			                = 2 - 3 + 3 = 2
			
			leftDiagonal[3] -> D3: (0,0), (1,1), (2,2), (3,3)
			                = 0 - 0 + 3 = 3
			                = 1 - 1 + 3 = 3
			                = 2 - 2 + 3 = 3
			                = 3 - 3 + 3 = 3
			
			leftDiagonal[4] -> D4: (1,0), (2,1), (3,2)
			                = 1 - 0 + 3 = 4
			                = 2 - 1 + 3 = 4
			                = 3 - 2 + 3 = 4
			
			leftDiagonal[5] -> D5: (2,0), (3,1)
			                = 2 - 0 + 3 = 5
			                = 3 - 1 + 3 = 5
			
			leftDiagonal[6] -> D6: (3,0) = 3 - 0 + 3 = 6
 * 
 * 
 * 
 * Time Complexity - O(N!) (without print) 
 * 				   - O(N!) * O(N^2) (with print)
 * 
 * Space Complexity - O(N^2) + O(N) + O(N) + O(N) + O(N) =~ O(N^2)
 * 
 * 
 * O/P
		
		. Q . . 
		. . . Q 
		Q . . . 
		. . Q . 
		
		------------------------------------------
		. . Q . 
		Q . . . 
		. . . Q 
		. Q . . 
		
		------------------------------------------
 * 
 */

public class Nqueen_2 {
	public static void main(String []args) {
		
		int n = 4;
		char[][] chess = new char[n][n];
		
		boolean colArray[] = new boolean[n];
		boolean leftDiagonal[] = new boolean[2*n - 1];
		boolean rightDiagonal[] = new boolean[2*n - 1];
		
		for(int i=0;i<n;i++) {
			Arrays.fill(chess[i],'.');
		}
		
		int row = 0;
		boolean hasSolution = placeQueen(chess,row,n,colArray,leftDiagonal,rightDiagonal);
		if(!hasSolution) {
			System.out.println("No solution possible");
		}
	}
	
	public static boolean placeQueen(char[][]chess, int row, int n, boolean[]colArray, 
			boolean[] leftDiagonal, boolean[] rightDiagonal) {
		
		if(row == n) {
			printChessBoard(chess,n);
			System.out.println();
			System.out.println("------------------------------------------");
			return true;
		}
		
		boolean hasSolution = false;
		
		for(int col = 0;col<n;col++) {
			
			int leftDiagonalIndex = row - col + n - 1;
			int rightDiagonalIndex = row + col;
			
			if(!colArray[col] && !leftDiagonal[leftDiagonalIndex] && !rightDiagonal[rightDiagonalIndex]) {
				colArray[col]=true;
				leftDiagonal[leftDiagonalIndex]=true;
				rightDiagonal[rightDiagonalIndex]=true;
				chess[row][col]='Q';
				
				boolean result = placeQueen(chess,row+1, n , colArray, leftDiagonal, rightDiagonal);
				if(result) {
					hasSolution = true;
					//uncomment below statement if you want only 1 solution
					//return hasSolution
				}
				
				
				//Backtracking if solution is not fit
				chess[row][col]='.';
				colArray[col]=false;
				leftDiagonal[leftDiagonalIndex]=false;
				rightDiagonal[rightDiagonalIndex]=false;
				
			}
		}
		
		return hasSolution;
	}
	
	public static void printChessBoard(char[][]chess, int n) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(chess[i][j]+" ");
			}
			System.out.println();
		}
	}
}
