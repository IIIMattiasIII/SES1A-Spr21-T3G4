package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ALI TAHMID KARIM
 */

import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.beans.property.*;
import java.io.*;
import java.text.*;



import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SearchController {
    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    
    @FXML private void handleButton1(ActionEvent event) throws Exception{
    Stage stage = new Stage();
    
   FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchByBook.fxml"));
   Parent root = loader.load();
   stage.setScene(new Scene(root));
   stage.sizeToScene();
   stage.show();
    
    
    }
     @FXML private void handleButton2(ActionEvent event) throws Exception{
     
    Stage stage = new Stage();
    System.out.println("Button clicked");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchByAuthor.fxml"));
    Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.sizeToScene();
    stage.show();
    
    
    
    }
      @FXML private void handleButton3(ActionEvent event)throws Exception{
    Stage stage = new Stage();
    System.out.println("Button clicked");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchByGenre.fxml"));
    Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.sizeToScene();
    stage.show();
    
    
    
    
    
    }
}
