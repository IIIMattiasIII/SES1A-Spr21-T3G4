/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ALI TAHMID KARIM
 */

package controller;
import javafx.application.Platform;
import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ELMS;
import model.Account;
import model.Book;
import model.Search;
import model.ELMS;



public class AuthorBooksController extends Controller<ELMS>  {
@FXML private TextField BookAuthor;
@FXML private ListView  List;
@FXML private Button search_Button; 
@FXML private ListView List2;
@FXML private Text text;
String author = "";
  public AuthorBooksController() throws IOException {
        //
    }

  
  @FXML private void initialize(){ //List.setItems(search.allAuthors());
                                 //List2.setItems(search.byAuthor(getSelectedItem()));
                                 //setList2();
                                 
  }
 // @FXML private void setList2(){List2.setItems(search.byAuthor());}
  
  
  @FXML public String getSelectedItem(){return(String) List.getSelectionModel().getSelectedItem();}
  
 @FXML public void addAuthor(String add){this.author = add;}

@FXML public String getName(){
    return BookAuthor.getText();
    
} 

public final ELMS getELMS() { return model; }

@FXML public void setList(ActionEvent e)throws IOException{
   
    ViewLoader.showStage(getELMS(), "/view/SearchByBook.fxml","practise",new Stage());
    //List.setItems(search.byAuthor(getSelectedItem()));
}
@FXML public void handleBorrow2(ActionEvent event){//borrowings.borrowBook(search.byName(getSelectedBook()),getAccount())
  //  Book title = search.byName(getSelectedBook());
    ObservableList<Book> forBorrow = FXCollections.observableArrayList();
    Account user =getELMS().getSelectedAccount();
    forBorrow = search.byauthor(getSelectedItem());
    for(Book book : forBorrow){
    borrowings.borrowBook(book, user);
    text.setText("You have successfully borrowed the book "+book.getTitle());
    
    }
}
@FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }

@FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
    ViewLoader.showStage(getELMS(), "/view/AuthorBooks.fxml", this.stage.getTitle(), this.stage);
    }
}
