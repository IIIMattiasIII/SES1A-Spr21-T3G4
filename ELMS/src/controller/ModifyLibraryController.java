package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private Book selBook = null;

    @FXML
    public void initialize() {
        modBtn.disableProperty().bind(Bindings.isEmpty(booksTv.getSelectionModel().getSelectedItems()));
        removeBtn.disableProperty().bind(Bindings.isEmpty(booksTv.getSelectionModel().getSelectedItems()));
        booksTv.setItems(getELMS().getBooks());
        msgTxt.setVisible(false);
    }

    public final ELMS getELMS() {
        return model;
    }
    
    private final Book getSelected() { 
        if (booksTv.getSelectionModel().getSelectedItem() != null) 
            return booksTv.getSelectionModel().getSelectedItem();
        else
            return null;
    }
    
    @FXML public void handleModBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/ModifyBook.fxml", this.stage.getTitle(), new Stage());
    }
    
    @FXML public void handleRemoveBtn(ActionEvent e) throws IOException {
        //
    }

    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
