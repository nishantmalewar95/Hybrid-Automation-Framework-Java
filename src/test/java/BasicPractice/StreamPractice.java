package BasicPractice;

import java.util.Arrays;
import java.util.List;

public class StreamPractice {

	public static void main(String[] args) {
		
		List<String> cities = Arrays.asList("Mumbai","Pune","Delhi","Banglore","Chennai");
		
		cities.stream()
		      .filter(city -> city.startsWith("C"))
		      .forEach(System.out::println);
	}

}
