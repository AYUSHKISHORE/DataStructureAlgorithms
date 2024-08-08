package Hashing;

import java.util.*;
import java.util.LinkedList;
import java.util.ArrayList;

public class DirectChaining {

	public static void main(String []args) {
		DirectChainingImplementation dc = new DirectChainingImplementation(5);
		dc.insertHashTable("The");
		dc.insertHashTable("quick");
		dc.insertHashTable("brown");
		dc.insertHashTable("fox");
		dc.insertHashTable("over");
		dc.insertHashTable("ox");
		dc.displayHashTable();
		
		dc.SearchWord("quick");
		dc.deleteKeyHashTable("quick");
		dc.SearchWord("quick");
		
		dc.displayHashTable();
	
	}
}


class DirectChainingImplementation{
	LinkedList<String>[] hashTable;
	int lastUsedCell;
	DirectChainingImplementation(int size) {
		hashTable = new LinkedList[size];
		lastUsedCell = 0;
	}
	
	
	private int ModASCII(String word, int length) {
		char ch[] = word.toCharArray();
		int sum=0;
		
		for(int i=0;i<ch.length;i++) {
			sum+=ch[i];
		}
		return sum%length;
	}
	
	public void insertHashTable(String value) {
		int index = ModASCII(value,hashTable.length);
		if(hashTable[index]==null) {
			hashTable[index] = new LinkedList<String>();
			hashTable[index].add(value);
		}else {
			hashTable[index].add(value);
		}
		System.out.println("value added = "+value);
	}
	
	public void displayHashTable() {
		if(hashTable == null) {
			System.out.println("Hashtable is empty");
			return;
		}else {
			for(int i=0;i<hashTable.length;i++) {
				System.out.println("index = "+i+" value = "+hashTable[i]);
			}
			return;
		}
	}
	
	public boolean SearchWord(String word) {
		int index = ModASCII(word, hashTable.length);
		if(hashTable!=null && hashTable[index]!=null && hashTable[index].contains(word)) {
			System.out.println("Word found in hashTable at loc = "+index);
			return true;
		}
		else {
			System.out.println("Word = "+word+"  not found");
			return false;
		}
	}
	
	public void deleteKeyHashTable(String word) {
		boolean found = SearchWord(word);
		if(found) {
			int index = ModASCII(word,hashTable.length);
			hashTable[index].remove(word);
			System.out.println("Word deleted = "+word);
		}else {
			System.out.println("Word not found");
		}
	}
}