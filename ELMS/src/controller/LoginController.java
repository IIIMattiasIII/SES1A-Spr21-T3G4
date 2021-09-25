package controller;
import au.edu.uts.ap.javafx.*;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import model.ELMS;
import model.Account;
import model.*;



public class LoginController extends Controller<ELMS> {    

    public LoginController() throws IOException {
        //
    }

    public final ELMS getELMS() { return model; }
    
    @FXML public void handleLoginBtn(ActionEvent e) throws IOException {
        // TBD: Add login logic
        Account selected = null;
        if (true) {
            for (Account a : getELMS().getAccounts()) {
                if (a.getID() == 0) { 
                    selected = a;
                    getELMS().setSelectedAccount(selected);
                }
            }
        }
        if (selected != null) {
            ViewLoader.showStage(getELMS(), "/view/ELMS.fxml", this.stage.getTitle(), this.stage);
        }
    }
    
    @FXML public void handleExitBtn(ActionEvent e) { Platform.exit(); }
    

}
