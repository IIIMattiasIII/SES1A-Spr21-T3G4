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
public class SearchByAuthor extends Controller<ELMS>  {
    
@FXML private TextField BookAuthor;

@FXML private ListView  List;

@FXML private Button search_Button; 

@FXML private ListView List2;

@FXML private Text text;

String author = "";

ObservableList<String> authors = FXCollections.observableArrayList();

  public SearchByAuthor() throws IOException {
        //
    }
  @FXML private void initialize(){ List.setItems(getELMS().getSearch().allAuthors());
                                  
  }
  
 
  
  @FXML private String getSelectedItem(){
      
      
      return(String) List.getSelectionModel().getSelectedItem();
  
  }


@FXML public String getName(){
    
    return BookAuthor.getText();
    
} 

public final ELMS getELMS() { return model; }

public String getauthor(){return author;}

@FXML public void setList(ActionEvent e)throws IOException{
    
    //ViewLoader.showStage(getELMS(), "/view/AuthorBooks.fxml","practise",new Stage());
    List.setItems(getELMS().getSearch().byAuthor(getSelectedItem()));
}

@FXML public void resetlist(){initialize();}

@FXML public void handleBorrow2(ActionEvent event){//borrowings.borrowBook(search.byName(getSelectedBook()),getAccount())
    
  //  Book title = search.byName(getSelectedBook());
  
    ObservableList<Book> forBorrow = FXCollections.observableArrayList();
    
    Account user =getELMS().getSelectedAccount();
    
    forBorrow = getELMS().getSearch().byName(getSelectedItem());
    
    for(Book book : forBorrow){
        
    getELMS().getBorrowings().borrowBook(book, user);
    
    text.setText("You have successfully borrowed the book "+book.getTitle());
    
    }
}

@FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }


@FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
    
    ViewLoader.showStage(getELMS(), "/view/LibraryMenu.fxml", this.stage.getTitle(), this.stage);
    
    }

}

