package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
    private IntegerProperty ID = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final int permissionLevel;
    private final String password;
    private final Boolean fined;
    private int borrowCount;
    
    public Account(int ID, String nameF, String nameS, String password, int permLvl) {
        this.ID.set(ID);
        this.name.set(nameF + " " + nameS);
        permissionLevel = permLvl;
        this.password = password;
        fined = false;
        // TBD: Add other attributes in constructor + their getters and setters
        borrowCount = 0;
    }
    
    public int getID() { return this.ID.get(); }
    public ReadOnlyIntegerProperty idProperty() { return ID; }
    
    public int getPassHash() { return password.hashCode(); }

    public Boolean isFined() {
        return fined;
    }
    public int getPermissionLevel() { return this.permissionLevel; }

    // Methods added for the borrow function //
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
