package Sorting;

/*
 * It is a efficient algorithm for sorting an array where the elements are in the range from 1 to n 
 * (n is length of array -1) It works by placing each element is correct position cyclic manner
 *  
 */

//Time Compelxity - O(n)
//Space Complexity - O(1)
public class CyclicSort {
	public static void main(String []args) {
		int a[] = {4,5,1,2,3};
		CyclicSortImp cs = new CyclicSortImp(a);
		cs.sort();
		cs.printArray();
	}
}

class CyclicSortImp{
	int a[];
	CyclicSortImp(int a[]){
		this.a=a;
	}
	
	void sort() {
		
		int index = 0;
		
		while(index!=a.length-1) {
			if(index+1==a[index]) {
				index++;
			}
			int temp = a[index];
			a[index]=a[a[index]-1];
			a[temp-1]=temp;

		}
	}
	
	void printArray() {
		System.out.println(java.util.Arrays.toString(a));
	}
}



