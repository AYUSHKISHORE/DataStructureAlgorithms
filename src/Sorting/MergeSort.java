package Sorting;

/*
 * 
 * Merge sort is a divide and conquer algorithm
 * 
 * Divide the i/p array in the two halves and we keep halving recursively until they become too small that cannot be broken further
 * 
 *  [6 4 3 7 5 1 2]
 *  [6 4 3 7][5 1][2]
 *  [6 4][3 7][5][1][2]
 *  [6][4][3][7][5][1][2]
 *  [4 6][3 7] [1 5][2]
 *  [3 4 6 7][1 2 5]
 *  [1 2 3 4 5 6 7]
 * 
 * 
 * It is kind of stable sort
 * 
 */

// time complexity = O(nlogn) stable algorithm
// space complexity = O(n) outplace 
public class MergeSort {

	public static void main(String []args) {
		int a[] = {6,4,3,7,5,1,2};
		MergeSortImp ms = new MergeSortImp(a);
		ms.sort(a);
		ms.printArray();
	}
}

class MergeSortImp{
	int[] a;
	MergeSortImp(int a[]){
		this.a=a;
	}
	
	void sort(int a[]) {
		if(a.length<=1) {
			return;
		}
		int mid = (a.length)/2;
		int leftarr[]= new int[mid];
		int rightarr[]= new int[a.length-mid];
		System.arraycopy(a, 0, leftarr, 0, mid);
		System.arraycopy(a, mid, rightarr, 0, a.length-mid);
		sort(leftarr);
		sort(rightarr);
		merge(a,leftarr,rightarr);
	}
	
	void merge(int a[],int leftarr[],int rightarr[]) {
		int i=0,j=0,k=0;
		
		while(i<leftarr.length && j<rightarr.length) {
			if(leftarr[i]<=rightarr[j]) {
				a[k++]=leftarr[i++];
			}else {
				a[k++]=rightarr[j++];
			}
		}
		while(i<leftarr.length) {
			a[k++]=leftarr[i++];
		}
		
		while(j<rightarr.length) {
			a[k++]=rightarr[j++];
		}
	}
	
	void printArray() {
		System.out.println(java.util.Arrays.toString(a));
	}
}
