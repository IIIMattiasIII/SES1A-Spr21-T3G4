package model;

//// import
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author chantelmills
 */
public class Borrow {
    
    ////attributes
    ObservableList<Book> books;
    public ObservableList<Book> borrowedBooks = FXCollections.observableArrayList(); // list that stores all the books the user has borrowed
    int max;
    
    ////constructors
    public Borrow (/* books + users (?) */) {
        max = 3;
        // TBC
    }
    
    ////methods
    
    // method for borrowing
    // if stock == 0 to begin with, then book can't be borrowed
    // if the user has already reached the max, then the book can't be borrowed
    public Book borrowBook() {
        if (borrowedBooks.size() < max) {   
            if (/*book selected*/.getStock() != 0) { // will be fixed up
                borrowedBooks.add(/*book selected*/);
                stockChange();
            }
        }
    }
    
    // method to change stock available
    private int stockChange() {
        return getStock()--;
    }
    
    
}
