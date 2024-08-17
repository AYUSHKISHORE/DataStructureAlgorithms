package Sorting;

/*
 * Bubble sort also referred as sinking sort( as we sink with the next element)
 * We repeatedly compare each pair of adjacent items & swap then if are in wrong order
 */

//Time Complexity - O(n^2)
//Space Complexity - O(1)
/*
 * when i/p is almost sorted then it fine to use
 */

public class BubbleSort {

	public static void main(String []args) {
		int a[] = {3,5,1,2,5,7,4};
		BubbleSortImp bs = new BubbleSortImp(a);
		bs.sort();
		bs.printArray();
	}
}

class BubbleSortImp{
	int a[];
	BubbleSortImp(int []a){
		this.a = a;
	}
	
	public void sort() {
		int n = a.length;
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<n-i-1;j++) {
				if(a[j]>a[j+1]) {
					int temp = a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
	}
	
	public void printArray() {
		System.out.println(java.util.Arrays.toString(a));
	}
}