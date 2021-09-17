/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Search;


/**
 *
 * @author ALI TAHMID KARIM
 */
public class SearchByBooks {
@FXML private TextField BookName;
    
Search search = new Search();

@FXML public String getName(){
    return BookName.getText();
    
}   
public final Search getSearch(){return search;}
}
