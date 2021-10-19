package controller;

import au.edu.uts.ap.javafx.*;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import model.Account;
import model.ELMS;

public class PrescribeBookController extends Controller<ELMS> {
    @FXML private TableView<Account> mainTv;
    @FXML private TableColumn<Account, String> idCol;
    @FXML private TableColumn<Account, String> nameCol;
    @FXML private Button presBtn;
    @FXML private Button closeBtn;
    @FXML private Label msgTxt;
    private ObservableList<Account> contents = FXCollections.observableArrayList();
    
    @FXML public void initialize() {
        for (Account a : getELMS().getAccounts()) {
            if (a.getPermissionLevel() == 2) {
                if (!a.isPrescribed(getELMS().getSelectedBook(), getELMS().getSelectedAccount())) {
                    contents.add(a);
                }
            }
        }
        mainTv.setItems(contents);
        int size = contents.size() > 8 ? 421 : 404;
        mainTv.setMaxWidth(size);
        presBtn.disableProperty().bind(Bindings.isEmpty(mainTv.getSelectionModel().getSelectedItems()));
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }

    public final ELMS getELMS() { return model; }
    
    private final Account getSelected() { 
        return mainTv.getSelectionModel().getSelectedItem();
    }
    
    void displayMsg(String s) {
        msgTxt.setVisible(true);
        msgTxt.setText(s);
    }
    
    void prescribe() {
        msgTxt.setVisible(false);
        if (getSelected() != null) {
            if (!getSelected().isPrescribed(getELMS().getSelectedBook(), getELMS().getSelectedAccount())) {
                getSelected().prescribeBook(getELMS().getSelectedBook(), getELMS().getSelectedAccount());
                displayMsg(getELMS().getSelectedBook().getTitle() + " has been assigned to selected student.");
                closeBtn.setText("Close");
            //        updateBtn.setDisable(true);  <-- This is returning errors. Will use the line below until this is fixed.
                presBtn.setVisible(false);
            } else {
                displayMsg("You've already prescribed this book to the selected student.");
            }
        } else {
            displayMsg("Invalid selection.");
        }
    }

    @FXML public void handlePresBtn(ActionEvent e) {
        prescribe();
    }

    @FXML
    public void handleCloseBtn(ActionEvent e) {
        this.stage.close();
    }
}
