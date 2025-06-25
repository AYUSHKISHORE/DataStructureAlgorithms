package MinimumSpanningTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Prims {
	public static void main(String []args) {
		ArrayList<WeightedNodePrims> nodeList = new ArrayList<>();
		nodeList.add(new WeightedNodePrims("A",0));
		nodeList.add(new WeightedNodePrims("B",1));
		nodeList.add(new WeightedNodePrims("C",2));
		nodeList.add(new WeightedNodePrims("D",3));
		nodeList.add(new WeightedNodePrims("E",4));
		
		PrimsAlgorithm graph = new PrimsAlgorithm(nodeList);
		graph.addWeightedUndirectedEdge(0, 1, 5);
		graph.addWeightedUndirectedEdge(0, 2, 13);
		graph.addWeightedUndirectedEdge(0, 4, 15);
		graph.addWeightedUndirectedEdge(1, 2, 10);
		graph.addWeightedUndirectedEdge(1, 3, 8);
		graph.addWeightedUndirectedEdge(2, 3, 6);
		graph.addWeightedUndirectedEdge(2, 4, 20);
		System.out.println("Running Prims Algorithm");
		graph.PrimsAlgo(nodeList.get(0));
	}
}
class DisjointSetPrims{
	ArrayList<WeightedNodePrims> nodeList = new ArrayList<>();
	public static void makeSet(ArrayList<WeightedNodePrims> nodeList) {
		for(WeightedNodePrims node : nodeList) {
			DisjointSetPrims set = new DisjointSetPrims();
			set.nodeList.add(node);
			node.set = set;
		}
	}
	
	public static DisjointSetPrims findSet(WeightedNodePrims node) {
		return node.set;
	}
	
	public static DisjointSetPrims union(WeightedNodePrims firstNode, WeightedNodePrims secondNode) {
		if(firstNode.set.equals(secondNode.set)) {
			return null;
		}else {
			if(firstNode.set.nodeList.size()>secondNode.set.nodeList.size()) {
				for(WeightedNodePrims node : secondNode.set.nodeList) {
					firstNode.set.nodeList.add(node);
					node.set=firstNode.set;
				}
				return firstNode.set;
			}else {
				for(WeightedNodePrims node : firstNode.set.nodeList) {
					secondNode.set.nodeList.add(node);
					node.set=secondNode.set;
				}
				return secondNode.set;
			}
		}
	}
	
	void printAllNodesOfSet() {
		System.out.println("Printing all nodes of the set!!!");
		for(WeightedNodePrims node : nodeList) {
			System.out.print(node+" ");
		}
		System.out.println();
	}	
	
}

class PrimsUndirectedEdge{
	WeightedNodePrims first;
	WeightedNodePrims second;
	int weight;
	
	PrimsUndirectedEdge(WeightedNodePrims first, WeightedNodePrims second, int weight){
		this.first = first;
		this.second = second;
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return "Edge ("+first+" , "+second+") weight = "+weight;
	}
}


//WeightedNode
class WeightedNodePrims implements Comparable<WeightedNodePrims>{
	String name;
	int index;
	WeightedNodePrims parent;
	boolean isVisited;
	int distance;
	ArrayList<WeightedNodePrims> neighbors = new ArrayList<WeightedNodePrims>();
	HashMap<WeightedNodePrims, Integer> weightMap = new HashMap<>();
	DisjointSetPrims set;
	
	WeightedNodePrims(String name, int index){
		this.name = name;
		this.index = index;
		distance = Integer.MAX_VALUE/10;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int compareTo(WeightedNodePrims second) {
		return this.distance -second.distance;
	}
}

class PrimsAlgorithm{
	ArrayList<WeightedNodePrims> nodeList = new ArrayList<>();
	
	PrimsAlgorithm(ArrayList<WeightedNodePrims> nodeList){
		this.nodeList=nodeList;
	}
	
	public void addWeightedUndirectedEdge(int i, int j , int d) {
		WeightedNodePrims first = nodeList.get(i);
		WeightedNodePrims second = nodeList.get(j);
		first.neighbors.add(second);
		second.neighbors.add(first);
		
		first.weightMap.put(second, d);
		second.weightMap.put(first, d);
		
		PrimsUndirectedEdge edge = new PrimsUndirectedEdge(nodeList.get(i),nodeList.get(j),d);
	}
	
	public void PrimsAlgo(WeightedNodePrims node) {
		//Making the first Index Weight as 0 && (all other index as infinity - which already done while making node)
		node.distance=0;
		PriorityQueue<WeightedNodePrims> queue = new PriorityQueue<>();
		queue.addAll(nodeList);
		while(!queue.isEmpty()) {
			WeightedNodePrims curr = queue.remove();
			for(WeightedNodePrims neighbor : curr.neighbors) {
				if(queue.contains(neighbor)) {
					if(neighbor.distance>  curr.weightMap.get(neighbor)) {
						neighbor.distance = curr.weightMap.get(neighbor);
						neighbor.parent = curr;
						neighbor.isVisited=true;
						queue.remove(neighbor);
						queue.add(neighbor);
					}
				}
			}
		}
		int cost = 0;
		for(WeightedNodePrims nodeToCheck : nodeList) {
			System.out.println("Node: "+nodeToCheck+" ,Key: "+ nodeToCheck.distance +" ,Parent: "+nodeToCheck.parent);
			cost +=nodeToCheck.distance;
		}
		System.out.println("\nTotal Cost of MST: "+ cost);
		
	}
}


