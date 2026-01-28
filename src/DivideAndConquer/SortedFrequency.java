package DivideAndConquer;

/*
 * Problem Statement
 *  -> Given a sorted array and a number, write a function  that counts the occurrences of the number in the array.

    sortedFrequency([1, 1, 2, 2, 2, 2, 3], 2) # 4
    sortedFrequency([1, 1, 2, 2, 2, 2, 3], 3) # 1
    sortedFrequency([1, 1, 2, 2, 2, 2, 3], 4) # -1
    sortedFrequency([], 4) # -1

Time Complexity - O(log n)
Space Complexity - O(1)
 * 
 * 
 */

public class SortedFrequency {
	public static void main(String []args) {
		
		CountOccurrence oc = new CountOccurrence();

		int[] array1 = {1,1,2,2,2,2,3};
		int number1 = 2;
		
		System.out.println(oc.countFreq(array1,number1)); //O/P = 4
		
		int[] array2 = {};
		int number2 = 4;
		
		System.out.println(oc.countFreq(array2,number2)); //O/P = -1;
		
		int[] array3 = {1,1,2,2,2,2,3};
		int number3 = 4;
		
		System.out.println(oc.countFreq(array3,number3)); //O/P = -1;
		
	}
}

class CountOccurrence{
	
	public int countFreq(int[] array, int number) {
		
		int left = 0;
		int right = array.length-1;
		
		while(left<=right) {
			int mid =(int) left + (right-left) / 2;
			
			if(array[mid]==number) {
				
				int leftIdx = mid;
				int rightIdx = mid;
				
				while(array[leftIdx]==number && leftIdx>=0) {
					leftIdx-=1;
				}
				
				while(array[rightIdx]==number && rightIdx<array.length) {
					rightIdx+=1;
				}
				return rightIdx - leftIdx - 1;
			}
			
			if(array[mid]<number) {
				left=mid+1;
			}else if(array[mid]>number) {
				right=mid-1;
			}
		}
		return -1;
		
	}
}
