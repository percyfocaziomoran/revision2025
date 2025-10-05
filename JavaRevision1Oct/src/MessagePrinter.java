//practise for Concurrent Developing -> practise of threads, etc
public class MessagePrinter implements Runnable{
    String message;
    int n;

    public MessagePrinter(String message, int n) { //constructor
        this.message = message;
        this.n = n;
    }

    public void run(){ //run method -> to get the message to print n amount of times
        //code for thread here
        for(int i = 0; i < n; i++){
            System.out.println(message);
        }
    }

    public static void main(String[] args){ //the main method saying how many
        Runnable r = new MessagePrinter("Happy Birthday", 10);
        Thread t = new Thread(r);
        Thread two = new Thread(new MessagePrinter("hi", 5));
        t.start();
        two.start();
    }
}
