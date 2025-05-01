package Graphs;

import java.util.ArrayList;
import java.util.HashMap;


//NOTE floydWarshal algorithm is best All pair shortest path when there is no -ve cycle
//TIME COMPLEXITY - O(V^3)
//SPACE COMPLEXITY - O(V^2)
//Doesn't support -ve cycle
public class FloydWarshall {

	public static void main(String []args) {
		ArrayList<WeightedNodeFloydWarshal> nodeList = new ArrayList<>();
		nodeList.add(new WeightedNodeFloydWarshal("A",0));
		nodeList.add(new WeightedNodeFloydWarshal("B",1));
		nodeList.add(new WeightedNodeFloydWarshal("C",2));
		nodeList.add(new WeightedNodeFloydWarshal("D",3));
		
		WeightedGraphFloydWarshal newGraph = new WeightedGraphFloydWarshal(nodeList);
		newGraph.addWeightedEdge(0, 3, 1);
		newGraph.addWeightedEdge(0, 1, 8);
		newGraph.addWeightedEdge(1, 2, 1);
		newGraph.addWeightedEdge(2, 0, 4);
		newGraph.addWeightedEdge(3, 1, 2);
		newGraph.addWeightedEdge(3, 2, 9);
		System.out.println("Floyd Warshall");
		newGraph.floydWarshal();
	}
}


class WeightedNodeFloydWarshal{
	String name;
	int index;
	int distance;
	WeightedNodeFloydWarshal parent;
	boolean isVisited;
	ArrayList<WeightedNodeFloydWarshal> neighbors = new ArrayList<WeightedNodeFloydWarshal>();
	HashMap<WeightedNodeFloydWarshal, Integer> weightMap = new HashMap<>();
	
	WeightedNodeFloydWarshal(String name, int index){
		this.name = name;
		this.index = index;
		distance = Integer.MAX_VALUE/10;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

class WeightedGraphFloydWarshal{
	ArrayList<WeightedNodeFloydWarshal> nodeList = new ArrayList<>();
	
	WeightedGraphFloydWarshal(ArrayList<WeightedNodeFloydWarshal> nodeList){
		this.nodeList = nodeList;
	}
	
	void addWeightedEdge(int i, int j, int distance) {
		WeightedNodeFloydWarshal first = nodeList.get(i);
		WeightedNodeFloydWarshal second = nodeList.get(j);
		first.neighbors.add(second);
		first.weightMap.put(second, distance);
	}
	
	void floydWarshal() {
		int size = nodeList.size();
		int[][]V = new int[size][size];
		
		//First we will make the matrix
		for(int i=0;i<size;i++) {
			WeightedNodeFloydWarshal first = nodeList.get(i);
			for(int j=0;j<size;j++) {
				WeightedNodeFloydWarshal second = nodeList.get(j);
				if(i==j) {
					V[i][j]=0;
				}else if(first.weightMap.containsKey(second)) {//if there is a direct path then we'll put the exact distance
					V[i][j]=first.weightMap.get(second);
				}else {
					V[i][j]=Integer.MAX_VALUE/10; // to prevent overflow thats why we divided by 10
				}
			}
		}
		
		//Actual Algorithm that runs O(V^3) where V is number of vertexes;
		for(int v=0;v<size;v++) {
			for(int i=0;i<size;i++) {
				for(int j=0;j<size;j++) {
					if(V[i][j]> V[i][v]+ V[v][j]) {
						V[i][j]= V[i][v]+ V[v][j];
					}
				}
			}
		}
		
		//Printing Floyd Warshal Matrix for all pair shortest path
		PrintFloydWarshalMatrix(V,size);
		
		
		
		
	}
	
	void PrintFloydWarshalMatrix(int [][]V, int size) {
		System.out.println("Printing Floyd Warshal Matrix for all pair shortest path");
		System.out.print("    ");
		for(int i=0;i<size;i++) {
			System.out.print(nodeList.get(i) + " ");
		}
		System.out.println();
		for(int i=0;i<size;i++) {
			System.out.print(nodeList.get(i)+ " : ");
			for(int j=0;j<size;j++) {
				System.out.print(V[i][j]+" ");
			}
			System.out.println();
		}
	}
}