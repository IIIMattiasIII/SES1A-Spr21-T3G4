package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ELMS { 
    private final ObservableList<Account> accounts = FXCollections.observableArrayList();
    private Account selectedAccount = null;
    private final ObservableList<Book> books = FXCollections.observableArrayList();
    private boolean userSelected;

    public ELMS() {
        userSelected = false;
        addAccount(0, "admin", "", "password", 0);
        importBooks();
    }
    
    private void importBooks() {
        // TBD: add code to populate books list from books csv sheet
    }
    
    private void importAccounts() {
        // TBD: add code to populate accounts list from users csv sheet
    }
    
    public ObservableList<Account> getAccounts() {
        return accounts;
    }
    
    public void addAccount(int ID, String nameF, String nameS, String pass, int permLvl) {
        accounts.add(new Account(ID, nameF, nameS, pass, permLvl));
        // TBD: Add integration with accounts csv
    }
    
    public Account getSelectedAccount() {
        return selectedAccount;
    }
    
    public void setSelectedAccount(Account a) {
        selectedAccount = a;
        userSelected = true;
    }
    
    public boolean isUserSelected() {
        return userSelected;
    }
    
    public ObservableList<Book> getBooks() {
        return this.books;
    }
}
