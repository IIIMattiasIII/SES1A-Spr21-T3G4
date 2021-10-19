package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class ELMS { 
    private final ObservableList<Account> accounts;
    private Account selectedAccount;
    private boolean userSelected;
    private final ObservableList<Book> books;
    private final ObservableList<Book> availableBooks;
    private final ObservableList<Pair<Account, Book>> requestedBooks;
    private final ObservableList<Book> overdueBooks;
    private Book selectedBook;
    private Search search;
    private Borrowings borrowings;

    public ELMS() {
        this.accounts = FXCollections.observableArrayList();
        this.books = FXCollections.observableArrayList();
        this.availableBooks = FXCollections.observableArrayList();
        this.requestedBooks = FXCollections.observableArrayList();
        this.overdueBooks = FXCollections.observableArrayList();
        search = new Search(this);
        borrowings = new Borrowings();
        selectedAccount = null;
        selectedBook = null;
        userSelected = false;
        importAccounts();
        importBooks();
    }
    
    public Search getSearch(){return search;}
    
    public Borrowings getBorrowings(){return borrowings;}
    
    private void importBooks() {
        BufferedReader csvReader;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/books.csv");
            csvReader = new BufferedReader(new InputStreamReader(inputStream));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                books.add(new Book(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5])));
                availableBooks.add(new Book(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5])));
            }
            csvReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void importAccounts() {
        BufferedReader csvReader;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/usersdemo.csv");
            csvReader = new BufferedReader(new InputStreamReader(inputStream));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                accounts.add(new Account(Integer.parseInt(data[0]), data[1], data[2], data[3], Integer.parseInt(data[4])));
            }
            csvReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ObservableList<Account> getAccounts() {
        return accounts;
    }
    
    public void addAccount(int ID, String nameF, String nameS, String pass, int permLvl) {
        FileWriter csvWriter;
        try {
            final String dir = System.getProperty("user.dir");
            csvWriter = new FileWriter(dir+"/src/data/usersdemo.csv", true); // Not sure this is best way to get the source - may need to be modified
            csvWriter.append(""+ID);
            csvWriter.append(",");
            csvWriter.append(nameF);
            csvWriter.append(",");
            csvWriter.append(nameS);
            csvWriter.append(",");
            csvWriter.append(pass);
            csvWriter.append(",");
            csvWriter.append(""+permLvl);
            csvWriter.append("\n");
            csvWriter.close();
            accounts.add(new Account(ID, nameF, nameS, pass, permLvl));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addBook(String ID, String title, String author, int year, String genre, int stock) {
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
            csvWriter.append(""+year);
            csvWriter.append(",");
            csvWriter.append(genre);
            csvWriter.append(",");
            csvWriter.append(""+stock);
            csvWriter.append("\n");
            csvWriter.close();
            books.add(new Book(ID, title, author, year, genre, stock));
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
        checkExpired();
    }

    private void checkExpired(){
        ObservableList<Borrow> borrowedBooks = borrowings.borrowedBooks;
        for (Borrow borrow : borrowedBooks) {
            Book book = borrow.getBook();
            Account account = borrow.getAccount();
            LocalDateTime dueDate = borrow.getDueDate();
            if (LocalDateTime.now().isAfter(dueDate)) {
                account.setFined();
                this.overdueBooks.add(book);
            }
        }
    }
    
    public boolean isUserSelected() {
        return userSelected;
    }
    
    public Book getSelectedBook() {
        return selectedBook;
    }
    
    public void setSelectedBook(Book b) {
        selectedBook = b;
    }
    
    public ObservableList<Book> getBooks() {
        return this.books;
    }

    public ObservableList<Book> getRentedBooks(Account user){
        ObservableList<Book> rented = FXCollections.observableArrayList();
        ObservableList<Borrow> borrowedBooks = borrowings.borrowedBooks;
        for (Borrow book : borrowedBooks) {
            if (book.getAccount() == user ) {
                rented.add(book.getBook());
            }
        }
        return rented;
    }

    public ObservableList<Book> getOverdueBooks() {
        return this.overdueBooks;
    }

    public ObservableList<Pair<Account, Book>> getRequests() {
        return this.requestedBooks;
    }

    public ObservableList<Book> getAvailableBooks() {
        return this.availableBooks;
    }
}