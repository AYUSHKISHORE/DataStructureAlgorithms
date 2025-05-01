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

public class SSSPP_BFS_AdM {
	public static void main(String []args) {
		ArrayList<SSSPP_BFS_GraphNode_AdM> nodeList = new ArrayList<>();
		nodeList.add(new SSSPP_BFS_GraphNode_AdM("A",0));
		nodeList.add(new SSSPP_BFS_GraphNode_AdM("B",1));
		nodeList.add(new SSSPP_BFS_GraphNode_AdM("C",2));
		nodeList.add(new SSSPP_BFS_GraphNode_AdM("D",3));
		nodeList.add(new SSSPP_BFS_GraphNode_AdM("E",4));
		nodeList.add(new SSSPP_BFS_GraphNode_AdM("F",5));
		nodeList.add(new SSSPP_BFS_GraphNode_AdM("G",6));
		SSSPP_BFS_Graph_AdM g = new SSSPP_BFS_Graph_AdM(nodeList);
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

class SSSPP_BFS_GraphNode_AdM{
	String name;
	int index;
	boolean isVisited;
	SSSPP_BFS_GraphNode_AdM parent;
	SSSPP_BFS_GraphNode_AdM(String name, int index){
		this.name = name;
		this.index = index;
		this.isVisited=false;
	}
}

class SSSPP_BFS_Graph_AdM{
	ArrayList<SSSPP_BFS_GraphNode_AdM> nodeList = new ArrayList<SSSPP_BFS_GraphNode_AdM>();
	int adm[][];
	SSSPP_BFS_Graph_AdM(ArrayList<SSSPP_BFS_GraphNode_AdM> nodeList){
		this.nodeList=nodeList;
		adm = new int[nodeList.size()][nodeList.size()];
	}
	
	void addUndirectedEdge(int i, int j) {
		adm[i][j]=1;
		adm[j][i]=1;
	}
	
	ArrayList<SSSPP_BFS_GraphNode_AdM> getNeighbors (SSSPP_BFS_GraphNode_AdM node){
		ArrayList<SSSPP_BFS_GraphNode_AdM> neighbors = new ArrayList<SSSPP_BFS_GraphNode_AdM>();
		for(int i=0;i<nodeList.size();i++) {
			if(adm[node.index][i]==1) {
				neighbors.add(nodeList.get(i));
			}
		}
		return neighbors;
	}
	
	//Send any of node here we are giving node 0
	void BFSForSSSPP(SSSPP_BFS_GraphNode_AdM node) {
		Queue<SSSPP_BFS_GraphNode_AdM> queue = new LinkedList<>();
		queue.add(node);
		while(!queue.isEmpty()) {
			SSSPP_BFS_GraphNode_AdM currentNode = queue.remove();
			currentNode.isVisited = true;
			
			System.out.print("Printing the path for current node = "+ currentNode.name + " : ");
			pathPrint(currentNode);
			System.out.println();
			
			ArrayList<SSSPP_BFS_GraphNode_AdM> neighbors = getNeighbors(currentNode);
			for(SSSPP_BFS_GraphNode_AdM neighbor : neighbors) {
				if(!neighbor.isVisited) {
					queue.add(neighbor);
					neighbor.isVisited=true;
					neighbor.parent=currentNode;
					
				}
			}
		}
	}
	
	void pathPrint(SSSPP_BFS_GraphNode_AdM node) {
		if(node.parent!=null) {
			pathPrint(node.parent);
		}
		
		System.out.print(node.name+" ");
	}
	
}
