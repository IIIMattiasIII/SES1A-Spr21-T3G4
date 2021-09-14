package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.stage.Stage;
import model.ELMS;

public class AdminController extends Controller<ELMS> { 
    public AdminController() {
        //
    }

    public final ELMS getELMS() { return model; }
    
    @FXML public void handleAddBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/AddBook.fxml", this.stage.getTitle(), new Stage());
    }
    
    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
