package BasicPractice;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Remove_Duplicate {

	public static void main(String[] args) {
		
		//from array
		int[] dupArray = {1,2,2,3,4,4,5};
		int[] uniqueArray = Arrays.stream(dupArray).distinct().toArray();
		System.out.println("unique Array: "+ Arrays.toString(uniqueArray));
		
		//from string
		String dupst = "ABSHABBAHAHHA";
		String uniqueString = Arrays.stream(dupst.split("")).distinct().collect(Collectors.joining());
		System.out.println("Unique string : "+ uniqueString);
	}

}
