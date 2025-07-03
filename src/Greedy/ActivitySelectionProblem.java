package Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//Time Complexity = O(nLogn)
//Space Complexity = O(1) since we are not storing result

public class ActivitySelectionProblem {
	public static void main(String[]args) {
		String ActivityName[] ={"A1","A2","A3","A4","A5","A6"};
		int startTime[]= {0,3,1,5,5,8};
		int endTime[]= {6,4,2,8,7,9};
		
		ArrayList<Activity> activityList = new  ArrayList<Activity>();
		for(int i=0;i<startTime.length;i++) {
			activityList.add(new Activity(ActivityName[i],startTime[i],endTime[i]));
		}
		ActivitySelection.activitySelection(activityList);
	}
}

class Activity{
	private String name;
	private int startTime;
	private int finishTime;
	
	public Activity(String name, int startTime, int finishTime) {
		this.name=name;
		this.startTime=startTime;
		this.finishTime=finishTime;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public void setStartTime(int startTime) {
		this.startTime=startTime;
	}
	
	public int getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(int finishTime) {
		this.finishTime=finishTime;
	}
	
	@Override
	public String toString() {
		return "Activity: "+name+ " , startTime: "+startTime+" , finishTime: "+finishTime;
	}
}

class ActivitySelection{
	static void activitySelection(ArrayList<Activity> activityList) {
		
		Comparator<Activity> finishComparator = new Comparator<>() {
			@Override
			public int compare(Activity o1, Activity o2) {
				return o1.getFinishTime() - o2.getFinishTime();
			}
		};
		
		Collections.sort(activityList, finishComparator); // Sorting in ascending order of finishTime :: Time Complexity  - (OnLogn)
		
		Activity previousActivity =activityList.get(0);
		
		System.out.println("\n\n Recommended Schedule: \n"+ activityList.get(0));
		
		for(int i=1;i<activityList.size(); i++) {
			Activity activity = activityList.get(i);
			if(previousActivity.getFinishTime()<=activity.getStartTime()) {
				System.out.println(activity);
				previousActivity=activity;
			}
		}
	}
}
