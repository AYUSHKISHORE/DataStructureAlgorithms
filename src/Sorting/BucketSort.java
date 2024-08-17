package Sorting;

import java.util.*;


/*
 * In Bucket Sort -> We make bucket based on the number of element is array and then divide 
 * the element in the bucket.
 * 
 * Then sort each bucket and combine the bucket serial wise
 * 
 * Nos of Bucket = round(sqrt(array.length))
 * 
 * Ideal Bucket = ceil(number*NosOfBucket/MaxNumber)
 * 
 * 
 *  5 3 4 7 2 8 6 9 1
 *  
 *  Nos of bucket = sqrt(9) = 3
 *  
 *  [3 2 1]		[5 4 6]		[7 8 9]
 *  Bucket1		Bucket2		Bucket3
 *  
 *  --> then sort
 *  
 *  [1 2 3]		[4 5 6]		[7 8 9]
 *  Bucket1		Bucket2		Bucket3
 * 
 * Merge -> [1 2 3 4 5 6]
 * 
 * When to use bucket sort?
 * -> when i/p is uniformly distributed like 1 2 4 5 3 8 7 9
 * Not like -> 1 2 4 91 93 95
 * 
 * *** It is stable/Unstable sort based bucket sorting (Eg here -> Collections.sort(smallBucket[i]);)
 * 
 * Time Complexity - O(nlogn)
 * Space Complexity - O(n)
 */
public class BucketSort {

	public static void main(String []args) {
		int a[]= {5,1,4,3,6,7};
		BucketSortImp bs = new BucketSortImp(a);
		bs.sort();
		bs.printArray();
	}
}

class BucketSortImp{
	int a[];
	
	BucketSortImp(int a[]){
		this.a=a;
	}
	
	void sort() {
		int maxElement=findMaxElement(a);
		int bucketSize = (int)Math.ceil(Math.sqrt(a.length));
		
		ArrayList<Integer>[] smallBucket = new ArrayList[bucketSize];
		
		for(int j=0;j<a.length;j++) {
			int idealBucket = (int)Math.ceil(a[j]*((float)bucketSize/(float)maxElement));
			if(smallBucket[idealBucket-1]==null) {
				smallBucket[idealBucket-1]= new ArrayList<Integer>();
				smallBucket[idealBucket-1].add(a[j]);
			}else {
				smallBucket[idealBucket-1].add(a[j]);
			}
		}
		for(int i=0;i<bucketSize;i++) {
			Collections.sort(smallBucket[i]);
		}
		
		int index=0;
		for(int i=0;i<bucketSize;i++) {
			for(int j=0;j<smallBucket[i].size();j++) {				
				a[index]=smallBucket[i].get(j);
				index++;
			}
		}
		
	}
	
	private static int findMaxElement(int[] a) {
		int maxElement = Integer.MIN_VALUE;
		for(int i=0;i<a.length;i++) {
			if(a[i]>maxElement) {
				maxElement=a[i];
			}
		}
		return maxElement;
	}
	
	void printArray() {
		System.out.println(java.util.Arrays.toString(a));
	}
}
