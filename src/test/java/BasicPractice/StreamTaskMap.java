package BasicPractice;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StreamTaskMap {

    private static final Logger logger = LogManager.getLogger(StreamTaskMap.class);

    public static void main(String[] args) {
        // Step 1: Initialize the list of integers
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6);
        logger.info("Original Numbers: {}", numbers);

        // Step 2: Apply Stream Pipeline (Map -> Filter -> Sum)
        int finalSum = numbers.stream()
                .map(n -> n * n)                   // Square each number
                .filter(n -> n > 20)               // Keep squares > 20
                .mapToInt(Integer::intValue)       // Convert to primitive int stream for math
                .sum();                            // Calculate total sum

        // Step 3: Print and Log the result
        logger.info("Calculation completed. The sum of squares greater than 20 is: {}", finalSum);
        System.out.println("Final Result: " + finalSum);
    }
}