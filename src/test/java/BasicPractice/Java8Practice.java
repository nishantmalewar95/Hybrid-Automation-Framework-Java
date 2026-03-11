package BasicPractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8Practice {

	public static void main(String[] args) {
/*		
		List<String> names = Arrays.asList("Nikhil","john","jane","Alex","Jack");
		List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		names.stream().filter(s->s.startsWith("J")).forEach(System.out::println);
		
		nums.stream().filter(n->n%2==0).map(n->n*2).forEach(n->System.out.println("Double of even: "+n));
		
		List<String> upperNames = names.stream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println("uppercase List : "+ upperNames);
		
		int totalSum = nums.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Total sum: "+totalSum);
*/		
		
		// Initializing Data
        List<String> names = Arrays.asList("Nikhil", "John", "Jane", "Alex", "Jack");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("--- 1. Names starting with 'J' ---");
        // filter: Condition check karta hai
        // forEach: Result ko print karta hai
        names.stream()
             .filter(name -> name.startsWith("J"))
             .forEach(name -> System.out.println(name));

        System.out.println("\n--- 2. Double the Even Numbers ---");
        // filter: Sirf even numbers lega (n % 2 == 0)
        // map: Number ko double kar dega (n * 2)
        nums.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * 2)
            .forEach(System.out::println); 

        System.out.println("\n--- 3. Collect Uppercase Names to List ---");
        // map: String ko uppercase me badlega
        // collect: Nayi list me save karega
        List<String> upperNames = names.stream()
                                       .map(name -> name.toUpperCase())
                                       .collect(Collectors.toList());
        System.out.println(upperNames);

        System.out.println("\n--- 4. Sum without Loop ---");
        // mapToInt: Integer stream ko int stream me badalta hai sum use karne ke liye
        int totalSum = nums.stream()
                           .mapToInt(n -> n)
                           .sum();
        System.out.println("Total Sum: " + totalSum);
        
        //1.reverse a string 
        String str = "Automation";
        
        String reversed = Stream.of(str.split(""))
        		.reduce("", (rev,c)->c+rev);
        System.out.println(reversed);
        
       
        //2.palindrome
        
        String s = "MADAM";
        
        boolean isPalindrome = IntStream.range(0, s.length()/2)
        		.allMatch(i->s.charAt(i)==s.charAt(s.length()-1-i));
        
        System.out.println(isPalindrome);
        
        //Sort Names by Length
        List<String> input = Arrays.asList("Nishant","John","Janesh","sunder","Jham");
        
        System.out.println("--- Exercise 5: Sorting by Length ---");
        
        input.stream().sorted(Comparator.comparing(String::length))
        .forEach(System.out::println);
        
        //2. Unique Even Numbers (Duplicates hatana)
        List<Integer> numbers=Arrays.asList(2,4,2,6,4,8,10,2);
        
        System.out.println("\n--- Exercise 6: Unique Even Numbers ---");
        
        List<Integer> result = numbers.stream()
        		.filter(n->n%2==0)
        		.distinct()
        		.collect(Collectors.toList());
        
        System.out.println(result);
        
        
        
        //3. City Count (Grouping Logic)
        List<String> cities =Arrays.asList("Mumbai","Pune","Nagpur","Mumbai","Delhi","Pune");
        
        System.out.println("\n--- Exercise 7: City Count (Grouping) ---");
        
        Map<String, Long> countMap=cities.stream()
        		.collect(Collectors.groupingBy(
                          city->city,
                          Collectors.counting()
                          ));
        System.out.println(countMap);
        
        
        System.out.println("\n--- Exercise 8: Filter, Sort, and Limit ---");
        filterAndSortNumbers();
        }
        
	//4.Filter, Sort, and Limit
    public static void filterAndSortNumbers() {
    	
    	List<Integer> numbers =Arrays.asList(1,8,3,12,5,9,2,15,7);
    	
    	System.out.println("Processing numbers..");
    	
    	List<Integer> result = numbers.stream().
    			filter(n->n>5).
    			sorted(Comparator.reverseOrder())
    			.limit(3)
    			.collect(Collectors.toList());
    	
    	result.forEach(System.out::println);
    
	}

}
