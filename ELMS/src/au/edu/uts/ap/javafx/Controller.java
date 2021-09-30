package au.edu.uts.ap.javafx;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.*;
import model.*;

public abstract class Controller<M> {
    protected M model;
    protected Stage stage;
    @FXML public ListView  List;
    public Search search = new Search();
    public Borrowings borrowings= new Borrowings();
  @FXML public void availableList(){
    
    List.setItems(search.getAvailablebooks());
}
  @FXML public void resetList(ActionEvent event)throws IOException{
    
    List.setItems(search.getAvailablebooks());
}
}
