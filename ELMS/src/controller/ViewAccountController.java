package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.TableView;
import model.Book;
import model.ELMS;

public class ViewAccountController extends Controller<ELMS> {    
    public ViewAccountController() {
        //
    }
    @FXML private TableView<Book> RentBookTable;
    @FXML private TableView<Book> rentingHistoryTable;
    @FXML private TableView<Book> assignedBooksTable;
    @FXML private TableView<Book> mainTable;
    @FXML private TableView<Book> finesTable;

    public final ELMS getELMS() { return model; }
    /*titleClmn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
    authorClmn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
    stockClmn.setCellValueFactory(cellData -> cellData.getValue().stockProperty());
    IDClmn.setCellValueFactory(cellData -> cellData.getValue().IDProperty());*/
    @FXML public void handleRentBooksBtn(ActionEvent e) throws IOException {
        /*for (Book book : model.getBooks()) {
        }
        mainTable = RentBookTable;
        mainTable.setItems((ObservableList<Book>) RentBookTable);*/
    }
    @FXML public void handleAssignedBooksBtn(ActionEvent e) throws IOException {
        /*mainTable = assignedBooksTable;
        mainTable.setItems((ObservableList<Book>) assignedBooksTable);*/
    }
    @FXML public void handleFinesBtn(ActionEvent e) throws IOException {
        /*mainTable  = finesTable;
        mainTable.setItems((ObservableList<Book>) finesTable);*/
    }
    @FXML public void handleRentHistBtn(ActionEvent e) throws IOException {
        /*mainTable = rentingHistoryTable;
        mainTable.setItems((ObservableList<Book>) rentingHistoryTable);*/
    }
    
    //@FXML private TableView<Book> Table;
    
    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
