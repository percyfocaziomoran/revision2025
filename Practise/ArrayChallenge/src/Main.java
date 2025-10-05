public class Main {
    public static void main(String[] args) {
        //given an array, determine if its sort decreasing or increasing order and then
        //rotate at least once in counter-clockwise order

        //ex: [3,4,1,2] would be [1,2,3,4] rotated twice, so we would return TRUE
        //
        int[] array1 = new int[] {1,2,3,4,5,6,7}; //should be false
        System.out.println("Array 1 is rotated, true or false: " + isItRotated(array1));
        int[] array2 = new int[] {3,4,5,6,7,8,1,2}; //should be true, rotated twice
        System.out.println("Array 2 is rotated, true or false: " + isItRotated(array2));
        int[] array3 = new int[] {9,8,7,13,12,11,10}; //should be true, decreasing order then rotated
        System.out.println("Array 3 is rotated, true or false: " + isItRotated(array3));
        int[] array4 = new int[] {100, 101, 102, 103, 104, 105, 94, 95, 96, 97, 98, 99};
        System.out.println("Array 4 is rotated, true or false:" + isItRotated(array4));
        int[] array5 = new int[] {34, 36, 35, 37}; //should be false
        System.out.println("Array 5 is rotated, true or false: " + isItRotated(array5));
        int[] array6 = new int[] {10000000, 9999999, 9999998, 10000001}; //should be false
        System.out.println("Array 6 is rotated, true or false: " + isItRotated(array6));

    }
    public static boolean isItRotated(int[] arr){
        //first, we check constraints
        if(arr.length >= 10000000){
            System.out.println("The array length is too long to determine, therefore ");
            return false; //not rotated
        }
        if(arr.length <= 1){
            System.out.println("The array length is too short to be rotated, therefore ");
            return false; //not rotated as not long enough to rotate
        }
        for(int i = 0; i < arr.length-1; i++){
            //constraints for inside values
            if(arr[i] < 1 || arr[i] >= 10000000){
                System.out.println("The integers include either negative or too large values, therefore ");
                return false;
            }
            int j = i+1;
            int k = Math.abs(arr[i]-arr[j]); //determines distance between first and last
            int l = arr.length-1;
            if(k!= 1 && k != l){ //should be same as length of array -1
//                System.out.println("I is " + arr[i] + " J is " + arr[j]);
//                System.out.println("K alert: " + k + " Length-1: " + l);
                return false; //if not, return a false, not descending / increasing and rotated
            }
        }
        //after for loop, gone through each value and returned false for every situation
        //except an increasing / decreasing not rotated
        int last = arr[arr.length-1];
        int val = Math.abs(last - arr[0]);
        if(val != 1){
//            System.out.println("Last is " + last + " first is " + arr[0] + " distance is " + val);
            return false; //proof of non-rotation
        }
        return true; //rotated
    }
}
//check +1 between each, then if more than 1, check if array length -1 is the distance between
//if yes, continue on. if yes to all, return true.
//needs to be absolute values to be decreasing or increasing


//bubble sort -?

//if i were, id order it, then rotate it