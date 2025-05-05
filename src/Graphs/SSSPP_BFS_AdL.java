package Graphs;
import java.util.*;


/*
 * Incase of SSSPP
 * TIME COMPLEXITY - O(V) * Adjacent Neighbors i.e ( O(V*E)
 * SPACE COMPLEXITY - O(V) - Size of queue
 * 
 * Incase of All pair shortest path problem
 * TIME COMPLEXITY - O(V) * Adjacent Neighbors i.e ( O(V^2 *E)
 * SPACE COMPLEXITY - O(VE) - Size of queue
 * 
 * Best for unweighted graph
 */

public class SSSPP_BFS_AdL {

	public static void main(String []args) {
		ArrayList<SSSPP_BFS_AdL_Node> nodeList = new ArrayList<>();
		nodeList.add(new SSSPP_BFS_AdL_Node("A",0));
		nodeList.add(new SSSPP_BFS_AdL_Node("B",1));
		nodeList.add(new SSSPP_BFS_AdL_Node("C",2));
		nodeList.add(new SSSPP_BFS_AdL_Node("D",3));
		nodeList.add(new SSSPP_BFS_AdL_Node("E",4));
		nodeList.add(new SSSPP_BFS_AdL_Node("F",5));
		nodeList.add(new SSSPP_BFS_AdL_Node("G",6));
		SSSPP_BFS_AdL_Graph g = new SSSPP_BFS_AdL_Graph(nodeList);
		g.addUndirectedEdge(0, 1);
		g.addUndirectedEdge(0, 2);
		g.addUndirectedEdge(1, 3);
		g.addUndirectedEdge(1, 6);
		g.addUndirectedEdge(2, 3);
		g.addUndirectedEdge(2, 4);
		g.addUndirectedEdge(3, 5);
		g.addUndirectedEdge(4, 5);
		g.addUndirectedEdge(5, 6);
		g.BFSForSSSPP(nodeList.get(0));
	}
	
}

class SSSPP_BFS_AdL_Node{
	String name;
	int index;
	ArrayList<SSSPP_BFS_AdL_Node> neighbors = new ArrayList<SSSPP_BFS_AdL_Node>();
	SSSPP_BFS_AdL_Node parent;
	boolean isVisited;
	SSSPP_BFS_AdL_Node(String name, int index){
		this.name=name;
		this.index=index;
		this.isVisited=false;
	}
}

class SSSPP_BFS_AdL_Graph{
	ArrayList<SSSPP_BFS_AdL_Node> nodeList = new ArrayList<SSSPP_BFS_AdL_Node>();
	SSSPP_BFS_AdL_Graph(ArrayList<SSSPP_BFS_AdL_Node> nodeList){
		this.nodeList=nodeList;
	}
	
	void addUndirectedEdge(int i, int j) {
		nodeList.get(i).neighbors.add(nodeList.get(j));
		nodeList.get(j).neighbors.add(nodeList.get(i));
	}
	
	void pathPrint(SSSPP_BFS_AdL_Node node) {
		if(node.parent!=null) {
			pathPrint(node.parent);
		}
		System.out.print(node.name+" ");
	}
	
	
	void BFSForSSSPP(SSSPP_BFS_AdL_Node node) {
		Queue<SSSPP_BFS_AdL_Node> queue = new LinkedList<>();
		queue.add(node);
		while(!queue.isEmpty()) {
			SSSPP_BFS_AdL_Node current =queue.poll();
			current.isVisited=true;
			ArrayList<SSSPP_BFS_AdL_Node> neighbors = current.neighbors;
			System.out.print("Printing the path for current node = "+ current.name+ " -> ");
			pathPrint(current);
			System.out.println();
			for(SSSPP_BFS_AdL_Node neighbor : neighbors) {
				if(!neighbor.isVisited) {
					queue.add(neighbor);
					neighbor.parent=current;
					neighbor.isVisited=true;
				}
			}
		}
	}
}
