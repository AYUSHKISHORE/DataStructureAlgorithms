package JavaConcepts;
import java.util.*;

public class PersonComparator{
	public static void main(String[]args) {
		List<Person> people = new ArrayList<>();
		people.add(new Person("A",20));
		people.add(new Person("B",10));
		people.add(new Person("C",30));
		
		Collections.sort(people, new NameComparator());
		System.out.println("Sorting based on Name");
		for(Person p : people) {
			System.out.println(p);
		}
		
		Collections.sort(people, new AgeComparator());
		System.out.println("Sorting based on Age");
		for(Person p : people) {
			System.out.println(p);
		}
		

		System.out.println("Sorting based on name using new style");
		Comparator<Person> nameComparator = new Comparator<Person>() {
			@Override
			public int compare(Person p1 , Person p2) {
				return p2.getName().compareTo(p1.getName());
			}
		};
		

		Collections.sort(people, nameComparator);
		for(Person p : people) {
			System.out.println(p);
		}
	}
}


class Person {

	private String name;
	private int age;
	
	Person(String name, int age){
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
	public String toString() {
		return name + " ( "+ age+" ) ";
	}
}



class NameComparator implements Comparator<Person>{
	@Override
	public int compare(Person p1, Person p2) {
		return p1.getName().compareTo(p2.getName());
	}
}

class AgeComparator implements Comparator<Person>{
	@Override
	public int compare(Person p1 , Person p2) {
		if(p1.getAge()>p2.getAge()) {
			return -1;
		}else if(p1.getAge()<p2.getAge()) {
			return 1;
		}else {
			return 0;
		}
	}
}

	



	

