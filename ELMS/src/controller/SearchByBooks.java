/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import model.ELMS;
import model.Account;
import model.Search;


/**
 *
 * @author ALI TAHMID KARIM
 */
public class SearchByBooks extends Controller<ELMS>  {
  public SearchByBooks() throws IOException {
        //
    }
@FXML private TextField BookName;
@FXML private ListView  List;
@FXML private Button search_Button; 
Search search = new Search();

@FXML public String getName(){
    return BookName.getText();
    
}   


@FXML public void setList(ActionEvent e)throws IOException{
    List.setItems(search.byTitle(getName()));
}
}
