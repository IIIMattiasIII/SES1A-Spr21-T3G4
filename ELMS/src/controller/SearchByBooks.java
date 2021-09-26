/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import model.ELMS;


/**
 *
 * @author ALI TAHMID KARIM
 */
public class SearchByBooks extends Controller<ELMS>  {
@FXML private TextField BookName;
@FXML private ListView  List;
@FXML private Button search_Button; 
  public SearchByBooks() throws IOException {
        //
    }
 
Search search = new Search();

@FXML public String getName(){
    return BookName.getText();
    
}   
public final ELMS getELMS() { return model; }

@FXML public void setList(ActionEvent e)throws IOException{
    
    List.setItems(search.byTitle(getName()));
}
@FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
