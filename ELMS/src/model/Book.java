package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final IntegerProperty year = new SimpleIntegerProperty();
    private final StringProperty genre = new SimpleStringProperty();
    private final IntegerProperty stock = new SimpleIntegerProperty();
    
    public Book(String ID, String title, String author, int year, String genre, int stock) {
        this.id.set(ID);
        this.title.set(title);
        this.author.set(author);
        this.year.set(year);
        this.genre.set(genre);
        this.stock.set(stock);
        
    }
    
    // Constructor variant used for staff book request
    public Book(String title, String author, int year) {
        this.id.set(null);
        this.title.set(title);
        this.author.set(author);
        this.year.set(year);
        this.genre.set(null);
        this.stock.set(0);
        
    }
    
    public String getID() { return this.id.get(); }
    public void setID(String id) { this.id.set(id); }
    public StringProperty idProperty() { return this.id; }
    
    public String getTitle() { return this.title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return this.title; }
    
    public String getAuthor() { return this.author.get(); }
    public void setAuthor(String author) { this.author.set(author); }
    public StringProperty authorProperty() { return this.author; }
    
    public int getYear() { return this.year.get(); }
    public void setYear(int year) { this.year.set(year); }
    public IntegerProperty yearProperty() { return this.year; }
    
    public String getGenre() { return this.genre.get(); }
    public void setGenre(String genre) { this.genre.set(genre); }
    public StringProperty genreProperty() { return this.genre; }
    
    public int getStock() { return this.stock.get(); }
    public void setStock(int stock) { this.stock.set(stock); }
    public IntegerProperty stockProperty() { return this.stock; }
    

}
