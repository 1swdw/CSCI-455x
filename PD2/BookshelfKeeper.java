// Name: wu yadong
// USC NetID: 3795420637
// CSCI455 PA2
// Spring 2025

import java.util.ArrayList;

/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
 * non-decreasing order by height, with the restriction that single books can only be added
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

    /**
     Representation invariant:
     * 1. bookshelf order must always be in **non-decreasing order.
     * 2.  book heights must be positive.
     * 3. sortedBookshelf must be a valid Bookshelf instance and match bookContainer.
     * 4. count1, count2, lastOperationCount1, and lastOperationCount2 must always be non-negative.
     * 5. lastOperation must be either "pick", "put", or an empty string "" (never null).
     * 6. The number of books in bookContainer should always match sortedBookshelf.getNumBooks().
     */

    // <add instance variables here>
    private int count1;
    private int count2;
    private int lastOperationCount1;
    private int lastOperationCount2;
    private String lastOperation;
    private ArrayList<Integer> bookContainer;
    private Bookshelf sortedBookshelf;



    /**
     * Creates a BookShelfKeeper object with an empty bookshelf
     */
    public BookshelfKeeper() {
        count1 = 0;
        count2 = 0;
        lastOperation = " ";

        bookContainer = new ArrayList<Integer>();

    }

    /**
     * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
     * Note: method does not make a defensive copy of the bookshelf.
     *
     * PRE: sortedBookshelf.isSorted() is true.
     */
    public BookshelfKeeper(Bookshelf sortedBookshelf) {
        this.sortedBookshelf = sortedBookshelf;
        bookContainer = new ArrayList<Integer>(sortedBookshelf.getBooks());
        lastOperation = "";
    }

    /**
     * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted
     * after picking up the book.
     *
     * Returns the number of calls to mutators on the contained bookshelf used to complete this
     * operation. This must be the minimum number to complete the operation.
     *
     * PRE: 0 <= position < getNumBooks()
     */
    public int pickPos(int position) {
        ArrayList<Integer> temp0 = new ArrayList<>();
        int count3 = 0;

        //consider the case when the position is either in the beginning and end
        if (position == 0){
            sortedBookshelf.removeFront();
            count1++;
            count3++;
        }
        else if (position == bookContainer.size()-1) {
            sortedBookshelf.removeLast();
            count1++;
            count3++;
        }
        int midIndex;
        if (bookContainer.size() % 2 == 0) {
            midIndex = bookContainer.size()/2 - 1;
        }else {
            midIndex = bookContainer.size() / 2;
        }


        //consider the case when the position is neither in the beginning and end
        // else if ( position <= midIndex) {



        if ( position <= midIndex) {
            for (int i = 0; i <= position; i++){
                temp0.add(bookContainer.get(i));
                sortedBookshelf.removeFront();
                count1++;
                count3++;
            }

            // consider the case when temp size is not one, use the for-loop make
            // those elements before position back to bookshelf
            for (int j = temp0.size() - 2; j >= 0; j--){
                sortedBookshelf.addFront(temp0.get(j));
                count1++;
                count3++;
            }
        }else {
            for (int l = bookContainer.size() - 1; l >= position; l--){
                temp0.add(bookContainer.get(l));
                sortedBookshelf.removeLast();
                count1++;
                count3++;
            }

            // consider the case when temp size is not one, use the for-loop make
            // those elements after position back to bookshelf
            //if(temp0.size() != 1){
            for (int m = temp0.size() - 2; m >= 0; m--){
                sortedBookshelf.addLast(temp0.get(m));
                count1++;
                count3++;
            }
        }

        // Since the bookContainer is copy of "sortedBookshelf.getBooks()," update bookContainer to reflect the changes
        bookContainer = new ArrayList<>(sortedBookshelf.getBooks());
        lastOperationCount1 = count3;
        lastOperation = "pick";
        return count3;
    }

    /**
     * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted
     * after the insertion.
     *
     * Returns the number of calls to mutators on the contained bookshelf used to complete this
     * operation. This must be the minimum number to complete the operation.
     *
     * PRE: height > 0
     */
    public int putHeight(int height) {
        int count4 = 0;
        //find the position for the given height
        int a = 0;
        while( a < sortedBookshelf.size() && height > sortedBookshelf.getHeight(a)){
            a++;
        }

        // make an initialization
        ArrayList<Integer> temp = new ArrayList<>();

        //do remove and add operations, and count their total number from left
        //when the size of bookContainer is empty or the size is less equal than 2
        if (bookContainer.isEmpty()){
            sortedBookshelf.addFront(height);
            count2++;
            count4++;
        }
        else if ((bookContainer.get(0) > height)) {
            sortedBookshelf.addFront(height);
            count2++;
            count4++;
        }
        else if ((bookContainer.get(bookContainer.size() -1) < height)) {
            sortedBookshelf.addLast(height);
            count2++;
            count4++;
        }

        //when the size of bookContainer is not 0, and the height will not be inserted into at beginning or end
        else if ( a <= bookContainer.size()/2 ) {


            for (int b = 0; b < a; b++){
                temp.add(bookContainer.get(b));
                sortedBookshelf.removeFront();
                count2++;
                count4++;
            }
            sortedBookshelf.addFront(height);
            count2++;
            count4++;
            for (int c = temp.size() - 1; c >= 0; c--){
                sortedBookshelf.addFront(temp.get(c));
                count2++;
                count4++;
            }
        }
        //do remove and add operations, and count their total number from right
        else {
            for (int d = bookContainer.size() - 1; d >= a; d--){ // d > a - 1 might out of bound
                temp.add(bookContainer.get(d));
                sortedBookshelf.removeLast();
                count2++;
                count4++;
            }
            sortedBookshelf.addLast(height);
            count2++;
            count4++;
            for (int e = temp.size() - 1; e >= 0; e--){
                sortedBookshelf.addLast(temp.get(e));
                count2++;
                count4++;
            }
        }

        // Since the bookContainer is copy of "sortedBookshelf.getBooks()," update bookContainer to reflect the changes
        bookContainer = new ArrayList<>(sortedBookshelf.getBooks());
        lastOperationCount2 = count4;
        lastOperation = "put";
        return count4;

        // dummy code to get stub to compile
    }

    /**
     * Returns the total number of calls made to mutators on the contained bookshelf
     * so far, i.e., all the ones done to perform all of the pick and put operations
     * that have been requested up to now.
     */
    public int getTotalOperations() {


        return count1 + count2;   // dummy code to get stub to compile
    }

    /**
     * Returns the number of books on the contained bookshelf.
     */
    public int getNumBooks() {
        int totalNumberBooks;
        totalNumberBooks = bookContainer.size();
        return totalNumberBooks;   // dummy code to get stub to compile
    }

    /**
     * Returns string representation of this BookshelfKeeper. Returns a String containing height
     * of all books present in the bookshelf in the order they are on the bookshelf, followed
     * by the number of bookshelf mutator calls made to perform the last pick or put operation,
     * followed by the total number of such calls made since we created this BookshelfKeeper.
     *
     * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
     *
     */
    public String toString() {
        return sortedBookshelf.toString() + " " +
                (lastOperation.equals("pick") ? lastOperationCount1 : lastOperationCount2) + " " +
                getTotalOperations();
    }

    /**
     * Returns true iff the BookshelfKeeper data is in a valid state.
     * (See representation invariant comment for details.)
     */
    private boolean isValidBookshelfKeeper() {
        if (bookContainer == null) {
            return false;
        }

        // All book heights must be positive
        for (int height : bookContainer) {
            if (height <= 0) {
                return false;
            }
        }

        // Books must be in non-decreasing order
        for (int i = 1; i < bookContainer.size(); i++) {
            if (bookContainer.get(i) < bookContainer.get(i - 1)) {
                return false; // Books must be in non-decreasing order
            }
        }

        return true;
    }

    // add any other private methods here
}
