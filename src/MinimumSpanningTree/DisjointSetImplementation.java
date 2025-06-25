package MinimumSpanningTree;

import java.util.ArrayList;
import java.util.HashMap;

public class DisjointSetImplementation {
	public static void main(String[]args) {
		ArrayList<WeightedNode> nodeList = new ArrayList<>();
		nodeList.add(new WeightedNode("A",0));
		nodeList.add(new WeightedNode("B",1));
		nodeList.add(new WeightedNode("C",2));
		nodeList.add(new WeightedNode("D",3));
		
		DisjointSet.makeSet(nodeList);
		WeightedNode first = nodeList.get(0);
		WeightedNode second = nodeList.get(1);
		DisjointSet output = DisjointSet.findSet(second);
		System.out.println("output"+output);
		output.printAllNodesOfSet();
		
		DisjointSet.union(first, second);
		output = DisjointSet.findSet(second);
		output.printAllNodesOfSet();
	}
}


class WeightedNode{
	String name;
	int index;
	int distance;
	WeightedNode parent;
	boolean isVisited;
	ArrayList<WeightedNode> neighbors = new ArrayList<>();
	HashMap<WeightedNode, Integer> weightMap = new HashMap<>();
	DisjointSet set;
	WeightedNode(String name, int index){
		this.name=name;
		this.index=index;
		distance=Integer.MAX_VALUE/10; //avoid overflow
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}


class DisjointSet{
	 ArrayList<WeightedNode> nodeList = new ArrayList<>();
	
	static void makeSet(ArrayList<WeightedNode> nodeList) {
		for(WeightedNode node : nodeList) {
			DisjointSet set = new DisjointSet();
			set.nodeList.add(node);
			node.set=set;
		}
	}
	
	static DisjointSet findSet(WeightedNode node) {
		return node.set;
	}
	
	static DisjointSet union(WeightedNode node1, WeightedNode node2) {
		if(node1.set.equals(node2.set)) {
			return null;
		}else {
			DisjointSet set1 = node1.set;
			DisjointSet set2 = node2.set;
			if(set1.nodeList.size()>set2.nodeList.size()) {
				for(WeightedNode node : set2.nodeList) {
					set1.nodeList.add(node);
					node.set=set1;
				}
				return set1;
			}else {
				for(WeightedNode node : set1.nodeList) {
					set2.nodeList.add(node);
					node.set=set2;
				}
				return set2;
			}
		}
		
	}
	 void printAllNodesOfSet() {
		System.out.println("Printing all nodes of the set");
		for(WeightedNode node : nodeList) {
			System.out.print(node + " ");
		}
		System.out.println();
	}
	
}