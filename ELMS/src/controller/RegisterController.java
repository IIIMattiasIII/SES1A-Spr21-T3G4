package controller;

import au.edu.uts.ap.javafx.*;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Account;
import model.ELMS;

public class RegisterController extends Controller<ELMS> {

    @FXML
    private ToggleGroup roleTog;
    @FXML
    private TextField idTf;
    @FXML
    private TextField nameFTf;
    @FXML
    private TextField nameSTf;
    @FXML
    private PasswordField passTf;
    @FXML
    private PasswordField passRepTf;
    @FXML
    private Button regBtn;
    @FXML 
    private Label msgTxt;

    @FXML
    public void initialize() {
        regBtn.disableProperty().bind(Bindings.isEmpty(idTf.textProperty()));
        regBtn.disableProperty().bind(Bindings.isEmpty(nameFTf.textProperty()));
        regBtn.disableProperty().bind(Bindings.isEmpty(nameSTf.textProperty()));
        regBtn.disableProperty().bind(Bindings.isEmpty(passTf.textProperty()));
        regBtn.disableProperty().bind(Bindings.isEmpty(passRepTf.textProperty()));
        
        idTf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                idTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        nameFTf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("a-zA-Z")) {
                nameFTf.setText(newValue.replaceAll("[^a-zA-Z]", ""));
            }
        });
        nameSTf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("a-zA-Z")) {
                nameSTf.setText(newValue.replaceAll("[^a-zA-Z]", ""));
            }
        });
        
        idTf.setOnKeyPressed(event -> regEnterCheck(event));
        nameFTf.setOnKeyPressed(event -> regEnterCheck(event));
        nameSTf.setOnKeyPressed(event -> regEnterCheck(event));
        passTf.setOnKeyPressed(event -> regEnterCheck(event));
        passRepTf.setOnKeyPressed(event -> regEnterCheck(event));
        
        msgTxt.setVisible(false);
    }

    public final ELMS getELMS() {
        return model;
    }

    private final int getID() {
        return Integer.parseInt(idTf.getText().trim());
    }

    private final String getNameF() {
        return nameFTf.getText().trim();
    }
        
    private final String getNameS() {
        return nameSTf.getText().trim();
    }

    private final String getPass() {
        return passTf.getText();
    }
    
    private final String getPassRep() {
        return passRepTf.getText();
    }
    
    void displayMsg(String s) {
        msgTxt.setVisible(true);
        msgTxt.setText(s);
    }

    void register() {
        msgTxt.setVisible(false);
        int role;
        RadioButton selRad = (RadioButton) roleTog.getSelectedToggle();
        if (selRad.getText().equals("Staff")) {
            role = 1;
        } else {
            role = 2;
        }
        if (getPass().equals(getPassRep())) {
            boolean idMatch = false;
            for (Account a : getELMS().getAccounts()) {
                if (this.getID() == a.getID()) { 
                    idMatch = true;
                    break;
                }
            }
            if (idMatch) {
                displayMsg("An account has already been created with this ID.");
            } else {
                getELMS().addAccount(this.getID(), this.getNameF(), this.getNameS(), this.getPass(), role);
                displayMsg("Account has been created. You can now close this window.");
                // TBD: Add and call method for sending account creation email.
            }
        } else {
            displayMsg("Passwords do not match.");
        }
    }

    void regEnterCheck(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            register();
        }
    }

    @FXML
    public void handleRegBtn(ActionEvent e) {
        register();
    }

    @FXML
    public void handleExitBtn(ActionEvent e) {
        this.stage.close();
    }
}
