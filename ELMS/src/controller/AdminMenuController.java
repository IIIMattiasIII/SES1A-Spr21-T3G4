package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.ELMS;

public class AdminMenuController extends Controller<ELMS> { 
    @FXML private Button maintBtn;
    
    @FXML public void initialize() {
        updateMaintStyle();
    }
    
    public final ELMS getELMS() { return model; }
    
    @FXML public void handleAddBtn(ActionEvent e) throws IOException {
        Stage s = new Stage();
        s.getIcons().add(new Image("icon.png"));
        ViewLoader.showStage(getELMS(), "/view/AddBook.fxml", this.stage.getTitle(), s);
    }
    
    @FXML public void handleModBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/ModifyLibrary.fxml", this.stage.getTitle(), this.stage);
    }

    @FXML public void handleReqBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/ViewRequests.fxml", this.stage.getTitle(), this.stage);
    }

    @FXML public void handleMaintBtn(ActionEvent e) throws IOException {
        getELMS().maintenance = !getELMS().maintenance;
        updateMaintStyle();   
    }
    
    private void updateMaintStyle() {
        if (getELMS().maintenance) {
            maintBtn.setText("Disable Maintenance");
            maintBtn.getStyleClass().add("menuButtonToggled");
        } else {
            maintBtn.setText("Enable Maintenance");
            maintBtn.getStyleClass().remove("menuButtonToggled");
        }
    }
    
    @FXML public void handleExitBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/Login.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMSMenu.fxml", this.stage.getTitle(), this.stage);
    }
}
