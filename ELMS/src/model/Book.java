package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final StringProperty genre = new SimpleStringProperty();
    private final IntegerProperty stock = new SimpleIntegerProperty();
    //private final IntegerProperty returnDate = new SimpleIntegerProperty();
    
    public Book(String ID, String title, String author, String genre, int stock) {
        this.id.set(ID);
        this.title.set(title);
        this.author.set(author);
        this.genre.set(genre);
        this.stock.set(stock);
        //this.returnDate.set(returnDate);
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
    
    public String getGenre() { return this.genre.get(); }
    public void setGenre(String genre) { this.genre.set(genre); }
    public StringProperty genreProperty() { return this.genre; }
    
    public int getStock() { return this.stock.get(); }
    public void setStock(String stock) { this.title.set(stock); }
    public IntegerProperty stockProperty() { return this.stock; }
    
    /*public int getreturnDate() { return this.returnDate.get(); }
    public void serReturnDate(int returnDate) { this.returnDate.set(returnDate); }
    public IntegerProperty returnDateProperty() { return this.returnDate; }*/
    
    public void addStock(int amount) {
        stock.set(stock.get() + amount);
    }
    
    public void reduceStock(int amount) {
        stock.set(stock.get() - amount);
    }
}
