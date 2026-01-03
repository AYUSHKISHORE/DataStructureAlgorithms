package DivideAndConquer;


/* Problem
 * We need count zeros in given array
 * -> note zeros are followed by 1
 * Eg [1,1,1,1,1,1,0,0,0]
 * 
 */
public class CountZero1 {
	public static void main(String []args) {
		int[] array = {1,1,1,1,1,1,0,0,0};
		CtZeros ct = new CtZeros();
		System.out.println("Total Zeros = "+ct.count(array));
	}
}

class CtZeros{
	public int count(int[] array) {
		
		int left = 0;
		int right = array.length-1;
		
		while(left<=right) {
			
			int mid = (int) left + (right - left) / 2;
			
			if(array[mid]==1) {
				left = mid + 1;
			}else {
				right = mid -1;
			}
			
		}
		return array.length - left;
	}
}
