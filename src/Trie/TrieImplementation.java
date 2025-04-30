package Trie;


import java.util.*;
import java.util.HashMap;
import java.util.Map;

/*
 *  A trie is a tree based datastructure the organizes information in a hierarchy
 *  
 *  
 *  Properties 
 *  1) It is typically used to store or search string in a space & time efficient way.
 *  2) Any node in trie can store non repetitive multiple characters.
 *  			[AB]
 *  		[T]		 [IB]
 *  	[*]				  [*]
 *  
 *  Strings are AT, BI, BB
 *  
 *   Every node stores link of the next character of the string
 *   Every node keeps track of 'end of string'
 *   
 *   when we work with string our main objective is find the string thats why we use trie
 *   
 *   Trie is mostly used in 
 *   	SearchEngine -> Word Suggestion
 *   	Spelling Checker
 *   
 *   
 *   				   	[AB]
 *   			   [I]  		[A I  M]
 *  			[R   T]	  	  [R] [L]  [*]	
 *  		   [*]    [*]    [*]  [*]
 * 
 * Strings are -> AIR , AIT , BAR, BIL, BM
 * 
 * 
 * 	Node are physically looklike
 * 		NODE [
 * 				map[character]=LinkToNextNodeChildren
 * 				EndOfString boolean
 * 			 ]
 * 
 * 
 * Operation on trie
 * 	Creation of trie
 * 	Insertion in trie
 * 	Search for a string
 * 	Delete a string from trie
 * 
 * 
 * Deletion of a string from trie has 4 cases
 * 	1) API & APPLE (DELETE -> API) -> when there is a common character AP
 *  2) API & APIS & APPLE (DELETE -> API) -> When word to be deleted is a prefix of another word
 *  3) API & APIS & APPLE (DELETE -> APIS) -> When word to be deleted has prefix 
 *  4) K & APPLE (DELETE -> K) -> Not dependent string
 */
public class TrieImplementation {

	public static void main(String []args) {
		Trie th1 = new Trie();
		th1.insert("API");
		th1.insert("APPLE");
		th1.insert("API");
		th1.insert("APIS");
		th1.Search("API");
		th1.Search("APM");
		th1.Search("AP");
		
		System.out.println("DELETION CASES");
		Trie th2 = new Trie();
		System.out.println("Case 1) Word1= API & Word2=APPLE & DeletedWord =API");
		//Case 1) Word1= API & Word2=APPLE & DeletedWord = "API"
		th2.insert("API");
		th2.insert("APPLE");
		th2.delete("API");
		th2.Search("API");//Doesn't exist
		th2.Search("APPLE");//Should exist
		
		System.out.println("Case 2) Word1= API & Word2=APPLE & Word3=APIS & DeletedWord = API");
		//Case 2) Word1= API & Word2=APPLE & Word3=APIS & DeletedWord = "API";
		th2.insert("API");
		th2.insert("APIS");
		th2.delete("API");
		th2.Search("API");//Doesn't exist but word is present as prefix
		th2.Search("APIS");//Should exist;
		th2.Search("APPLE");
		
		System.out.println("Case 3) Word1=API & Word2=APPLE & Word3= APIS & DeletedWord = \"APIS\";");
		//Case 3) Word1=API & Word2=APPLE & Word3= APIS & DeletedWord = "APIS";
		th2.insert("API");
		th2.delete("APIS");
		th2.Search("API");//Should exist
		th2.Search("APPLE");//Should exist
		th2.Search("APIS");// Doesn't exist
		
		System.out.println("Case 4) Word1=API & Word2=APPLE & Word3= APIS & Word4=K DeletedWord = \"K\"");
		//Case 4) Word1=API & Word2=APPLE & Word3= APIS & Word4=K DeletedWord = "K";
		th2.insert("APIS");
		th2.insert("K");
		th2.delete("K");
		th2.Search("APPLE");//Should Exist
		th2.Search("API");//Should exist;
		th2.Search("APIS");//Should exist;
		th2.Search("K");//doesn't exist;
		
		System.out.println("Case 5) Word1=API & Word2=APPLE & Word3= APIS  DeletedWord = \"B\"");
		//Case 5) Word1=API & Word2=APPLE & Word3= APIS  DeletedWord = "B";
		th2.delete("B");
	}
}

class TrieNode{
	Map<Character,TrieNode> children;
	boolean endOfString;
	TrieNode(){
		children = new HashMap<>();
		endOfString = false;
	}
	
}

class Trie{
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
		System.out.println("Trie has been created!!!");
	}
	
	public void insert(String value) {//TIMECOMPLEXITY - O(M) and SPACE COMPLEXITY - O(M)
		TrieNode current=root;
		int size=0;
		for(int i=0;i<value.length();i++) {
			Character ch = value.charAt(i);
			TrieNode node = current.children.get(ch);
			if(node == null) {
				size++;
				node = new TrieNode();
				current.children.put(ch, node);
			}
			current = node;
		}
		if(value.length()-size==0) {
			current.endOfString=true;
			System.out.println("Word is inserted = "+ value +"!!!");
		}else if(size==0) {
			current.endOfString=true;
			System.out.println("Word already exist = "+value);
		}else {
			current.endOfString=true;
			System.out.println("Word is added = "+value);
		}	
	}
	
	
	public boolean Search(String value) {//TIME COMPLEXITY - O(M) & SPACE COMPLEXITY - O(1)
		TrieNode current = root;
		for(int i=0;i<value.length();i++) {
			Character ch = value.charAt(i);
			TrieNode node = current.children.get(ch);
			if(node == null) {
				System.out.println("Word doesn't exists = "+ value +"!!!");
				return false;
			}
			current=node;
		}
		if(current.endOfString==true) {
			System.out.println("Value exist in trie = "+value);
			return true;
		}else {
			System.out.println("Word = "+value +" is the prefix of another word !!!");
			return false;
		}
	}
	
	private boolean deleteWord(TrieNode parent, String value, int index) {
		Character ch = value.charAt(index);
		TrieNode current = parent.children.get(ch);
		
		if(current.children.size()>1) {//Means currentNode has more than one child
			System.out.println("1 ="+ch);
			deleteWord(current,value,index+1);
			return false;
		}
		
		if(index==value.length()-1) {
			if(current.children.size()>=1) {// means value is a prefix of another string
				System.out.println("2 ="+ch);
				current.endOfString=false;
				return false;
			}else {
				System.out.println("3 ="+ch);
				parent.children.remove(ch);
				return true;
			}
		}
		
		if(current.endOfString==true) {//Word to be deleted has the prefix
			System.out.println("4 ="+ch);
			deleteWord(current,value,index+1);
			return false;
		}
		
		System.out.println("5 ="+ch);
		boolean canThisBeDeleted = deleteWord(current,value,index+1);
		System.out.println("canThisBeDeleted"+canThisBeDeleted);
		if(canThisBeDeleted) {// When word is not dependent on another node
			System.out.println("6 ="+current.toString());
			parent.children.remove(ch);
			return true;
		}else {
			System.out.println("7 ="+ch);
			return false;
		}
	}
	
	public void delete(String value) {
		if(Search(value)) {
			deleteWord(root,value,0);
			return;
		}else {
			System.out.println("Word = "+value+" doesn't exists");
		}
	}
	
	
}
