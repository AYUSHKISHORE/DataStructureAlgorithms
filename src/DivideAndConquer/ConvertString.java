package DivideAndConquer;

/*
 * Problem
 *  - S1 & S2 are given strings
 *  - Convert S2 to S1 using delete, insert & replace operation
 *  - Find Min count of edit operation
 *  
 *  
 *  Example1)
 *  - S1 = "catch"
 *  - S2 = "carch"
 *  
 *  O/P = 1 {explanation: Replace r with t}
 *  
 *  
 *  
 *  S1 = table
 *  S2 = tgable
 *  deleteOperation =  f(2,3)
 *  
 *  S1 = table
 *  S2 = tble
 *  insertOperation = f(3,2)
 *  
 *  S1 = table
 *  S2 = tcble 
 *  replaceOperation = f(3,3)
 *  
 */
public class ConvertString {
	public static  void main(String[]args) {
		ConvertOneStrToAnotherStr ctr = new ConvertOneStrToAnotherStr();
		System.out.println(ctr.findMinOperation("table", "tcbles"));
		
	}
}

class ConvertOneStrToAnotherStr{
	private int findMinOperation(String s1, String s2, int index1, int index2) {
		
		if(index1==s1.length()) { // This means string1 is end , so we need to delete rest string2
			return s2.length()- index2;
		}
		
		if(index2 == s2.length()) {
			return s1.length() - index1;
		}
		
		if(s1.charAt(index1)==s2.charAt(index2)) {
			return findMinOperation(s1,s2,index1+1, index2+1);
		}
		
		int deleteOpr  = 1 + findMinOperation(s1,s2,index1,index2+1);
		int insertOpr  = 1 + findMinOperation(s1,s2,index1+1,index2);
		int replaceOpr = 1 + findMinOperation(s1,s2,index1+1,index2+1);
		
		return Math.min(deleteOpr, Math.min(insertOpr, replaceOpr));
	}
	
	public int findMinOperation(String s1, String s2) {
		return this.findMinOperation(s1, s2, 0, 0);
	}
}