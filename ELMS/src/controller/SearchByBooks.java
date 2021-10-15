/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;
import javafx.application.Platform;
import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
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
@FXML private Button borrow_button;
@FXML private Button search_Button; 
@FXML private TableView<Book> List;
@FXML private TableColumn<Book,String> name;
@FXML private TableColumn<Book,String> author;
@FXML private TableColumn<Book,String> genre;
@FXML private Text text;
 
  public SearchByBooks() throws IOException {
        //
    }
  
 @FXML private void initialize(){
     List.setItems(getELMS().getBooks());
     name.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
     author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
     genre.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
 }


@FXML public String getName(){
    return BookName.getText();
    
}   
public final ELMS getELMS() { return model; }

@FXML public void set(ActionEvent e) throws IOException{
    
    List.setItems(getELMS().getSearch().byName(getName()));
    name.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
    author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
    genre.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
    
}

@FXML public void setList(ActionEvent e)throws IOException{
    
    List.setItems(getELMS().getSearch().byName(getSelectedBook()));
    name.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
    author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
    genre.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
}

@FXML private void resetList(ActionEvent e){initialize();}

@FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }

private String getSelectedBook(){
    
 return(String) List.getSelectionModel().getSelectedItem().titleProperty().get();

}

@FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
    
     ViewLoader.showStage(getELMS(), "/view/LibraryMenu.fxml",stage.getTitle(), stage);
    }

@FXML public void handleBorrow(ActionEvent event){
    
    Date date = new Date();
    
    ObservableList <Book> books = FXCollections.observableArrayList();
    
    books = getELMS().getSearch().byName(getSelectedBook());
    
    for(Book book : books){
       
       getELMS().getSelectedAccount().borrow(new Pair<>(date,book));
     text.setText(book.getTitle() + " borrowed"); 
    }  
    }
    
}
