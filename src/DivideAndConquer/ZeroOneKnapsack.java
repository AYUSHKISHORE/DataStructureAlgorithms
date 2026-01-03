package DivideAndConquer;

/*
 * Problem 
 * 	- Given the weights & profit of N items
 *  - Find the maximum profits within the given capacity (c).
 *  - Items cannot be broken
 *  
 *  mango{wt:3,p:31} ; apple{wt:1,p:26}, orange{wt:2,p:17}, banana{wt:5,p:72}
 *  maxCapacity = 7
 *  
 *  
 *  logic
 *  
 *  option1 = 31 + F(item2,item3,item4)
 *  option2 = F(item2,item3,item4)
 *  max(option1, option2)
 *  
 * 
 */


public class ZeroOneKnapsack {
	public static void main(String []args) {
		
		int[] wts = {3,1,2,5};
		int[] pts = {31,26,17,72};
		int capacity=7;
		
		ZOKnapsack zok = new ZOKnapsack();
		System.out.println(zok.computeProfits(wts,pts,capacity));
	}
}

class ZOKnapsack{
	private int computeProfits(int[] wts, int[] pts, int i,int capacity) {
		if(i>=wts.length || i<0 || capacity<=0) {
			return 0;
		}
		int profit1 = 0;
		if(wts[i]<=capacity) {
			profit1=pts[i]+computeProfits(wts,pts,i+1,capacity-wts[i]);
		}
		int profit2 = computeProfits(wts,pts,i+1,capacity);
		return Math.max(profit1, profit2);
	}
	
	public int computeProfits(int[] wts, int[] pts, int capacity) {
		return this.computeProfits(wts, pts, 0, capacity);
	}
}
