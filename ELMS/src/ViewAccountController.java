package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import model.Account;
import model.Book;
import model.ELMS;

public class ViewAccountController extends Controller<ELMS> {    
    @FXML private TableView<Book> mainTv;
    @FXML private TableColumn<Book, String> idCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> varCol;
    @FXML private Button rentBtn;
    @FXML private Button assignedBtn;
    @FXML private Button finesBtn;
    @FXML private Button historyBtn;
    @FXML private Button actionBtn;
    private Button prevBtn;
    private Account user;
    
    @FXML private void initialize() {
        actionBtn.disableProperty().bind(Bindings.isEmpty(mainTv.getSelectionModel().getSelectedItems()));
        user = getELMS().getSelectedAccount();
        rentBtn.fire();
    }
    
    public final ELMS getELMS() { return model; }
    
    private final Book getSelected() { 
        return mainTv.getSelectionModel().getSelectedItem();
        // Method will likely need updating to return a Pair, or multiple methods need to be added
    }
    
    @FXML public void handleRentBooksBtn(ActionEvent e) throws IOException {
        selectBtn(rentBtn);
        mainTv.setItems(getELMS().getRentedBooks(user)); // Replace with user's rented books
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        varCol.setText("Return By Date");
        // varCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());  
        // ^ To be added once observable list is updated to a Pair and date is added
        actionBtn.setText("Return Book");
        actionBtn.setVisible(true);
    }
    
    @FXML public void handleAssignedBooksBtn(ActionEvent e) throws IOException {
        selectBtn(assignedBtn);
        mainTv.setItems(getELMS().getBooks()); // Replace with assigned books
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        varCol.setText("Assigned By");
        // varCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());  
        // ^ To be added once observable list for assigned books is created
    }
    
    @FXML public void handleRentHistBtn(ActionEvent e) throws IOException {
        selectBtn(historyBtn);
        mainTv.setItems(getELMS().getBooks()); // Replace with renting history books
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        varCol.setText("Date Returned");
        // varCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());  
        // ^ To be added once observable list for returned books is added
    }
    
    @FXML public void handleFinesBtn(ActionEvent e) throws IOException {
        selectBtn(finesBtn);
        // Add code for populating table with fines
        mainTv.setItems(getELMS().getOverdueBooks()); // Replace with renting overdue books
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        varCol.setCellValueFactory(cellData -> cellData.getValue().fineProperty());
        varCol.setText("Fined Money");
    }
    
    private void selectBtn(Button b) {
        resetBtns();
        if (prevBtn != null) {
            prevBtn.getStyleClass().remove("buttonToggled");
        }
        b.getStyleClass().add("buttonToggled");
        b.setDisable(true);
        prevBtn = b;
    }
    
    private void resetBtns() {
        rentBtn.setDisable(false);
        assignedBtn.setDisable(false);
        historyBtn.setDisable(false);
        //finesBtn.setDisable(false);
        if (user.isFined()) {
            finesBtn.setDisable(false);
        } else {
            finesBtn.setDisable(true);
        }
        actionBtn.setText(null);
        actionBtn.setVisible(false);
    }
    
    @FXML public void handleActionBtn(ActionEvent e) {
        // Add actions in for actionBtn
        int focusedIndex = mainTv.getSelectionModel().getFocusedIndex();
        Book book = mainTv.getItems().get(focusedIndex);
        if (book != null ) {
            TextInputDialog alert = new TextInputDialog();
            alert.setTitle(" fined money");
            alert.setContentText(" fined money ");
            Optional<String> optional = alert.showAndWait();
            if (optional.isPresent()) {
                System.out.println("get the money from dailog:" + optional.get());
            };
        }
    }

    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
