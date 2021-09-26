package controller;

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
public class SearchByAuthor extends Controller<ELMS>  {
@FXML private TextField BookAuthor;
@FXML private ListView  List;
@FXML private Button search_Button; 
  public SearchByAuthor() throws IOException {
        //
    }
 
Search search = new Search();

@FXML public String getName(){
    return BookAuthor.getText();
    
}   


@FXML public void setList(ActionEvent e)throws IOException{
    
    List.setItems(search.byAuthor(getName()));
}
}

