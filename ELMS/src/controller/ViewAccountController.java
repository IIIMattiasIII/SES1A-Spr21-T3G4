package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.TableView;
import model.Book;
import model.Borrow;
import model.Borrowings;
import model.ELMS;

public class ViewAccountController extends Controller<ELMS> {    
    public ViewAccountController() {
        //
    }
    @FXML private TableView<Book> mainTable;

    public final ELMS getELMS() { return model; }
    public final Borrowings getBorrowings() { return model; }
    
    @FXML public void handleRentBooksBtn(ActionEvent e) throws IOException {
        mainTable.setItems(getBorrowings().borrowedBooks());
    }
    @FXML public void handleAssignedBooksBtn(ActionEvent e) throws IOException {
        //mainTable.setItems(getELMS().getAssignedBooks());
    }
    @FXML public void handleFinesBtn(ActionEvent e) throws IOException {
        
    }
    @FXML public void handleRentHistBtn(ActionEvent e) throws IOException {
        //mainTable.setItems(getELMS().getBooks());
    }
    
    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
