public class ALG_1 {
    public static int fibonacci(int n) {
        // TODO: Implement Algorithm 1 Recursive Fibonacci Calculation
        if(n==0)return 0;
        if(n==1)return 1;
        if( n>=2){
            return fibonacci(n-1)+fibonacci(n-2);
        }
        return -1;
    }
}
