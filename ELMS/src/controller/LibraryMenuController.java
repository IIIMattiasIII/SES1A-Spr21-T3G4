package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.*;

public class LibraryMenuController extends Controller<ELMS> {    
    @FXML private ToggleGroup paramTog;
    @FXML private Toggle paramTitle;
    @FXML private ListView<String> paramLv;
    @FXML private TableView<Book> mainTv;
    @FXML private TableColumn<Book, String> idCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> yearCol;
    @FXML private TableColumn<Book, String> genreCol;
    @FXML private TableColumn<Book, String> stockCol;
    private ObservableList<String> authors = FXCollections.observableArrayList();
    private ObservableList<String> genres = FXCollections.observableArrayList();
    private ObservableList<String> years = FXCollections.observableArrayList();
    private ObservableList<String> listPop = FXCollections.observableArrayList();
    private ObservableList<Book> tablePop = FXCollections.observableArrayList();
    @FXML private TextField searchTf;
    @FXML private Button borrowBtn;
    @FXML private Button presBtn;
    @FXML private Label msgTxt;
    
    @FXML public void initialize() {
        setupLists();
        borrowBtn.disableProperty().bind(Bindings.isEmpty(mainTv.getSelectionModel().getSelectedItems()));
        presBtn.disableProperty().bind(Bindings.isEmpty(mainTv.getSelectionModel().getSelectedItems()));
        if (getELMS().getSelectedAccount().getPermissionLevel() > 1) {
            presBtn.setVisible(false);
        }
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        yearCol.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asString());
        genreCol.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        stockCol.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asString());
        paramLv.getSelectionModel().selectedItemProperty().addListener(
            (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (newValue != null) {
                    updateTable();
                }
            }
        );
        paramTog.getToggles().forEach(t -> {
            t.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    if (newValue) {
                        searchTf.clear();
                        updateList();
                    }
                }
            );
        });
        resetTable();
    }
    
    private void setupLists() {
        for (Book b : getELMS().getAvailableBooks()) {
            if (!authors.contains(b.getAuthor()))
                authors.add(b.getAuthor());
            if (!genres.contains(b.getGenre()))
                genres.add(b.getGenre());
            if (!years.contains(b.getYear()+""))
                years.add(b.getYear()+"");
        }
    }

    public final ELMS getELMS() { return model; }
    
    private final String getSelParam() { 
        return paramLv.getSelectionModel().getSelectedItem();
    }
    
    private final Book getSelBook() { 
        return mainTv.getSelectionModel().getSelectedItem();
    }
    
    private final RadioButton getSelRad() {
        return (RadioButton) paramTog.getSelectedToggle();
    }
    
    private final String getSearch() {
        return searchTf.getText().trim();
    }
    
    private void displayMsg(String s) {
        msgTxt.setVisible(true);
        msgTxt.setText(s);
    }
    
    private void setTableItems(ObservableList<Book> o) {
        mainTv.setItems(o);
        int size = mainTv.getItems().size() > 7 ? 858 : 841;
        mainTv.setPrefWidth(size);
    }
   
    public void handleSearchBtn(ActionEvent e) {
        if (getSelRad().getText().equals("Title")) {
            paramLv.setItems(null);
            paramLv.setPlaceholder(new Label("Title results visible in table."));
            updateTable(getSearch());
        } else {
            paramLv.setPlaceholder(new Label("No results found."));
            updateList(getSearch());
        }
    }
    
    private void updateList(String s) {
        listPop.clear();
        switch (getSelRad().getText()) {
            case ("Author"):
                for (String a : authors) {
                    if (a.toLowerCase().contains(s.toLowerCase())) {
                        listPop.add(a);
                    }
                }
                break;
            case ("Year"):
                for (String y : years) {
                    if (y.toLowerCase().contains(s.toLowerCase())) {
                        listPop.add(y);
                    }
                }
                break;
            case ("Genre"):
                for (String g : genres) {
                    if (g.toLowerCase().contains(s.toLowerCase())) {
                        listPop.add(g);
                    }
                }
                break;
            default:
                resetTable();
                return;
        }
        paramLv.setItems(listPop);
    }
    
    private void updateList() {
        switch (getSelRad().getText()) {
            case ("Author"):
                paramLv.setItems(authors);
                break;
            case ("Year"):
                paramLv.setItems(years);
                break;
            case ("Genre"):
                paramLv.setItems(genres);
                break;
            default:
                paramLv.setItems(null);
                paramLv.setPlaceholder(new Label("Title results visible in table."));
                resetTable();
                break;
        }
    }
    
    private void updateTable() {
        tablePop.clear();
        switch (getSelRad().getText()) {
            case ("Author"):
                for (Book b : getELMS().getAvailableBooks()) {
                    if (b.hasAuthor(getSelParam())) {
                        tablePop.add(b);
                    }
                }
                break;
            case ("Year"):
                for (Book b : getELMS().getAvailableBooks()) {
                    if (b.hasYear(Integer.parseInt(getSelParam()))) {
                        tablePop.add(b);
                    }
                }
                break;
            case ("Genre"):
                for (Book b : getELMS().getAvailableBooks()) {
                    if (b.hasGenre(getSelParam())) {
                        tablePop.add(b);
                    }
                }
                break;
            default:
                resetTable();
                return;
        }
        setTableItems(tablePop);
    }
    
    private void updateTable(String s) {
        if (!getSelRad().getText().contains("Title")) {
            updateTable();
        } else {
            tablePop.clear();
            for (Book b : getELMS().getAvailableBooks()) {
                if (b.getTitle().toLowerCase().contains(s.toLowerCase())) {
                    tablePop.add(b);
                }
            }
            setTableItems(tablePop);
        }
    }
    
    public void handleResetBtn(ActionEvent e) {
        resetTable();
        searchTf.clear();
        paramTog.selectToggle(paramTitle);
        paramLv.setPlaceholder(new Label("Select a paramter to see\nall options in a category\nor search with a keyword."));
    }
    
    private void resetTable() {
        tablePop.clear();
        tablePop.addAll(getELMS().getAvailableBooks());
        setTableItems(tablePop);
    }
    
    @FXML public void handleBorrowBtn(ActionEvent e) {
        if (getELMS().getSelectedAccount().isFined()) {
            displayMsg("You currenlty have outstanding fines. Borrowing is not permitted.");
        } else if (getSelBook().getStock() < 1) {
            displayMsg("There are no available copies of the selected book.");
        } else if (getELMS().getSelectedAccount().hasBorrowed(getSelBook())) {
            displayMsg("You have already borrowed this book.");
        } else {
            getELMS().borrowBook(getSelBook());
            displayMsg("You have borrowed " + getSelBook().getTitle() + ".");
        }
    }
    
    @FXML public void handlePresBtn(ActionEvent e) throws IOException {
        if (getSelBook() != null) {
            getELMS().setSelectedBook(getSelBook());
            Stage s = new Stage();
            s.getIcons().add(new Image("icon.png"));
            ViewLoader.showStage(getELMS(), "/view/PrescribeBook.fxml", this.stage.getTitle(), s);
        } else {
            displayMsg("Invalid selection.");
        }
    }
    
    @FXML public void handleExitBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/Login.fxml", this.stage.getTitle(), this.stage);
    }
    
    @FXML public void handleReturnBtn(ActionEvent e) throws IOException { 
        ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
    }
}
