package model;

import java.util.Date;
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
    private final ObservableList<Book> rentedBooks;
    private final ObservableList<Book> rentHistory;
    private final ObservableList<Book> assignedBooks;
    private final ObservableList<Pair<Date,Book>> borrowed;
    
    public Account(int ID, String nameF, String nameS, String password, int permLvl) {
        this.assignedBooks = FXCollections.observableArrayList();
        this.rentHistory = FXCollections.observableArrayList();
        this.rentedBooks = FXCollections.observableArrayList();
        this.borrowed = FXCollections.observableArrayList();
        this.ID.set(ID);
        this.name.set(nameF + " " + nameS);
        permissionLevel = permLvl;
        this.password = password;
        fined = false;
    }
    public void borrow(Pair pair){  borrowed.add(pair); }
    public int getID() { return this.ID.get(); }
    public ReadOnlyIntegerProperty idProperty() { return ID; }
    
    public String getName(){return name.get();}
    public ReadOnlyStringProperty nameProperty() { return name; }
    
    public int getPassHash() { return password.hashCode(); }

    public Boolean isFined() {
        return fined;
    }
    
    public int getPermissionLevel() { return this.permissionLevel; }
    
    public ObservableList<Book> getRented() {
        return this.rentedBooks;
    }
    
    public ObservableList<Book> getRentHist() {
        return this.rentHistory;
    }
    
    public ObservableList<Book> getAssigned() {
        return this.assignedBooks;
    }
    
    public void borrowBook() {
        //
    }
    
    public void returnBook(){
        //
    }
}
