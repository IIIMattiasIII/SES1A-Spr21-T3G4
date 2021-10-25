package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.ELMS;

public class StaffMenuController extends Controller<ELMS> { 
    public StaffMenuController() {
        //
    }

    public final ELMS getELMS() { return model; }
    
    @FXML public void handleReqBtn(ActionEvent e) throws IOException {
        Stage s = new Stage();
        s.getIcons().add(new Image("icon.png"));
        ViewLoader.showStage(getELMS(), "/view/StaffBookReq.fxml", this.stage.getTitle(), s);
    }
    
    @FXML public void handleExitBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/Login.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMSMenu.fxml", this.stage.getTitle(), this.stage);
    }
}
