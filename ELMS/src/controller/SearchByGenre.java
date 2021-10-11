package controller;

import javafx.stage.Stage;
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

@FXML private Button search_Button; 

@FXML private ListView List;

@FXML private Text text;

 public SearchByGenre() throws IOException {
        //
    }
@FXML private void initialize(){ 
    
    System.out.println(getELMS().getSearch().allGenres());
    
    List.setItems(getELMS().getSearch().allGenres());}


@FXML public String getName(){
    
    return bookGenre.getText();  
} 


@FXML private String getItem(){return(String) List.getSelectionModel().getSelectedItem();}


public final ELMS getELMS() { return model; }



@FXML private void setList(ActionEvent e)throws IOException{
    
    List.setItems(getELMS().getSearch().byCategory(getItem()));
}
@FXML private void resetlist(ActionEvent e) throws IOException{   List.setItems(getELMS().getSearch().allGenres()); }

//@FXML public void handleBorrow3(ActionEvent event){//borrowings.borrowBook(search.byName(getSelectedBook()),getAccount())
    
  //  Book title = search.byName(getSelectedBook());
  
    //ObservableList<Book> forBorrow = FXCollections.observableArrayList();
    
   // Account user =getELMS().getSelectedAccount();
    
   // forBorrow = getELMS().getSearch().byName(getItem());
    
    //for(Book book : forBorrow){
        
    //getELMS().getBorrowings().borrowBook(book, user);
    
    //text.setText("You have successfully borrowed the book "+book.getTitle());
    
   // }
//}

@FXML public void handleBorrow3(ActionEvent event){
    
    Date date = new Date();
    
    ObservableList <Book> books = FXCollections.observableArrayList();
    
    books = getELMS().getSearch().byName(getItem());
    
    for(Book book : books){
       
       getELMS().getSelectedAccount().borrow(new Pair<>(date,book));
     text.setText(book.getTitle() + " borrowed"); 
    }  
    }

@FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }



@FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
    
    ViewLoader.showStage(getELMS(), "/view/LibraryMenu.fxml",stage.getTitle(), stage);
     
  }



}
