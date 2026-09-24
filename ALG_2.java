import java.math.BigInteger;

public class ALG_2 {
    public static BigInteger fibonacci(int n) {
        // TODO: Implement Algorithm 2 Iterative Fibonacci Calculation
        if(n == 0) return BigInteger.ZERO;
        if(n==1) return BigInteger.ONE;
        // I am initializing my counters
        BigInteger c1 = BigInteger.ZERO;
        BigInteger c2= BigInteger.ONE;
        //Looping through the F(2) to F(n) by repeatedly summing the previous two numbers
        for(int i = 2; i<n; i++){
            BigInteger Fnext = c1.add(c2);
            c1=c2;
            c2=Fnext;
        }
        return c2;
    }
}
