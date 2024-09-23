package JavaConcepts;
import java.util.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class PersonComparable implements Comparable<PersonComparable> {

	private String name;
	private int age;
	PersonComparable(String name, int age){
		this.name=name;
		this.age=age;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	@Override
	public int compareTo(PersonComparable other) {
		return Integer.compare(this.age, other.age);
	}
	
	@Override
	public String toString() {
		return name +"("+ age+")";
	}
	
	public static void main(String []args) {
		List<PersonComparable> people = new ArrayList<>();
		people.add(new PersonComparable("A",20));
		people.add(new PersonComparable("B",10));
		people.add(new PersonComparable("C",30));
		
		Collections.sort(people);
		for(PersonComparable p : people) {
			System.out.println(p);
		}
	}
	
}
