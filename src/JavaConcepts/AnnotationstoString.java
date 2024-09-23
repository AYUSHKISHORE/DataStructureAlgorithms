package JavaConcepts;

/*
 * Annotations are used to provide supplemental about a program.
 * Annotations start with @
 * Annotation do not change the actions of the compiled program.
 * Annotations are not pure comments as they can change the way a program is treated by the compiler
 * Annotations basically are used to provide additional information, so could be an alternative to XML & Java marker interface
 * 
 * java.lang.annotation.Annotation
 * 1)Standard (Builtin) Annotation
 * 		1.1) -> General Purpose Annotation (java.lang.package) -> @Override | @Deprecated | @SafeVarArgs | @SupressWarning | @FunctionalInterface
 * 		1.2) -> Meta Annotations -> @Inherited | @Documented | @Target | @Retention | @Repeatable
 * 2)Custom Annotations 
 * 
 * 
 * toString() -> In java the toString() method is used to provide a string representation of an object.
 * -> It is defined in the object class, which  means every java class inherits the method.
 * -> By default toString() returns a string consisting  of the class name followed by the "@" character and the object's hashcode in hexadecimal.
 * -> However its common practice to override toString() in your own classes to provide a more meaningful string representation of the object
 * 
 * Default Implemenation
 * Object obj = new Object();
 * System.out.println(obj.toString());
 * 
 * 
 */
public class AnnotationstoString {

	public static void main(String []args) {

		PersonAnnotation p1 = new PersonAnnotation("A",10);
		PersonAnnotation p2 = new PersonAnnotation("B",20);
		
		System.out.println(p1);
		System.out.println(p2);
		
		Derived Obj = new Derived();
		Obj.display();
		
	}
}

class PersonAnnotation{
	String name;
	int age;
	PersonAnnotation(String name, int age){
		this.name=name;
		this.age=age;
	}
	
	@Override
	public String toString() {
		return "name = "+name+" age = "+ age;
	}
}

class Base{
	public void display() {
		System.out.println("Base Display");
	}
}

class Derived extends Base{
	@Override
	public void display() {
		System.out.println("Derived Display");
	}
}
