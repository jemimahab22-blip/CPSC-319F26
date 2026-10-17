import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * PROVIDED NAMESPACE - DO NOT MODIFY.
 *
 * Verification checks the final result after sorting. It is not part of the
 * sorting algorithm and its work is not included in the comparison or
 * move counts reported by Main.
 */
public final class Verification {
    // Namespace only class
    private Verification() {}

    // Checks whether the elements are sorted.
    public static boolean isSorted(String[] list) {
        return IntStream.range(1, list.length).allMatch(i -> list[i - 1].compareTo(list[i]) <= 0);
    }

    // Checks that the result contains exactly the original elements.
    public static boolean sameContents(String[] original, String[] result) {
        if (original.length != result.length) return false;
        return elementCounts(original).equals(elementCounts(result));
    }

    // Counts how many times each element occurs.
    private static Map<String, Integer> elementCounts(String[] list) {
        return Arrays.stream(list).collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
    }
}
