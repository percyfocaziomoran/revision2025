
public class PrimeNumberSequential {
    static final int N = 1000;
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        int freq = 1; //2 is prime
        for(int k = 0; k < N; k++){ //start past 2
            if(PTest.isPrime(k)) {
                freq++;
            }
        }
        long endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;
        System.out.printf("Number of primes in range %d to %d = %d\n",1,N,freq);
        System.out.println(runningTime + " millisecs (" + (runningTime/1000.0) + ")secs");
    }
}
class PTest {
    public static boolean isPrime(int n){
        if(n <= 1){ //if 1 or less, not a prime number
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;


    }
}
