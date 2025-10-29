//Percy Focazio-Moran

//Problem 4:
//Use the Callable interface to find all prime numbers in the range 1 to 1000.
//Return the count of primes for each thread.

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.*;

public class PrimeNumSeq {
    static final int N = 1000;
    public static void main(String[] args){
        int numProc = Runtime.getRuntime().availableProcessors(); //usually 8 for me
        ExecutorService pool = Executors.newFixedThreadPool(numProc);
//        ArrayList<Future<Integer>> futureSeq = new ArrayList<Future<Integer>>();

        long startTime = System.currentTimeMillis(); //tracking time w/ threads
        Future<Integer> f = pool.submit(new Sequential(N));
//        futureSeq.add(f); //adds to Future ArrayList

        try {
            System.out.println("Up until the integer " + N + ", there are " + f.get() + " prime integers."); //to print sum
        }catch (InterruptedException e){
            System.out.println("Oops. Interrupted Exception: " + e);
        }catch (ExecutionException e){
            System.out.println("Oops. Execution Exception: " + e);
        }
        long endTime = System.currentTimeMillis(); //tracking time w/ threads
        long totalTime = endTime - startTime; //total

        System.out.println("This process took " + totalTime/1000.0 + " seconds. ");


//          REALISED THIS WAS UNNEEDED, I CAN EXPLAIN WHY IF NEEDED
//        for(int i = 0; i <= N; i++){
//            Future<Integer> f = pool.submit(new Sequential(i)); //temp hold int
//
//            futureSeq.add(f); //adds to Future ArrayList
//
//        } //goes through all 0-999999 but that already happens in the thread section
//        int[] arr = new int[N];
//        for(int j =0; j <= N; j++){
//            Future<Integer> f = futureSeq.get(j);
//            arr[j] = futureSeq.get(j);
//            try {
//                if (f == 1) {
//                    sum++;
//                }
//            }catch(InterruptedException e){
//
//            }
//        }
//        System.out.println(sum);
//        //now i need to add together futureSeq --> should be full of 0 and 1s

        pool.shutdown();
    }
}

class Sequential implements Callable<Integer>{
    private int n;
    Sequential(int a){ //where 'a' is the number we sequentially go to
        n = a;
    }
    public Integer call(){
        int freq = 0;
        for(int k = 0; k < n; k++){
            if(isPrime(k)) {
                freq++;
                System.out.println(freq + ". Here lies the prime: " + k); //just checking work, but this will not be part of final
            }
        }
        return freq;
    }
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