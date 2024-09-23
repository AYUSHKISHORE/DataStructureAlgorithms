package Graphs;
import java.util.*;


/*
 * TIME COMPLEXITY - O(V+E) where V is vertex and E is edge (neighbor of V)
 * SPACE COMPLEXITY - O(V+E) where V is actual sort then E is recursion of stack
 * 
 * Sorts given action in such a way that if there is a dependency of one action on another, then the dependent actions always comes later than parent action

 * 
 */
public class TopologicalSortUsingAdjacencyList {
	public static void main(String []args) {
		ArrayList<TopologicalGraphNodeUsingAdL> nodeList = new ArrayList<TopologicalGraphNodeUsingAdL>();
		nodeList.add(new TopologicalGraphNodeUsingAdL("A",0));
		nodeList.add(new TopologicalGraphNodeUsingAdL("B",1));
		nodeList.add(new TopologicalGraphNodeUsingAdL("C",2));
		nodeList.add(new TopologicalGraphNodeUsingAdL("D",3));
		nodeList.add(new TopologicalGraphNodeUsingAdL("E",4));
		nodeList.add(new TopologicalGraphNodeUsingAdL("F",5));
		nodeList.add(new TopologicalGraphNodeUsingAdL("G",6));
		nodeList.add(new TopologicalGraphNodeUsingAdL("H",7));
		
		
		TopologicalGraphUsingAdL g = new TopologicalGraphUsingAdL(nodeList);
		g.addDirectedEdge(0,2);
		g.addDirectedEdge(2,4);
		g.addDirectedEdge(4,7);
		g.addDirectedEdge(4,5);
		g.addDirectedEdge(5,6);
		g.addDirectedEdge(1,2);
		g.addDirectedEdge(1,3);
		g.addDirectedEdge(3,5);
		//g.toString();
		g.TopologicalSort();
	}

}

class TopologicalGraphNodeUsingAdL{
	String name;
	int index;
	boolean isVisited;
	ArrayList<TopologicalGraphNodeUsingAdL> neighbors = new ArrayList<TopologicalGraphNodeUsingAdL>();
	TopologicalGraphNodeUsingAdL(String name, int index){
		this.name = name;
		this.index = index;
		this.isVisited = false;
	}
}


class TopologicalGraphUsingAdL{
	ArrayList<TopologicalGraphNodeUsingAdL> nodeList = new ArrayList<TopologicalGraphNodeUsingAdL>();
	
	TopologicalGraphUsingAdL(ArrayList<TopologicalGraphNodeUsingAdL> nodeList){
		this.nodeList=nodeList;
	}
	
	void addDirectedEdge(int i , int j) {
		TopologicalGraphNodeUsingAdL node = nodeList.get(i);
		node.neighbors.add(nodeList.get(j));// Since it is a directedGraph so only condition is there
	}
	
	ArrayList<TopologicalGraphNodeUsingAdL> getNeighbors(TopologicalGraphNodeUsingAdL node){
		return node.neighbors;
	}
	
	void TopologicalSort() {
		Stack<TopologicalGraphNodeUsingAdL> stack = new Stack<TopologicalGraphNodeUsingAdL>();
		for(int i=0;i<nodeList.size();i++) {
			if(!nodeList.get(i).isVisited) {
				TopologicalVisit(nodeList.get(i),stack);
			}
		}
		System.out.println("Topological Sort :: ");
		while(!stack.isEmpty()) {
			System.out.print(stack.pop().name+" ");
		}
	}
	
	void TopologicalVisit(TopologicalGraphNodeUsingAdL node , Stack<TopologicalGraphNodeUsingAdL> stack) {
		ArrayList<TopologicalGraphNodeUsingAdL> neighbors=getNeighbors(node);
		for(TopologicalGraphNodeUsingAdL neighbor : neighbors) {
			if(!neighbor.isVisited) {
				TopologicalVisit(neighbor,stack);
			}
		}
		stack.push(node);
		node.isVisited=true;
	}
	
}
