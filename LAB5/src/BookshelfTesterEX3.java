import java.util.ArrayList;


public class BookshelfTesterEX3{

    private static final int HEIGHT = 7;
    private static final int HEIGHT1 = 30;
    private static final int POSITION = 2;
    //create a constructor for ArrayList
    static ArrayList<Integer> book1 = new ArrayList<>();
    //add numbers into constructor "arraylist"
    static {
        book1.add(20);
        book1.add(5);
        book1.add(11);
        book1.add(9);
        book1.add(18);
    }
    static ArrayList<Integer> book2 = new ArrayList<>();
    //add numbers into constructor "arraylist"

    static ArrayList<Integer> book3 = new ArrayList<>();
    //add numbers into constructor "arraylist"
    static {
        book3.add(2);
        book3.add(5);
        book3.add(6);
        book3.add(9);
        book3.add(18);
    }


    public static void main(String[] args) {
        testBookShelfEmpty();
        testSecondConstructor();

        //do not forget to write parameter
        addFront(HEIGHT);
        addLast(HEIGHT1);
        removeFront();
        removeLast();
        getHeight(POSITION);
        arraysize();
        isSorted();
    }

    //check if the bookshelf is empty
    public static void testBookShelfEmpty() {
        Bookshelf bookshelf = new Bookshelf();
        System.out.println("FirstConstructor Output: " + bookshelf.size());
        System.out.println("FirstConstructor Expected: 0\n");
    }

    //check the book in the correct order
    public static void testSecondConstructor() {
        // create a object
        Bookshelf bookshelf1 = new Bookshelf(book1);
        System.out.println("SecondConstructor Output: " + bookshelf1.toString());
        //System.out.println(bookshelf1) means printiut reference
        System.out.println("Expected: Expected: [20, 5, 11, 9, 18]\n");

        Bookshelf bookshelf2 = new Bookshelf(book2);
        System.out.println("SecondConstructor Output: " + bookshelf2.toString());
        System.out.println("Expected: Expected: []\n");

    }
    //check addFront method, unnecessary write a type here
    public static void addFront(int HEIGHT) {
        //check addFront method
        Bookshelf bookshelfFront = new Bookshelf(book1);
        bookshelfFront.addFront(HEIGHT);
        System.out.println("After adding it into the first position, the First element is: " + bookshelfFront.toString());
        System.out.println("Expected: [7, 20, 5, 11, 9, 18]\n");
    }
    //check addLast method
    public static void addLast(int HEIGHT1) {
        //check addFront method
        Bookshelf bookshelfLast = new Bookshelf(book1);
        bookshelfLast.addLast(HEIGHT1);
        System.out.println("After adding it into the last position, the array is: " + bookshelfLast.toString());
        System.out.println("Expected:[20, 5, 11, 9, 18, 7, 30]\n" + HEIGHT1 + "\n");
    }

    //check removeFront method
    public static void removeFront() {

        Bookshelf bookshelfRemove = new Bookshelf(book1);
        bookshelfRemove.removeFront();
        System.out.println("After removing the first element, the array is: " +  bookshelfRemove.toString());
        System.out.println("Expected: [5, 11, 9, 18]\n");
    }

    //check removeLast method
    public static void removeLast() {

        Bookshelf remoevelast = new Bookshelf(book1);
        remoevelast.removeLast();
        System.out.println("After removing the last element, the array is: " +  remoevelast.toString());
        System.out.println("Expected:[20, 5, 11, 9]\n");
    }

    //check getHeight method
    public static void getHeight(int POSITION) {
        Bookshelf getheight = new Bookshelf(book1);
        System.out.println("The element at given position is: " +  getheight.getHeight(POSITION));
        System.out.println("Expected element is: 11 \n");

    }

    //check array size
    public static void arraysize() {
        Bookshelf arraysize = new Bookshelf(book1);
        System.out.println("The size is: " +  arraysize.size());
        System.out.println("Expected size is: 5\n");

    }

    //check if array is sorted
    public static void isSorted(){
        Bookshelf sort = new Bookshelf(book3);
        System.out.println("String output is: " + sort.toString());
        System.out.println("Expected: [2, 5, 6, 9, 18]");
        System.out.println("Output is: " + sort.isSorted() + "\n");

    }

}
