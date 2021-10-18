package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class Account {
    private IntegerProperty ID = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final int permissionLevel;
    private final String password;
    private final Boolean fined;
    private final ObservableList<Pair<Book,Date>> rentedBooks;
    private final ObservableList<Book> rentHistory;
    private final ObservableList<Book> assignedBooks;
    private final ObservableList<Pair<Book, Integer>> finedBooks;
    
    public Account(int ID, String nameF, String nameS, String password, int permLvl) {
        this.assignedBooks = FXCollections.observableArrayList();
        this.rentHistory = FXCollections.observableArrayList();
        this.rentedBooks = FXCollections.observableArrayList();
        this.finedBooks = FXCollections.observableArrayList();
        this.ID.set(ID);
        this.name.set(nameF + " " + nameS);
        permissionLevel = permLvl;
        this.password = password;
        fined = false;

        //temporary code for testing with rentedBooks as a Pair + using "Test Book" in the fined list//
       Book xBook = new Book("B1234", "Test book", "Person A", 2021, "Fiction", 1);
       Date xDate = new Date();
       Pair<Book,Date> xx = new Pair(xBook, xDate);
       rentedBooks.add(xx);
       Book yBook = new Book("B4321", "Test book 2", "Person B", 2021, "Fiction", 1);
       Date yDate = new Date();
       Pair<Book,Date> yy = new Pair(yBook, yDate);
       rentedBooks.add(yy);
       finedBooks.add(new Pair(xBook, 5));
    }
    
    public int getID() { return this.ID.get(); }
    public ReadOnlyIntegerProperty idProperty() { return ID; }
    
    public String getName(){return name.get();}
    public ReadOnlyStringProperty nameProperty() { return name; }
    
    public int getPassHash() { return password.hashCode(); }

    public Boolean isFined() {
        return fined;
    }
    
    public int getPermissionLevel() { return this.permissionLevel; }
    
    public ObservableList<Pair<Book,Date>> getRented() {
        return this.rentedBooks;
    }
    
    public ObservableList<Book> getRentHist() {
        return this.rentHistory;
    }
    
    public ObservableList<Book> getAssigned() {
        return this.assignedBooks;
    }

    public ObservableList<Pair<Book,Integer>> getFinedBooks() {
        return this.finedBooks;
    }
    
    public void borrowBook() {
        //
    }
    
    public void returnBook(){
        //
    }
}
