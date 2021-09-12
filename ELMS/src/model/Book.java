package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Book {
    public final StringProperty title = new SimpleStringProperty();
    public final StringProperty author = new SimpleStringProperty();
    public final IntegerProperty stock = new SimpleIntegerProperty();
    public final IntegerProperty ID = new SimpleIntegerProperty();
    
    public Book(String title, String author, int stock, int ID) {
        this.title.set(title);
        this.author.set(author);
        this.stock.set(stock);
        this.ID.set(ID);
        // TBD: Add other attributes in constructor
    }
    
    public String getTitle() { return this.title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return this.title; }
    
    public String getAuthor() {return this.author.get(); }
    public void setAuthor(String author) {this.author.set(author); }
    public StringProperty authorProperty() { return this.author; }
    
    public int getStock() { return this.stock.get(); }
    public void setStock(int stock) { this.stock.set(stock); }
    public IntegerProperty stockProperty() {return this.stock; }
    
    public int getID() { return this.ID.get(); }
    public void setID(int ID) { this.ID.set(ID); }
    public IntegerProperty IDProperty() {return this.ID; }
    // TBD: Add other getters and setters for other attribute properties
}
