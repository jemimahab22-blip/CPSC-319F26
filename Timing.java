import java.util.function.IntFunction;

public class Timing {

    // <R> is the changing return type generic (int vs BigInteger) for any
    // of the three Fibonacci functions passed in as IntFunction<R> (yes we
    // pass in the function to the function!)
    public static <R> long measureRunNs(int n, IntFunction<R> fibonacci) {
        // TODO: Record start, compute F(n), record end, return elapsed.
        long start = System.nanoTime();
        fibonacci.apply(n); // Executes the function. We don't need to store
                            // the resulting type so ignore it!
        long end = System.nanoTime();
        long duration = end-start;
        return duration;
    }

    // <R> is the changing return type generic (int vs BigInteger) for any
    // of the three Fibonacci functions passed in as IntFunction<R> (yes we
    // pass in the function to the function!)
    public static <R> double averageTimeMs(int n, int repeats, IntFunction<R> fibonacci) {
        // TODO: Run the result multiple times and return the average in ms.
        int num_runs = 10000;
        long total_duration =0;
        for(int i=0; i<num_runs; i++) {
            long start = System.nanoTime();
            fibonacci.apply(n);
            long end = System.nanoTime();
            total_duration+=(end-start);

        }
        return measureRunNs(n, fibonacci) / 1_000_000.0;

    }
}
