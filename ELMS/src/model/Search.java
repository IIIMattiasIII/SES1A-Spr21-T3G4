package model;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Search {
    ObservableList<Book> books = FXCollections.observableArrayList(); // Book class

    //constructors
    // This was brought back in because we need a way for the search class to have access to the list of books
    // The value of inputBooks will == ELMS.books
    // The ELMS class will need an attribute of class Search
    // So the class will have a link/reference to the list of books
    public Search(ObservableList<Book> inputBooks) {
        books = inputBooks;

    }
    
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
     public ObservableList<Book> byName(String title) {
        // loop over database (list of books) // return books that match
       ObservableList<Book> matches = FXCollections.observableArrayList(); // please check
        
        for (Book bookMatch : books) {
            if (bookMatch.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches.add(bookMatch);
               
            }
        }
        return matches;
    }
    //public ObservableList<Book> byName(String title) {
    
    public ObservableList<String> getAvailablebooks(){
    ObservableList<String> sendAvailable = FXCollections.observableArrayList();
    for(Book availableBook: books){
    sendAvailable.add(availableBook.getTitle());
    }
    return sendAvailable;
    }
    
    public ObservableList<String> byCategory(String category) {
//         IDs contain one letter (J,B,A,P) followed by 4 digit number / eg. J0001, B1234
//         J-journal, B-book, A-article, P-research paper
        ObservableList<String> matches = FXCollections.observableArrayList(); // please check!
        
        for (Book bookMatch : books) {
            if (bookMatch.getGenre().toLowerCase().contains(category.toLowerCase())) {
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
    public ObservableList<Book> byauthor(String author) {
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
            if (bookMatch.getID().toLowerCase().contains(category.toLowerCase())) {
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
            aGenre = book.getGenre();
            for (String genre : genreList) {
                if (aGenre.equals(genre)) {
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

    public ObservableList<String> matchAuthor (String searchedAuthor, ObservableList<String> authorList) {
        // method to return all authors that match author typed in
        ObservableList<String> authorMatch = FXCollections.observableArrayList();
        
        for (String author : authorList) {
            if (author.toLowerCase().contains(searchedAuthor.toLowerCase())) { 
                authorMatch.add(author);
            }
        }
        return authorMatch;
    }
    
    public ObservableList<String> matchGenre (String searchedGenre, ObservableList<String> genreList) {
        // method to return all genres that match genre typed in
        ObservableList<String> genreMatch = FXCollections.observableArrayList();
        
        for (String genre : genreList) {
            if (genre.toLowerCase().contains(searchedGenre.toLowerCase())) { 
                genreMatch.add(genre);
            }
        }
        return genreMatch;
    }
}

