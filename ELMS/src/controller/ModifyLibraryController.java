package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Book;
import model.ELMS;

public class ModifyLibraryController extends Controller<ELMS> {
    @FXML private TableView<Book> booksTv;
    @FXML private TableColumn<Book, String> idCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> genreCol;
    
    @FXML private Button removeBtn;
    @FXML private Button modBtn;
    @FXML private Label msgTxt;

    @FXML
    public void initialize() {
        modBtn.disableProperty().bind(Bindings.isEmpty(booksTv.getSelectionModel().getSelectedItems()));
        removeBtn.disableProperty().bind(Bindings.isEmpty(booksTv.getSelectionModel().getSelectedItems()));
        booksTv.setItems(getELMS().getBooks()); // Needs to be updated to getAvailableBooks once availableBooks list is added
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        genreCol.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        msgTxt.setVisible(false);
    }

    public final ELMS getELMS() {
        return model;
    }
    
    private Book getSelected() {
        return booksTv.getSelectionModel().getSelectedItem();
    }
    
    void displayMsg(String s) {
        msgTxt.setVisible(true);
        msgTxt.setText(s);
    }
    
    @FXML public void handleModBtn(ActionEvent e) throws IOException {
        if (getSelected() != null) {
            getELMS().setSelectedBook(getSelected());
            ViewLoader.showStage(getELMS(), "/view/ModifyBook.fxml", this.stage.getTitle(), new Stage());
        } else {
            displayMsg("Invalid selection.");
        }
    }
    
    @FXML public void handleRemoveBtn(ActionEvent e) throws IOException {
        if (getSelected() != null) {
            getELMS().getBooks().remove(getSelected()); // Needs to be updated to getAvailableBooks once availables books list is added
        } else {
            displayMsg("Invalid selection.");
        }
    }

    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
