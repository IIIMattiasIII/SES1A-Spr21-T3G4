package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import model.ELMS;

public class ELMSController extends Controller<ELMS> {    
    @FXML private Button adminBtn;
    @FXML private Button staffBtn;
    
    public ELMSController() {
        //
    }
    
    @FXML public void initialize() {
        switch (getELMS().getSelectedAccount().getPermissionLevel()) {
            case 0:
                adminBtn.setDisable(false);
                staffBtn.setDisable(false);
                break;
            case 1:
                adminBtn.setVisible(false);
                staffBtn.setDisable(false);
                break;
            default:
                adminBtn.setVisible(false);
                staffBtn.setVisible(false);
                break;
        }
        
    }

    public final ELMS getELMS() { return model; }
    
    @FXML public void handleLibBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/LibraryMenu.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleAccBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/ViewAccount.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleAdminBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/AdminMenu.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleStaffBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/StaffMenu.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
}
