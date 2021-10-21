package controller;
import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.ELMS;
import model.Account;



public class LoginController extends Controller<ELMS> {
    @FXML
    private TextField idTf;
    @FXML
    private PasswordField passTf;
    @FXML
    private Button loginBtn;
    @FXML
    private Button regBtn;
    @FXML
    private Label msgTxt;

    @FXML
    public void initialize() {
        // Bind button to textfields. Requires contents to be re-enabled
        loginBtn.disableProperty().bind(Bindings.isEmpty(idTf.textProperty()));
        loginBtn.disableProperty().bind(Bindings.isEmpty(passTf.textProperty()));
        // Change listener to force the contents of the ID text field to be only numbers
        idTf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                idTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        msgTxt.setVisible(false);
    }

    public final ELMS getELMS() {
        return model;
    }

    private final int getID() {
        return Integer.parseInt(idTf.getText());
    }

    private final int getPass() {
        return passTf.getText().hashCode();
    }

    void displayMsg(String s) {
        msgTxt.setVisible(true);
        msgTxt.setText(s);
    }

    void login() throws IOException {
        msgTxt.setVisible(false);
        Account selected = null;
        for (Account a : getELMS().getAccounts()) {
            if (a != null && a.getID() == this.getID()) { // Compare IDs - search with entered
                if (a.getPassHash() == this.getPass()) { // Compare password hashes
                    selected = a; 
                    getELMS().setSelectedAccount(selected);
                    break;
                }
            }
        }
        if (selected != null) { // if the details were correct and an account was selected, change the view to the homepage
            ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
        } else {
            displayMsg("Invalid ID or password. Please try again.");
        }
    }

    @FXML
    public void handleLoginBtn(ActionEvent e) throws IOException {
        login();
    }

    @FXML
    public void handleRegBtn(ActionEvent e) throws IOException {
        Stage s = new Stage();
        s.getIcons().add(new Image("icon.png"));
        ViewLoader.showStage(getELMS(), "/view/Register.fxml", this.stage.getTitle(), s);
    }

    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
}
