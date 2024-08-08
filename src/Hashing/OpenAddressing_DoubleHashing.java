package Hashing;

import java.util.ArrayList;

public class OpenAddressing_DoubleHashing {
	public static void main(String []args) {
		DoubleHashing ht = new DoubleHashing(5);
		ht.insertHashTable("The");
		ht.insertHashTable("quick");
		ht.insertHashTable("brown");
		ht.insertHashTable("fox");
		ht.insertHashTable("over");
		ht.insertHashTable("ox");
		ht.display();
		
		ht.search("quick");
		ht.search("abcd");
		ht.delete("quick");
		ht.search("quick");
		
		ht.display();
	}
}

class DoubleHashing{
	String hashTable[];
	int usedCellNumber;
	DoubleHashing(int size){
		hashTable = new String[size];
		usedCellNumber=0;
	}
	
	private int ModASCIIMethod1(String word, int size) {
		char[] ch = word.toCharArray();
		int sum=0;
		
		for(int i=0;i<word.length();i++) {
			sum+=ch[i];
		}
		return sum%size;
	}
	
	private int ModASCIIMethod2(String word, int size) {
		char[] ch = word.toCharArray();
		int sum = 0;
		for(int i=0;i<word.length();i++) {
			sum+=ch[i];
		}
		int digitSum = getDigitSum(sum);
		return digitSum%sum;
	}
	private int getDigitSum(int sum) {
		int digitSum=0;
		while(sum>0) {
			int val = sum%10;
			digitSum+=val;
			sum=sum/10;
		}
		return digitSum;
	}
	
	private void rehashkeys(String word) {
		ArrayList<String> arr = new ArrayList<String>();
		for(String s: hashTable) {
			if(s!=null) {
				arr.add(s);
			}
		}
		arr.add(word);
		//Setting the usedCellNumber to 0 and increasing the size of hashTable
		usedCellNumber=0;
		hashTable= new String[hashTable.length*2];
		for(String s: arr) {
			insertHashTable(s);
		}
	}
	
	private double getLoadFactor(int usedCells, int size) {
		return (usedCells * 1.0)/size;
	}
	
	public void insertHashTable(String word) {
		double loadFactor = getLoadFactor(usedCellNumber,hashTable.length);
		if(loadFactor>=0.75) {
			rehashkeys(word);
		}else {
			int x = ModASCIIMethod1(word,hashTable.length);
			int y = ModASCIIMethod2(word,hashTable.length);
			for(int i=0;i<hashTable.length;i++) {
				int newIndex=(x+(i*y))%hashTable.length;
				if(hashTable[newIndex]==null) {
					hashTable[newIndex]=word;
					System.out.println("word ="+word+" inserted at index = "+newIndex);
					break;
				}else {
					System.out.println("Index = "+newIndex+" already occupied");
				}
			}
			usedCellNumber++;
		}
		
	}
	
	public boolean search(String word) {
		System.out.println("wordTobeSearched = "+word);
		int x = ModASCIIMethod1(word,hashTable.length);
		int y = ModASCIIMethod2(word,hashTable.length);
		for(int i=0;i<hashTable.length;i++) {
			int newIndex = (x + (i*y))%hashTable.length;
			if(hashTable[newIndex]!=null && hashTable[newIndex].contains(word)) {
				System.out.println("found word = "+word+" at index ="+newIndex);
				return true;
			}
		}
		System.out.println("word "+ word +" doesn't exist");

		return false;
	}
	
	public void delete(String word) {
		boolean found = search(word);
		if(found) {
			int x = ModASCIIMethod1(word,hashTable.length);
			int y = ModASCIIMethod2(word,hashTable.length);
			for(int i=0;i<hashTable.length;i++) {
				int newIndex = (x + (i*y))%hashTable.length;
				if(hashTable[newIndex]!=null && hashTable[newIndex].contains(word)) {
					hashTable[newIndex]=null;
					return;
				}
			}
		}else {
			System.out.println("word "+ word +" doesn't exist so can't be deleted");
		}
	}
	
	public void display() {
		System.out.println("*********DISPLAY HASHTABLE*********");
		for(String s:hashTable) {
			System.out.println(s);
		}
		
		System.out.println("*********************************");
	}

}
