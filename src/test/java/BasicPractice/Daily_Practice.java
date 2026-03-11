package BasicPractice;

import java.util.Arrays;

public class Daily_Practice {

	public static void main(String[] args) {
	
	int[] num = {43,44,54,23,223,32,90};
	
	Arrays.stream(num).max().ifPresent(System.out::println);
	
	
	
	}

}
