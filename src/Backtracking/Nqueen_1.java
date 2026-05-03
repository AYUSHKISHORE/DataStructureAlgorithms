package Backtracking;

import java.util.Arrays;

/*
 * Place N queen on a NxN chess board, in such a manner that no queen(s) can attack each other
 * 
 * Logic: We start from 0th row to ...N-1 row in such a manner that in every row we select the best solution 
 * (by validating whether the cell is safe or not) -by checking upper row, upper left diagonal, upper right diagonal is not attacking the current cell.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Time Complexity - O(N! * N) - Without print logic
 * Time Complexity = O(N! * N + S * N^2) With print logic
 * 	N!= because we place queen row wise and for each row checks cols by N then (N-1) then (N-2).....
 * i.e But because queens cannot be in the same column, the number of possible placements is roughly:
 * N * (N - 1) * (N - 2) * ... * 1 = N! = ~ O(N!)
 * 
 * isSafe function takes = O(N)
 * 
 * 
 * printChess  = O(N^2) - For 1 solution
 * for S solution = O(N^2 *S)
 * 
 * 
 * Space Complexity = O(N^2) + O(N) {O(N^2) - for char array and O(N) for recursion stack}
 * 
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


public class Nqueen_1 {

	public static void main(String []args) {
		int n = 4;
		char chess[][] = new char[n][n];
		//filling the array row wise
		for(int i=0;i<n;i++) {
			Arrays.fill(chess[i],'.');
		}
		int row = 0;
		boolean hasSolution = placeQueen(chess, row ,n);
		if(!hasSolution) {
			System.out.println("No Solution Exist");
		}
		
	}
	
	public static boolean placeQueen(char[][] chess,int row, int n) {
		
		if(row == n) {
			printChessBoard(chess);
			System.out.println("----------------------------------");
			return true; // it prints all the possible solution
		}
		
		boolean hasSol = false;
		for(int col = 0;col<n;col++) {
			
			if(isSafe(row,col,chess,n)) {
				chess[row][col]='Q';
				//now we move to next row
				boolean result = placeQueen(chess,row+1,n);
				if(result) {
					hasSol = true;
					//if we want only 1 solution uncomment below line
					//return hasSol
				}
				
				//this line will only reached if it gets no solution hence we backtrack
				chess[row][col]='.';

			}
		}
		return hasSol;
	}
	
	public static boolean isSafe(int row, int col, char[][]chess,int n) {
		
		//check if upper row of same col has queen
		for(int i=row-1;i>=0;i--) {
			if(chess[i][col]=='Q') {
				return false;
			}
		}
		
		//check if left diagonal has queen
		for(int i=row-1,j=col-1; i>=0 && j>=0; i--,j--) {
			if(chess[i][j]=='Q') {
				return false;
			}
		}
		
		//check if right diagonal has queen
		for(int i=row-1,j=col+1; i>=0 && j<n; i--,j++) {
			if(chess[i][j]=='Q') {
				return false;
			}
		}
		return true;
	}
	
	
	public static void printChessBoard(char[][]chess) {
		
		for(int i=0;i<chess.length;i++) {
			for(int j=0;j<chess[0].length;j++) {
				System.out.print(chess[i][j]+" ");
			}
			System.out.println();
		}
	}
}

