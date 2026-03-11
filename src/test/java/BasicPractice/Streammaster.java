package BasicPractice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streammaster {


	public static void main(String[] args) {
		
		List<String> input = Arrays.asList("mumbai", "pune", "delhi", "bangalore");
		
		// SCENARIO 1: transformation + printing (No storage)
		System.out.println("--- Scenario 1: Sirf Print Karna Hai ---");
		
		input.stream().map(String::toUpperCase)// Har city ko uppercase mein badla (Map used)
		.forEach(System.out::println); // Wahi print kar diya (No variable needed)
		
		// SCENARIO 2: transformation + storage (Variable needed)
		System.out.println("\n--- Scenario 2: Data Save Karna Hai ---");
		
		List<String> upperCities =input.stream()
				   .map(String::toUpperCase) // Transform kiya
				   .collect(Collectors.toList()); // List mein pack kiya
		
		// Ab aap 'upperCities' ko program mein kahin bhi use kar sakte hain
        System.out.println("Saved List: " + upperCities);
        
        
       // SCENARIO 3: Sorting without Map
        System.out.println("\n--- Scenario 3: Sirf Sorting (No Map needed) ---");
        input.stream().sorted().forEach(System.out::println);
        
        
		}

}
