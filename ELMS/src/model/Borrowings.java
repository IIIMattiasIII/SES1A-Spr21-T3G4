/*
 * This class contains the list of borrowed books 
 * 
 */
package model;

//imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author chantelmills
 */
public class Borrowings {
    
    ////attributes
    public ObservableList<Borrow> borrowedBooks = FXCollections.observableArrayList(); // list that stores all the books the user has borrowed
    static final int MAX = 3;
    
    ////constructors
    public Borrowings() {}
    
    ////methods
    // method for borrowing
    // if stock == 0 to begin with, then book can't be borrowed
    // if the user has already reached the max, then the book can't be borrowed
    public String borrowBook(Book book, Account account) {
        
        // check if stock available
        // check if MAX has been reached by user
        // if stock = true && booksBorrowed < MAX then add book to 'borrowedBooks'
        
        if (book.getStock() == 0) {
            return "No stock available for this book";
        }
        
        if (account.getBorrowCount() >= MAX) {
            return "You have already reached you maximum borrowed books";
        }
        
        account.borrowedBook();
        book.reduceStock(1);
        borrowedBooks.add(new Borrow(book, account));
        return "Borrow complete";
    }
    
}