package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        BufferedReader csvReader;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/books.csv");
            csvReader = new BufferedReader(new InputStreamReader(inputStream));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
//                System.out.println(data[0]+", "+data[1]+", "+data[2]+", "+data[3]+", "+data[4]);
                books.add(new Book(data[0], data[1], data[2], data[3], Integer.parseInt(data[4])));
            }
            csvReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void importAccounts() {
        // TBD: add code to populate accounts list from users csv sheet
    }
    
    public ObservableList<Account> getAccounts() {
        return accounts;
    }
    
    public void addAccount(int ID, String nameF, String nameS, String pass, int permLvl) {
        accounts.add(new Account(ID, nameF, nameS, pass, permLvl));
    }
    
    public void addBook(String ID, String title, String author, String genre, int stock) {
        FileWriter csvWriter;
        try {
            final String dir = System.getProperty("user.dir");
            csvWriter = new FileWriter(dir+"/src/data/books.csv", true); // Not sure this is best way to get the source - may need to be modified
            csvWriter.append(ID);
            csvWriter.append(",");
            csvWriter.append(title);
            csvWriter.append(",");
            csvWriter.append(author);
            csvWriter.append(",");
            csvWriter.append(genre);
            csvWriter.append(",");
            csvWriter.append(""+stock);
            csvWriter.append("\n");
            csvWriter.close();
            books.add(new Book(ID, title, author, genre, stock));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex);
        }
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
