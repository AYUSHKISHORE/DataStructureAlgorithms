package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * Incase of SSSPP
 * TIME COMPLEXITY - O(V) * Adjacent Neighbors i.e ( O(V*E)
 * SPACE COMPLEXITY - O(V) - Size of queue
 * 
 * Incase of All pair shortest path problem
 * TIME COMPLEXITY - O(V) * Adjacent Neighbors i.e ( O(V^2 *E)
 * SPACE COMPLEXITY - O(VE) - Size of queue
 * 
 * Best for weighted graph , can also work for unweighted graph (but didn't work for -ve cycle)
 */

public class DijikstraUsingAdjacencyList {

	public static void main(String []args) {
		ArrayList<WeightedNode> nodeList = new ArrayList<>();
		nodeList.add(new WeightedNode("A",0));
		nodeList.add(new WeightedNode("B",1));
		nodeList.add(new WeightedNode("C",2));
		nodeList.add(new WeightedNode("D",3));
		nodeList.add(new WeightedNode("E",4));
		nodeList.add(new WeightedNode("F",5));
		nodeList.add(new WeightedNode("G",6));
		
		
		WeightedGraph graph = new WeightedGraph(nodeList);
		graph.addWeightedEdge(0, 1, 2);
		graph.addWeightedEdge(0, 2, 5);
		graph.addWeightedEdge(2, 5, 8);
		graph.addWeightedEdge(1, 2, 6);
		graph.addWeightedEdge(1, 3, 1);
		graph.addWeightedEdge(1, 4, 3);
		graph.addWeightedEdge(3, 4, 4);
		graph.addWeightedEdge(4, 6, 9);
		graph.addWeightedEdge(5, 6, 7);
		graph.addWeightedEdge(0, 1, 2);
		
		System.out.println("Printing Dijikstra from Source A");
		graph.dijikstra(nodeList.get(0));
	}
}

class WeightedNode implements Comparable<WeightedNode>{
	String name;
	int index;
	ArrayList<WeightedNode> neighbors = new ArrayList<WeightedNode>();
	HashMap<WeightedNode, Integer> weightMap = new HashMap<>();
	int distance;
	WeightedNode parent;
	
	WeightedNode(String name, int index){
		this.name = name;
		this.index = index;
		this.distance = Integer.MAX_VALUE;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int compareTo(WeightedNode o) {
		return this.distance - o.distance;
	}
	
}

class WeightedGraph{
	ArrayList<WeightedNode> nodeList = new ArrayList<>();
	
	public WeightedGraph(ArrayList<WeightedNode> nodeList) {
		this.nodeList = nodeList;
	}
	
	public void addWeightedEdge(int i, int j, int distance) {
		WeightedNode first = nodeList.get(i);
		WeightedNode second = nodeList.get(j);
		first.neighbors.add(second);
		first.weightMap.put(second,distance);
	}
	
	public static void pathPrint(WeightedNode node) {
		if(node.parent!=null) {
			pathPrint(node.parent);
		}
		System.out.print(node.name+" ");
	}
	
	void dijikstra(WeightedNode node) {
		PriorityQueue<WeightedNode> queue = new PriorityQueue<>();
		node.distance = 0;//Setting distance as 0 for node from which we want to start
		queue.addAll(nodeList);  // adding all nodes in priority queue so that it is arrange as per the weight
		
		while(!queue.isEmpty()) {
			WeightedNode currNode = queue.remove();
			for(WeightedNode neighbor : currNode.neighbors) {
				if(queue.contains(neighbor)) { // if queue contains neighbor then only proceed, else not(as it already computed)
					if(neighbor.distance > currNode.distance + currNode.weightMap.get(neighbor)) {
						neighbor.distance = currNode.distance + currNode.weightMap.get(neighbor);
						neighbor.parent = currNode;
						//refresh the neighbor weights in priority queue
						queue.remove(neighbor);
						queue.add(neighbor);
						
					}
				}
			}
		}
		
		for(WeightedNode currnode : nodeList) {
			System.out.print("Node"+ currnode+ " distance: "+currnode.distance + " path: ");
			pathPrint(currnode);
			System.out.println();
		}
	}
}