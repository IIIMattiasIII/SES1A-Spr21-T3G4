package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Pair;
import model.Account;
import model.Book;
import model.ELMS;

public class ViewRequestsController extends Controller<ELMS> {
    @FXML private TableView<Pair<Account, Book>> reqsTv;
    @FXML private TableColumn<Pair<Account, Book>, String> requesterCol;
    @FXML private TableColumn<Pair<Account, Book>, String> titleCol;
    @FXML private TableColumn<Pair<Account, Book>, String> authorCol;
    @FXML private TableColumn<Pair<Account, Book>, String> yearCol;
    @FXML private Button removeBtn;
    @FXML private Label msgTxt;
    
    @FXML public void initialize() {
        removeBtn.disableProperty().bind(Bindings.isEmpty(reqsTv.getSelectionModel().getSelectedItems()));
        reqsTv.setItems(getELMS().getRequests());
        int size = getELMS().getRequests().size() > 8 ? 821 : 804;
        reqsTv.setMaxWidth(size);
        requesterCol.setCellValueFactory(cellData -> cellData.getValue().getKey().nameProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().getValue().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().getValue().authorProperty());
        yearCol.setCellValueFactory(cellData -> cellData.getValue().getValue().yearProperty().asString());
    }

    public final ELMS getELMS() { return model; }
    
    private final Pair<Account, Book> getSelected() { 
        return reqsTv.getSelectionModel().getSelectedItem();
    }
    
    void displayMsg(String s) {
        msgTxt.setVisible(true);
        msgTxt.setText(s);
    }

    @FXML public void handleRemoveBtn(ActionEvent e) {
        msgTxt.setVisible(false);
        if (getSelected() != null) {
            getELMS().getRequests().remove(getSelected());
            displayMsg("Request for "+ getSelected().getValue().getTitle() +"has been removed.");
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
