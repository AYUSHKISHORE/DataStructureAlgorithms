package Arrays;

// Here we need to find if any element exist more than N/2 times in array
//time complexity = O(2N) and Space Complexity = O(1)
public class MajorityElement {
	
	public static void main(String[]args) {
		//Using boyer moore voting algorithm
		
		int a[]= new int[] {1,1,1,1,2,3,4};
		int element = BoyerMooreVotingAlgorithm(a);
		System.out.println(element);
	}
	
	public static int BoyerMooreVotingAlgorithm(int a[]) {
		int candidate = -1;
		int count =0;
		
		for(int i=0;i<a.length;i++) {
			if(count == 0) {
				candidate = a[i];
				count = 1;
			}else {
				if(candidate == a[i]) {
					count++;
				}else {
					count--;
				}
			}
		}
		count = 0;
		
		for(int i=0;i<a.length;i++) {
			if(candidate == a[i]) {
				count++;
			}
			if(count>a.length/2) {
				return candidate;
			}
		}
		return -1;
	}

}
