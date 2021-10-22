package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import java.text.DecimalFormat;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Pair;
import model.*;

public class ViewAccountController extends Controller<ELMS> {    
    @FXML private TableView mainTv;
    @FXML private TableColumn<Pair<Book, Object>, String> idCol;
    @FXML private TableColumn<Pair<Book, Object>, String> titleCol;
    @FXML private TableColumn<Pair<Book, Object>, String> authorCol;
    @FXML private TableColumn<Pair<Book, Account>, String> accCol;
    @FXML private TableColumn<Pair<Book, Date>, String> dateCol;
    @FXML private TableColumn<Pair<Book, Double>, String> fineCol;
    @FXML private Button rentBtn;
    @FXML private Button prescribedBtn;
    @FXML private Button finesBtn;
    @FXML private Button historyBtn;
    @FXML private Button actionBtn;
    @FXML private Button renewBtn;
    @FXML private Label title;
    @FXML private Label msgTxt;
    private Button prevBtn;
    private Account user;
    
    @FXML private void initialize() {
        actionBtn.disableProperty().bind(Bindings.isEmpty(mainTv.getSelectionModel().getSelectedItems()));
        renewBtn.disableProperty().bind(Bindings.isEmpty(mainTv.getSelectionModel().getSelectedItems()));
        user = getELMS().getSelectedAccount();
        user.checkOverdue();
        title.setText("View Account | "+user.getName());
        rentBtn.fire();
        msgTxt.setVisible(false);
    }
    
    public final ELMS getELMS() { return model; }
    
    private final Object getSelected() { 
        return mainTv.getSelectionModel().getSelectedItem();
    }

    private void setContents(ObservableList o) {
        mainTv.setItems(o);
        int size = mainTv.getItems().size() > 12 ? 771 : 754;
        mainTv.setMaxWidth(size);
    }
    
    void displayMsg(String s) {
        msgTxt.setVisible(true);
        msgTxt.setText(s);
    }

    @FXML public void handleRentBooksBtn(ActionEvent e) {
        selectBtn(rentBtn);
        dateCol.setText("Return By Date");
        dateCol.setVisible(true);
        setContents(user.getRented());
        idCol.setCellValueFactory(cellData -> cellData.getValue().getKey().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().getKey().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().getKey().authorProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().getValue().dueDateProperty());
        actionBtn.setText("Return Book");
        actionBtn.setVisible(true);
        renewBtn.setText("Renew Book");
        renewBtn.setVisible(true);
    }
    
    @FXML public void handlePrescribedBooksBtn(ActionEvent e) {
        selectBtn(prescribedBtn);
        accCol.setVisible(true);
        setContents(user.getAssigned());
        idCol.setCellValueFactory(cellData -> cellData.getValue().getKey().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().getKey().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().getKey().authorProperty());
        accCol.setCellValueFactory(cellData -> cellData.getValue().getValue().nameProperty());
        
    }
    
    @FXML public void handleRentHistBtn(ActionEvent e) {
        selectBtn(historyBtn);
        dateCol.setText("Date Borrowed");
        dateCol.setVisible(true);
        setContents(user.getRentHist());
        idCol.setCellValueFactory(cellData -> cellData.getValue().getKey().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().getKey().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().getKey().authorProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().getValue().initDateProperty());  
    }
    
    @FXML public void handleFinesBtn(ActionEvent e) {
        selectBtn(finesBtn);
        fineCol.setVisible(true);
        setContents(user.getFined());
        idCol.setCellValueFactory(cellData -> cellData.getValue().getKey().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().getKey().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().getKey().authorProperty());
        fineCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(formatted(cellData.getValue().getValue())));
        actionBtn.setText("Pay Fine");
        actionBtn.setVisible(true);
    }
    
    private String formatted(double money) {
        return new DecimalFormat("$###,##0.00").format(money);
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
        msgTxt.setVisible(false);
        rentBtn.setDisable(false);
        prescribedBtn.setDisable(false);
        historyBtn.setDisable(false);
        if (user.isFined()) {
            finesBtn.setDisable(false);
        } else {
            finesBtn.setDisable(true);
        }
        actionBtn.setText(null);
        actionBtn.setVisible(false);
        renewBtn.setVisible(false);
        accCol.setVisible(false);
        dateCol.setVisible(false);
        fineCol.setVisible(false);
    }
    
    @FXML public void handleActionBtn(ActionEvent e) {
        if (actionBtn.getText().equalsIgnoreCase("Return Book")) {
            Pair<Book, Date> selected = (Pair<Book, Date>) getSelected();
            if (user.returnBook(selected)) {
                getELMS().returnBook(selected.getKey());
                displayMsg("You have returned '" + selected.getKey().getTitle() + "'.");
            } else {
                displayMsg("Unable to return due to outstanding fines.");
            }
        } else if (actionBtn.getText().equalsIgnoreCase("Pay Fine")) {   // Pay fine functionality - TBD: integrate with payment system
            Pair<Book, Double> selected = (Pair<Book, Double>) getSelected();
            // if user pays money (where cost = p.getValue();) - see integration comment above
                for (Pair<Book, Date> p : user.getRented()) {
                    if (p.getKey() == selected.getKey()) {
                        p.getValue().updateToCurrent();
                        break;
                    }
                }
                user.getFined().remove(selected);
                user.checkOverdue();
            // }
        }
    }

    @FXML public void handleRenewBtn (ActionEvent e) {
        boolean found = false;
        Pair<Book, Date> selected = (Pair<Book, Date>) getSelected();
        for (Pair<Book, Double> pair : user.getFined()) {
            if (selected.getKey().getTitle().equals(pair.getKey().getTitle())) {
                displayMsg("Unable to renew due to outstanding fines.");
                found = true;
                break;
            }
        }
        if (!found) {
            msgTxt.setVisible(false);
            selected.getValue().updateToCurrent();
            displayMsg("You have renewed '" + selected.getKey().getTitle() + "'.");
        }
    }

    @FXML public void handleExitBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/Login.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMSMenu.fxml", this.stage.getTitle(), this.stage);
    }
}
