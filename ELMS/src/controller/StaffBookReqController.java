package controller;

import au.edu.uts.ap.javafx.*;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Book;
import model.ELMS;

public class StaffBookReqController extends Controller<ELMS> {

    @FXML
    private TextField titleTf;
    @FXML
    private TextField authorTf;
    @FXML
    private TextField yearTf;
    @FXML
    private Button reqBtn;
    @FXML
    private Button closeBtn;
    @FXML
    private Label msgTxt;

    @FXML
    public void initialize() {
        // Disable button when textfields are empty
        reqBtn.disableProperty().bind(Bindings.isEmpty(titleTf.textProperty()));
        reqBtn.disableProperty().bind(Bindings.isEmpty(authorTf.textProperty()));
        reqBtn.disableProperty().bind(Bindings.isEmpty(yearTf.textProperty()));
        // Change listener to force the contents of the year text field to be only numbers
        yearTf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                yearTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        msgTxt.setVisible(false);
    }

    public final ELMS getELMS() {
        return model;
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

    void displayMsg(String s) {
        msgTxt.setVisible(true);
        msgTxt.setText(s);
    }

    void reqBook() {
        msgTxt.setVisible(false);
        boolean match = false;
        for (Book b : getELMS().getBooks()) {
            if (b != null && this.getTitle().equals(b.getTitle()) && this.getAuthor().equals(b.getAuthor())) { // Check a book does not exist with the entered ID
                match = true;
                break;
            }
        }
        if (match) { // Inform user if match is found
            displayMsg("It looks like this book already exists in the library.\nIf you think this is an error, please contact the library management.");
        } else { // Else, add book to requests list for review by admins
            getELMS().getRequests().add(new Book(getTitle(), getAuthor(), getYear()));
            displayMsg("The book has been requested. You can now close this window.");
            closeBtn.setText("Close");
            //        reqBtn.setDisable(true);  <-- This is returning errors. Will use the line below until this is fixed.
            reqBtn.setVisible(false);
        }
    }

    @FXML
    public void handleReqBtn(ActionEvent e) {
        reqBook();
    }

    @FXML
    public void handleCloseBtn(ActionEvent e) {
        this.stage.close();
    }
}
