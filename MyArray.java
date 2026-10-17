import java.util.Arrays;
import java.util.Objects;

/*
 * PROVIDED CLASS - DO NOT MODIFY.
 *
 * MyArray stores sortable Strings while exposing only tracked comparison and
 * copy operations. A comparison is one ordering test between sortable values.
 * A move is one copy of a sortable String into another array location or
 * temporary storage location.
 *
 * extract() is provided only for verification/output and must not be used by
 * sorting implementations.
 */
public final class MyArray {
    // Cannot read elements directly
    private final String[] elements;

    /*
     * TemporaryStringStorage represents temporary algorithm storage for one String.
     * Its value cannot be inspected or constructed directly outside MyArray.
     */
    public static final class TemporaryStringStorage {
        // Cannot read element directly
        private final String element;

        // Cannot construct directly
        private TemporaryStringStorage(String initial_element) { element = Objects.requireNonNull(initial_element); }
    }

    // Initialize the array with our elements
    public MyArray(String[] initial_elements) {
        Objects.requireNonNull(initial_elements);
        for (String element : initial_elements) Objects.requireNonNull(element);
        elements = initial_elements.clone();
    }

    // Get the size of the array
    public int size() { return elements.length; }

    // Copies [start, end) into a new array and records one move per copied element.
    public MyArray copyRange(int start, int end) {
        Objects.checkFromToIndex(start, end, elements.length);
        SortCounter.incrementMoveCount(end - start);
        return new MyArray(Arrays.copyOfRange(elements, start, end));
    }

    // Returns the stored elements in array order without affecting the counters.
    // Should NEVER be called in the sorting algorithms.
    public String[] extract() { return elements.clone(); }

    // Returns whether the left element is less than the right element.
    public boolean lessThan(int left, int right) {
        SortCounter.incrementComparisonCount();
        return elements[left].compareTo(elements[right]) < 0;
    }

    // Returns whether the left element is less than or equal to the right element.
    public boolean lessThanOrEqualTo(int left, int right) {
        SortCounter.incrementComparisonCount();
        return elements[left].compareTo(elements[right]) <= 0;
    }

    // Returns whether the array element is less than the temporary element.
    public boolean lessThan(int index, TemporaryStringStorage other) {
        Objects.requireNonNull(other);
        SortCounter.incrementComparisonCount();
        return elements[index].compareTo(other.element) < 0;
    }

    // Returns whether the array element is less than or equal to the temporary element.
    public boolean lessThanOrEqualTo(int index, TemporaryStringStorage other) {
        Objects.requireNonNull(other);
        SortCounter.incrementComparisonCount();
        return elements[index].compareTo(other.element) <= 0;
    }

    // Returns whether an element from arr1 is less than an element from arr2.
    public static boolean lessThan(MyArray arr1, int arr1_index, MyArray arr2, int arr2_index) {
        Objects.requireNonNull(arr1);
        Objects.requireNonNull(arr2);
        SortCounter.incrementComparisonCount();
        return arr1.elements[arr1_index].compareTo(arr2.elements[arr2_index]) < 0;
    }

    // Returns whether an element from arr1 is less than or equal to an element from arr2.
    public static boolean lessThanOrEqualTo(MyArray arr1, int arr1_index, MyArray arr2, int arr2_index) {
        Objects.requireNonNull(arr1);
        Objects.requireNonNull(arr2);
        SortCounter.incrementComparisonCount();
        return arr1.elements[arr1_index].compareTo(arr2.elements[arr2_index]) <= 0;
    }

    // Copies one array element into temporary storage and records one move.
    public TemporaryStringStorage copyToTemp(int index) {
        SortCounter.incrementMoveCount();
        return new TemporaryStringStorage(elements[index]);
    }

    // Copies one array element to another position in this array and records one move.
    public void copyBetween(int source_index, int destination_index) {
        SortCounter.incrementMoveCount();
        elements[destination_index] = elements[source_index];
    }

    // Copies a temporary element into this array and records one move.
    public void copyFromTemp(TemporaryStringStorage other, int index) {
        Objects.requireNonNull(other);
        SortCounter.incrementMoveCount();
        elements[index] = other.element;
    }

    // Copies one element from the source array into the destination array and records one move.
    public static void copyBetween(MyArray source, int source_index, MyArray destination, int destination_index) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(destination);
        // Fall back to the same-array case
        if (source == destination) {
            source.copyBetween(source_index, destination_index);
            return;
        }
        SortCounter.incrementMoveCount();
        destination.elements[destination_index] = source.elements[source_index];
    }
}
