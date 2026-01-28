package DivideAndConquer;

/*
 * Divide and Conquer - findRotatedIndex

Write a function which accepts a rotated array of sorted numbers and an integer. The function should return the index of the integer in the array. If the value is not found, return -1.

Constraints:


    findRotatedIndex([3, 4, 1, 2], 4) # 1
    findRotatedIndex([4, 6, 7, 8, 9, 1, 2, 3, 4], 8) # 3
    findRotatedIndex([6, 7, 8, 9, 1, 2, 3, 4], 3) # 6
    findRotatedIndex([37, 44, 66, 102, 10, 22], 14) # -1
    findRotatedIndex([6, 7, 8, 9, 1, 2, 3, 4], 12) # -1
    findRotatedIndex([11, 12, 13, 14, 15, 16, 3, 5, 7, 9], 16) # 5
    findRotatedIndex([11, 12, 13, 17, 39], 17) # 3
    findRotatedIndex([11], 11) # 0
    findRotatedIndex([], 11) # -1
    findRotatedIndex([4, 4, 4, 4, 4], 5) # -1
 * 
 * 
 * 
	Time Complexity - O(log n)
	
	Space Complexity - O(1)
 * 
 */


public class RotatedIndex {
	public static void main(String []args) {
		
		FindIndex fi = new FindIndex();
		
		
		int[] array1 = {3,4,1,2};
		int number1 = 1;
		
		System.out.println(fi.search(array1, number1));
		
		int[] array2 = {6,7,8,9,1,2,3,4};
		int number2 = 12;
		
		System.out.println(fi.search(array2, number2));
		
	}
}

class FindIndex{
	
	public int search(int[]array , int num) {
		
		int left = 0;
		int right = array.length-1;
		
		while(left<=right) {
			int mid = (int) left + (right-left)/2;
			
			if(array[mid]==num) {
				return mid;
			}
			
			if(array[left]<=array[mid]) {
				
				if(array[left]<=num && num < array[mid]) {
					right = mid - 1;
				}else {
					left = mid + 1;
				}
			}else {
				if(array[mid]<num && num <= array[right]) {
					left = mid + 1;
				}else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}
	
}

