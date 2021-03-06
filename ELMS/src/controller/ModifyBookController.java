package controller;

import au.edu.uts.ap.javafx.*;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Book;
import model.ELMS;

public class ModifyBookController extends Controller<ELMS> {
    @FXML
    private TextField idTf;
    @FXML
    private TextField titleTf;
    @FXML
    private TextField authorTf;
    @FXML
    private TextField yearTf;
    @FXML
    private TextField genreTf;
    @FXML
    private TextField stockTf;
    @FXML
    private TextField fineTf;
    @FXML
    private Button updateBtn;
    @FXML
    private Button closeBtn;
    @FXML
    private Label msgTxt;
    private Book selBook;

    @FXML
    public void initialize() {
        selBook = getELMS().getSelectedBook();
        // Add data into fields from the selected book
        idTf.setText(selBook.getID());
        titleTf.setText(selBook.getTitle());
        authorTf.setText(selBook.getAuthor());
        yearTf.setText(selBook.getYear()+"");
        genreTf.setText(selBook.getGenre());
        stockTf.setText(selBook.getStock()+"");
        fineTf.setText(selBook.getFineAmount()+"");
        // Change listener to force the contents of the stock text field to be only numbers
        stockTf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                stockTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
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

    private final int getYear() {
        return Integer.parseInt(yearTf.getText().trim());
    }

    private final String getGenre() {
        return genreTf.getText().trim();
    }

    private final int getStock() {
        return Integer.parseInt(stockTf.getText().trim());
    }

    private final double getFine() {
        return Double.parseDouble(fineTf.getText().trim());
    }

    void displayMsg(String s) {
        msgTxt.setVisible(true);
        msgTxt.setText(s);
    }

    void updateBook() {
        msgTxt.setVisible(false);
        selBook.setID(this.getID());
        selBook.setTitle(this.getTitle());
        selBook.setAuthor(this.getAuthor());
        selBook.setYear(this.getYear());
        selBook.setGenre(this.getGenre());
        selBook.setStock(this.getStock());
        selBook.setFullStock(this.getStock());
        selBook.setFineAmount(this.getFine());
        displayMsg("Book has been updated. You can now close this window.");
        closeBtn.setText("Close");
        //        updateBtn.setDisable(true);  <-- This is returning errors. Will use the line below until this is fixed.
        updateBtn.setVisible(false);
    }

    @FXML
    public void handleUpdateBtn(ActionEvent e) {
        updateBook();
    }

    @FXML
    public void handleCloseBtn(ActionEvent e) {
        this.stage.close();
    }
}
