package controller;
import javafx.application.Platform;
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
public class SearchByYear extends Controller<ELMS>  {
@FXML private TextField bookYear;
@FXML private Button borrow_button;
@FXML private Button search_Button; 
@FXML private TableView<Book> List;
@FXML private TableColumn<Book,String> name;
@FXML private TableColumn<Book,String> author;
@FXML private TableColumn<Book,String> genre;
@FXML private TableColumn<Book,String> year;
@FXML private TableColumn<Book,String> availabilities;
@FXML private Text text;
 
  public SearchByYear() throws IOException {
      
        //
    }
  
 @FXML private void initialize(){
     List.setItems(getELMS().getBooks());
     name.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
     author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
     genre.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
     year.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asString());
     availabilities.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asString());
 }


@FXML public String getName(){
    return bookYear.getText();
    
}   
public final ELMS getELMS() { return model; }

@FXML public void set(ActionEvent e) throws IOException{
    int bookyear = Integer.parseInt(getName());
    System.out.print(bookyear);
    List.setItems(getELMS().getSearch().byYear(bookyear));
    name.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
    author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
    genre.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
    year.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asString());
    availabilities.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asString());
    
}

@FXML public void setList(ActionEvent e)throws IOException{
    
    List.setItems(getELMS().getSearch().byName(getSelectedBook()));
    name.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
    author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
    genre.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
    year.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asString());
    availabilities.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asString());
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
       
       getELMS().getSelectedAccount().borrow(new Pair<>(book,date));
       book.setStock(book.stockProperty().get()-1);
     text.setText(book.getTitle() + " borrowed"); 
    }  
    }
    
}
    

