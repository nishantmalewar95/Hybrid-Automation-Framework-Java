package BasicPractice;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Count_Occurrences {

	public static void main(String[] args) {
		
		String input = "HDFCBanking";
		System.out.println("Charct counts: ");
		Map<String, Long> counts = Arrays.stream(input.split("")).collect(Collectors.groupingBy(c ->c, Collectors.counting()));
		System.out.println(counts);
	}

}
