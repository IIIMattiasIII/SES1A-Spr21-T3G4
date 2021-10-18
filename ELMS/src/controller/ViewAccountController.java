package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Pair;
import model.Account;
import model.Book;
import model.ELMS;
import model.Date;

public class ViewAccountController extends Controller<ELMS> {    
    @FXML private TableView mainTv;
    @FXML private TableColumn<Pair<Book, Object>, String> idCol;
    @FXML private TableColumn<Pair<Book, Object>, String> titleCol;
    @FXML private TableColumn<Pair<Book, Object>, String> authorCol;
    @FXML private TableColumn<Pair<Book, Account>, String> accCol;
    @FXML private TableColumn<Pair<Book, Date>, String> dateCol;
    @FXML private TableColumn<Pair<Book, Integer>, String> fineCol;
    @FXML private Button rentBtn;
    @FXML private Button prescribedBtn;
    @FXML private Button finesBtn;
    @FXML private Button historyBtn;
    @FXML private Button actionBtn;
    private Button prevBtn;
    private Account user;
    
    @FXML private void initialize() {
        actionBtn.disableProperty().bind(Bindings.isEmpty(mainTv.getSelectionModel().getSelectedItems()));
        user = getELMS().getSelectedAccount();
        rentBtn.fire();
    }
    
    public final ELMS getELMS() { return model; }
    
    private final Object getSelected() { 
        return mainTv.getSelectionModel().getSelectedItem();
    }
    
    @FXML public void handleRentBooksBtn(ActionEvent e) {
        selectBtn(rentBtn);
        dateCol.setText("Return By Date");
        dateCol.setVisible(true);
        mainTv.setItems(user.getRented());
        int size = mainTv.getItems().size() > 12 ? 771 : 754;
        mainTv.setMaxWidth(size);
        idCol.setCellValueFactory(cellData -> cellData.getValue().getKey().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().getKey().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().getKey().authorProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().getValue().dueDateProperty());
        actionBtn.setText("Return Book");
        actionBtn.setVisible(true);
    }
    
    @FXML public void handlePrescribedBooksBtn(ActionEvent e) {
        selectBtn(prescribedBtn);
        accCol.setVisible(true);
        mainTv.setItems(user.getAssigned());
        int size = mainTv.getItems().size() > 12 ? 771 : 754;
        mainTv.setMaxWidth(size);
        idCol.setCellValueFactory(cellData -> cellData.getValue().getKey().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().getKey().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().getKey().authorProperty());
        accCol.setCellValueFactory(cellData -> cellData.getValue().getValue().nameProperty());
        
    }
    
    @FXML public void handleRentHistBtn(ActionEvent e) {
        selectBtn(historyBtn);
        dateCol.setText("Date Borrowed");
        dateCol.setVisible(true);
        mainTv.setItems(user.getRentHist());
        int size = mainTv.getItems().size() > 12 ? 771 : 754;
        mainTv.setMaxWidth(size);
        idCol.setCellValueFactory(cellData -> cellData.getValue().getKey().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().getKey().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().getKey().authorProperty());
         dateCol.setCellValueFactory(cellData -> cellData.getValue().getValue().initDateProperty());  
    }
    
    @FXML public void handleFinesBtn(ActionEvent e) {
        selectBtn(finesBtn);
        fineCol.setVisible(true);
        dateCol.setText("Fine");
        int size = mainTv.getItems().size() > 12 ? 771 : 754;
        mainTv.setMaxWidth(size);
        /*idCol.setCellValueFactory(cellData -> cellData.getValue().getKey().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().getKey().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().getKey().authorProperty());
        fineCol.setCellValueFactory(cellData -> cellData.getValue().getValue().dueDateProperty());*/
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
        prescribedBtn.setDisable(false);
        historyBtn.setDisable(false);
        if (user.isFined()) {
            finesBtn.setDisable(false);
        } else {
            finesBtn.setDisable(true);
        }
        actionBtn.setText(null);
        actionBtn.setVisible(false);
        accCol.setVisible(false);
        dateCol.setVisible(false);
        fineCol.setVisible(false);
    }
    
    @FXML public void handleActionBtn(ActionEvent e) {
        // Add actions in for actionBtn
    }

    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
