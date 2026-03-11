package BasicPractice;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8Task {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Nikhil", "John",  "Ankit", "Amit", "Naveen");

        List<String> result = names.stream()
        .filter(n -> n.startsWith("A"))
        .sorted()
        .collect(Collectors.toList());

        System.out.println("Result: "+result);

    }
}
