package model;

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
    ObservableList<Book> books = FXCollections.observableArrayList(); // Book class
    
    // Constructors
    //ObservableList<Book> inputBooks
    public Search() {
         books.add(new Book("software", "113212", "Mohammad Karim")); //will come up with a better solution for constructor later, meantime this is just for testing -Mohammad Karim
         books.add(new Book("Study", "11212", "difficult"));
    }
    
    // Methods
    // userInput for search - not needed since data from controller/view
//    public String userInput() {
//        Scanner sc = new Scanner(System.in);
//        searchInput = sc.nextLine(); // need lines 30-31 to be changed to the text box from controller
//        return searchInput;
//    }
    
    //ObservableList<Book>
    public ObservableList<String> byTitle(String title) {
        // loop over database (list of books) // return books that match
       ObservableList<String> matches = FXCollections.observableArrayList(); // please check
        
        for (Book bookMatch : books) {
            if (bookMatch.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches.add(bookMatch.getTitle());
               
            }
        }
        return matches;
    }
    public ObservableList<String> getAvailablebooks(){
    ObservableList<String> sendAvailable = FXCollections.observableArrayList();
    for(Book availableBook: books){
    sendAvailable.add(availableBook.getTitle());
    }
    return sendAvailable;
    }
//    // shows whole list of books available when the "search" menu is open
//    public void showAllBooks() {
//        // show all books on opening
//        for (Book book : books) {
//            System.out.println(book);
//        }
//    }
    
    public ObservableList<String> byCategory(String category) {
//         IDs contain one letter (J,B,A,P) followed by 4 digit number / eg. J0001, B1234
//         J-journal, B-book, A-article, P-research paper
        ObservableList<String> matches = FXCollections.observableArrayList(); // please check!
        
        for (Book bookMatch : books) {
            if (bookMatch.getIsbn().toLowerCase().contains(category.toLowerCase())) {
                matches.add(bookMatch.getTitle());
            }
        }
        return matches;
    }
    
    public ObservableList<String> byAuthor(String author) {
        // loop over database (list of books) // return books that match
        ObservableList<String> matches = FXCollections.observableArrayList(); // please check!
        
        for (Book bookMatch : books) {
            if (bookMatch.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                matches.add(bookMatch.getTitle());
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

    public ObservableList<String> allAuthors(){
        // method to display all available authors in the database
        ObservableList<String> authorList = FXCollections.observableArrayList();
        String anAuthor;
        boolean notFound = true;
        
        for (Book book : books) {
            anAuthor = book.getAuthor();
            for (String author : authorList) {
                if (anAuthor.equals(author)) {
                    notFound = false;
                    break;
                }
            }
            if (notFound) {
                authorList.add(anAuthor);
            }
        }
        
        return authorList;
    }
    
    
    public ObservableList<String> allGenres() {
        // method to display all available genres/categories in the database
        ObservableList<String> genreList = FXCollections.observableArrayList();
        String aGenre;
        boolean notFound = true;
        
        for (Book book : books) {
            aGenre = book.getAuthor();
            for (String author : genreList) {
                if (aGenre.equals(author)) {
                    notFound = false;
                    break;
                }
            }
            if (notFound) {
                genreList.add(aGenre);
            }
        }
        
        return genreList;
    }
    
    // show all books searched for
//    public void showSearchedBook() {
//        for (Book book : byTitle()) {
//            System.out.println(book);
//        }
//    }
    
}

