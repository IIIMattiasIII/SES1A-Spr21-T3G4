package controller;

import javafx.stage.Stage;
import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import java.util.ArrayList;
import model.Date;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Pair;
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
@FXML private Text text;
@FXML private TableView<Book> List;
@FXML private TableColumn<Book,String> name;
@FXML private TableColumn<Book,String> author;
@FXML private TableColumn<Book,String> genre;

 public SearchByGenre() throws IOException {
        //
    }
@FXML private void initialize(){ 
     List.setItems(getELMS().getBooks());
     name.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
     author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
     genre.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
    }


@FXML public String getName(){
    
    return bookGenre.getText();  
} 


@FXML private String getItem(){return(String) List.getSelectionModel().getSelectedItem().genreProperty().get();}


public final ELMS getELMS() { return model; }

@FXML private void set(ActionEvent e){ 

List.setItems(getELMS().getSearch().byCategory(getName()));


}

@FXML private void setList(ActionEvent e)throws IOException{
    
     List.setItems(getELMS().getSearch().byCategory(getItem()));
     name.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
     author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
     genre.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
}
@FXML private void resetlist(ActionEvent e) throws IOException{  initialize(); }


@FXML public void handleBorrow3(ActionEvent event){
    
    Date date = new Date();
    
    ObservableList <Book> books = FXCollections.observableArrayList();
    
    books = getELMS().getSearch().byCategory(getItem());
    
    for(Book book : books){
       
       getELMS().getSelectedAccount().borrow(new Pair<>(book,date));
     text.setText(book.getTitle() + " borrowed"); 
    }  
    }

@FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }



@FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
    
    ViewLoader.showStage(getELMS(), "/view/LibraryMenu.fxml",stage.getTitle(), stage);
     
  }



}
