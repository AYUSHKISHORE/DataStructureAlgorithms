package Graphs;
import java.util.*;

public class AdjacencyList {

	public static void main(String []args) {
		ArrayList<GraphNodeAdjacencyList> arr = new ArrayList<GraphNodeAdjacencyList>();
		arr.add(new GraphNodeAdjacencyList("A",0));
		arr.add(new GraphNodeAdjacencyList("B",1));
		arr.add(new GraphNodeAdjacencyList("C",2));
		arr.add(new GraphNodeAdjacencyList("D",3));
		arr.add(new GraphNodeAdjacencyList("E",4));
		
		
		GraphAdjacencyList g = new GraphAdjacencyList(arr);
		g.addUndirectedGraph(0, 1);
		g.addUndirectedGraph(0, 2);
		g.addUndirectedGraph(0, 3);
		g.addUndirectedGraph(1, 4);
		g.addUndirectedGraph(2, 3);
		g.addUndirectedGraph(3, 4);
		System.out.println(g.toString());
	}
}

class GraphNodeAdjacencyList{
	String name;
	int index;
	
	ArrayList<GraphNodeAdjacencyList> neighbors = new ArrayList<GraphNodeAdjacencyList>();
	GraphNodeAdjacencyList(String name, int index){
		this.name=name;
		this.index=index;
	}
}

class GraphAdjacencyList{
	ArrayList<GraphNodeAdjacencyList> nodeList = new ArrayList<GraphNodeAdjacencyList>();
	
	GraphAdjacencyList(ArrayList<GraphNodeAdjacencyList> nodeList){
		this.nodeList=nodeList;
	}
	
	void addUndirectedGraph(int i, int j) {
		GraphNodeAdjacencyList first = nodeList.get(i);
		GraphNodeAdjacencyList second = nodeList.get(j);
		
		first.neighbors.add(second);
		second.neighbors.add(first);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		for(int i=0;i<nodeList.size();i++) {
			sb.append(nodeList.get(i).name+" : ");
			for(int j=0;j<nodeList.get(i).neighbors.size();j++) {
				if(j==nodeList.get(i).neighbors.size()-1) {
					sb.append(nodeList.get(i).neighbors.get(j).name);
				}else {
					sb.append(nodeList.get(i).neighbors.get(j).name+" -> ");
				}
			}
			sb.append("\n");
		}
		
		
		return sb.toString();
	}
}
