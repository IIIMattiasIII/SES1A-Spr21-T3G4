package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.awt.print.Book;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ELMS;

public class assignedBooksController extends Controller<ELMS> {    
    public assignedBooksController() {
        @FXML private TableView<Book> assignedBooksTable;
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
