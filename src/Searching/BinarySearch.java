package Searching;

/*
 * Binary Search - we eliminate the half of the remaining element instead of eliminating them one by one
 * 
 * Binary Search works only for sorted Array
 * 
 *			1 2 3 4 5 6 7 8 9 (Search = 6)
 *	5 mid 	1 2 3 4 | 6 7 8 9
 *  7 mid(5<6)      | 6 | 7 8 9
 *  (6<7)	6 found
 *  
 *  In above case 
 *  we found 6 in 3 steps
 *  log-base2(total-elements) = log-base2(9) = 3 ===> 2^3 ~9
 *  
 *  So timecomplexity = O(logn)
 * 
 */

//Time Complexity - O(logn)
//Space Complexity - O(1)
public class BinarySearch {

	public static void main(String []args) {
		int a[]= {1,2,3,7,9};
		BinarySearchAlgo bs = new BinarySearchAlgo(a);
		bs.search(7);
		bs.search(5);
	}
}

class BinarySearchAlgo{
	int a[];
	BinarySearchAlgo(int a[]){
		this.a=a;
	}
	
	void search(int value) {
		int low=0;
		int high=a.length-1;
		int mid = (low+high)/2;
		
		while(low<=high && a[mid]!=value) {
			if(a[mid]>value) {
				high=mid-1;
			}else {
				low=mid+1;
			}
			mid=(low+high)/2;
		}
		
		if(a[mid]==value) {
			System.out.println("value = "+value+" found at index = "+mid);
			return;
		}else {
			System.out.println("value = "+value+" not found  ");
			return;
		}
		
	}
}
