import java.util.*;
import java.util.function.Predicate;

public class GenericMethod {
    // Count the number of elements in a collection that have a specific property
    // The <T> representation can accept arguments of any type (such as Integer, String, User, etc.)
    public static <T> int countNumber(Collection<T> collection, Predicate<T> predicate) {
        int count = 0;
        for (T item : collection) {
            // Determines whether the element satisfies the passed condition
            if (predicate.test(item)) {
                count++;
            }
        }
        return count;
    }

    // Exchange the positions of two different elements in an array
    public static <T> void exchange(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Find the maximal element in the range [begin, end) of a list.
    public static <T extends Comparable<T>> T maxElement(T[] list, int begin, int end) {
        if (begin < 0 || end > list.length || begin >= end) {
            throw new IllegalArgumentException("Invalid range");
        }
        T max = list[begin];
        // We cannot directly compare two generic objects with >, only numeric objects are supported >
        // To compare sizes in generics, we must use the Comparable<T> interface
        for (int i = begin + 1; i < end; i++) {
            if (list[i].compareTo(max) > 0){
                max = list[i];
            }
        }
        return max;
    }
}
