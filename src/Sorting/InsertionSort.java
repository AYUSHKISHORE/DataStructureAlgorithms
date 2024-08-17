package Sorting;


/*
 * 
 *  Inserion Sort - Incase of insertion sort, we divided array in 2 part sorted and unsorted
 *  & from unsorted we pick 1 element and find its position in sorted array
 *  
 *  In selection sort is similar approach there we find the min element from unsorted element and then place it correct position
 *  
 *  Selection V/S Insertion
 *  In selection sort - we pick the smallest element 1 by 1 from unsorted array & place it in sorted array in incremental order
 *  
 *  In insertion sort - we pick a element 1 by 1 in incremental order and then place in correct position in sorted array
 *  
 *  
 *  When to use insertion sort?
 *  
 *  We there is continuous flow of nos & we want them to keep sorted
 *  
 *  sorted arr			| unsorted array
 * 						| 5 3 4 7 2
 * 					5	| 3 4 7 2
 * 				3	5	| 4 7 2
 * 			3	4	5	| 7 2
 * 		3	4	5	7	| 2
 * 2 	3	4	5	7	|
 * 
 * 
 */	

// time complexity - O(n^2)
//space complexity - O(1)
//inplace and stable sort
public class InsertionSort {

	public static void main(String []args) {
	
		int a[] = {5,3,4,7,2};
		InsertionSortImp is = new InsertionSortImp(a);
		is.sort();
		is.printArray();
	}
	
}

class InsertionSortImp{
	int a[];
	InsertionSortImp(int[] a){
		this.a = a;
	}
	
	
	void sort() {
		int n=a.length;
		for(int i=1;i<n;i++) {
			int j=i;			
			while(j-1>=0 && a[j]<=a[j-1]) {
				int temp = a[j];
				a[j]=a[j-1];
				a[j-1]=temp;
				j--;
			}
		}
	}
	
	void printArray() {
		System.out.println(java.util.Arrays.toString(a));
	}
}
