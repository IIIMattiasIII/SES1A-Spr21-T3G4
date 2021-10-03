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


/**
 *
 * @author ALI TAHMID KARIM
 */
public class SearchByBooks extends Controller<ELMS>  {
@FXML private TextField BookName;
@FXML private Button borrow_button;
@FXML private Button search_Button; 
@FXML private Text text;
 
  public SearchByBooks() throws IOException {
        //
    }
  
 @FXML private void initialize(){ availableList();}


@FXML public String getName(){
    return BookName.getText();
    
}   
public final ELMS getELMS() { return model; }

@FXML public void setList(ActionEvent e)throws IOException{
    
    List.setItems(search.byTitle(getName()));
}

@FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }

private String getSelectedBook(){
 return(String) List.getSelectionModel().getSelectedItem();
 
}

@FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
     ViewLoader.showStage(getELMS(), "/view/LibraryMenu.fxml",stage.getTitle(), stage);
    }
@FXML public void handleBorrow(ActionEvent event){//borrowings.borrowBook(search.byName(getSelectedBook()),getAccount())
  //  Book title = search.byName(getSelectedBook());
    ObservableList<Book> forBorrow = FXCollections.observableArrayList();
    Account user =getELMS().getSelectedAccount();
    forBorrow = search.byName(getSelectedBook());
    for(Book book : forBorrow){
    borrowings.borrowBook(book, user);
    text.setText("You have successfully borrowed the book "+book.getTitle());
    
    }
    //for(Account print: account){
   // System.out.println(print.getName());
    //}
   
    System.out.print(user.getName());
}
}