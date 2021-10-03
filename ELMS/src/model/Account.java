package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Account {
    private IntegerProperty ID = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final int permissionLevel;
    private final String password;
    private final Boolean fined;
    private ObservableList<Book> rentedBooks;
    private ObservableList<Book> rentHistory;
    private ObservableList<Book> assignedBooks;
    private int borrowCount;
    
    public Account(int ID, String nameF, String nameS, String password, int permLvl) {
        this.ID.set(ID);
        this.name.set(nameF + " " + nameS);
        permissionLevel = permLvl;
        this.password = password;
        fined = false;
        // TBD: Add other attributes in constructor + their getters and setters
    }
    
    public int getID() { return this.ID.get(); }
    public ReadOnlyIntegerProperty idProperty() { return ID; }
    
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
    
    public int getBorrowCount() {
        return borrowCount;
    }
    
    public void borrowedBook() {
        borrowCount++;
    }
    
    public void returnedBook(){
        borrowCount--;
    }
}
