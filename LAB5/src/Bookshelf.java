// Name: wu yadong
// USC NetID: 3795420637
// CSCI455 PA2
// Spring 2025


/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.toutch
 */

import java.util.ArrayList;

public class Bookshelf {

    /**
     Representation invariant:
     * - The list `books` must never be null.
     * - All book heights must be positive (greater than 0).
     */

    private ArrayList<Integer> books;


    /**
     * Creates an empty Bookshelf object i.e. with no books
     */
    public Bookshelf() {
        books = new ArrayList<>();
        assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)
    }

    /**
     * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
     * values: [20, 1, 9].
     *
     * PRE: pileOfBooks contains an array list of 0 or more positive numbers
     * representing the height of each book.
     */
    public Bookshelf(ArrayList<Integer> pileOfBooks) {
        this.books = new ArrayList<>(pileOfBooks);
        assert isValidBookshelf();
    }

    /**
     * Inserts book with specified height at the start of the Bookshelf, i.e., it
     * will end up at position 0.
     *
     * PRE: height > 0 (height of book is always positive)
     */
    public void addFront(int height) {
        assert isValidBookshelf();
        books.add(0, height);

    }

    /**
     * Inserts book with specified height at the end of the Bookshelf.
     *
     * PRE: height > 0 (height of book is always positive)
     */
    public void addLast(int height) {
        assert height > 0;
        books.add(height);
    }

    /**
     * Removes book at the start of the Bookshelf and returns the height of the
     * removed book.
     *
     * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
     */
    public int removeFront() {
        assert size()>0;
        int removeBook = books.remove(0);
        return removeBook;
    }

    /**
     * Removes book at the end of the Bookshelf and returns the height of the
     * removed book.
     *
     * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
     */
    public int removeLast() {
        assert size()>0;
        int removedBook = books.remove(books.size()-1);
        return removedBook;
    }

    /*
     * Gets the height of the book at the given position.
     *
     * PRE: 0 <= position < this.size()
     */
    public int getHeight(int position){



        return books.get(position);

    }

    /**
     * Returns number of books on the this Bookshelf.
     */
    public int size() {
        return books.size();   // dummy code to get stub to compile

    }

    /**
     * Returns string representation of this Bookshelf. Returns a string with the height of all
     * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
     * by example here:  “[7, 33, 5, 4, 3]”
     */
    public String toString() {

        return books.toString();   // dummy code to get stub to compile

    }

    /**
     * Returns true iff the books on this Bookshelf are in non-decreasing order.
     * (Note: this is an accessor; it does not change the bookshelf.)
     */
    public boolean isSorted() {

        for (int i = 0; i < books.size() - 1; i++) {
            if (books.get(i) > books.get(i + 1)) {
                // Found an out-of-order element, so it's not sorted
                return false;
            }
        }
        // If no issues found, it's sorted
        return true;
    }

    /**
     * Returns true iff the Bookshelf data is in a valid state.
     * (See representation invariant comment for more details.)
     */
    private boolean isValidBookshelf() {
        if (books == null) {
            return false;
        }

        for (int height : books) {
            if (height <= 0) {
                return false; // All book heights must be positive
            }
        }

        return true;

    }

}