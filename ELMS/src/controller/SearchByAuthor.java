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
public class SearchByAuthor extends Controller<ELMS>  {
    
@FXML private TextField BookAuthor;

@FXML private TableView<Book>  List;

@FXML private TableColumn<Book,String>name;

@FXML private Button search_Button; 



@FXML private Text text;

String author = "";

ObservableList<String> authors = FXCollections.observableArrayList();

  public SearchByAuthor() throws IOException {
    
        //
    }
  @FXML private void initialize(){ 
   List.setItems(getELMS().getBooks());  
   name.setCellValueFactory(cellData -> cellData.getValue().authorProperty());                           
  }
  

  @FXML private String getSelectedItem(){
      
      
      return(String) List.getSelectionModel().getSelectedItem().authorProperty().get();
  
  }


@FXML public String getName(){
    
    return BookAuthor.getText();
    
} 

public final ELMS getELMS() { return model; }

public String getauthor(){return author;}

@FXML public void setList(ActionEvent e)throws IOException{
   List.setItems(getELMS().getSearch().byauthor(getSelectedItem()));  
   name.setCellValueFactory(cellData -> cellData.getValue().titleProperty()); 
    //ViewLoader.showStage(getELMS(), "/view/AuthorBooks.fxml","practise",new Stage());
    //List.setItems(getELMS().getSearch().byAuthor(getSelectedItem()));
}

@FXML public void resetlist(){initialize();}

@FXML public void handleBorrow2(ActionEvent event){
    
    Date date = new Date();
    
    ObservableList <Book> books = FXCollections.observableArrayList();
    
    books = getELMS().getSearch().byName(getSelectedItem());
    
    for(Book book : books){
       
       getELMS().getSelectedAccount().borrow(new Pair<>(date,book));
     text.setText(book.getTitle() + " borrowed"); 
    }  
    }

//@FXML public void handleBorrow2(ActionEvent event){//borrowings.borrowBook(search.byName(getSelectedBook()),getAccount())
    
   // Book title = getELMS().getSearch().byName(getSelectedItem());
  
    //ObservableList<Book> forBorrow = FXCollections.observableArrayList();
    
    //Account user =getELMS().getSelectedAccount();
    
    //forBorrow = getELMS().getSearch().byName(getSelectedItem());
    
    //for(Book book : forBorrow){
        
    //getELMS().getBorrowings().borrowBook(book, user);
    
   // text.setText("You have successfully borrowed the book "+book.getTitle());
    
   // }
//}

@FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }


@FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
    
    ViewLoader.showStage(getELMS(), "/view/LibraryMenu.fxml", this.stage.getTitle(), this.stage);
    
    }

}

