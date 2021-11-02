package controller;

import au.edu.uts.ap.javafx.*;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import static javafx.scene.text.TextAlignment.CENTER;
import model.ELMS;

public class PreviewBookController extends Controller<ELMS> {
    @FXML private Label titleLbl;
    @FXML private VBox vbox;
    @FXML private ImageView iv;
    
    @FXML public void initialize() {
        titleLbl.setText(getELMS().getSelectedBook().getTitle());
        String title = getELMS().getSelectedBook().getTitle().toLowerCase();
        File file = new File("src/data/previews/"+title+".jpg");
        if (file.exists()) {
            Image image = new Image(file.toURI().toString());
            iv.setImage(image);
            iv.setPreserveRatio(true);
            iv.setFitHeight(500);
        } else {
            vbox.getChildren().clear();
            Label lbl = new Label("No preview is currently available.\nPlease try again later.");
            lbl.getStyleClass().add("h2");
            lbl.setTextAlignment(CENTER);
            vbox.getChildren().add(lbl);
        }
    }

    public final ELMS getELMS() { return model; }
    
    @FXML
    public void handleCloseBtn(ActionEvent e) {
        this.stage.close();
    }
}
