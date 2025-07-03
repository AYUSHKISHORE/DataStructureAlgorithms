package Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FractionalKnapsackImplementation {
	public static void main(String[]args) {
		int weights[] = {20,30,10};
		int value[] = {100,120,60};
		int capacity = 50;
		ArrayList<KnapsackItem> items = new ArrayList<>();
		for(int i=0;i<weights.length;i++) {
			items.add(new KnapsackItem(i+1,value[i],weights[i]));
		}
		FractionalKnapsack.knapsack(items, capacity);
		
	}
}

class KnapsackItem{
	private int index;
	private int value;
	private int weight;
	private double ratio;
	
	public KnapsackItem(int index, int value, int weight) {
		this.index=index;
		this.value=value;
		this.weight=weight;
		this.ratio = (value*1.0)/weight;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index=index;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value=value;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight=weight;
	}
	
	public double getRatio() {
		return ratio;
	}
	
	public void setRatio(double ratio) {
		this.ratio=ratio;
	}
	
	@Override
	public String toString() {
		return "Item index: "+index+ " weight: "+weight+" value: "+value+" ratio: "+ratio;
	}
}

class FractionalKnapsack{
	static void knapsack(ArrayList<KnapsackItem> items, int capacity) {
		Comparator<KnapsackItem> comparator = new Comparator<KnapsackItem>() {
			@Override
			public int compare(KnapsackItem o1, KnapsackItem o2) {
				if(o2.getRatio()>o1.getRatio())return 1;
				else return -1;
			}
		};
		Collections.sort(items, comparator);
		int usedCapacity = 0;
		double totalValue = 0.0;
		
		for(KnapsackItem item : items) {
			if(usedCapacity+item.getWeight()<=capacity) {
				usedCapacity+=item.getWeight();
				totalValue = item.getValue();
				System.out.println("Taken: "+item);
			}else {
				int leftWeight = capacity - usedCapacity;
				double value = item.getRatio() * leftWeight;
				usedCapacity +=leftWeight;
				totalValue +=value;
				System.out.println("Taken item index = "+item.getIndex()+" obtainedValue: "+value+ " leftWeight: "+leftWeight+ " ratio: "+item.getRatio());
			}
			
			if(usedCapacity==capacity)break;
		}
		
		System.out.println("\nTotal Value Obtained : "+totalValue);
	}
}
