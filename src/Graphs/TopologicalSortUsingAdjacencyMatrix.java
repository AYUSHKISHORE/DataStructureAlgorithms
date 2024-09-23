package Graphs;
import java.util.*;


/*
 * TIME COMPLEXITY - O(V+E) where V is vertex and E is edge (neighbor of V)
 * SPACE COMPLEXITY - O(V+E) where V is actual sort then E is recursion of stack
 * 
 * Sorts given action in such a way that if there is a dependency of one action on another, then the dependent actions always comes later than parent action

 * 
 */
public class TopologicalSortUsingAdjacencyMatrix {

	public static void main(String []args) {
		ArrayList<TopologicalGraphNodeAdM> nodeList = new ArrayList<TopologicalGraphNodeAdM>();
		nodeList.add(new TopologicalGraphNodeAdM("A",0));
		nodeList.add(new TopologicalGraphNodeAdM("B",1));
		nodeList.add(new TopologicalGraphNodeAdM("C",2));
		nodeList.add(new TopologicalGraphNodeAdM("D",3));
		nodeList.add(new TopologicalGraphNodeAdM("E",4));
		nodeList.add(new TopologicalGraphNodeAdM("F",5));
		nodeList.add(new TopologicalGraphNodeAdM("G",6));
		nodeList.add(new TopologicalGraphNodeAdM("H",7));
		
		
		TopologicalGraphAdM g = new TopologicalGraphAdM(nodeList);
		g.addDirectedEdge(0,2);
		g.addDirectedEdge(2,4);
		g.addDirectedEdge(4,7);
		g.addDirectedEdge(4,5);
		g.addDirectedEdge(5,6);
		g.addDirectedEdge(1,2);
		g.addDirectedEdge(1,3);
		g.addDirectedEdge(3,5);
		//g.toString();
		g.topologicalSort();
	}
	
}

class TopologicalGraphNodeAdM{
	String name;
	int index;
	boolean isVisited;
	TopologicalGraphNodeAdM(String name, int index){
		this.name = name;
		this.index = index;
		this.isVisited = false;
	}
}


class TopologicalGraphAdM{
	ArrayList<TopologicalGraphNodeAdM> nodeList = new ArrayList<TopologicalGraphNodeAdM>();
	int adM[][];
	TopologicalGraphAdM(ArrayList<TopologicalGraphNodeAdM> nodeList){
		this.nodeList = nodeList;
		adM = new int[nodeList.size()][nodeList.size()];
	}
	
	
	void addDirectedEdge(int i, int j) {
		adM[i][j]=1;
		//adM[j][i]=1;NOT REQUIRED AS IT IS A DIRECTED GRAPH
	}
	
	//For getting the neighbors
	ArrayList<TopologicalGraphNodeAdM> getNeighbors(TopologicalGraphNodeAdM node){
		ArrayList<TopologicalGraphNodeAdM> neighbors = new ArrayList<TopologicalGraphNodeAdM>();
		for(int i=0;i<adM.length;i++) {
			if(adM[node.index][i]==1) {
				neighbors.add(nodeList.get(i));
			}
		}
		return neighbors;
	}
	
	void topologicalVisit(TopologicalGraphNodeAdM node, Stack<TopologicalGraphNodeAdM> stack) {
		ArrayList<TopologicalGraphNodeAdM> neighbors = getNeighbors(node);
		for(TopologicalGraphNodeAdM neighbor : neighbors) {
			if(!neighbor.isVisited) {
				topologicalVisit(neighbor,stack);
			}
		}
		
		stack.push(node);
		node.isVisited=true;
	}
	
	void topologicalSort() {
		Stack<TopologicalGraphNodeAdM> stack = new Stack<>();
		for(TopologicalGraphNodeAdM node:nodeList) {
			if(!node.isVisited) {
				topologicalVisit(node, stack);
			}
		}
		
		System.out.println("TOPOLOGICAL SORT");
		while(!stack.isEmpty()) {
			System.out.print(stack.pop().name+" ");
		}
	}
	
	
	
}