package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import model.ELMS;

public class ELMSController extends Controller<ELMS> {    
    public ELMSController() {
        //
    }

    public final ELMS getELMS() { return model; }
    
    @FXML public void handleLibBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/LibraryMenu.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleAccBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/ViewAccount.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
}
