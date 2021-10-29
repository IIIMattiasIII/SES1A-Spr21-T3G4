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
        // Bind button to textfields. Requires contents to be re-enabled
        regBtn.disableProperty().bind(Bindings.isEmpty(idTf.textProperty()));
        regBtn.disableProperty().bind(Bindings.isEmpty(nameFTf.textProperty()));
        regBtn.disableProperty().bind(Bindings.isEmpty(nameSTf.textProperty()));
        regBtn.disableProperty().bind(Bindings.isEmpty(passTf.textProperty()));
        regBtn.disableProperty().bind(Bindings.isEmpty(passRepTf.textProperty()));
        // Change listeners to force the contents of certain text fields
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
        RadioButton selRad = (RadioButton) roleTog.getSelectedToggle(); // Getting and setting the permission level based on the toggle
        int role = selRad.getText().equals("Staff") ? 1 : 2;
        if (idTf.getText().length() > 1 && getNameF().length() > 1 && getNameS().length() > 1 && getPass().length() > 4) {
            if (getPass().equals(getPassRep())) { // Check the passwords match
                boolean idMatch = false;
                for (Account a : getELMS().getAccounts()) { // Check for existing account
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
                    regBtn.setVisible(false); // Like with addBooks, setDisable(true) causes errors. So this is the temporary workaround
                    // TBD: Add and call method for sending account creation email.
                }
            } else {
                displayMsg("Passwords do not match.");
            }
        } else {
            displayMsg("Invalid entry. A field is incomplete.");
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
