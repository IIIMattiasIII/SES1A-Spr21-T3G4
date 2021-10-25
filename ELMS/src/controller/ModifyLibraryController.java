package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Book;
import model.ELMS;

public class ModifyLibraryController extends Controller<ELMS> {
    @FXML private TableView<Book> booksTv;
    @FXML private TableColumn<Book, String> idCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> yearCol;
    @FXML private TableColumn<Book, String> genreCol;
    private ObservableList<Book> tablePop = FXCollections.observableArrayList();
    @FXML private Button removeBtn;
    @FXML private Button modBtn;
    @FXML private Label msgTxt;

    @FXML
    public void initialize() {
        modBtn.disableProperty().bind(Bindings.isEmpty(booksTv.getSelectionModel().getSelectedItems()));
        removeBtn.disableProperty().bind(Bindings.isEmpty(booksTv.getSelectionModel().getSelectedItems()));
        setContents();
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        yearCol.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asString());
        genreCol.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        msgTxt.setVisible(false);
    }

    public final ELMS getELMS() {
        return model;
    }
    
    private Book getSelected() {
        return booksTv.getSelectionModel().getSelectedItem();
    }
    
    private void setContents() {
        tablePop.clear();
        for (Book b : getELMS().getBooks()) {
            if (b.getStock() == b.getFullStock()) {
                tablePop.add(b);
            }
        }
        booksTv.setItems(tablePop);
    }
    
    void displayMsg(String s) {
        msgTxt.setVisible(true);
        msgTxt.setText(s);
    }
    
    @FXML public void handleModBtn(ActionEvent e) throws IOException {
        if (getSelected() != null) {
            getELMS().setSelectedBook(getSelected());
            Stage s = new Stage();
            s.getIcons().add(new Image("icon.png"));
            ViewLoader.showStage(getELMS(), "/view/ModifyBook.fxml", this.stage.getTitle(), s);
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

    @FXML public void handleExitBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/Login.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
