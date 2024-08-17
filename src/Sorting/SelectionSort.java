package Sorting;

/*
 * Selection Sort
 * 
 * In selection sort we repeatedly find the min element and move to the sorted part of the array
 * 
 * 			Sorted 	| UnsortedPart
 * Array 			|	5 7 4 3 8
 * Array		3	|   5 7 4 8
 * Array 		3 4	|   5 7 8
 * Array       3 4 5|   7 8
 * Array	 3 4 5 7|   8
 * Array   3 4 5 7 8|
 */


//time complexity - O(n^2)
//Space complexity - O(10
public class SelectionSort {

	public static void main(String []args) {
		int a[] = {5,7,4,3,8};
		SelectionSortImp ss = new SelectionSortImp(a);
		ss.sort();
		ss.printArray();
	}
	
}

class SelectionSortImp {
	int a[];
	SelectionSortImp(int a[]){
		this.a = a;
	}
	
	void sort() {
		int n=a.length;
		for(int i=0;i<n;i++) {
			int minIndex=i;
			for(int j=i+1;j<n;j++) {
				if(a[minIndex]>a[j]) {
					minIndex=j;
				}
			}
			if(minIndex!=i) {
				int temp = a[minIndex];
				a[minIndex]=a[i];
				a[i]=temp;
			}
		}
	}
	
	void printArray() {
		System.out.println(java.util.Arrays.toString(a));
	}
}
