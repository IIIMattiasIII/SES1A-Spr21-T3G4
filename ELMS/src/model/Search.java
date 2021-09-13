/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
 *
 * @author ALI TAHMID KARIM
 */
public class Search {


/**
 *
 * @author chantelmills
 */

    
    // attributes
    ObservableList<Book> books; // Book class
    
    // Constructors
    public Search(ObservableList<Book> inputBooks) {
        books = inputBooks;
    }
    
    // Methods
    // userInput for search - not needed since data from controller/view
//    public String userInput() {
//        Scanner sc = new Scanner(System.in);
//        searchInput = sc.nextLine(); // need lines 30-31 to be changed to the text box from controller
//        return searchInput;
//    }
    
    
    public ObservableList<Book> byTitle(String title) {
        // loop over database (list of books) // return books that match
        ObservableList<Book> matches = FXCollections.observableArrayList(); // please check
        
        for (Book bookMatch : books) {
            if (bookMatch.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches.add(bookMatch);
            }
        }
        return matches;
    }
    
//    // shows whole list of books available when the "search" menu is open
//    public void showAllBooks() {
//        // show all books on opening
//        for (Book book : books) {
//            System.out.println(book);
//        }
//    }
    
    public ObservableList<Book> byCategory(String category) {
//         IDs contain one letter (J,B,A,P) followed by 4 digit number / eg. J0001, B1234
//         J-journal, B-book, A-article, P-research paper
        ObservableList<Book> matches = FXCollections.observableArrayList(); // please check!
        
        for (Book bookMatch : books) {
            if (bookMatch.getIsbn().toLowerCase().contains(category.toLowerCase())) {
                matches.add(bookMatch);
            }
        }
        return matches;
    }
    
    public ObservableList<Book> byAuthor(String author) {
        // loop over database (list of books) // return books that match
        ObservableList<Book> matches = FXCollections.observableArrayList(); // please check!
        
        for (Book bookMatch : books) {
            if (bookMatch.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                matches.add(bookMatch);
            }
        }
        return matches;
    }
    
    public ObservableList<Book> byAll(String category, String input) {
        // loop over database (list of books) // return books that match
        ObservableList<Book> matches = FXCollections.observableArrayList(); // please check!
        
        for (Book bookMatch : books) {
            if (bookMatch.getIsbn().toLowerCase().contains(category.toLowerCase())) {
                if (bookMatch.getAuthor().toLowerCase().contains(input.toLowerCase())) {
                    matches.add(bookMatch);
                }
                else {
                    if (bookMatch.getTitle().toLowerCase().contains(input.toLowerCase())) {
                        matches.add(bookMatch);
                    }
                }
            }
        }
        return matches;
    }
    
    // show all books searched for
//    public void showSearchedBook() {
//        for (Book book : byTitle()) {
//            System.out.println(book);
//        }
//    }
    
}

