package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.stage.Stage;
import model.ELMS;

public class StaffMenuController extends Controller<ELMS> { 
    public StaffMenuController() {
        //
    }

    public final ELMS getELMS() { return model; }
    
    @FXML public void handleSetBtn(ActionEvent e) throws IOException {
//        ViewLoader.showStage(getELMS(), "/view/PrescribeBook.fxml", this.stage.getTitle(), new Stage());
    }
    
    @FXML public void handleReqBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/StaffBookReq.fxml", this.stage.getTitle(), new Stage());
    }
    
    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
