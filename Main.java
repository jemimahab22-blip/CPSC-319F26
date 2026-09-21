import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public static void runTests(int alg_num, int n, int repeats) {
    // Run value test
    String printed_f_n = switch (alg_num) {
        case 1 -> Integer.toString(ALG_1.fibonacci(n));
        case 2 -> String.format("%.2e", new BigDecimal(ALG_2.fibonacci(n)));
        case 3 -> String.format("%.2e", new BigDecimal(ALG_3.fibonacci(n)));
        default -> "SHOULD NOT BE HERE";
    };
    IO.println("F(" + n + ") = " + printed_f_n);

    // Run timing test
    double ave_time_ms = switch (alg_num) {
        case 1 -> Timing.averageTimeMs(n, repeats, ALG_1::fibonacci);
        case 2 -> Timing.averageTimeMs(n, repeats, ALG_2::fibonacci);
        case 3 -> Timing.averageTimeMs(n, repeats, ALG_3::fibonacci);
        default -> Double.NaN;
    };
    IO.println("Average Time (ms) = " + ave_time_ms);
}

void main(String[] args) {
    // Maybe automate your table tests using command line flags/inputs...
    // Or create a separate entry point program for automatic testing...
    // Or suffer doing it manually...

    try (Scanner scanner = new Scanner(System.in)) {

        // Get which algorithm we want from user
        IO.print("Provide algorithm number (1, 2, or 3): ");
        int alg_num = scanner.nextInt();
        switch(alg_num){
            case 1: IO.println("Selected Recursive Fibonacci (Alg 1)"); break;
            case 2: IO.println("Selected Iterative Fibonacci (Alg 2)"); break;
            case 3: IO.println("Selected Matrix Exponentiation (Alg 3)"); break;
            default: IO.println("Unrecognized Algorithm, terminating..."); return;
        }

        // Get our input n
        IO.print("Enter a non-negative input n: ");
        int n = scanner.nextInt();
        if (n < 0) {
            IO.println("n must be >= 0, terminating...");
            return;
        }

        // Get our number of repeats for averaging our timing results
        IO.print("Enter positive number of repeats for averaging: ");
        int repeats = scanner.nextInt();
        if (repeats <= 0) {
            IO.println("Repeats must be > 0, terminating...");
            return;
        }

        IO.println("Proceeding to result generation...");

        runTests(alg_num, n, repeats);

    } catch (InputMismatchException e) {
        IO.println("Invalid input provided, terminating...");
    } catch (Exception e) {
        IO.println("An unexpected error occurred: " + e.getMessage());
    }
}