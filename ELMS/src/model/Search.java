/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

// import here
import java.util.*;

/**
 *
 * @author chantelmills
 */
public class Search {
    
    // attributes
    private String searchInput;
    LinkedList<Book> books = new LinkedList<>(); // Book class
    // books meant to be an --> observableArrayList
    
    public Search(String searchInput) {
        this.searchInput = searchInput; //input of search from user
    }
    
    // userInput for search
    public String userInput() {
        Scanner sc = new Scanner(System.in);
        searchInput = sc.nextLine();
        return searchInput;
    }
    
    
    public LinkedList<String> findBook() { // The type isn't correct I think
        // loop over database (list of books) // return books that match
        LinkedList<String> matches = new LinkedList<>();
        
        for (String bookMatch : books) {
            if (bookMatch.contains(userInput())) {
                matches.add(bookMatch);
            }
        }
        return matches;
    }
    
    // shows whole list of books available when the "search" menu is open
    public void showAllBooks() {
        // show all books on opening
        for (Book book : books) {
            System.out.println(book);
        }
    }
    
    // show all books searched for
    public void showSearchedBook() {
        for (String book : findBook()) {
            System.out.println(book);
        }
    }
    
//    public void/char identifierBooks() {
        // switch statement?
        // IDs contain one letter (J,B,A,P) followed by 4 digit number
        // J-journal, B-book, A-article, P-research paper
        // if (/**/.contains('J') { display all ID's with 'J' }
//    }
    
    

}

