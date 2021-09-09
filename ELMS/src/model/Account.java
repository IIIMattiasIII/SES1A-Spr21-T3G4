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
    
    public Account(int ID, String nameF, String nameS, String password, int permLvl) {
        this.ID.set(ID);
        this.name.set(nameF + " " + nameS);
        permissionLevel = permLvl;
        this.password = password;
        // TBD: Add other attributes in constructor + their getters and setters
    }
    
    public int getID() { return this.ID.get(); }
    public ReadOnlyIntegerProperty idProperty() { return ID; }
    
    public int getPassHash() { return password.hashCode(); }
}
