package Graphs;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Incase of SSSPP
 * TIME COMPLEXITY - O(V) * Adjacent Neighbors i.e ( O(V*E)
 * SPACE COMPLEXITY - O(V) - Size of queue
 */
 /*
  Step-by-step why it’s O(V × E)
	One “edge scan” takes E steps
	In code you do something like:

	for each node u in the graph         // V iterations
  		for each edge (u → v) out of u     // total across all u is E
    		do a constant amount of work
	If you add up “for each outgoing edge” across all nodes, you visit every edge exactly once, so that inner part is E operations.

	You repeat the edge scan V–1 times
	The Bellman–Ford algorithm says “relax every edge” one more time in each pass, and you need V–1 passes. In big-O land, V–1 is the same as V. So you do that edge scan V times.

	Multiply them together

	One pass = E steps

	V passes = V × E steps

	That’s why we say the running time is O(V × E).
	*/
 
/*
 * 
 * Incase of All pair shortest path problem
 * TIME COMPLEXITY - O(V) * Adjacent Neighbors i.e ( O(V^2 *E)
 * SPACE COMPLEXITY - O(VE) - Size of queue
 * 
 * Best for all graph (as it can identify -ve cycle)
 */

public class Bellmanford {
	public static void main(String []args) {
		ArrayList<WeightedNodeBellmanFord> nodeList = new ArrayList<>();
		nodeList.add(new WeightedNodeBellmanFord("A",0));
		nodeList.add(new WeightedNodeBellmanFord("B",1));
		nodeList.add(new WeightedNodeBellmanFord("C",2));
		nodeList.add(new WeightedNodeBellmanFord("D",3));
		nodeList.add(new WeightedNodeBellmanFord("E",4));
		nodeList.add(new WeightedNodeBellmanFord("F",5));
		nodeList.add(new WeightedNodeBellmanFord("G",6));
		
		//Case of no cycle
		WeightedGraphBellmanFord newGraph = new WeightedGraphBellmanFord(nodeList);
		newGraph.addDirectedEdge(0, 1, 2); // index1, index2, distance
		newGraph.addDirectedEdge(0, 2, 5);
		newGraph.addDirectedEdge(1, 2, 6);
		newGraph.addDirectedEdge(1, 3, 1);
		newGraph.addDirectedEdge(1, 4, 3);
		newGraph.addDirectedEdge(2, 5, 8);
		newGraph.addDirectedEdge(3, 4, 4);
		newGraph.addDirectedEdge(4, 6, 9);
		newGraph.addDirectedEdge(5, 6, 7);
		
		System.out.println("Printing Bellman Ford algorithm from Source A");
		newGraph.bellmanford(nodeList.get(0));
		
		
		System.out.println();
		System.out.println();
		System.out.println("-ve cycle case");
		//Case of -ve cycle
		
		ArrayList<WeightedNodeBellmanFord> nodeList2 = new ArrayList<>();
		nodeList2.add(new WeightedNodeBellmanFord("A",0));
		nodeList2.add(new WeightedNodeBellmanFord("B",1));
		nodeList2.add(new WeightedNodeBellmanFord("C",2));
		nodeList2.add(new WeightedNodeBellmanFord("D",3));
		nodeList2.add(new WeightedNodeBellmanFord("E",4));
		
		//Case of no cycle
		WeightedGraphBellmanFord newGraph2 = new WeightedGraphBellmanFord(nodeList2);
		newGraph2.addDirectedEdge(0, 2, 6); // index1, index2, distance
		newGraph2.addDirectedEdge(0, 3, -6);
		newGraph2.addDirectedEdge(1, 0, 3);
		newGraph2.addDirectedEdge(2, 3, 1);
		newGraph2.addDirectedEdge(3, 1, 1);
		newGraph2.addDirectedEdge(4, 1, 4);
		newGraph2.addDirectedEdge(4, 3, 2);
		
		System.out.println("Printing Bellman Ford algorithm from Source A");
		newGraph2.bellmanford(nodeList2.get(0));
		
	}
}

class WeightedNodeBellmanFord{
	String name;
	int index;
	int distance;
	ArrayList<WeightedNodeBellmanFord> neighbors = new ArrayList<>();
	HashMap<WeightedNodeBellmanFord, Integer> weightMap = new HashMap<>();
	WeightedNodeBellmanFord parent;
	boolean isVisted=false;
	
	WeightedNodeBellmanFord(String name, int index){
		this.name = name;
		this.index = index;
		this.distance = Integer.MAX_VALUE/10; //to reduce overflow
	}
	
	@Override
	public String toString() {
		return name;
	}
}

class WeightedGraphBellmanFord{
	ArrayList<WeightedNodeBellmanFord> nodeList = new ArrayList<>();
	WeightedGraphBellmanFord(ArrayList<WeightedNodeBellmanFord> nodeList){
		this.nodeList = nodeList;
	}
	
	void addDirectedEdge(int i, int j, int distance) {
		WeightedNodeBellmanFord first = nodeList.get(i);
		WeightedNodeBellmanFord second = nodeList.get(j);
		first.neighbors.add(second);
		first.weightMap.put(second,distance);
	}
	
	 void pathPrint(WeightedNodeBellmanFord parent) {
		if(parent.parent!=null) {
			pathPrint(parent.parent);
		}
		System.out.print(parent.name+" ");
	}
	
	void bellmanford(WeightedNodeBellmanFord sourceNode) {
		sourceNode.distance=0;
		
		//Note in bellmanford algorithm its run V-1 times 
		for(int i=0;i<nodeList.size()-1;i++) {
			for(WeightedNodeBellmanFord curr : nodeList) {
					for(WeightedNodeBellmanFord neighbor : curr.neighbors) {
						if(neighbor.distance > curr.distance + curr.weightMap.get(neighbor)) {
							neighbor.distance = curr.distance + curr.weightMap.get(neighbor);
							neighbor.parent = curr;
						}
					}
			}
		}
		
		//Now we will run 1 more time to check if it contains -ve cycle
		for(WeightedNodeBellmanFord curr : nodeList) {
			for(WeightedNodeBellmanFord neighbor : curr.neighbors) {
				if(neighbor.distance > curr.distance + curr.weightMap.get(neighbor)) {
					System.out.println("Contains -ve cycle");
					System.out.println("curr name "+curr.name);

					System.out.println("node name "+neighbor.name);
					System.out.println("old distance "+neighbor.distance);
					int  newdistance = curr.distance + curr.weightMap.get(neighbor);
					System.out.println("new distance "+ newdistance);
					return;
				}
			}
		}
		
		System.out.println("-ve cycle not found");
		for(WeightedNodeBellmanFord curr : nodeList) {
			System.out.print("Node "+ curr.name+ " distance: "+curr.distance + " path: ");
			pathPrint(curr);
			System.out.println();
		}
	}
}
