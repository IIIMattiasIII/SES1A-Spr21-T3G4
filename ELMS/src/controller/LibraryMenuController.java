package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.ELMS;

public class LibraryMenuController extends Controller<ELMS> {    
    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    public LibraryMenuController() {
        //
    }

    public final ELMS getELMS() { return model; }
    
    @FXML public void handleLibBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/LibraryMenu.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleAccBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/Account.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML private void handleButton1(ActionEvent event) throws Exception{
    Stage stage = new Stage();
    
   FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SearchByBook.fxml"));
   Parent root = loader.load();
   stage.setScene(new Scene(root));
   stage.sizeToScene();
   stage.show();
    
    
    }
     @FXML private void handleButton2(ActionEvent event) throws Exception{
     
    Stage stage = new Stage();
    System.out.println("Button clicked");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SearchByAuthor.fxml"));
    Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.sizeToScene();
    stage.show();
    
    
    
    }
      @FXML private void handleButton3(ActionEvent event)throws Exception{
    Stage stage = new Stage();
    System.out.println("Button clicked");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SearchByGenre.fxml"));
    Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.sizeToScene();
    stage.show();
    
    
    
    
    
    }
}
