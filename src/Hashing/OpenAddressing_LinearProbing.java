package Hashing;
import java.util.ArrayList;

public class OpenAddressing_LinearProbing {

	public static void main(String []args) {
		LinearProbing ht = new LinearProbing(5);
		ht.insertHashTable("The");
		ht.insertHashTable("quick");
		ht.insertHashTable("brown");
		ht.insertHashTable("fox");
		ht.insertHashTable("over");
		ht.insertHashTable("ox");
		ht.display();
		
		ht.search("quick");
		ht.delete("quick");
		ht.search("quick");
		
		ht.display();
	}
}

class LinearProbing{
	String[] hashTable;
	int usedCellNumber;
	LinearProbing(int size){
		hashTable = new String[size];
		int usedCellNumber = 0;
	}
	
	private int ModASCII(String word, int size) {
		char[] ch = word.toCharArray();
		int sum=0;
		for(int i=0;i<ch.length;i++) {
			sum+=ch[i];
		}
		return sum%size;
	}
	
	private double getLoadFactor(int usedCells, int size) {
		return (usedCells * 1.0)/size;
	}
	
	private void rehashkeys(String word) {
		
		ArrayList<String> arr = new ArrayList<String>();
		for(String s : hashTable) {
			if(s!=null) {
				arr.add(s);	
			}
			
		}
		arr.add(word);
		usedCellNumber=0;
		hashTable = new String[hashTable.length*2];
		for(String s : arr) {
			insertHashTable(s);
		}
	}
	
	public void insertHashTable(String word) {
	    System.out.println("word to be inserted = "+word);
		double loadFactor = getLoadFactor(usedCellNumber, hashTable.length);
		if(loadFactor>=0.75) {
			rehashkeys(word);
		}else {
			int index = ModASCII(word,hashTable.length);
			for(int i=index;i<hashTable.length+index;i++) {// FOR CYCLIC ROTATION
				int newIndex = i%hashTable.length;
				if(hashTable[newIndex]==null) {
					hashTable[newIndex]=word;
					System.out.println("Word "+ word +" Inserted at position = "+newIndex);
					break;
				}else {
					System.out.println("Index = "+newIndex+" already occupied");
				}
			}
			usedCellNumber++;
			return;
		}
	}
	
	public boolean search(String word) {
		int index = ModASCII(word,hashTable.length);
		for(int i=index;i<hashTable.length+index;i++) {
			int newIndex=i%hashTable.length;
			if(hashTable!=null && hashTable[newIndex]!=null && hashTable[newIndex].contains(word)) {
				System.out.println("value found at index = "+newIndex);
				return true;
			}
		}
		System.out.println("Word = "+ word +" not found");
		return false;		
	}
	
	public void delete(String word) {
		boolean found = search(word);
		if(found) {
			int index = ModASCII(word,hashTable.length);
			for(int i=index;i<hashTable.length+index;i++) {
				int newIndex=i%hashTable.length;
				if(hashTable!=null && hashTable[newIndex]!=null && hashTable[newIndex].contains(word)) {
					hashTable[newIndex]=null;
					System.out.println("word = "+word+" deleted!!!");
					return;
				}
			}
		}else {
			System.out.println("Word doesn't exist in hashTable");
			return;
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