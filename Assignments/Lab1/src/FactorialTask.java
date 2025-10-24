//Percy Focazio-Moran

//Problem 2: FactorialTask.java
    //Create a thread pool using ExecutorService. Submit tasks that calculate the
    //factorial of numbers from 1 to 5.

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class FactorialTask {
    public static void main(String[] args){
        int amountT = Runtime.getRuntime().availableProcessors(); //as meany threads as processors available
        ExecutorService executor = Executors.newFixedThreadPool(amountT);

//        Runnable runTask= () -> {
//            try{
//                executor.wait(300); //make it wait
//            }catch(InterruptedException e){
//                e.printStackTrace();
//            }
//        };

        Callable<Integer> five = () -> {
            int sum = 0;
            for(int i=0; i <= 5; i++){ //needs to be specific to number, figuring out how
                sum += i;
            }
            System.out.println("The factorial of 5 is " + sum);
            return sum;
        };
        Callable<Integer> four = () -> {
            int sum = 0;
            for(int i=0; i <= 4; i++){ //needs to be specific to number, figuring out how
                sum += i;
            }
            System.out.println("The factorial of 4 is " + sum);
            return sum;
        };
        Callable<Integer> three = () -> {
            int sum = 0;
            for(int i=0; i <= 3; i++){ //needs to be specific to number, figuring out how
                sum += i;
            }
            System.out.println("The factorial of 3 is " + sum);
            return sum;
        };
        Callable<Integer> two = () -> {
            int sum = 0;
            for(int i=0; i <= 2; i++){ //needs to be specific to number, figuring out how
                sum += i;
            }
            System.out.println("The factorial of 2 is " + sum);
            return sum;
        };
        Callable<Integer> one1 = () -> {
            int sum = 1;
            System.out.println("The factorial of 1 is " + sum);
            return sum;
        };

        List<Callable<Integer>> taskList = Arrays.asList(one1, two, three, four, five);

        try {
            List<Future<Integer>> futures = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();
    }

}
