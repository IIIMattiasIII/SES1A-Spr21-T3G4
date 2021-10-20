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
    private Boolean fined;
    private final ObservableList<Pair<Book, Date>> rentedBooks;
    private final ObservableList<Pair<Book, Date>> rentHistory;
    private final ObservableList<Pair<Book, Account>> prescribedBooks;
    private final ObservableList<Pair<Book, Double>> finedBooks;
    public Account(int ID, String nameF, String nameS, String password, int permLvl) {
        this.prescribedBooks = FXCollections.observableArrayList();
        this.rentHistory = FXCollections.observableArrayList();
        this.rentedBooks = FXCollections.observableArrayList();
        this.finedBooks = FXCollections.observableArrayList();
        this.ID.set(ID);
        this.name.set(nameF + " " + nameS);
        permissionLevel = permLvl;
        this.password = password;
        fined = false;
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
    
    public ObservableList<Pair<Book, Date>> getRented() {
        return this.rentedBooks;
    }
    
    public ObservableList<Pair<Book, Date>> getRentHist() {
        return this.rentHistory;
    }
    
    public ObservableList<Pair<Book, Account>> getAssigned() {
        return this.prescribedBooks;
    }
    
    public ObservableList<Pair<Book, Double>> getFined() {
        return this.finedBooks;
    }
    
    public void prescribeBook(Book b, Account a) {
        prescribedBooks.add(new Pair<>(b, a));
    }
    
    public boolean isPrescribed(Book b, Account a) {
        for (Pair<Book, Account> book : prescribedBooks) {
            if (book.getKey() == b && book.getValue() == a) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasBorrowed(Book b) {
        for (Pair<Book, Date> p : rentedBooks) {
            if (p.getKey() == b) {
                return true;
            }
        }
        return false;
    }
    
    public void borrowBook(Pair<Book, Date> b) {
        rentedBooks.add(b);
        rentHistory.add(b);
    }
    
    public Boolean returnBook(Pair<Book, Date> b){
        for (Pair<Book, Double> p : finedBooks) {
            if (p.getKey() == b.getKey()) {
                return false;
            }
        }
        rentedBooks.remove(b);
        return true;
    }
    
    public void checkOverdue() {
        long t = System.currentTimeMillis();
        finedBooks.clear();
        for (Pair<Book, Date> p : rentedBooks) {
            if (p.getValue().getDueTime() <= t) {
                long diff = t-p.getValue().getDueTime();
                double mult =  java.lang.Math.floor(diff/604800000)+1;  // For every week it stays overdue, fine amount is accrued
                finedBooks.add(new Pair<>(p.getKey(), p.getKey().getFineAmount()*mult));
            }
        }
        fined = !finedBooks.isEmpty();       
    }
}
