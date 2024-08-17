package Sorting;

/*
 * QuickSort
 * 
 * Quicksort is a divide and conquer algorithm
 * Find the pivot number and make sure smaller number located at the left of pivot element & bigger numbers are located at the right of the pivot number
 * 
 * Unlike merge sort extra space is not required.
 *  
 * Here we are taking right most element as pivot element.
 * 
 *  if L<P -> then L++ will continue
 *  if L>P -> then R++ will continue
 *  if R<P then swap(L,R) then L++
 *  if L==R then stop (swap (L,P))
 *  
 *  arr = [3 5 8 1 2 9 4 7 6]
 *  
 *  lowIndex=0
 *  high= 8
 *  i=-1
 *  pivot = 6
 *  
 *  [j=lowIndex{0}, 3<6, i++, swap(a[i],a[j])==> swap(3,3)] => [3 5 8 1 2 9 4 7 6]
 *  [j=1,i=0, 5<6 , i++{i=1}, swap(a[i],a[j])==> swap(5,5)] => [3 5 8 1 2 9 4 7 6]
 *  [j=2,i=1, 8>6 continue]									=> [3 5 8 1 2 9 4 7 6]
 *  [j=3,i=1, 1<6 i++{i=2}, swap(a[i],a[j])==> swap(1,8)]	=> [3 5 1 8 2 9 4 7 6]
 *  [j=4,i=2, 2<6 i++{i=3}, swap(a[i],a[j])==> swap(8,2)]   => [3 5 1 2 8 9 4 7 6]
 *  [j=5,i=3 9>6 continue]									=> [3 5 1 2 8 9 4 7 6]
 *  [j=6,i=3 4<6 i++{i=4}, swap(a[i],a[j])==> swap(8,4)]	=> [3 5 1 2 4 9 8 7 6]
 *  [j=7,i=4 7>6 continue]									=> [3 5 1 2 4 9 8 7 6]
 *  [j=8,i=4 continue] loop end								=> [3 5 1 2 4 9 8 7 6]
 *  
 *  replace (i+1) with pivot element
 *  return index of pivot element with i+1 =5 (here)
 *  
 *  repeat the step with
 *  (a,low,pivot-1)
 *  (a,pivot+1,high)
 *  
 */


//Time complexity - O(nlogn)
//Space complexity - O(n) -> due to recursive stack
//Outspace and Unstable alogrithm
public class QuickSort {
	
	public static void main(String []args) {
		int a[] = {3,5,8,1,2,9,4,7,6};
		QuickSortImp qs = new QuickSortImp(a);
		qs.sort();
		qs.printArray();
	}
}

class QuickSortImp{
	int a[];
	QuickSortImp(int a[]){
		this.a=a;
	}
	
	void sort() {
		int low = 0;
		int high = a.length-1;
		quickSort(a,low,high);
	}
	
	private void quickSort(int a[],int low ,int high) {
		if(low<high) {
			int partitionIndex = partition(a,low,high);
			quickSort(a,low, partitionIndex-1);
			quickSort(a,partitionIndex+1,high);
		}
	}
	
	private static int partition(int a[],int low, int high) {
			int i=low-1;
			int pivot = a[high];
			
			for(int j=low;j<=high;j++) {
				if(a[j]<pivot) {
					i++;
					int temp = a[i];
					a[i]=a[j];
					a[j]=temp;
				}
			}
			//Now swap pivot element with 1st gt number
			int temp = a[i+1];
			a[i+1]=a[high];
			a[high]=temp;
			return i+1;
	}
	
	public void printArray() {
		System.out.println(java.util.Arrays.toString(a));
	}
}