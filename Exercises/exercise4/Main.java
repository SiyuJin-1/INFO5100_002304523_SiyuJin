import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        // Define regex patterns
        String[] patterns = {
            "\\d+",              // Matches one or more digits
            "[a-zA-Z]+",         // Matches one or more alphabet letters
            "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+", // Matches a email address
            "\\d{3}-\\d{3}-\\d{4}", // Matches a Phone number in the format XXX-XXX-XXXX
            "\\d{4}-\\d{2}-\\d{2}"    // Matches dates in the format YYYY-MM-DD
        };

        // Define positive and negative test cases
        String[] positiveCase = {
            "12345", "abcABC", "jin.sy@northeastern.edu", "012-345-6789", "2025-03-06"};
        String[] negativeCase = {
            "abcABC", "12345", "12345-neu@com", "999-999", "2025/03/06"};

        // Test each pattern with both positive and negative cases
        for (int pattern = 0; pattern < patterns.length; pattern++) {
            Pattern regex = Pattern.compile(patterns[pattern]);
            System.out.println("-----------------------Testing pattern: " + patterns[pattern] + "-----------------------\n");
            
            // Test positive cases
            Matcher positiveMatcher = regex.matcher(positiveCase[pattern]);
            System.out.println("Positive example: " + positiveCase[pattern]);
            System.out.println("Matching result: " + positiveMatcher.matches());
            System.out.println();

            // Test negative cases
            Matcher negativeMatcher = regex.matcher(negativeCase[pattern]);
            System.out.println("Negative example: " + negativeCase[pattern]);
            System.out.println("Matching result: " + negativeMatcher.matches());
            System.out.println();
        }
    }
}
