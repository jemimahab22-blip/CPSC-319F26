/*
 * PROVIDED NAMESPACE - DO NOT MODIFY.
 *
 * SortCounter stores the global experiment counts shared by all participating
 * data structures. Counting is enabled only while a sorting experiment runs.
 *
 * Sorting implementations must not increment, reset, enable, disable, or
 * otherwise access the counter directly.
 */
public final class SortCounter {
    // Namespace only class
    private SortCounter() {}

    private static boolean enabled = false;
    public static void enable()       { enabled = true; }
    public static void disable()      { enabled = false; }
    public static boolean isEnabled() { return enabled; }

    private static long comparison_count = 0;
    public static void incrementComparisonCount()      { if (enabled) comparison_count++; }
    public static void incrementComparisonCount(int n) { if (enabled) comparison_count += n; }
    public static long getComparisonCount()            { return comparison_count; }

    private static long move_count = 0;
    public static void incrementMoveCount()      { if (enabled) move_count++; }
    public static void incrementMoveCount(int n) { if (enabled) move_count += n; }
    public static long getMoveCount()            { return move_count; }

    public static void reset() {
        comparison_count = 0;
        move_count = 0;
    }
}
