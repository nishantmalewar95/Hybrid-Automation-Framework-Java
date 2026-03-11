package BasicPractice;

import java.util.ArrayList;

public class JavaCollections {

	public static void main(String[] args) {
		
		ArrayList<String> Name= new ArrayList<String>();
		Name.add("Ankit");
		Name.add("Ankush");
		Name.add("ALtaf");
		
		System.out.println(Name);
		Name.add("Anish");
		System.out.println(Name);
		
		Name.add(1,"Rohit");
		System.out.println(Name);
		
		Name.remove(1);
		System.out.println(Name);
		
		Name.set(1, "Rohit");
		System.out.println(Name);
		System.out.println(Name.get(2));
		
		Name.clear();
		System.out.println(Name);
	}

}
