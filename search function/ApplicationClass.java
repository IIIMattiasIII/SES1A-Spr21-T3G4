/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ALI TAHMID KARIM
 */
import  javafx.application.*;
import  javafx.fxml.*;
import  javafx.stage.*;
import  javafx.scene.*;

public class ApplicationClass extends Application{
    public static void main(String[] args){
        launch(args);
    }
    
   @Override
   public void start(Stage stage) throws Exception{
   FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchFunction.fxml"));
   Parent root = loader.load();
   stage.setScene(new Scene(root));
   stage.sizeToScene();
   stage.show();
   
   }
    
    
}
