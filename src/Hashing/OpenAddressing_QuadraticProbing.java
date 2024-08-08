package Hashing;
import java.util.*;

public class OpenAddressing_QuadraticProbing {
	public static void main(String []args) {
		QuadraticProbing ht = new QuadraticProbing(5);
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

class QuadraticProbing{
	String hashTable[];
	int usedCellNumber;
	QuadraticProbing(int size){
		hashTable = new String[size];
		usedCellNumber=0;
	}
	
	private int ModASCII(String word, int size) {
		char[] ch = word.toCharArray();
		int sum=0;
		
		for(int i=0;i<word.length();i++) {
			sum+=ch[i];
		}
		return sum%size;
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
			int index = ModASCII(word,hashTable.length);
			int counter = 0;
			for(int i=index;i<index+hashTable.length;i++) {
				int newIndex=(index+(counter*counter))%hashTable.length;
				if(hashTable[newIndex]==null) {
					hashTable[newIndex]=word;
					System.out.println("word ="+word+" inserted at index = "+newIndex);
					break;
				}else {
					System.out.println("Index = "+newIndex+" already occupied");
				}
				counter++;
			}
			usedCellNumber++;
		}
		
	}
	
	public boolean search(String word) {
		System.out.println("wordTobeSearched = "+word);
		int index = ModASCII(word,hashTable.length);
		int counter =0;
		for(int i=index;i<index+hashTable.length;i++) {
			int newIndex = (index + (counter*counter))%hashTable.length;
			if(hashTable[newIndex]!=null && hashTable[newIndex].contains(word)) {
				System.out.println("found word = "+word+" at index ="+newIndex);
				return true;
			}else {
				if(counter>hashTable.length) {
					System.out.println("word "+ word +" doesn't exist");
					break;
				}
				
				counter++;
			}
		}
		return false;
	}
	
	public void delete(String word) {
		boolean found = search(word);
		if(found) {
			int index = ModASCII(word,hashTable.length);
			int counter =0;
			for(int i=index;i<index+hashTable.length;i++) {
				int newIndex = (index + (counter*counter))%hashTable.length;
				if(hashTable[newIndex]!=null && hashTable[newIndex].contains(word)) {
					hashTable[newIndex]=null;
					return;
				}else {
					if(counter>hashTable.length) {
						System.out.println("word "+ word +" doesn't exist");
						break;
					}
					
					counter++;
				}
			}
			return;
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
