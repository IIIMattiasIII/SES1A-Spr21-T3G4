package controller;

import javafx.stage.Stage;
import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import model.ELMS;
import model.Account;
import model.Book;
import model.Search;


/**
 *
 * @author ALI TAHMID KARIM
 */
public class SearchByGenre extends Controller<ELMS>  {
@FXML private TextField bookGenre;
@FXML private Button search_Button; 
 public SearchByGenre() throws IOException {
        //
    }
@FXML private void initialize(){ availableList();}


@FXML public String getName(){
    return bookGenre.getText();
    
}   
public final ELMS getELMS() { return model; }

@FXML public void setList(ActionEvent e)throws IOException{
    
    List.setItems(search.byCategory(getName()));
}
@FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }

@FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
     ViewLoader.showStage(getELMS(), "/view/LibraryMenu.fxml",stage.getTitle(), stage);
    }
}
