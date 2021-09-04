package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty title = new SimpleStringProperty();
    // TBD: Add other attributes
    
    public Book(String title) {
        this.title.set(title);
        // TBD: Add other attributes in constructor
    }
    
    public String getTitle() { return this.title.get(); }
    public void setTitle(String title) { this.title.set(title); }
    public StringProperty titleProperty() { return this.title; }
    
    // TBD: Add other getters and setters for other attribute properties
}
