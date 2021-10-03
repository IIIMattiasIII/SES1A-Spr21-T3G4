package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Account;
import model.Book;
import model.ELMS;

public class ViewAccountController extends Controller<ELMS> {    
    @FXML private TableView<Book> mainTv;
    @FXML private TableColumn<Book, String> idCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> genreCol;
    @FXML private Button rentBtn;
    @FXML private Button assignedBtn;
    @FXML private Button finesBtn;
    @FXML private Button historyBtn;
    private Button prevBtn;
    private Account user;

    public ViewAccountController() {
        //
    }
    
    @FXML private void initialize() {
        user = getELMS().getSelectedAccount();
        rentBtn.fire();
        /*idCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID()));
        titleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        authorCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        genreCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()));*/
    }

    public final ELMS getELMS() { return model; }
    
    @FXML public void handleRentBooksBtn(ActionEvent e) throws IOException {
        mainTv.setItems(getELMS().getBooks());
        //selectBtn(rentBtn);
    }
    
    @FXML public void handleAssignedBooksBtn(ActionEvent e) throws IOException {
        selectBtn(assignedBtn);
        mainTv.setItems(getELMS().getBooks());
    }
    
    @FXML public void handleRentHistBtn(ActionEvent e) throws IOException {
        selectBtn(historyBtn);
        mainTv.setItems(getELMS().getBooks());
    }
    
    @FXML public void handleFinesBtn(ActionEvent e) throws IOException {
        selectBtn(finesBtn);
        // Add code for populating table with fines
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
        if (user.isFined()) {
            finesBtn.setDisable(false);
        } else {
            finesBtn.setDisable(true);
        }
    }
    
    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
