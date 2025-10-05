//Percy Focazio-Moran, 3147979

//Thank you for the exercises! This was really fun. Glad we could do something to get back into it.

import java.util.InputMismatchException;
import java.util.Scanner;

public class JavaRevisionOct1 {
    public static void main(String[] args) { //my very tiny main, just links to main menu, which has the try/catch and switch
        mainMenu();
    }
    public static void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, and welcome to the main menu. To navigate, enter a number between 1-7. Enter 0 to exit.");
        int problem = scanner.nextInt(); //brings you to whichever number you put in
        try{
            switch (problem) {
                case 0:
                    break; //ends program
                case 1:
                    problemOne(); //brings you to problem, then to start over on main menu
                    mainMenu();
                    break; //ends
                case 2:
                    problemTwo();
                    mainMenu();
                    break;
                case 3:
                    problemThree();
                    mainMenu();
                    break;
                case 4:
                    problemFour();
                    mainMenu();
                    break;
                case 5:
                    problemFive();
                    mainMenu();
                    break;
                case 6:
                    problemSix();
                    mainMenu();
                    break;
                case 7:
                    problemSeven();
                    mainMenu();
                    break;
            }
        }catch (InputMismatchException e){ //ensures only the correct numbers are entered, prevents breaks
            System.out.println("Invalid input. Please stay within 0-7.");
            mainMenu(); //gives you another chance
        }
    }
    public static void problemOne() {
        //Write a program/class that asks the user to enter their name and outputs
        // “Hello sss, welcome back to another semester of fun with java”, where
        // ‘sss’ is the name entered by the user.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Please enter your name: ");
        String name = scanner.next(); //scanner gives user chance to add name
        System.out.println("Hello " + name + "! Welcome back to another semester of fun with Java. ");
    }
    public static void problemTwo(){
        //Write a class that takes an integer input and prints an output message
        //stating whether the user entered an odd or even number.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Please give an integer: ");
        int num = scanner.nextInt();
        if(num%2 == 0){ //checks if even by seeing if divisible by 2
            System.out.println("Your number " + num + " is even!");
        }else{
            System.out.println("Your number " + num + " is odd!");
        } // its one or the other
    }
    public static void problemThree(){
        //Write a class that initialises an integer array of length 10.
        //Fill the array with random numbers and then add all the values in the array.
        //Output the answer.
        System.out.println("This problem generates a random integer array within the int range (up to 2147483647 or as low as -2147483647). Enjoy. ");
        int[] intArray = new int[10];
        for (int i = 0; i<10; i++) { //goes through loop 10 times (size of array)
            int ran = (int)(Math.random()*2147483647); //any number within int range
            intArray[i] = makeThatNegorPos(ran); //for the whole of the array, will fill with random number
        }
        System.out.println("Randomised integer array: ");
        for(int j = 0; j<10; j++){ //to print with spacing
            System.out.print(intArray[j] + " ");
        }
        System.out.println(); //for spacing
    }
    public static int makeThatNegorPos(int num){
        int posOrNeg = (int)(Math.random()*2147483647); //random number just for the negative if statement
        if(posOrNeg % 2 == 0){ //makes it randomly negative to account for negative numbers
            num = num * -1;
        }
        return num;
    }
    public static void problemFour(){
        //Write a number guessing program. Assign a value to a variable called number at the top of the class
        // (you could use math.random() for this). Give a prompt that asks for five guesses.
        // See whether any of the guesses matches the number and then print an appropriate message if one does.
        Scanner scanner = new Scanner(System.in);
        int ranNum = makeThatNegorPos((int)(Math.random()*20));
        System.out.println("WELCOME to the NUMBER GUESSING GAME. FIRST, WHO are you?? (enter yer name plz if you dare to...) ");
        String name = scanner.next();
        System.out.println("Alrighty " + name + ". You've got *5* guesses to guess my number. I'll give you a hint: it's between -20 and 20.");
        System.out.println("Guess 1: ");
        int guess1 = scanner.nextInt();
        areYeRight(guess1, ranNum, name);
        System.out.println("First time, no charm. Sucks for you. Ready to continue?");
        System.out.println("Guess 2: ");
        int guess2 = scanner.nextInt();
        areYeRight(guess2, ranNum, name);
        System.out.println("Oof, still struggling. Here we go?");
        System.out.println("Guess 3: ");
        int guess3 = scanner.nextInt();
        areYeRight(guess3, ranNum, name);
        System.out.println("Ah, yes, you still think you can do this. Continue I suppose.");
        System.out.println("Guess 4: ");
        int guess4 = scanner.nextInt();
        areYeRight(guess4, ranNum, name);
        System.out.println("This is your last shot. I don't believe in you, but you might?");
        System.out.println("Guess 5: ");
        int guess5 = scanner.nextInt();
        areYeRight(guess5, ranNum, name);
        System.out.println("Well, I can't say I'm surprised you lost. Better luck next time, huh?");
        mainMenu();
    }
    public static void areYeRight(int guess, int rando, String name){
        if(guess == rando){ //just checking if it is the same int
            System.out.println("Woah there " + name + ". I can't believe you actually guessed it. Congrats I guess?" );
            mainMenu();
        }
    }
    public static void problemFive(){
        //Write a tax calculation program. Prompt the user to input two salaries for a family and
        // output their combined tax bill. A family pays no tax if its income is less than 15000 euro.
        // The family pays 10% tax if the combined salaries are 15000 euro through 19,999.99 euro, or
        // 20% tax if the combined salaries are from 20000 euro through 29,999.99.
        //Otherwise the family pays 30% tax.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi there family man. Do you have one or more working adults in your household? (enter 1 for one, 2 for two, etc). ");
        int workingAdults = scanner.nextInt(); //determining how many adults are bringing money to house
        double[] adultSalaries = new double[workingAdults]; //array of salaries of all of the adults
        for (int num = 0; num<workingAdults; num++){
            System.out.println("What is the income of adult " + (num+1) + "? "); //+1 to make it start at 1 not 0
            adultSalaries[num] = scanner.nextDouble();
        }
        double totalSalary = 0; //initialised at 0
        for(int i = 0; i < workingAdults; i++){
            totalSalary = totalSalary + adultSalaries[i]; //adding together the total income to household
        }
        if(totalSalary<15000){ //if the total income is less than 15000, we tell them no tax
            System.out.println("Your family pays no tax, as the total salary is under 15,000 euros.");
            mainMenu();
        }else if(15000<totalSalary && totalSalary<=19999.99){
            System.out.println("Your family pays 10% tax. You will pay " + taxCalc(totalSalary, .1) + " euros in taxes.");
            mainMenu();
        }else if(20000<totalSalary && totalSalary<=29999.99){
            System.out.println("Your family pays 20% tax. You will pay " + taxCalc(totalSalary, .2) + " euros in taxes.");
            mainMenu();
        }else{
            System.out.println("Your family pays 30% tax. You will pay " + taxCalc(totalSalary, .3) + " euros in taxes.");
            mainMenu();
        }

    }
    public static double taxCalc(double salary, double taxLevel){
        double taxAmount = salary * taxLevel; //just salary * tax level (so 15001 * .1 for 10% tax)
        return taxAmount;
    }
    public static void problemSix(){
        //Write a program with a main method that calls a method called GreaterThan, it takes
        // two integer arguments and returns an integer. It should return the largest.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi there. Let's have a fun integer fight. Can you give me two integers?");
        System.out.println("Integer 1:"); //I made it a game for fun
        int one = scanner.nextInt();
        System.out.println("Integer 2:");
        int two = scanner.nextInt();
        int winner = greaterThan(one, two);
        System.out.println("*blast noises* Woah there. It looks like the winner is " + winner);
        mainMenu();
    }
    public static int greaterThan(int one, int two){ //just seeing which number is greater with logic if statements
        if(one<two){
            return two;
        }else{
            return one;
        }
    }
    public static void problemSeven(){
        //Write a program that asks the user for two initials. Print a message telling the user
        // whether the first initial falls alphabetically before the second (you should ignore case).
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello there, this is the final problem. Will you help me by giving me two initials?");
        System.out.println("Initial 1: ");
        char responseA = charCutter(scanner.next()); //cuts response into char in upper case
        System.out.println("Initial 2: ");
        char responseB = charCutter(scanner.next());
        if((responseA-0)>(responseB-0)){ //logic / if statemments to determine which ASCII is bigger
            System.out.println(responseB + " is before " + responseA + " in the alphabet.");
        } else if ((responseB-0)>(responseA-0)) {
            System.out.println(responseA + " is before " + responseB + " in the alphabet.");
        }else{
            System.out.println("The initials are the same!"); //just in case A and a or the same were entered
        }
        mainMenu();
    }
    public static char charCutter(String response){ //the cut process
        String temp = response.toUpperCase(); //first, all upper case
        char cut = temp.charAt(0); //shortened to just first character
        if(cut < 64 || cut > 90){ //ensures its within upper case letter ascii
            System.out.println("Oops! One of your initials is not a letter. Please try again. ");
            mainMenu(); //return to main menu if not to prevent false answers / breaking
        }
        return cut; //returns upper case letter
    }
}