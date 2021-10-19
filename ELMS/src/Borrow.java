/*
 * This class contains the record of each individually borrowed book
 * 
 */

package model;

//// imports  
import java.time.LocalDateTime;

/**
 *
 * @author chantelmills
 */
public class Borrow {
    
    ////attributes
    Book book;
    Account account;
    LocalDateTime dueDate;
    LocalDateTime borrowedDate;
    
    
    ////constructors
    public Borrow (Book book, Account account) {
        this.book = book;
        this.account = account;
        borrowedDate = LocalDateTime.now();
        dueDate = borrowedDate.plusDays(-1); // assume 2 week borrow times
    }
    
    ////methods
    
    public Book getBook() {
        return book;
    }
            
    public Account getAccount() {
        return account;
    }
    
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    
    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }
    
}
