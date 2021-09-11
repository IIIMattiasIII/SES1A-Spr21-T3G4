package controller;

import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
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
        loginBtn.disableProperty().bind(Bindings.isEmpty(idTf.textProperty()));
        loginBtn.disableProperty().bind(Bindings.isEmpty(passTf.textProperty()));
        idTf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                idTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        passTf.setOnKeyPressed(event -> loginEnterCheck(event));
        idTf.setOnKeyPressed(event -> loginEnterCheck(event));
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
            if (a != null && a.getID() == this.getID()) {
                if (a.getPassHash() == this.getPass()) {
                    selected = a;
                    getELMS().setSelectedAccount(selected);
                    break;
                }
            }
        }
        if (selected != null) {
            ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
        } else {
            displayMsg("Invalid ID or password. Please try again.");
        }
    }

    void loginEnterCheck(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                login();
            } catch (IOException ex) {
                displayMsg("An error has occured. Please contact your IT administrator. Error: " + ex);
            }
        }
    }

    @FXML
    public void handleLoginBtn(ActionEvent e) throws IOException {
        login();
    }
    
    @FXML
    public void handleRegBtn(ActionEvent e) throws IOException {
        ViewLoader.showStage(getELMS(), "/view/Register.fxml", this.stage.getTitle(), new Stage());
    }

    @FXML
    public void handleExitBtn(ActionEvent e) {
        Platform.exit();
    }
}
