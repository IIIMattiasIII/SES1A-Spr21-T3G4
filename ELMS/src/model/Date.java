package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.text.SimpleDateFormat;
import javafx.beans.property.ReadOnlyStringProperty;

public class Date {
    private long initTime;
    private long dueTime;
    private StringProperty initDate = new SimpleStringProperty();
    private StringProperty dueDate = new SimpleStringProperty();
    
    public Date() {
        updateToCurrent();
    }
    
    public void updateToCurrent() {
        initTime = System.currentTimeMillis();
        dueTime = initTime+1209660000; // +2 weeks and 1 minute in millis
        initDate.set(new SimpleDateFormat("dd/MM/yy 'at' HH:mm").format(new java.util.Date(initTime)));
        dueDate.set(new SimpleDateFormat("dd/MM/yy 'at' HH:mm").format(new java.util.Date(dueTime)));
    }
    
    public long getInitTime() { return initTime; }
    public long getDueTime() { return dueTime; }
    
    public String getInitDate(){ return initDate.get();}
    public ReadOnlyStringProperty initDateProperty() { return initDate; }
    
    public String getDueDate(){ return dueDate.get();}
    public ReadOnlyStringProperty dueDateProperty() { return dueDate; }
}