package Graphs;
import java.util.*;

//TIME COMPLEXITY - O(V+E) --> V is vertices and E is cummulation of Adjacent Edges
//SPACE COMPLEXITY - O(V+E) --> V is vertices and E is cummulation of Adjacent Edges
public class AdjacencyListTraversal {

	public static void main(String []args) {
		ArrayList<GraphNodeADL> arrBFS = new ArrayList<GraphNodeADL>();
		arrBFS.add(new GraphNodeADL("A",0));
		arrBFS.add(new GraphNodeADL("B",1));
		arrBFS.add(new GraphNodeADL("C",2));
		arrBFS.add(new GraphNodeADL("D",3));
		arrBFS.add(new GraphNodeADL("E",4));
		
		
		GraphADLImpl gBFS = new GraphADLImpl(arrBFS);
		gBFS.addUndirectedEdges(0, 1);
		gBFS.addUndirectedEdges(0, 2);
		gBFS.addUndirectedEdges(0, 3);
		gBFS.addUndirectedEdges(1, 4);
		gBFS.addUndirectedEdges(2, 3);
		gBFS.addUndirectedEdges(3, 4);
		System.out.println(gBFS.toString());
		gBFS.BFS();
	

		ArrayList<GraphNodeADL> arrDFS = new ArrayList<GraphNodeADL>();
		arrDFS.add(new GraphNodeADL("A",0));
		arrDFS.add(new GraphNodeADL("B",1));
		arrDFS.add(new GraphNodeADL("C",2));
		arrDFS.add(new GraphNodeADL("D",3));
		arrDFS.add(new GraphNodeADL("E",4));
	
		GraphADLImpl gDFS = new GraphADLImpl(arrDFS);
		gDFS.addUndirectedEdges(0, 1);
		gDFS.addUndirectedEdges(0, 2);
		gDFS.addUndirectedEdges(0, 3);
		gDFS.addUndirectedEdges(1, 4);
		gDFS.addUndirectedEdges(2, 3);
		gDFS.addUndirectedEdges(3, 4);
		System.out.println(gDFS.toString());
		gDFS.DFS();
	}
}
//Adjacency List
class GraphNodeADL{
	String name;
	int index;
	boolean isVisited;
	ArrayList<GraphNodeADL> neighbors = new ArrayList<GraphNodeADL>();
	GraphNodeADL(String name, int index){
		this.name=name;
		this.index=index;
		this.isVisited=false;
	}
}


class GraphADLImpl{

	ArrayList<GraphNodeADL> nodeList = new ArrayList<GraphNodeADL>();
	GraphADLImpl(ArrayList<GraphNodeADL> nodeList){
		this.nodeList=nodeList;
	}
	
	void addUndirectedEdges(int i, int j) {
		nodeList.get(i).neighbors.add(nodeList.get(j));
		nodeList.get(j).neighbors.add(nodeList.get(i));
	}
	
	void BFSTraversal(GraphNodeADL node) {
		Queue<GraphNodeADL> queue = new LinkedList<>();
		queue.add(node);
		
		while(!queue.isEmpty()) {
			GraphNodeADL curr = queue.poll();
			System.out.print(curr.name+" ");
			curr.isVisited = true;
			
			for(GraphNodeADL neighbor : curr.neighbors) {
				if(!neighbor.isVisited) {
					queue.add(neighbor);
					neighbor.isVisited=true;
				}
			}
		}
	}
	
	void BFS() {
		System.out.print("\nBFS traversal in Adjacency List: ");
		for(GraphNodeADL node : nodeList) {
			if(!node.isVisited) {
				BFSTraversal(node);
			}
		}
		System.out.println();

	}
	
	void DFSTraversal(GraphNodeADL node) {
		Stack<GraphNodeADL> stack = new Stack<>();
		stack.push(node);
		
		while(!stack.isEmpty()) {
			GraphNodeADL curr = stack.pop();
			System.out.print(curr.name+" ");
			curr.isVisited = true;
			
			for(GraphNodeADL neighbor : curr.neighbors) {
				if(!neighbor.isVisited) {
					stack.push(neighbor);
					neighbor.isVisited=true;
				}
			}
		}
	}
	
	void DFS() {
		System.out.print("\nDFS traversal in Adjacency List: ");
		for(GraphNodeADL node : nodeList) {
			if(!node.isVisited) {
				DFSTraversal(node);
			}
		}
		System.out.println();
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