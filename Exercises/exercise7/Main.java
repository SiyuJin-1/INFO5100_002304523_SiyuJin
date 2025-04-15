import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n************************ Question (a) ************************\n");
    
        // Check if the generic method works
        System.out.println("\n---------- Count odd numbers ----------\n");
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        // Count odd numbers
        int countOdd = GenericMethod.countNumber(numbers1, n -> n % 2 != 0); 
        System.out.println("The original list is: " + numbers1);
        System.out.println("Odd count: " + countOdd);

        // Count prime numbers
        System.out.println("\n-------- Count prime numbers --------\n");
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        int countPrime = GenericMethod.countNumber(numbers2, n -> {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        });
        System.out.println("The original list is: " + numbers2);
        System.out.println("Odd count: " + countPrime);

        // Count palindromes
        System.out.println("\n-------- Count palindromes --------\n");
        List<String> words = Arrays.asList("ab", "abca", "abba", "abcba");
        int countPalindrome = GenericMethod.countNumber(words, s -> {
            int len = s.length();
            for (int i = 0; i < len / 2; i++) {
                if (s.charAt(i) != s.charAt(len - 1 - i)) return false;
            }
            return true;
        });
        System.out.println("The original list is: " + words);
        System.out.println("Palindrome count: " + countPalindrome);
    
        System.out.println("\n************************ Question (b) ************************\n");
        // Primitive types in Java (such as int) cannot be used directly with generic arrays, so we need to use their wrapper classes, such as Integer, Double, etc
        Integer[] numbers3 = {1, 2, 3, 4, 5};
        System.out.println("The original array is: " + Arrays.toString(numbers3));
        GenericMethod.exchange(numbers3, 0, 2);
        System.out.println("Exchanged array: " + Arrays.toString(numbers3)); 

        System.out.println("\n************************ Question (c) ************************\n");
        Integer[] numbers4 = {1, 2, 3, 5, 7, 9};
        int maxNum = GenericMethod.maxElement(numbers4, 0, numbers4.length);
        System.out.println("Max in nums[] is: " + maxNum);
    }
}