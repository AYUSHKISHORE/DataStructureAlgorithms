package MinimumSpanningTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

//Kruskal Function
// TimeComplexity =  O(V + ELogE + EV) = O(ELogE)
// SpaceComplexity = O(V+E)
public class KruskalImplementation {
	public static void main(String []args) {
		ArrayList<WeightedNodeKruskal> nodeList = new ArrayList<>();
		nodeList.add(new WeightedNodeKruskal("A",0));
		nodeList.add(new WeightedNodeKruskal("B",1));
		nodeList.add(new WeightedNodeKruskal("C",2));
		nodeList.add(new WeightedNodeKruskal("D",3));
		nodeList.add(new WeightedNodeKruskal("E",4));
		
		Kruskal graph = new Kruskal(nodeList);
		graph.addWeightedUndirectedEdge(0, 1, 5);
		graph.addWeightedUndirectedEdge(0, 2, 13);
		graph.addWeightedUndirectedEdge(0, 4, 15);
		graph.addWeightedUndirectedEdge(1, 2, 10);
		graph.addWeightedUndirectedEdge(1, 3, 8);
		graph.addWeightedUndirectedEdge(2, 3, 6);
		graph.addWeightedUndirectedEdge(2, 4, 20);
		System.out.println("Running Kruskal Algorithm");
		graph.kruskal();

	}
}

class WeightedNodeKruskal{
	String name;
	int index;
	int distance;
	WeightedNodeKruskal parent;
	boolean isVisited;
	ArrayList<WeightedNodeKruskal> neighbors = new ArrayList<>();
	HashMap<WeightedNodeKruskal, Integer> weightMap = new HashMap<>();
	DisjointSetKruskal set;
	
	WeightedNodeKruskal(String name, int index){
		this.name=name;
		this.index=index;
		//distance = Integer.MAX_VALUE/10;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}

class DisjointSetKruskal{
	ArrayList<WeightedNodeKruskal> nodeList = new ArrayList<>();
	static void makeSet(ArrayList<WeightedNodeKruskal> nodeList) {
		for(WeightedNodeKruskal node : nodeList) {
			DisjointSetKruskal set = new DisjointSetKruskal();
			set.nodeList.add(node);
			node.set=set;
		}
	}
	
	static DisjointSetKruskal findSet(WeightedNodeKruskal node) {
		return node.set;
	}
	
	static DisjointSetKruskal union(WeightedNodeKruskal first, WeightedNodeKruskal second) {
		if(first.set.equals(second.set)) {
			return null;
		}
		if(first.set.nodeList.size()>second.set.nodeList.size()) {
			for(WeightedNodeKruskal node : second.set.nodeList) {
				first.set.nodeList.add(node);
				node.set=first.set;
			}
			return first.set;
		}else {
			for(WeightedNodeKruskal node : first.set.nodeList) {
				second.set.nodeList.add(node);
				node.set=second.set;
			}
			return second.set;
		}
	}
	
	void printAllNodesOfSet() {
		System.out.println("Printing all nodes of the set!!!");
		for(WeightedNodeKruskal node : nodeList) {
			System.out.print(node+" ");
		}
		System.out.println();
	}
}

class UndirectedEdge{
	WeightedNodeKruskal first;
	WeightedNodeKruskal second;
	int weight;
	
	UndirectedEdge(WeightedNodeKruskal first, WeightedNodeKruskal second, int weight){
		this.first=first;
		this.second=second;
		this.weight=weight;
	}
	
	@Override
	public String toString() {
		return "Edge (" + first + "," + second + "), weight =" + weight;
	}
}



class Kruskal{
	ArrayList<WeightedNodeKruskal> nodeList = new ArrayList<>();
	ArrayList<UndirectedEdge> edgeList = new ArrayList<>();
	
	Kruskal(ArrayList<WeightedNodeKruskal> nodeList){
		this.nodeList=nodeList;
	}
	
	public void addWeightedUndirectedEdge(int firstIndex, int secondIndex, int weight) {
		WeightedNodeKruskal first = nodeList.get(firstIndex);
		WeightedNodeKruskal second = nodeList.get(secondIndex);
		first.neighbors.add(second);
		second.neighbors.add(first);
		first.weightMap.put(second,weight);
		second.weightMap.put(first, weight);
		
		UndirectedEdge edge = new UndirectedEdge(first,second,weight); //Used for sorting edge
		edgeList.add(edge);
	}
	
	void kruskal() {
		DisjointSetKruskal.makeSet(nodeList); // ->O(V)
		Comparator<UndirectedEdge> comparator = new Comparator<UndirectedEdge>() { //  O(ELogE)
		@Override
		public int compare(UndirectedEdge o1 , UndirectedEdge o2) {
			return o1.weight - o2.weight;
		}};
		
		Collections.sort(edgeList,comparator);
		int cost = 0;
		for(UndirectedEdge edge: edgeList) { // O(E)
			WeightedNodeKruskal first = edge.first;
			WeightedNodeKruskal second = edge.second;
			if(!first.set.equals(second.set)) { // O(1)
				DisjointSetKruskal.union(first, second); // O(V)
				cost+=edge.weight;
				System.out.println("Taken "+edge);
			}
		} // O(EV)
		System.out.println("\n Total cost of MST: "+cost);
	}
}
