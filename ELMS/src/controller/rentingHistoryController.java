package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.ELMS;

public class rentingHistoryController extends Controller<ELMS> {    
    public rentingHistoryController() {
      @FXML private TableView<Book> rentingHistoryTable;
       @FXML private Button handeExitBtn;
       @FXML private Button handleReturnBtn;
        //
    }
     public final ELMS getELMS() { return model; }
    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/Account.fxml", this.stage.getTitle(), this.stage);
    }
}
