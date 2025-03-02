// Name: wu yadong
// USC NetID: 3795420637
// CSCI455 PA2
// Spring 2025

import java.util.ArrayList;
import java.util.Scanner;

public class BookshelfKeeperProg {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter initial arrangement of books followed by newline:");

        //remove spaces at the beginning  and at the end
        //also remove all white spaces if the user only
        // type them, and return an empty array
        String input = scanner.nextLine().trim();
        ArrayList<Integer> initialBooks = new ArrayList<>();

        if (!input.isEmpty()){
            // split the string into integer substring
            //also remove spaces between element and last element
            String[] str = input.split("\\s+");
            //convert the integer strings into single number, and add them into an arraylist
            for (int i = 0; i < str.length; i++){
                if (isValidInteger(str[i])){
                    initialBooks.add(Integer.parseInt(str[i]));
                }else {
                    System.out.println("ERROR: Height of a book must be positive.");
                    System.out.println("Exiting Program.");
                    // Exit the program
                    scanner.close();
                    return;
                }
            }
        }

        // check if the initial line is no-descending
        Bookshelf tempLine = new Bookshelf(initialBooks);
        if (!tempLine.isSorted()) {
            System.out.println("ERROR: Heights must be specified in non-decreasing order.");
            System.out.println("Exiting Program.");
            scanner.close();
            // Program exits, so nothing else runs
            return;
        }

        // check if each number in the initial line is positive
        else if (tempLine.isSorted()) {
            for (int i = 0; i < initialBooks.size(); i++){
                if (initialBooks.get(i) < 0){
                    System.out.println("ERROR: Height of a book must be positive.");
                    System.out.println("Exiting Program.");
                    scanner.close();
                    // Program exits, so nothing else runs
                    return;
                }
            }
        }

        //the reason why re-create the object is that it will be used in bookShelfKeeper again
        Bookshelf bookshelf = new Bookshelf(initialBooks);

        //pass the object of bookshelf through BookshelfKeeper
        //the object will be used in the class BookshelfKeeper
        BookshelfKeeper keeper = new BookshelfKeeper(bookshelf);
        System.out.println(keeper.toString());

        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");

        //while loop can make the command keep to show up until
        //the user type end
        while(true) {


            String operation = scanner.nextLine().trim();

            // finish the program directly if the user input "end"
            if (operation.equals("end")) {
                System.out.println("Exiting Program.");
                break;
            }

            //break the whole string the user input into single substring
            String [] substring = operation.split("\\s+");
            if (substring.length != 2) {
                System.out.println("ERROR: input consisted of two single substrings. The first must be a a word, and the second one must be a number");
                System.out.println("Exiting Program.");
                scanner.close();
                // Program exits, so nothing else runs
                return;
            } else if (!(substring[0].equals("pick") || substring[0].equals("put"))) {
                System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                System.out.println("Exiting Program.");
                scanner.close();
                // Program exits, so nothing else runs
                return;
            } else if (substring[0].equals("pick") && (Integer.parseInt(substring[1]) < 0 || Integer.parseInt(substring[1]) >= keeper.getNumBooks())) {
                System.out.println("ERROR:  Entered pick operation is invalid on this shelf.");
                System.out.println("Exiting Program.");
                scanner.close();
                // Program exits, so nothing else runs
                return;
            } else if (substring[0].equals("put") && Integer.parseInt(substring[1])<=0){
                System.out.println("ERROR: Height of a book must be positive.");
                System.out.println("Exiting Program.");
                scanner.close();
                // Program exits, so nothing else runs
                return;
            }

            //print out the line and the number of operations after complete picking and putting
            if (substring[0].equals("pick")){
                int val1 =  keeper.pickPos(Integer.parseInt(substring[1]));
                //System.out.println(keeper.toString() + " " + val1 + " " +  keeper.getTotalOperations());
                System.out.println(keeper.toString());
            }else {
                int val2 = keeper.putHeight(Integer.parseInt(substring[1]));
                //System.out.println(keeper.toString() + " " + val2 + " " +keeper.getTotalOperations());
                System.out.println(keeper.toString());
            }
        }
    }

    private static boolean isValidInteger(String str){
        for (int i = 0; i < str.length(); i++) {

            // Get character at index i
            char c = str.charAt(i); // Get character at index i
            if (!Character.isDigit(c)) {
                // Invalid number, return false
                return false;
            }
        }
        return true;
    }
}
