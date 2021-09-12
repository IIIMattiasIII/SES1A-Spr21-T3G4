package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty title = new SimpleStringProperty();
    private String isbn; // copied from 'Bookshell' branch
    private String author; // copied from 'Bookshell' branch
    // TBD: Add other attributes
    
    public Book(String title, String id, String author) {
        this.title.set(title);
        this.isbn = id; // copied from 'Bookshell' branch
        this.author = author; // copied from 'Bookshell' branch
        // TBD: Add other attributes in constructor
    }
    
    public String getTitle() { return this.title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return this.title; }
    
    
    public String getIsbn() { // method copied from 'Bookshell' branch
        return isbn;
    }
    public void setIsbn(String isbn) { // method copied from 'Bookshell' branch
        this.isbn = isbn;
    }
    
    public String getAuthor() { // method copied from 'Bookshell' branch
        return isbn;
    }
    public void setAuthor(String isbn) { // method copied from 'Bookshell' branch
        this.isbn = isbn;
    }
    
    // TBD: Add other getters and setters for other attribute properties
}
