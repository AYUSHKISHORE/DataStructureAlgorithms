package Searching;
/*
 * 
 * Linear search is mostly used when array is unsorted
 */

//Time Complexity = O(n)
//Space Complexity = O(n)
public class LinearSearch {

	public static void main(String []args) {
		int a[] = {5,9,3,1,2,8,4,7,6};
		LinearSearchAlgo ls = new LinearSearchAlgo(a);
		ls.Search(1);
		ls.Search(90);
	}
}
class LinearSearchAlgo{
	int a[];
	LinearSearchAlgo(int a[]){
		this.a=a;
	}
	
	void Search(int value) {
		for(int i=0;i<a.length;i++) {
			if(a[i]==value) {
				System.out.println("value = "+value+" found at index = "+i);
				return;
			}
		}
		System.out.println("value = "+value+" not found");
		
	}
}
