import java.math.BigInteger;

public class ALG_3 {

    private static BigInteger[][] multiplyMatrices(BigInteger[][] A, BigInteger[][] B) {
        // TODO: Implement Matrix 2×2 Multiplication helper (call in matrixPower somewhere...)
        BigInteger[][] C = new BigInteger[2][2];
        C[0][0] = A[0][0].multiply(B[0][0]).add(A[0][1].multiply(B[0][1]));
        C[0][1] = A[0][0].multiply(B[0][1]).add(A[0][1].multiply(B[1][1]));
        C[1][0] = A[1][0].multiply(B[0][0].add(A[1][1].multiply(B[1][0])));
        C[1][1] = A[1][0].multiply(B[0][1].add(A[1][1].multiply(B[1][1])));
        return C;

    }

    // Helper B — identity2x2(): return the 2×2 identity matrix
    private static BigInteger[][] identity2x2() {
        // TODO: Implement Identity 2x2 initialization helper (call in matrixPower somewhere...)
        BigInteger[][] I = new BigInteger[2][2];
        I[0][0] = BigInteger.ONE;
        I[0][1] = BigInteger.ZERO;
        I[1][0] = BigInteger.ZERO;
        I[1][1] = BigInteger.ONE;

        return I;
    }

    // Helper C — matrixPower(FM, p): fast exponentiation (recursive)
    private static BigInteger[][] matrixPower(BigInteger[][] FM, int p) {
        // TODO: Implement Matrix Power helper (call in fibonacci...)
        BigInteger[][] result = identity2x2();
        BigInteger[][] base = FM;

        while(p>0){
            if(p%2!=0){
                result = multiplyMatrices(result, base);}
            base = multiplyMatrices(base, base);
            p>>=1;
        }

        return result;
    }

    public static BigInteger fibonacci(int n) {
        // TODO: Implement Algorithm 3 Matrix Exponentiation Fibonacci Calculation
        if(n==0)return BigInteger.ZERO;
        if(n==1)return BigInteger.ONE;
        BigInteger[][] FM = {
                {BigInteger.ONE,BigInteger.ONE},
                {BigInteger.ONE, BigInteger.ZERO}
        };
        BigInteger[][] P = matrixPower(FM, n-1);

        return P[0][0];
    }
}
