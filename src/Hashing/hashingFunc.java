package Hashing;

public class hashingFunc {
	
	public static void main(String []args) {
		
		System.out.println("Int Mod Answer = "+IntModMethod(5,5));
		System.out.println("String Mod Answer = "+StringModASCIIMethod1("ABCD",5));
		System.out.println("String Mod Answer = "+StringModASCIIMethod2("CDEF",5));
		
		
		
	}
	
	public static int IntModMethod(int number , int cellNumber) {
		return number%cellNumber;
	}
	
	public static int StringModASCIIMethod1(String word, int cellNumber) {
		
		int number=0;
		for(int i=0;i<word.length();i++) {
			number+=word.charAt(i);
		}
		return number%cellNumber;
	}
	
	public static int StringModASCIIMethod2(String word, int cellNumber) {
		int number = 0;
		char ch[]=word.toCharArray();
		for(int i=0;i<ch.length;i++) {
			number+=ch[i];
		}
		
		return number%cellNumber;
	}

}
