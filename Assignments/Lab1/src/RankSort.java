//Percy Focazio-Moran
//Problem 1: RankSort.java
    //Implement Rank Sort using multiple threads. Each thread should sort a part of the array.

public class RankSort {
    static final int N = 100000; // N is capitalised to show as final int
    public static void main(String[] args){
        int data[] = new int[N]; //new int array with size of N

        //fill data
        addData(data);

        //create copy to rank sort
        int copy[] = new int[data.length]; //identical size copy

        int amountT = Runtime.getRuntime().availableProcessors(); //as meany threads as processors available

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
            if(data[i] >= data[i+1]){
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
class RankerNoDup extends Thread {
    int data[];
    int copy[];
    int lb, ub;

    public RankerNoDup(int data[], int copy[], int lb, int ub){
        this.data = data;
        this.copy = copy;
        this.lb = lb;
        this.ub = ub;
    }

    public void run(){
        for(int i = lb; i < ub; i++){
            int rank = 0;
            for(int j = 0; j < data.length; j++){
                if(data[j] < data[i]){
                    rank++;
                }
                copy[rank] = data[i];
            }
        }
    }
}

//public class RankSort {
//    public static void main(String[] args){
//        //No duplicates allowed
//        int data[] = {4,7,1,56,78,23,5,69,85,90,45,81,10};
//        int copy[] = new int[data.length];
//        for(int i = 0; i < data.length; i++) {
//            //set rank = rank of data[i] and assign to copy k
//            int rank = 0;
//            for (int j = 0; j < data.length; j++) {
//                if (data[j] < data[i]){
//                    rank++;
//                }
//            }
//            copy[rank] = data[i];
//        }
//        for(int a = 0; a < copy.length; a++){
//            System.out.print(copy[a] + " ");
//        }
//        System.out.println();
//    }
//}