package Graphs;
import java.util.*;

//TIME COMPLEXITY - O(V+E) --> V is vertices and E is cummulation of Adjacent Edges
//SPACE COMPLEXITY - O(V+E) --> V is vertices and E is cummulation of Adjacent Edges
public class AdjacencyMatrixTraversal {
	public static void main(String []args) {
		ArrayList<GraphNodeAMT> arrBFS = new ArrayList<GraphNodeAMT>();
		arrBFS.add(new GraphNodeAMT("A",0));
		arrBFS.add(new GraphNodeAMT("B",1));
		arrBFS.add(new GraphNodeAMT("C",2));
		arrBFS.add(new GraphNodeAMT("D",3));
		arrBFS.add(new GraphNodeAMT("E",4));
		
		
		ArrayList<GraphNodeAMT> arrDFS = new ArrayList<GraphNodeAMT>();
		arrDFS.add(new GraphNodeAMT("A",0));
		arrDFS.add(new GraphNodeAMT("B",1));
		arrDFS.add(new GraphNodeAMT("C",2));
		arrDFS.add(new GraphNodeAMT("D",3));
		arrDFS.add(new GraphNodeAMT("E",4));
		
		GraphAmtImpl ghBFS = new GraphAmtImpl(arrBFS);
		ghBFS.addUndirectedEdges(0, 1);
		ghBFS.addUndirectedEdges(0, 2);
		ghBFS.addUndirectedEdges(0, 3);
		ghBFS.addUndirectedEdges(1, 4);
		ghBFS.addUndirectedEdges(2, 3);
		ghBFS.addUndirectedEdges(3, 4);
		System.out.println(ghBFS.toString());
		ghBFS.BFS();
		

		
		GraphAmtImpl ghDFS = new GraphAmtImpl(arrDFS);
		ghDFS.addUndirectedEdges(0, 1);
		ghDFS.addUndirectedEdges(0, 2);
		ghDFS.addUndirectedEdges(0, 3);
		ghDFS.addUndirectedEdges(1, 4);
		ghDFS.addUndirectedEdges(2, 3);
		ghDFS.addUndirectedEdges(3, 4);
		System.out.println(ghDFS.toString());
		ghDFS.DFS();
	}
}


class GraphNodeAMT{
	String name;
	int index;
	boolean isVisited;
	GraphNodeAMT(String name, int index){
		this.name=name;
		this.index=index;
		this.isVisited=false;
	}
}

class GraphAmtImpl{
	ArrayList<GraphNodeAMT> nodeList = new ArrayList<GraphNodeAMT>();
	int AdjMatrix[][];
	GraphAmtImpl(ArrayList<GraphNodeAMT> nodeList){
		this.nodeList=nodeList;
		AdjMatrix=new int[nodeList.size()][nodeList.size()];
	}
	
	void addUndirectedEdges(int i, int j) {
		AdjMatrix[i][j]=1;
		AdjMatrix[j][i]=1;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("   ");
		for(int i=0;i<nodeList.size();i++) {
			s.append(nodeList.get(i).name+" ");
		}
		s.append("\n");
		
		for(int i=0;i<nodeList.size();i++) {
			s.append(nodeList.get(i).name+": ");
			for(int j:AdjMatrix[i]) {
				s.append(j+" ");
			}
			s.append("\n");
		}
		return s.toString();
	}
	
	//this function gets the list of all neighbors of node which are unvisited
	 ArrayList<GraphNodeAMT> getNeighbors(GraphNodeAMT node){
		ArrayList<GraphNodeAMT> neighbors = new ArrayList<GraphNodeAMT>();
		for(int i=0;i<nodeList.size();i++) {
			if(AdjMatrix[node.index][i]==1 && !nodeList.get(i).isVisited) {
				neighbors.add(nodeList.get(i));
			}
		}
		return neighbors;
	}
	 
	 void BFSTraversal(GraphNodeAMT node) {
		 Queue<GraphNodeAMT> queue = new LinkedList<>();
		 queue.add(node);
		 
		 while(!queue.isEmpty()) {
			 GraphNodeAMT curr = queue.poll();
			System.out.print(curr.name+" ");
			curr.isVisited=true;
			 
			 ArrayList<GraphNodeAMT> neighbors  = getNeighbors(curr);
			 for(GraphNodeAMT val : neighbors) {
				 if(!val.isVisited) {
					 queue.add(val);
					 val.isVisited=true;
				 }
			 }
		 }
	 }
	 
	 void BFS() {
		 System.out.print("\n BFS Traversal: ");
		 for(GraphNodeAMT node : nodeList) {
			 if(!node.isVisited) {
				 BFSTraversal(node);
			 }
		 }
	 }
	 
	 
	 void DFSTraversal(GraphNodeAMT node) {
		 Stack<GraphNodeAMT> stack = new Stack<>();
		 stack.push(node);
		 
		 while(!stack.isEmpty()) {
			 GraphNodeAMT curr = stack.pop();
			System.out.print(curr.name+" ");
			curr.isVisited=true;
			 
			 ArrayList<GraphNodeAMT> neighbors  = getNeighbors(curr);
			 for(GraphNodeAMT val : neighbors) {
				 if(!val.isVisited) {
					 stack.push(val);
					 val.isVisited=true;
				 }
			 }
		 }
	 }
	 
	 void DFS() {
		 System.out.print("\n DFS Traversal: ");
		 for(GraphNodeAMT node : nodeList) {
			 if(!node.isVisited) {
				 DFSTraversal(node);
			 }
		 }
	 }
}