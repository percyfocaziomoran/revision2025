//Percy Focazio-Moran
//Taking code from RankSort and changing the amount of threads in pool to see difference in time

//Problem 3: ChangingThreadCount.java
    //Change the size of the thread pool and observe how it affects task completion
    //time.

//My 8 threads took longer? But overhead might be in part for that
//There are still issues here lol
public class ChangeThreadCount {
    static final int N = 100000; // N is capitalised to show as final int
    public static void main(String[] args){
        int data[] = new int[N]; //new int array with size of N

        //fill data
        addData(data);

        //create copy to rank sort
        int copy[] = new int[data.length]; //identical size copy

        int threadAmount = Runtime.getRuntime().availableProcessors(); //as meany threads as processors available

        System.out.println("Available threads: " + threadAmount); //8 for me;
        ranking(data, copy, threadAmount);

        int other = 4; //half the amount I have available
        System.out.println("Available threads: " + other); //four now
        ranking(data, copy, other);

    }
    public static void ranking(int[] data, int[] copy, int amountT){
        int index[] = new int[amountT+1]; //index size is size of amount of processors

        for(int i = 0; i < amountT; i++){
            index[i] = (i * N) / amountT; //dividing the work "evenly" 0 / 4. 10 / 4. 20 / 4. 30 / 4. would be 1/4 each
        }

        long startTime = System.currentTimeMillis(); //tracking time w/ threads

        Thread workers[] =  new RankerNoDup[amountT];

        for(int i = 0; i < amountT; i++){
            workers[i] = new RankerNoDup(data,copy,index[i],index[i+1]);
            workers[i].start();
        }

        try{
            for(int j = 0; j < amountT; j++){
                workers[j].join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime; //total time for threads

        //good practise, check to make sure it actually sorted
        data = copy;
        copy = null;
        boolean sorted = true;

        for(int i = 0; i < data.length-1; i++){
            if(data[i] >= data[i+1]){ //= because we checked no duplicates
                sorted = false;
            }
        }

        System.out.println("Sorted list: " + sorted);
        System.out.println("Running time: " + runTime/1000.0 + " seconds");
    }
    static void addData(int f[]){
        for(int i = 0; i < f.length / 2; i++){
            int k = (int)(Math.random()*f.length);
            int m = (int)(Math.random()*f.length);
            while(k == m){ //give it a good shuffle
                m = (int)(Math.random()*f.length);
            }
            int temp = f[k];
            f[k] = f[m];
            f[m] = temp;
        }
    }

}
