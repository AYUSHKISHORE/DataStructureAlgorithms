package Graphs;

import java.util.*;
public class AdjacencyMatrix {

	public static void main(String[]args) {
		ArrayList<GraphNodeAdjacencyMatrix> arr = new ArrayList<GraphNodeAdjacencyMatrix>();
		arr.add(new GraphNodeAdjacencyMatrix("A",0));
		arr.add(new GraphNodeAdjacencyMatrix("B",1));
		arr.add(new GraphNodeAdjacencyMatrix("C",2));
		arr.add(new GraphNodeAdjacencyMatrix("D",3));
		arr.add(new GraphNodeAdjacencyMatrix("E",4));
		
		GraphAdjacencyMatrix gh = new GraphAdjacencyMatrix(arr);
		gh.addUndirectedEdges(0, 1);
		gh.addUndirectedEdges(0, 2);
		gh.addUndirectedEdges(0, 3);
		gh.addUndirectedEdges(1, 4);
		gh.addUndirectedEdges(2, 3);
		gh.addUndirectedEdges(3, 4);
		System.out.println(gh.toString());
	}
}

class GraphNodeAdjacencyMatrix{
	String name;
	int index;
	GraphNodeAdjacencyMatrix(String name, int index){
		this.name=name;
		this.index=index;
	}
}

class GraphAdjacencyMatrix{
	ArrayList<GraphNodeAdjacencyMatrix> nodeList = new ArrayList<GraphNodeAdjacencyMatrix>();
	int [][] AdjacencyMatrix;
	
	GraphAdjacencyMatrix(ArrayList<GraphNodeAdjacencyMatrix> nodeList){
		this.nodeList=nodeList;
		//Now based on the number of element/vertices/node create the matrix
		AdjacencyMatrix = new int[nodeList.size()][nodeList.size()];
	}
	
	void addUndirectedEdges(int i, int j) {
		AdjacencyMatrix[i][j]=1;
		AdjacencyMatrix[j][i]=1;
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
			for(int j:AdjacencyMatrix[i]) {
				s.append(j+" ");
			}
			s.append("\n");
		}
		return s.toString();
	}
}