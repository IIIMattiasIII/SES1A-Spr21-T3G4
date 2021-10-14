/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;
import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Account;
import model.Book;
import model.ELMS;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


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
@FXML private Text text;
 
  public SearchByBooks() throws IOException {
        //
    }
  
 @FXML private void initialize(){ //List.setItems(getELMS().getSearch().getAvailablebooks());
   List.setItems(getELMS().getBooks());  
   name.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
           }


@FXML public String getName(){
    return BookName.getText();
    
}   
public final ELMS getELMS() { return model; }

@FXML public void setList(ActionEvent e)throws IOException{
     List.setItems(getELMS().getSearch().byName(getSelectedBook()));  
   name.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
    //List.setItems(getELMS().getSearch().byTitle(getName()));
}

@FXML private void resetList(ActionEvent e){
    List.setItems(getELMS().getBooks());  
   name.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
}

@FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }

private String getSelectedBook(){
 return(String) List.getSelectionModel().getSelectedItem().titleProperty().get();
 //return(String) List.getSelectionModel().getSelectedItem();
 
}

@FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
     ViewLoader.showStage(getELMS(), "/view/LibraryMenu.fxml",stage.getTitle(), stage);
    }
@FXML public void handleBorrow(ActionEvent event){//borrowings.borrowBook(search.byName(getSelectedBook()),getAccount())
  //  Book title = search.byName(getSelectedBook());
    ObservableList<Book> forBorrow = FXCollections.observableArrayList();
    Account user =getELMS().getSelectedAccount();
    forBorrow = getELMS().getSearch().byName(getSelectedBook());
    for(Book book : forBorrow){
    getELMS().getBorrowings().borrowBook(book, user);
    text.setText("You have successfully borrowed the book "+book.getTitle());
    
    }
    //for(Account print: account){
   // System.out.println(print.getName());
    //}
   
    System.out.print(user.getName());
}
}
