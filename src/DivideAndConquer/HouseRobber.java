package DivideAndConquer;

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
 *   
/*
 * Time Complexity - O(2^n)
 * 	Branching Factor - 2
 * 		int choseCurrentHouse = houseNetWorth[currIdx]+computeMaxRobbery(houseNetWorth,currIdx+2);
		int skipCurrentHouse = computeMaxRobbery(houseNetWorth,currIdx+1);
 *  Depth - n
 *  TimeComplexity - Branching Factor ^ Depth
 * 
 * Space Complexity - O(n) (Stack heap)
 * 
 */

public class HouseRobber {
	public static void main(String []args) {
		int[] houseNetWorth = {6,7,1,30,8,2,4};
		PlanMaxRobberyAmt rb = new PlanMaxRobberyAmt();
		System.out.println(rb.computeMaxRobbery(houseNetWorth));
	}
}

class PlanMaxRobberyAmt{
	private int computeMaxRobbery(int[] houseNetWorth, int currIdx) {
		if(houseNetWorth.length==0) {
			return -1;
		}
		if(currIdx>=houseNetWorth.length) {
			return 0;
		}
		int choseCurrentHouse = houseNetWorth[currIdx]+computeMaxRobbery(houseNetWorth,currIdx+2);
		int skipCurrentHouse = computeMaxRobbery(houseNetWorth,currIdx+1);
		
		return Math.max(choseCurrentHouse, skipCurrentHouse);
	}
	
	public int computeMaxRobbery(int[] houseNetWorth) {
		return this.computeMaxRobbery(houseNetWorth,0);
	}
}
