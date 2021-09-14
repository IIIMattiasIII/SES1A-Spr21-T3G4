package controller;

import au.edu.uts.ap.javafx.*;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Book;
import model.ELMS;

public class AddBookController extends Controller<ELMS> {

    @FXML
    private TextField idTf;
    @FXML
    private TextField titleTf;
    @FXML
    private TextField authorTf;
    @FXML
    private TextField genreTf;
    @FXML
    private TextField stockTf;
    @FXML
    private Button addBtn;
    @FXML
    private Button closeBtn;
    @FXML
    private Label msgTxt;

    @FXML
    public void initialize() {
        addBtn.disableProperty().bind(Bindings.isEmpty(idTf.textProperty()));
        addBtn.disableProperty().bind(Bindings.isEmpty(titleTf.textProperty()));
        addBtn.disableProperty().bind(Bindings.isEmpty(authorTf.textProperty()));
        addBtn.disableProperty().bind(Bindings.isEmpty(genreTf.textProperty()));
        addBtn.disableProperty().bind(Bindings.isEmpty(stockTf.textProperty()));
        msgTxt.setVisible(false);
    }

    public final ELMS getELMS() {
        return model;
    }

    private final String getID() {
        return idTf.getText().trim();
    }

    private final String getTitle() {
        return titleTf.getText().trim();
    }

    private final String getAuthor() {
        return authorTf.getText().trim();
    }

    private final String getGenre() {
        return genreTf.getText();
    }

    private final int getStock() {
        return Integer.parseInt(stockTf.getText().trim());
    }

    void displayMsg(String s) {
        msgTxt.setVisible(true);
        msgTxt.setText(s);
    }

    void addBook() {
        msgTxt.setVisible(false);
        boolean idMatch = false;
        for (Book b : getELMS().getBooks()) {
            if (b != null && this.getID().equals(b.getID())) {
                idMatch = true;
                break;
            }
        }
        if (idMatch) {
            displayMsg("A book has already been added to the library with this ID.");
        } else {
            getELMS().addBook(this.getID(), this.getTitle(), this.getAuthor(), this.getGenre(), this.getStock());
            displayMsg("Book has been added. You can now close this window.");
            closeBtn.setText("Close");
            //        addBtn.setDisable(true);  <-- This is returning errors. Will use the line below until this is fixed.
            addBtn.setVisible(false);
        }
    }

    void regEnterCheck(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            addBook();
        }
    }

    @FXML
    public void handleAddBtn(ActionEvent e) {
        addBook();
    }

    @FXML
    public void handleCloseBtn(ActionEvent e) {
        this.stage.close();
    }
}
